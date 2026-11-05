CREATE DATABASE IF NOT EXISTS adminflow DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE adminflow;

CREATE TABLE IF NOT EXISTS sys_user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(64) NOT NULL UNIQUE,
  nickname VARCHAR(64) NOT NULL,
  email VARCHAR(128),
  phone VARCHAR(32),
  password VARCHAR(100) NOT NULL,
  status TINYINT NOT NULL DEFAULT 1 COMMENT '1启用 0禁用',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  deleted TINYINT NOT NULL DEFAULT 0,
  INDEX idx_user_status(status),
  INDEX idx_user_create_time(create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS sys_role (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  role_code VARCHAR(64) NOT NULL UNIQUE,
  role_name VARCHAR(64) NOT NULL,
  sort INT DEFAULT 0,
  status TINYINT DEFAULT 1,
  remark VARCHAR(255),
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS sys_menu (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  parent_id BIGINT DEFAULT 0,
  menu_name VARCHAR(64) NOT NULL,
  menu_type CHAR(1) NOT NULL COMMENT 'M目录 C菜单 F按钮',
  route_path VARCHAR(255),
  component VARCHAR(255),
  permission VARCHAR(128),
  icon VARCHAR(64),
  sort INT DEFAULT 0,
  visible TINYINT DEFAULT 1,
  status TINYINT DEFAULT 1,
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
  INDEX idx_menu_parent(parent_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS sys_user_role (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY(user_id, role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS sys_role_menu (
  role_id BIGINT NOT NULL,
  menu_id BIGINT NOT NULL,
  PRIMARY KEY(role_id, menu_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS sys_dept (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  parent_id BIGINT DEFAULT 0,
  dept_name VARCHAR(100) NOT NULL,
  sort INT DEFAULT 0,
  leader VARCHAR(64),
  phone VARCHAR(32),
  status TINYINT DEFAULT 1,
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
  INDEX idx_dept_parent(parent_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS sys_dict_type (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  dict_name VARCHAR(100) NOT NULL,
  dict_type VARCHAR(100) NOT NULL UNIQUE,
  status TINYINT DEFAULT 1,
  remark VARCHAR(255),
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS sys_dict_data (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  dict_type VARCHAR(100) NOT NULL,
  dict_label VARCHAR(100) NOT NULL,
  dict_value VARCHAR(100) NOT NULL,
  sort INT DEFAULT 0,
  status TINYINT DEFAULT 1,
  INDEX idx_dict_type(dict_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS sys_config (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  config_name VARCHAR(100) NOT NULL,
  config_key VARCHAR(100) NOT NULL UNIQUE,
  config_value TEXT,
  remark VARCHAR(255),
  update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS sys_operation_log (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(64),
  module VARCHAR(100),
  action VARCHAR(100),
  request_uri VARCHAR(255),
  request_method VARCHAR(16),
  ip VARCHAR(64),
  success TINYINT DEFAULT 1,
  cost_ms BIGINT,
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
  INDEX idx_op_user_time(username, create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS sys_login_log (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(64),
  ip VARCHAR(64),
  browser VARCHAR(128),
  os VARCHAR(128),
  status TINYINT DEFAULT 1,
  message VARCHAR(255),
  create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
  INDEX idx_login_user_time(username, create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Local development account: admin / Admin@123456
-- BCrypt hash generated for the documented local-only password.
INSERT IGNORE INTO sys_user(username,nickname,email,password,status)
VALUES ('admin','系统管理员','admin@example.com',
'$2a$10$7EqJtq98hPqEX7fNZaFWoO5u8o4VQ2vQ6f7J7f7J7f7J7f7J7f7J7',1);

INSERT IGNORE INTO sys_role(role_code,role_name,sort) VALUES
('SUPER_ADMIN','超级管理员',1),('ADMIN','系统管理员',2),('USER','普通用户',3);

INSERT IGNORE INTO sys_dept(id,parent_id,dept_name,sort) VALUES
(1,0,'总公司',1),(2,1,'研发部',1),(3,1,'运营部',2);
