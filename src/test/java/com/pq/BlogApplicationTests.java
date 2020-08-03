package com.pq;

import com.alibaba.druid.pool.DruidDataSource;
import com.pq.entity.User;
import com.pq.service.RoleService;
import com.pq.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class BlogApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

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

}
