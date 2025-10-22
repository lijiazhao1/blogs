package com.example.blogs.mapper;

import com.example.blogs.model.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author ljz
 * @date 2025/10/21
 */
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    User selectByUsername(@Param("username") String username);

    /**
     * 根据用户ID查询用户
     * @param id 用户ID
     * @return 用户信息
     */
    User selectById(@Param("id") Long id);

    /**
     * 新增用户（注册）
     * @param user 用户信息
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 更新用户信息（不含密码）
     * @param user 用户信息
     * @return 影响行数
     */
    int update(User user);

    /**
     * 更新密码
     * @param id 用户ID
     * @param newPassword 新密码（加密后）
     * @return 影响行数
     */
    int updatePassword(@Param("id") Long id, @Param("newPassword") String newPassword);
}
