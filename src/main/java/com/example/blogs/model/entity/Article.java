package com.example.blogs.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ljz
 * @date 2025/10/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID（自增）
     */
    private Long id;

    /**
     * 文章标题（不可为空）
     */
    private String title;

    /**
     * 文章内容（HTML格式或Markdown，不可为空）
     */
    private String content;

    /**
     * 文章摘要（可选，用于列表页展示）
     */
    private String summary;

    /**
     * 封面图片URL（可选，列表页展示的封面图）
     */
    private String coverImg;

    /**
     * 作者ID（关联user表的id）
     */
    private Long userId;

    /**
     * 分类ID（关联category表的id）
     */
    private Long categoryId;

    /**
     * 文章状态：0-草稿，1-已发布（默认0）
     */
    private Integer status;

    /**
     * 浏览量（默认0，每访问一次+1）
     */
    private Integer viewCount;

    /**
     * 创建时间（文章首次保存时生成）
     */
    private LocalDateTime createTime;

    /**
     * 更新时间（文章修改时刷新）
     */
    private LocalDateTime updateTime;
}
