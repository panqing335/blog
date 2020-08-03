package com.pq;

import com.alibaba.druid.pool.DruidDataSource;
import com.pq.dto.RegisterDTO;
import com.pq.entity.User;
import com.pq.service.RoleService;
import com.pq.service.UserService;
import com.pq.util.CommonsUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
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

    @Test
    void userRegister() {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername("abc");
        registerDTO.setPassword("");
        registerDTO.setEmail("");

//        User user = new User();
//        user.setUsername(registerDTO.getUsername());

        Boolean register = userService.register(registerDTO.getUsername(),
                registerDTO.getPassword(),
                registerDTO.getEmail(),
                registerDTO.getPhone(),
                registerDTO.getSex()
        );

        System.out.println(register);
    }
}
