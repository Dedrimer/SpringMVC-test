package com.zjtec.travel.service;

import com.zjtec.travel.domain.User;

public interface UserService {
    /**
     * 校验用户名和密码，密码正确返回true
     * @param username
     * @param password
     * @return
     */
    boolean validUserPwd(String username,String password);

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 保存用户信息
     * @param ue
     * @return
     */
    boolean save(User ue);

    /**
     * 存在 username 或 email 则返回 true
     * @param username
     * @param email
     * @return
     */
    boolean existUserNameOrEmail(String username,String email);

    /**
     * 激活用户，成功返回true
     * @param username
     * @param code
     * @return
     */
    boolean activeUser(String username,String code);

    /**
     * 按用户名查找已激活用户信息
     * @param username
     * @return
     */
    User findActiveUserByUserName(String username);
}
