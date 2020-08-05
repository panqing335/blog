package com.pq.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pq.dto.ConditionDTO;
import com.pq.entity.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//* eq:  =
//* notEq:  !=
//* like:  LIKE  （前置已经加了 '%'）
//* between:  between
//* and:  改变接下来的连接方式为 AND练级（默认）
//* andOnce:  改变接下来一个的连接方式为 AND
//* or:  改变接下来的连接方式为 OR
//* orOnce 改变接下来一个的连接方式为 OR
//* division：括号  ，不支持括号嵌套括号
//* in:  IN
//* notIn:  NOT IN
//* notLike:   NOT LIKE
//* isNull:  IS NULL
//* isNotNull: IS NOT NULL
//* sql: 简易自定义带sql代码片段

/**
 * @Title: SearchUtil
 * @author: panqing
 * @projectName git-blog
 * @description: TODO
 * @data: 2020/8/41:09 下午
 */
@Data
public class SearchUtil {
    public static <T> QueryWrapper<T> parseWhereSql(List<ConditionDTO> conditionList, T Entity) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>(Entity);
        if (!conditionList.isEmpty()) {
            conditionList.forEach(conditionDTO -> {
                switch (conditionDTO.getType()) {
                    case "eq" -> queryWrapper.eq(conditionDTO.getColumn(), conditionDTO.getValue());
                    case "ne" -> queryWrapper.ne(conditionDTO.getColumn(), conditionDTO.getValue());
                    case "like" -> queryWrapper.like(conditionDTO.getColumn(), conditionDTO.getValue());
                    case "leftlike" -> queryWrapper.likeLeft(conditionDTO.getColumn(), conditionDTO.getValue());
                    case "rightlike" -> queryWrapper.likeRight(conditionDTO.getColumn(), conditionDTO.getValue());
                    case "notlike" -> queryWrapper.notLike(conditionDTO.getColumn(), conditionDTO.getValue());
                    case "gt" -> queryWrapper.gt(conditionDTO.getColumn(), conditionDTO.getValue());
                    case "lt" -> queryWrapper.lt(conditionDTO.getColumn(), conditionDTO.getValue());
                    case "ge" -> queryWrapper.ge(conditionDTO.getColumn(), conditionDTO.getValue());
                    case "le" -> queryWrapper.le(conditionDTO.getColumn(), conditionDTO.getValue());
                    case "sort" -> queryWrapper.orderBy(true, conditionDTO.getValue().equals("ase"), conditionDTO.getColumn());
                }
            });
        }
        return queryWrapper;
    }
}
