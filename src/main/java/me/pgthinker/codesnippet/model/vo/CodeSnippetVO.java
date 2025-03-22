package me.pgthinker.codesnippet.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Project: me.pgthinker.codesnippet.model
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/21 12:53
 * @Description:
 */
@Data
public class CodeSnippetVO {
    /**
     * id
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 代码描述
     */
    private String description;

    /**
     * 具体代码
     */
    private String content;

    /**
     * 语言
     */
    private String language;

    /**
     * copy次数
     */
    private Long copyCnt;

    /**
     * 类别
     */
    private String category;

    /**
     * 更新时间
     */
    private Date updateTime;
}
