-- auto-generated definition
create table tb_user
(
    id          bigint auto_increment comment '用户ID'
        primary key,
    username    varchar(50)   not null comment '用户名',
    nickname    varchar(20)   null,
    email       varchar(128)  null comment '邮箱',
    avatar      text          null comment '头像',
    phone       varchar(20)   null comment '联系电话',
    status      int(1)        not null comment '状态 0锁定 1有效',
    sex         int(1)        null comment '性别 0男 1女 2保密',
    salt        varchar(255)  null comment '盐',
    type        int default 1 not null comment '0:超级管理员，1：系统用户',
    password    varchar(128)  not null comment '密码',
    create_time datetime      not null comment '创建时间',
    update_time datetime      null comment '修改时间',
    deleted     int default 0 null
)
    comment '用户表';

-- auto-generated definition
create table tb_role
(
    id          bigint auto_increment comment '角色ID'
        primary key,
    role_name   varchar(100)     not null comment '角色名称',
    remark      varchar(100)     null comment '角色描述',
    status      int(1) default 1 null comment '是否可用,0:不可用，1：可用',
    create_time datetime         not null comment '创建时间',
    update_time datetime         null comment '修改时间',
    deleted     int    default 0 null
)
    comment '角色表';

-- auto-generated definition
create table tb_menu
(
    id          bigint auto_increment comment '菜单/按钮ID'
        primary key,
    parent_id   bigint        null comment '上级菜单ID',
    menu_name   varchar(50)   not null comment '菜单/按钮名称',
    url         varchar(50)   null comment '菜单URL',
    perms       text          null comment '权限标识',
    type        char(2)       not null comment '类型 0菜单 1按钮',
    order_num   bigint        null comment '排序',
    create_time datetime      not null comment '创建时间',
    update_time datetime      null comment '修改时间',
    deleted     int default 0 null
)
    comment '菜单表';

-- auto-generated definition
create table tb_user_role
(
    id          int(11) unsigned auto_increment
        primary key,
    user_id     bigint        not null comment '用户ID',
    role_id     bigint        not null comment '角色ID',
    create_time datetime      null,
    update_time datetime      null,
    deleted     int default 0 null
)
    comment '用户角色关联表';

-- auto-generated definition
create table tb_role_menu
(
    id          int(11) unsigned auto_increment
        primary key,
    role_id     bigint        not null comment '角色ID',
    menu_id     bigint        not null comment '菜单/按钮ID',
    create_time datetime      null,
    update_time datetime      null,
    deleted     int default 0 null
)
    comment '角色菜单关联表';

