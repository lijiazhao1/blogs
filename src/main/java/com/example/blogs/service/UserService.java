package com.example.blogs.service;

import com.example.blogs.model.entity.User;

/**
 * @author ljz
 * @date 2025/10/21
 */
public interface UserService {
    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码（明文）
     * @param nickname 昵称
     * @param email 邮箱
     * @return 注册成功的用户ID
     */
    Long register(String username, String password, String nickname, String email);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码（明文）
     * @return 登录成功的用户信息（不含密码）
     */
    User login(String username, String password);

    /**
     * 根据ID获取用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    User getUserById(Long id);

    /**
     * 更新用户信息
     * @param user 用户信息（含ID）
     */
    void updateUser(User user);

    /**
     * 重置密码
     * @param id 用户ID
     * @param oldPassword 旧密码（明文）
     * @param newPassword 新密码（明文）
     */
    void resetPassword(Long id, String oldPassword, String newPassword);
}
