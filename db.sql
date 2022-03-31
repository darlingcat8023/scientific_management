-- 用户基本信息表
create table user_account
(
    id             int auto_increment comment '主键id' primary key,
    created_at     timestamp     default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at     timestamp     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted_at     int           default 0                 not null comment '删除标志',
    account        varchar(50)   default ''                not null comment '账户',
    user_name      varchar(50)   default ''                not null comment '用户名',
    user_mobile    varchar(20)   default ''                not null comment '手机号',
    user_password  varchar(50)   default ''                not null comment '密码',
    user_school    varchar(50)   default ''                not null comment '所属院校',
    user_direction varvhar(100) default '' not null comment '研究方向',
    user_team      varchar(50) defalut '' not null comment '所属团队',
    user_extend    varchar(5000) default ''                not null comment '用户附加信息',
    registered     tinyint(1) default 0 not null comment '是否注册',
    is_admin       tinyint(1) default 0 not null comment '管理员账户   0 普通用户 1 管理员'
) comment '用户信息表' charset = utf8;

-- 用户登陆信息
create table user_login_info (
    id                  int auto_increment comment '主键id'     primary key,
    created_at          timestamp    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at          timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted_at          int          default 0                 not null comment '删除标志',
    user_id             int          default 0                 not null comment '用户id',
    user_token          varchar(255) default ''                not null comment '用户token',
    user_token_expires  timestamp    default CURRENT_TIMESTAMP not null comment '用户token过期时间',
    admin_token         varchar(255) default ''                not null comment '管理员token',
    admin_token_expires timestamp    default CURRENT_TIMESTAMP not null comment '管理员token过期时间'
) comment '用户登陆信息表' charset = utf8;

-- 项目信息
create table project_info
(
    id                 int auto_increment comment '主键id' primary key,
    created_at         timestamp    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at         timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted_at         int          default 0                 not null comment '删除标志',
    project_name       varchar(100) default ''                not null comment '项目名',
    project_type       varchar(50)  default ''                not null comment '学科分类',
    project_final_type varchar(50)  default ''                not null comment '最终分类',
    research_direction varchar(100) default ''                not null comment '研究方向',
    project_fund       int          default 0                 not null comment '项目经费',
    project_remark     varchar(255) default ''                not null comment '项目描述',
    project_status     int          default 1                 not null comment '项目状态，0 审核未通过 1 审核中 2 审核通过'
) comment '项目信息表' charset = utf8;

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
    audit_status        int          default 0                 not null comment '审核状态 0 无需审核 1 待处理 2 已处理',
    audit_step          int          default 1                 not null comment '审核环节 1 初审 2 终审',
    audit_result        int          default 0                 not null comment '审核结果 0 未通过 1 已通过',
    audit_comment       varchar(500) default ''                not null comment '审核备注'
) comment '项目审核信息表' charset = utf8;

-- 项目分类信息表
create table project_type_info (
    id                  int auto_increment comment '主键id'     primary key,
    created_at          timestamp    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at          timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted_at          int          default 0                 not null comment '删除标志',
    type_name           varchar(100) default ''                not null comment '分类名',
    committed_projects  int          default 0                 not null comment '已提交的项目数',
    passed_projects     int          default 0                 not null comment '通过的项目数',
    rejected_projects   int          default 0                 not null comment '拒绝的项目数'
) comment '项目分类信息表' charset = utf8;