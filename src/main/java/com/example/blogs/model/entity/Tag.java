package com.example.blogs.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ljz
 * @date 2025/10/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID（自增）
     */
    private Long id;

    /**
     * 标签名称（唯一，如"Java"、"SpringBoot"）
     */
    private String name;

    /**
     * 创建时间（标签添加时生成）
     */
    private LocalDateTime createTime;

    /**
     * 更新时间（标签修改时刷新）
     */
    private LocalDateTime updateTime;
}
