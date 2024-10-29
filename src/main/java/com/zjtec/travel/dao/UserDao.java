package com.zjtec.travel.dao;

import com.zjtec.travel.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    /**
     * 按用户名查找用户信息
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * 按用户名查找已激活用户信息
     * @param username
     * @return
     */
    User findActiveUserByUserName(String username);

    /**
     * 保存用户信息
     * @param ue
     * @return
     */
    int save(User ue);

    /**
     * 是否存在用户名或Email
     * @param username
     * @param email
     * @return
     */
    boolean existUserNameOrEmail(@Param("username") String username, @Param("email") String email);

    /**
     * 激活用户
     * @param username
     * @param code
     * @return
     */
    boolean activeUser(@Param("username")String username,@Param("code")String code);
}
