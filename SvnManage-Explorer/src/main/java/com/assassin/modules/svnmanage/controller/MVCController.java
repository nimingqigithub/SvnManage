package com.assassin.modules.svnmanage.controller;

import com.assassin.constants.PasswordVerifyState;
import com.assassin.constants.UserLoginState;
import com.assassin.constants.UserVerifyState;
import com.assassin.exception.ExceptionUtils;
import com.assassin.modules.svnmanage.dao.UserDao;
import com.assassin.modules.svnmanage.entity.UserEntity;
import com.assassin.modules.svnmanage.service.UserService;
import com.assassin.utils.DateUtils;
import com.assassin.utils.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/18.
 */
@Controller
public class MVCController {

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @RequestMapping(value="login",method = RequestMethod.GET)
    public ModelAndView login(HttpServletResponse response, HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("login/login");
        String type = request.getParameter("type");
        if(UserLoginState.LOGIN_LOGIN.equals(type)){
            request.getSession().removeAttribute("username");
            request.getSession().removeAttribute("staffname");
            request.getSession().removeAttribute("logintime");
            return modelAndView; //重新登陆
        }

        Object username =request.getSession().getAttribute("username");
        if(username!=null && !StringUtils.isNull(username.toString())){
            return new ModelAndView("login/index");
        }
        return modelAndView;
    }

    @RequestMapping(value="register",method = RequestMethod.GET)
    public ModelAndView register(HttpServletRequest request,HttpServletResponse response){
        ModelAndView modelAndView=new ModelAndView("login/register");
        return modelAndView;
    }

    /**
     * 找回密码
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value="password",method = RequestMethod.GET)
    public ModelAndView password(HttpServletResponse response,HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("login/findPassword");
        return modelAndView;
    }

    /**
     * 密码修改
     * @param userEntity
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value="password",method = RequestMethod.POST)
    public ModelAndView passwordPost(UserEntity userEntity,HttpServletResponse response,HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("login/findPassword");
        String type = request.getParameter("type");
        if(PasswordVerifyState.GETUSER.equals(type)){
            UserEntity userEntityResult = userDao.selectUserByUserName(userEntity.getYhm());
            if(userEntityResult==null){
                modelAndView.addObject("message","当前用户名("+userEntity.getYhm()+")不存在，请联系系统管理员！");
            }
            else{
                modelAndView.addObject("nexttype",PasswordVerifyState.FIRST_STEP);
                userEntityResult.setFsyhm(userEntityResult.getYhm());
                modelAndView.addObject("usermail",userEntityResult);
            }
        }
        else if(PasswordVerifyState.SECOND_STEP.equals(type)){
            modelAndView.addObject("nexttype",PasswordVerifyState.SECOND_STEP);
            userEntity.setYhm(userEntity.getFsyhm());
            modelAndView.addObject("usermail",userEntity);
        }
        return modelAndView;
    }

    /**
     * 用户注册
     * @param userEntity
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="login",method = RequestMethod.POST,produces = "application/json")
    public ModelAndView  loginVerify(UserEntity userEntity, HttpServletRequest request, HttpServletResponse response) throws Exception{

        String postType = request.getParameter("type");
        if(UserLoginState.LOGIN_REGISTER.equals(postType)){  //注册用户
            ModelAndView modelAndView;
            try{
                UserEntity userEntityResult  = userService.saveUser(userEntity);
                modelAndView=new ModelAndView("login/register");
                modelAndView.addObject("messagesuccess","注册成功！");
                return modelAndView;
            }
            catch(Exception er){
                String message = er.getMessage();
                modelAndView=new ModelAndView("login/register");
                modelAndView.addObject("message",message);
                modelAndView.addObject("register",userEntity);
                return modelAndView;
            }
        }
        else{  //登陆用户
            Map<String,UserEntity> mapValue = userService.VerifyUser(userEntity);
            String flag = mapValue.keySet().iterator().next();
            UserEntity userEntityResult = mapValue.get(flag);
            String message = "";
            switch (flag){
                case UserVerifyState.SUCCESS:
                    break;
                case UserVerifyState.PASSWORDNOTCORRECT:
                    message="密码不正确";
                    break;
                case UserVerifyState.USERNOTFOUND:
                    message="用户不存在";
                    break;
            }
            //response.sendRedirect("/Customer/jsp/Login/login.jsp");
            ModelAndView modelANdView;
            if(StringUtils.isNull(message)){
                HttpSession session=request.getSession();
                session.setAttribute("username",userEntityResult.getYhm());
                session.setAttribute("staffname", userEntityResult.getXm());
                session.setAttribute("logintime",DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
                session.setMaxInactiveInterval(300 * 60);
                Cookie namecookie = new Cookie("username",userEntity.getYhm());
                namecookie.setMaxAge(60*60*24*365);
                response.addCookie(namecookie);
                modelANdView=new ModelAndView("login/index");
                modelANdView.addObject("message",message);
                modelANdView.addObject("user",userEntity);
                return modelANdView;
            }
            else{
                modelANdView=new ModelAndView("login/login");
                modelANdView.addObject("message",message);
                modelANdView.addObject("user",userEntity);

                return modelANdView;
            }
        }
    }

    /**
     * 用户管理
     * @param allRequestParam
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value ="userManage",method = RequestMethod.GET,produces = "application/json")
    public ModelAndView  getUserList(@RequestParam Map<String,String> allRequestParam,
                                         HttpServletResponse response,HttpServletRequest request){
        ModelAndView  modelAndView = new ModelAndView("login/userManage");
        List<UserEntity> userEntityList  = userService.GetAllUser();
        modelAndView.addObject("userList",userEntityList);
        return modelAndView;
    }
}
