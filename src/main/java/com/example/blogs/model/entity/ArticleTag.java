package com.example.blogs.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ljz
 * @date 2025/10/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTag implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID（自增）
     */
    private Long id;

    /**
     * 文章ID（关联article表的id）
     */
    private Long articleId;

    /**
     * 标签ID（关联tag表的id）
     */
    private Long tagId;
}
