package com.assassin.modules.svnmanage.service;

import com.assassin.constants.UserVerifyState;
import com.assassin.modules.svnmanage.dao.UserDao;
import com.assassin.modules.svnmanage.entity.UserEntity;
import com.assassin.utils.DateUtils;
import com.assassin.utils.MyBASE64Util;
import com.assassin.utils.SendMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.*;

/**
 * Created by Administrator on 2017/5/18.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public List<UserEntity> GetAllUser(){
        return userDao.selectUser(new UserEntity());
    }

    /**
     * 保存用户信息
     * @param userEntity
     */
    public UserEntity saveUser(UserEntity userEntity){
        List<UserEntity> userEntitieList  = userDao.selectUser(userEntity);
        Long bsm = userEntity.getBsm()==null?0L:userEntity.getBsm();
        if(userEntitieList.size()>0){
            if(bsm<=0){
                throw new RuntimeException("当前用户名已存在，请重新录入！");
            }
            else{
                for (UserEntity userEntityTemp : userEntitieList){
                    if(!userEntityTemp.getBsm().equals(bsm)){
                        throw new RuntimeException("当前用户名已存在，请重新录入！");
                    }
                }
            }
        }
        userEntity.setMm(MyBASE64Util.encodeData(userEntity.getMm()));
        userEntity.setGxsj(new Date());
        if(bsm>0){
            userDao.update(userEntity);
        }
        else{
            userDao.insert(userEntity);
        }
        return userEntity;
    }

    /**
     * 用户名密码校验
     * @param userEntity
     * @return
     */
    public Map<String,UserEntity> VerifyUser(UserEntity userEntity){
        List<UserEntity> userEntityList=userDao.selectUser(userEntity);
        Map<String,UserEntity> mapValue = new HashMap<>();
        if(userEntityList!=null && userEntityList.size()>0){
            UserEntity userEntityTemp = userEntityList.get(0);
            if(!MyBASE64Util.encodeData(userEntity.getMm()).equals(userEntityTemp.getMm())){
                mapValue.put(UserVerifyState.PASSWORDNOTCORRECT,userEntityTemp);
            }
            mapValue.put(UserVerifyState.SUCCESS,userEntityTemp);
        }
        else{
            mapValue.put(UserVerifyState.USERNOTFOUND,new UserEntity());
        }
        return mapValue;
    }

    /**
     * 修改密码
     * @param userEntity
     */
    public UserEntity  ModifyPassword(UserEntity userEntity){
        UserEntity userEntityResult  = userDao.selectUserByUserName(userEntity.getYhm());
        if(userEntityResult==null){
            throw new RuntimeException("当前用户名不存在，请检查！");
        }
        userEntity.setMm(MyBASE64Util.encodeData(userEntity.getMm()));
        if(!userEntityResult.getMm().equals(MyBASE64Util.encodeData(userEntity.getYmm()))){
            throw new RuntimeException("原密码不正确，请重新录入！");
        }
        userDao.updatePassword(userEntity);
        return userEntity;
    }

    public UserEntity postMail(String yhm)  throws Exception{

        UserEntity userEntity  = userDao.selectUserByUserName(yhm);
        if(userEntity==null)
            throw new RuntimeException("当前用户信息不存在，请联系系统管理员！");
        StringBuilder sbText =new StringBuilder();
        sbText.append(String.format("%s:你好\r\n",userEntity.getXm()));
        sbText.append(String.format("   你申请的密码信息如下：\n"));
        sbText.append(String.format("     其中用户名：%sxxxx：%s\n",userEntity.getYhm(),MyBASE64Util.decodeData(userEntity.getMm())));
        sbText.append(String.format("   为了你的安全性考虑，请尽快登陆系统修改你的xxxx！\n"));
        sbText.append(String.format("                           SVN管理团队"));



        if(true){
            //SendMailUtil.sendCommonMail("292032561@qq.com","svn账户",sbText.toString());
            Map mapValue=new HashMap();
            mapValue.put("xm",userEntity.getXm());
            mapValue.put("yhm",userEntity.getYhm());
            mapValue.put("mm",MyBASE64Util.decodeData(userEntity.getMm()));
            mapValue.put("date", DateUtils.getDate("yyyy-MM-dd"));
            SendMailUtil.sendFtlMail("292032561@qq.com","svn账户","mailtemplate.ftl",mapValue);
        }
        else{
            Properties props = new Properties();
            // 开启debug调试
            props.setProperty("mail.debug", "true");
            // 发送服务器需要身份验证
            props.setProperty("mail.smtp.auth", "true");
            // 设置邮件服务器主机名
            props.setProperty("mail.host", "smtp.163.com");
            // 发送邮件协议名称
            props.setProperty("mail.transport.protocol", "smtp");

            // 设置环境信息
            Session session = Session.getInstance(props);

            // 创建邮件对象
            Message msg = new MimeMessage(session);
            msg.setSubject("关于规范编码格式的处理");
            // 设置邮件内容

            msg.setText("java代码编码格式规范");
            // 设置发件人
            msg.setFrom(new InternetAddress("nimingqi163@163.com"));

            Transport transport = session.getTransport();
            // 连接邮件服务器
            transport.connect("nimingqi163@163.com", "9644112641");
            // 发送邮件
            transport.sendMessage(msg, new Address[] {new InternetAddress("292032561@qq.com")});
            // 关闭连接
            transport.close();
        }
        return userEntity;

    }
}
