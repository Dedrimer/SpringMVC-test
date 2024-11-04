package com.zjtec.travel.service.impl;

import com.zjtec.travel.dao.UserDao;
import com.zjtec.travel.domain.User;
import com.zjtec.travel.service.UserService;
import com.zjtec.travel.util.MsgDigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public boolean validUserPwd(String username, String password) {
        logger.info("Validating user password for username: {}", username);
        User user = userDao.findByUserName(username);
        if (user == null) {
            //logger.warn("User not found: {}", username);
            return false;
        }
        if (!"Y".equals(user.getStatus())) {
            //logger.warn("User not active: {}", username);
            return false;
        }
        //logger.info("User found: {}", user);
        String salt = user.getSalt();
        //logger.debug("User salt: {}", salt);
        String encryptedPassword = MsgDigestUtils.encodeSHA256(password, salt);
        //logger.debug("Encrypted password: {}", encryptedPassword);
        boolean isPasswordValid = encryptedPassword.equals(user.getPassword());
        //logger.info("Password valid: {}", isPasswordValid);
        return isPasswordValid;
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public boolean save(User ue) {
        return userDao.save(ue)>0;
    }

    @Override
    public boolean existUserNameOrEmail(String username,String email){
        return userDao.existUserNameOrEmail(username,email);
    }

    @Override
    public boolean activeUser(String username, String code) {
        return userDao.activeUser(username,code);
    }

    @Override
    public User findActiveUserByUserName(String username) {
        return userDao.findActiveUserByUserName(username);
    }
}
