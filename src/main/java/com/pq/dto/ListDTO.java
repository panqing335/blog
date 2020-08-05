package com.pq.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;

/**
 * @Title: ListDTO
 * @author: panqing
 * @projectName git-blog
 * @description: TODO
 * @data: 2020/8/412:48 下午
 */
@Data
public class ListDTO implements Serializable {

    private Long pageNo;

    private Long pageSize;

    @Null
    private List<ConditionDTO> condition;
}
