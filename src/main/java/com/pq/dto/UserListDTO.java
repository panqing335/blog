package com.pq.dto;

import com.pq.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Title: UserListDTO
 * @author: panqing
 * @projectName git-blog
 * @description: TODO
 * @data: 2020/8/44:59 下午
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class UserListDTO extends User {

    private String roleName;

}
