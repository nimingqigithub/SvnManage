package com.assassin.modules.svnmanage.dao;

import com.assassin.common.MyBatisDao;
import com.assassin.modules.svnmanage.entity.UserEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
@MyBatisDao
public interface UserDao {
    List<UserEntity> selectUser(UserEntity userEntity);

    void insert(UserEntity userEntity);

    void update(UserEntity userEntity);

    Long verifyUser(UserEntity userEntity);

    UserEntity selectUserByUserName(String userName);

    void updatePassword(UserEntity userEntity);
}
