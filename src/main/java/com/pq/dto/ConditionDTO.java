package com.pq.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: ConditionDTO
 * @author: panqing
 * @projectName git-blog
 * @description: TODO
 * @data: 2020/8/41:15 下午
 */
@Data
public class ConditionDTO implements Serializable {
    private static final long serialVersionUID = -5099378457111419832L;
    /**
     * 数据库字段名
     */
    private String column;
    /**
     * 字段值
     */
    private String value;
    /**
     * 连接类型，如llike,equals,gt,ge,lt,le
     */
    private String type;
}
