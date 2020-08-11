<h1 align="center">个人博客-权限管理项目</h1>

## 简介
  Phper自学JAVA 准备撸个人博客，现已完成权限管理模块，暂只有后端，前端等vue3.0生态健全了再撸！

## 技术
* Java JDK14
* SpringBoot 2.3.1
* Mybatis-Plus
* Fastjson
* Shiro
* Jwt
* Mysql
* Druid
* Maven

##数据表：

sql文件打包自取

* tb_user: 用户表  
* tb_role: 角色表  
* tb_menu: 权限表  
* tb_user_role: 用户角色中间表  
* tb_role_menu: 角色权限中间表  

## 实现功能
### 前端：
未撸！
### 后端：
+ 用户注册，登录
+ 基于JwtToken的权限管理
+ 基于用户，角色，权限的后台管理api接口
+ 全局异常处理
+ 实现SearchUtil工具类（接收前端查询条件自动拼接sql）
+ 实现TreeUtil工具类（接收List<Entity>根据parentId建树）

## 运行
项目clone到本地，idea打开， 配置application.yml即可
