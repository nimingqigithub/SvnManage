package com.assassin.modules.svnmanage.controller;

import com.assassin.exception.ExceptionUtils;
import com.assassin.exception.RealEstateException;
import com.assassin.modules.svnmanage.dao.UserDao;
import com.assassin.modules.svnmanage.entity.UserEntity;
import com.assassin.modules.svnmanage.service.UserService;
import com.assassin.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/18.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 获取所有用户
     * @param allRequestParam
     * @param response
     * @return
     */
    @RequestMapping(value="user/getAllUser",method = RequestMethod.GET,produces = "application/json")
    public List<UserEntity> getAllUser(@RequestParam Map<String,String> allRequestParam, HttpServletResponse response){
        try{
            return userService.GetAllUser();
        }
        catch(Exception er){
            ExceptionUtils.LoadLog(er);
            throw new RealEstateException(ExceptionUtils.DealErrorMsg(er));
        }
    }

    /**
     * 保存用户
     * @param allRequestParam
     * @param userEntity
     * @param response
     * @return
     */
    @RequestMapping(value="user/saveUser",method = RequestMethod.POST,produces = "application/json")
    public UserEntity saveUser(@RequestParam Map<String,String> allRequestParam, @RequestBody UserEntity userEntity
                ,HttpServletResponse response){
        try{
            return userService.saveUser(userEntity);
        }
        catch(Exception er){
            ExceptionUtils.LoadLog(er);
            throw new RealEstateException(ExceptionUtils.DealErrorMsg(er));
        }
    }

    /**
     * 修改密码
     * @param userEntity
     * @param response
     */
    @RequestMapping(value="user/modifyPassword",method = RequestMethod.POST,produces = "application/json")
    public UserEntity modifyPassword(@RequestBody UserEntity userEntity,HttpServletResponse response){
        try{
            return userService.ModifyPassword(userEntity);
        }
        catch(Exception er){
            ExceptionUtils.LoadLog(er);
            throw new RealEstateException(ExceptionUtils.DealErrorMsg(er));
        }
    }

    @RequestMapping(value="user/postMail",method = RequestMethod.GET,produces = "application/json")
    public UserEntity postMail(@RequestParam  Map<String,String> allRequestParam,HttpServletRequest request,HttpServletResponse response){
        try{
            String yhm = CommonUtils.GetMapValue(allRequestParam,"yhm",true);
            return userService.postMail(yhm);
        }
        catch(Exception er){
            ExceptionUtils.LoadLog(er);
            throw new RealEstateException(ExceptionUtils.DealErrorMsg(er));
        }
    }
}
