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
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID（自增）
     */
    private Long id;

    /**
     * 评论内容（不可为空）
     */
    private String content;

    /**
     * 文章ID（关联article表的id，标识评论属于哪篇文章）
     */
    private Long articleId;

    /**
     * 评论用户ID（关联user表的id，标识谁评论的）
     */
    private Long userId;

    /**
     * 父评论ID（用于多级回复：null表示一级评论，非null表示回复某条评论）
     */
    private Long parentId;

    /**
     * 创建时间（评论提交时生成）
     */
    private LocalDateTime createTime;

    /**
     * 更新时间（评论修改时刷新，可选功能）
     */
    private LocalDateTime updateTime;
}
