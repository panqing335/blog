package com.pq;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pq.dto.ConditionDTO;
import com.pq.dto.RegisterDTO;
import com.pq.dto.UserListDTO;
import com.pq.entity.User;
import com.pq.service.RoleService;
import com.pq.service.UserService;
import com.pq.util.CommonsUtils;
import com.pq.util.SearchUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class LoginApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    private UserService userService;


    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());

        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());

    }
    @Test
    void doLogin() {
       String username = "系统测试人员";
       String password = "123456";
       String aa = CommonsUtils.encryptPassword(password);
        System.out.println(aa);
       String token = userService.doLogin(username, password);
        System.out.println(token);
    }

}
