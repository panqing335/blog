package com.pq;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class Main {
    public static void main(String[] args) {
        // 创建generator对象
        AutoGenerator autoGenerator = new AutoGenerator();
        // 连接数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/demo?serverTimeZone=UTC&useUnicode=true&characterEncoding=utf-8");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("panqing335");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");

        autoGenerator.setDataSource(dataSourceConfig);

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        globalConfig.setOpen(false);
        globalConfig.setAuthor("pq");
        globalConfig.setServiceName("%sService");

        autoGenerator.setGlobalConfig(globalConfig);

        // 包信息
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.pq");
        packageConfig.setModuleName("");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");

        autoGenerator.setPackageInfo(packageConfig);

        // 配置策略
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setTablePrefix("tb");

        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();
    }
}
