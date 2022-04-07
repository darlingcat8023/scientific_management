-- 用户基本信息表
create table user_account
(
    id             int auto_increment comment '主键id' primary key,
    created_at     timestamp     default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at     timestamp     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted_at     int           default 0                 not null comment '删除标志',
    user_name      varchar(50)   default ''                not null comment '用户名',
    user_mobile    varchar(20)   default ''                not null comment '手机号',
    user_password  varchar(50)   default ''                not null comment '密码',
    user_identity  varchar(50)   default ''                not null comment '身份证号',
    user_type      tinyint(1)    default 0                 not null comment '用户类型 0 学生 1教师',
    user_extend    varchar(5000) default ''                not null comment '用户附加信息',
) engine = InnoDB, comment = '用户信息表', default charset = utf8;

-- 管理员用户表
create table admin_user_account
(
    id             int auto_increment comment '主键id' primary key,
    created_at     timestamp     default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at     timestamp     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted_at     int           default 0                 not null comment '删除标志',
    account        varchar(50)   default 0                 not null comment '用户账户',
    user_name      varchar(50)   default ''                not null comment '用户名',
    user_password  varchar(50)   default ''                not null comment '密码'
) engine = InnoDB, comment = '用户信息表', default charset = utf8;

-- 初始化两个管理员
insert into admin_user_account (account, user_name, user_password) values ('root1', '一级审核员', 'root1'),('root2', '二级审核员', 'root2');

-- Token信息
create table token_info (
    id                  int auto_increment comment '主键id'     primary key,
    created_at          timestamp    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at          timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted_at          int          default 0                 not null comment '删除标志',
    token               varchar(255) default ''                not null comment 'token'
) engine = InnoDB, comment = 'Token信息表', default charset = utf8;

-- 项目信息
create table project_info
(
    id                 int auto_increment comment '主键id' primary key,
    created_at         timestamp    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at         timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted_at         int          default 0                 not null comment '删除标志',
    project_name       varchar(100) default ''                not null comment '项目名',
    project_type       varchar(50)  default ''                not null comment '学科分类',
    research_direction varchar(100) default ''                not null comment '研究方向',
    project_fund       int          default 0                 not null comment '项目经费',
    project_remark     varchar(255) default ''                not null comment '项目描述',
    project_status     int          default 1                 not null comment '项目状态，0 初始化 1 待修改 2 已拒绝 3 已通过',
    project_rejects    int          default 0                 not null comment '被打回的次数'
) comment '项目信息表' charset = utf8;

-- 项目附件
create table project_file
(
    id                 int auto_increment comment '主键id' primary key,
    created_at         timestamp    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at         timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted_at         int          default 0                 not null comment '删除标志',
    project_id         int          default 0                 not null comment '项目id',
    file_name          varchar(255) default ''                not null comment '文件名',
    file_content       longtext     default ''                not null comment '文件的base64'
) comment '项目文件' charset = utf-8;

-- 项目参与者表
create table project_participant_info (
    id                  int auto_increment comment '主键id'     primary key,
    created_at          timestamp    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at          timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted_at          int          default 0                 not null comment '删除标志',
    project_id          int          default 0                 not null comment '项目id',
    user_id             int          default 0                 not null comment '用户id',
    user_role           varchar(50)  default ''                not null comment '用户角色'
) comment '项目参与者表' charset = utf8;

-- 项目审核信息表
create table project_audit_info (
    id                  int auto_increment comment '主键id'     primary key,
    created_at          timestamp    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at          timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted_at          int          default 0                 not null comment '删除标志',
    project_id          int          default 0                 not null comment '项目id',
    audit_user_id       int          default 0                 not null comment '审核用户id',
    audit_step          tinyint(1)   default 0                 not null comment '审核环节 1 初审 2 终审',
    audit_result        tinyint(1)   default 0                 not null comment '审核结果 0 未通过 1 已通过',
    audit_active        tinyint(1)   default 0                 not null comment '状态激活 0 未激活 2 已激活',
    audit_comment       varchar(500) default ''                not null comment '审核备注'
) comment '项目审核信息表' charset = utf8;

-- 项目分类信息表
create table project_type_info (
    id                  int auto_increment comment '主键id'     primary key,
    created_at          timestamp    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at          timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted_at          int          default 0                 not null comment '删除标志',
    type_name           varchar(100) default ''                not null comment '分类名',
    committed_projects  int          default 0                 not null comment '已创建的项目数',
    passed_projects     int          default 0                 not null comment '通过的项目数',
    rejected_projects   int          default 0                 not null comment '拒绝的项目数'
) comment '项目分类信息表' charset = utf8;

insert into project_type_info (type_name) values
(''),(''),(''),(''),(''),(''),(''),('');