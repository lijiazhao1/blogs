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
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID（自增）
     */
    private Long id;

    /**
     * 分类名称（唯一，如"技术"、"生活"）
     */
    private String name;

    /**
     * 分类描述（可为空，说明分类用途）
     */
    private String description;

    /**
     * 创建时间（分类添加时生成）
     */
    private LocalDateTime createTime;

    /**
     * 更新时间（分类修改时刷新）
     */
    private LocalDateTime updateTime;
}
