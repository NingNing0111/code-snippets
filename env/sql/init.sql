CREATE TABLE code_snippet (
    id VARCHAR(64) NOT NULL PRIMARY KEY COMMENT 'id',
    title VARCHAR(64) NOT NULL COMMENT '标题',
    description TEXT COMMENT '代码描述',
    content TEXT NOT NULL COMMENT '具体代码',
    language VARCHAR(64) COMMENT '语言',
    category VARCHAR(64) NOT NULL DEFAULT '未分类' COMMENT '类别',
    create_time DATETIME NOT NULL DEFAULT  CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除'
);

CREATE TABLE copy_log (
    id BIGINT NOT NULL PRIMARY KEY COMMENT 'id',
    user_ip VARCHAR(64) COMMENT '用户ip',
    code_id VARCHAR(64) NOT NULL COMMENT '代码片段ID',
    user_agent TEXT COMMENT '请求agent',
    create_time DATETIME NOT NULL DEFAULT  CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除'
)