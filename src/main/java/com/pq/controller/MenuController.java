package com.pq.controller;


import com.pq.common.Result;
import com.pq.entity.Menu;
import com.pq.service.MenuService;
import com.pq.util.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
@Controller
@RequestMapping("//menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 功能描述: <br>
     * 〈菜单树〉
     * @Param: []
     * @Return: com.pq.common.Result
     * @Author: panqing
     * @Date: 2020/8/11 3:21 下午
     */
    @GetMapping(path = "/tree")
    public Result menuTree() {
        List<Menu> list = this.menuService.list();
        return Result.success(TreeUtil.build(list, 0));
    }

    /**
     * 功能描述: <br>
     * 〈保存菜单〉
     * @Param: [menu, id]
     * @Return: com.pq.common.Result
     * @Author: panqing
     * @Date: 2020/8/11 3:30 下午
     */
    @PostMapping(value = "/save")
    public Result save(@RequestBody Menu menu, String id) {
        if (id.isEmpty()) {
            this.menuService.save(menu);
        } else {
            menu.setId(Long.valueOf(id));
            this.menuService.updateById(menu);
        }
        return Result.success(true);
    }

    /**
     * 功能描述: <br>
     * 〈删除菜单〉
     * @Param: [id]
     * @Return: com.pq.common.Result
     * @Author: panqing
     * @Date: 2020/8/11 3:31 下午
     */
    @PostMapping(value = "/delete")
    public Result delete(@RequestBody String id) {
        this.menuService.removeById(id);
        return Result.success(true);
    }
}

