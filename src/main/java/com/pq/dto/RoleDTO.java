package com.pq.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

/**
 * @Title: RoleDTO
 * @author: panqing
 * @projectName git-blog
 * @description: TODO
 * @data: 2020/8/1011:29 上午
 */
@Data
@Builder
public class RoleDTO {
    Long id;
    @NotNull
    String roleName;
    String remark;
    @Builder.Default
    Integer status = 1;
}
