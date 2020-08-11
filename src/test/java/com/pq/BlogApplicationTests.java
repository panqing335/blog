package com.pq;

import com.alibaba.druid.pool.DruidDataSource;
import com.pq.dto.MenuCheckedDTO;
import com.pq.dto.RegisterDTO;
import com.pq.entity.Menu;
import com.pq.entity.TreeNode;
import com.pq.entity.User;
import com.pq.service.MenuService;
import com.pq.service.RoleMenuService;
import com.pq.service.RoleService;
import com.pq.service.UserService;
import com.pq.util.TreeUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
class BlogApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private MenuService menuService;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());

        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());

    }

    @Test
    void selectByUserId() {
        User user = userService.getById("184");
        System.out.println(user);
    }
    @Test
    void select() {
        List<User> users = userService.getBaseMapper().selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void selectJoin() {
        HashMap<String, Object> user = userService.selectMenuListByUserId("184");
        String roleName = String.valueOf(user.get("role_name"));
        System.out.println(roleName);
        String permsList = String.valueOf(user.get("perms_list"));
        List<String> list = Arrays.asList(permsList.split(","));
        list.forEach(System.out::println);
    }

    @Test
    void selectRoleName() {
        Map<String, Object> map = roleService.selectRoleNameByUserId("184");
        String roleName = String.valueOf(map.get("role_name"));
        System.out.println(roleName);
    }


    @Test
    void update() {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setEmail("4231");
        registerDTO.setNickname("4231");

        boolean update = userService.updateUser(registerDTO);
        System.out.println(update);
    }

    @Test
    void test() {
        MenuCheckedDTO checkedDTO = new MenuCheckedDTO();
        checkedDTO.setId(1L);
        checkedDTO.setType(2);
        MenuCheckedDTO checkedDTO1 = new MenuCheckedDTO();
        checkedDTO1.setId(2L);
        checkedDTO1.setType(1);
        MenuCheckedDTO checkedDTO2 = new MenuCheckedDTO();
        checkedDTO2.setId(309L);
        checkedDTO2.setType(1);

        List<MenuCheckedDTO> menuCheckedDTOList = new ArrayList<>();
        menuCheckedDTOList.add(checkedDTO);
        menuCheckedDTOList.add(checkedDTO1);
        menuCheckedDTOList.add(checkedDTO2);

        roleMenuService.checked(menuCheckedDTOList, "125");
    }

    @Test
    void test1() {
        List<Menu> menu = roleMenuService.selectMenuTreeByRoleId("125");
        System.out.println(menu);
    }
    @Test
    void test2() {
       List<Menu> menuList = menuService.list();
       menuList.stream()
               .filter(menu -> !menu.getId().equals(menu.getParentId()))
               .map(menu -> {
                   Menu node = new Menu();
                   node.setId(menu.getId());
                   node.setParentId(menu.getParentId());
                   node.setName(menu.getName());
                   return node;
               }).collect(Collectors.toList());
        TreeUtil.build(menuList, 0L).forEach(o->{
            System.out.println(o);
        });
    }

}
