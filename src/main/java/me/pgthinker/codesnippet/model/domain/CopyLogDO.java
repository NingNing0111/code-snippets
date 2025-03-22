package me.pgthinker.codesnippet.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName copy_log
 */
@TableName(value ="copy_log")
@Data
public class CopyLogDO implements Serializable {
    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户ip
     */
    @TableField(value = "user_ip")
    private String userIp;

    /**
     * 代码片段ID
     */
    @TableField(value = "code_id")
    private String codeId;

    /**
     * 请求agent
     */
    @TableField(value = "user_agent")
    private String userAgent;

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