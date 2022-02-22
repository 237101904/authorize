-- authorize database used in tests that use HSQL --

-- client和token表 --
create table oauth_client_details (
                                      client_id VARCHAR(255) PRIMARY KEY,
                                      resource_ids VARCHAR(255),
                                      client_secret VARCHAR(255),
                                      scope VARCHAR(255),
                                      authorized_grant_types VARCHAR(255),
                                      web_server_redirect_uri VARCHAR(255),
                                      authorities VARCHAR(255),
                                      access_token_validity INTEGER,
                                      refresh_token_validity INTEGER,
                                      additional_information TEXT(4096),
                                      autoapprove VARCHAR(255)
);

create table oauth_client_token (
                                    token_id VARCHAR(255),
                                    token BLOB,
                                    authentication_id VARCHAR(255) PRIMARY KEY,
                                    user_name VARCHAR(255),
                                    client_id VARCHAR(255)
);

create table oauth_access_token (
                                    token_id VARCHAR(255),
                                    token BLOB,
                                    authentication_id VARCHAR(255) PRIMARY KEY,
                                    user_name VARCHAR(255),
                                    client_id VARCHAR(255),
                                    authentication BLOB,
                                    refresh_token VARCHAR(255)
);

create table oauth_refresh_token (
                                     token_id VARCHAR(255),
                                     token BLOB,
                                     authentication BLOB
);

create table oauth_code (
                            code VARCHAR(255), authentication BLOB
);

create table oauth_approvals (
                                 userId VARCHAR(255),
                                 clientId VARCHAR(255),
                                 scope VARCHAR(255),
                                 status VARCHAR(10),
                                 expiresAt TIMESTAMP,
                                 lastModifiedAt TIMESTAMP
);


-- customized oauth_client_details table
create table ClientDetails (
                               appId VARCHAR(255) PRIMARY KEY,
                               resourceIds VARCHAR(255),
                               appSecret VARCHAR(255),
                               scope VARCHAR(255),
                               grantTypes VARCHAR(255),
                               redirectUrl VARCHAR(255),
                               authorities VARCHAR(255),
                               access_token_validity INTEGER,
                               refresh_token_validity INTEGER,
                               additionalInformation VARCHAR(4096),
                               autoApproveScopes VARCHAR(255)
);

-- 在oauth_client_details表添加数据 --
INSERT INTO `oauth_client_details` VALUES ('net5ijy', NULL, '123456', 'all,read,write', 'authorization_code,refresh_token,password', NULL, 'ROLE_TRUSTED_CLIENT', 7200, 7200, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('tencent', NULL, '123456', 'all,read,write', 'authorization_code,refresh_code', NULL, 'ROLE_TRUSTED_CLIENT', 3600, 3600, NULL, NULL);

-- 用户、角色表 --
CREATE TABLE `springcloud_user` (
                                    `id`  int(11) NOT NULL AUTO_INCREMENT ,
                                    `username`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
                                    `password`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
                                    `phone`  varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
                                    `email`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
                                    `create_time`  datetime NOT NULL ,
                                    PRIMARY KEY (`id`)
)
    ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1;

CREATE TABLE `springcloud_role` (
                                    `id`  int(11) NOT NULL AUTO_INCREMENT ,
                                    `name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
                                    PRIMARY KEY (`id`)
)
    ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1;

CREATE TABLE `springcloud_user_role` (
                                         `user_id`  int(11) NOT NULL ,
                                         `role_id`  int(11) NOT NULL ,
                                         FOREIGN KEY (`role_id`) REFERENCES `springcloud_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                         FOREIGN KEY (`user_id`) REFERENCES `springcloud_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                         INDEX `user_id_fk` USING BTREE (`user_id`) ,
                                         INDEX `role_id_fk` USING BTREE (`role_id`)
)
    ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

-- 用户表添加数据 --
INSERT INTO `springcloud_user` VALUES (1, 'admin001', '$2a$10$sXHKvdufrEfE2900ME40nOSBmeHRRUOF71szu22uaqqL8FIJeJDYW', '13622114309', '13622114309@189.cn', '2019-4-7 09:31:07');
INSERT INTO `springcloud_user` VALUES (2, 'admin002', '$2a$10$sXHKvdufrEfE2900ME40nOSBmeHRRUOF71szu22uaqqL8FIJeJDYW', '17809837654', '17809837654@189.cn', '2019-4-7 09:33:00');

-- 角色表添加数据 --
INSERT INTO `springcloud_role` VALUES (1, 'ADMIN');
INSERT INTO `springcloud_role` VALUES (2, 'DBA');
INSERT INTO `springcloud_role` VALUES (3, 'USER');

-- 用户角色关系表添加数据 --
INSERT INTO `springcloud_user_role` VALUES (1, 1);
INSERT INTO `springcloud_user_role` VALUES (2, 1);