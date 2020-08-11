package com.pq.dto;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

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
@Builder
public class ListDTO implements Serializable {

    @Builder.Default
    private Long pageNo = 1L;

    @Builder.Default
    private Long pageSize = 20L;

    @Null
    private List<ConditionDTO> condition;
}
