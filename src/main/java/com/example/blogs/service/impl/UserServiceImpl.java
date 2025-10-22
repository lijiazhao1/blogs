package com.example.blogs.service.impl;

import com.example.blogs.mapper.UserMapper;
import com.example.blogs.model.entity.User;
import com.example.blogs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ljz
 * @date 2025/10/21
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    // 密码加密器
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public Long register(String username, String password, String nickname, String email) {
        // 1. 校验用户名是否已存在
        User existingUser = userMapper.selectByUsername(username);
        if (existingUser != null) {
            throw new RuntimeException("用户名已存在");
        }

        // 2. 密码加密
        String encryptedPassword = passwordEncoder.encode(password);

        // 3. 构建用户对象并保存
        User user = User.builder()
                .username(username)
                .password(encryptedPassword)
                .nickname(nickname)
                .email(email)
                .status(1) // 默认为正常状态
                .build();

        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public User login(String username, String password) {
        // 1. 查询用户
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 2. 校验密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 3. 校验用户状态
        if (user.getStatus() == 0) {
            throw new RuntimeException("账号已禁用");
        }

        // 4. 脱敏（清除密码）
        user.setPassword(null);
        return user;
    }

    @Override
    public User getUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        user.setPassword(null); // 脱敏
        return user;
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        // 校验用户是否存在
        if (userMapper.selectById(user.getId()) == null) {
            throw new RuntimeException("用户不存在");
        }
        userMapper.update(user);
    }

    @Override
    @Transactional
    public void resetPassword(Long id, String oldPassword, String newPassword) {
        // 1. 查询用户
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        // 2. 校验旧密码
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("旧密码错误");
        }

        // 3. 加密新密码并更新
        String encryptedNewPassword = passwordEncoder.encode(newPassword);
        userMapper.updatePassword(id, encryptedNewPassword);
    }
}
