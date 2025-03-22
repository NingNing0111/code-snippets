package me.pgthinker.codesnippet.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName code_snippet
 */
@TableName(value ="code_snippet")
@Data
public class CodeSnippetDO implements Serializable {
    /**
     * id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 代码描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 具体代码
     */
    @TableField(value = "content")
    private String content;

    /**
     * 语言
     */
    @TableField(value = "language")
    private String language;

    /**
     * 类别
     */
    @TableField(value = "category")
    private String category;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableField(value = "deleted")
    private Boolean deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}