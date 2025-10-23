package com.example.blogs.controller;


import com.example.blogs.model.entity.User;
import com.example.blogs.service.UserService;
import com.example.blogs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ljz
 * @date 2025/10/21
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Map<String, Object> register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String nickname,
            @RequestParam String email) {
        Long userId = userService.register(username, password, nickname, email);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "注册成功");
        result.put("userId", userId);
        return result;
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<User> login(
            @RequestParam String username,
            @RequestParam String password) {
        User user = userService.login(username, password);
        /*Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "登录成功");
        result.put("user", user);*/
        return Result.success(user);
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/{id}")
    public Result getUserInfo(@PathVariable Long id) {
        User user = userService.getUserById(id);
        /*Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("user", user);*/
        return Result.success(user);
    }

    /**
     * 更新用户信息
     */
    @PutMapping
    public Result updateUser(@RequestBody User user) {
        userService.updateUser(user);
        /*Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "更新成功");*/
        return Result.success();
    }

    /**
     * 重置密码
     */
    @PutMapping("/password")
    public Result resetPassword(
            @RequestParam Long id,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {
        userService.resetPassword(id, oldPassword, newPassword);
        /*Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "密码重置成功");*/
        return Result.success();
    }
}
