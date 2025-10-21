package com.example.blogs.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ljz
 * @date 2025/10/20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    //用户id
    private Long id;
    //用户名
    private String username;
    //密码
    private String password;
    //昵称
    private String nickname;
    //头像URl
    private String avatar;
    //个人简介
    private String intro;
    //邮箱
    private String email;
    //状态，0禁用，1正常
    private Integer status;
    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
}
