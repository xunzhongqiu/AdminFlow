# AdminFlow

企业级开源后台管理系统，采用 Spring Boot 3 + Java 17 + Vue 3 + TypeScript + Element Plus。

> 当前交付版本：可运行基础版本（登录认证、JWT、用户列表、仪表盘、前端布局、MySQL 初始化、Docker 部署骨架）。角色/菜单/部门/日志等模块已在架构和数据库中预留，后续按迭代补齐业务闭环。

## 技术栈
- Backend: Java 17, Spring Boot 3.3.5, Spring Security, JWT, MyBatis-Plus, MySQL 8, Redis
- Frontend: Vue 3, TypeScript, Vite, Element Plus, Pinia, Vue Router, Axios, ECharts
- Deployment: Docker Compose, Nginx

## Quick start

### 1. Database
```bash
docker compose up -d mysql redis
```
MySQL 初始化脚本位于 `database/init.sql`，默认库 `adminflow`。

### 2. Backend
```bash
cd adminflow-server
mvn spring-boot:run
```
默认端口 `8080`。开发默认账号：`admin` / `Admin@123456`。
首次启动会在控制台提示初始化管理员密码；请在生产环境立即修改。

### 3. Frontend
```bash
cd adminflow-ui
npm install
npm run dev
```
访问 http://localhost:5173。

### 4. Docker
```bash
docker compose up -d --build
```
访问 http://localhost。

## API
- `POST /api/auth/login`
- `GET /api/auth/me`
- `GET /api/dashboard/overview`
- `GET /api/system/users`

登录返回 `data.accessToken`，前端自动保存并在请求中携带 `Authorization: Bearer <token>`。

## Configuration
通过环境变量覆盖：
- `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USERNAME`, `DB_PASSWORD`
- `JWT_SECRET`（生产环境必须替换为至少 32 字节随机密钥）
- `SERVER_PORT`

## Security notes
- 示例管理员账号仅用于本地开发，生产部署前必须修改密码和 JWT 密钥。
- 当前 JWT 使用 HMAC 签名；HTTPS、限流、审计告警、密码找回、MFA、细粒度数据权限需要在生产上线前完成。
- 不要提交真实 `.env`、密钥或生产数据库备份。

## Roadmap
- [x] Vue3 管理端基础工程、登录页、主布局、仪表盘
- [x] Spring Security + JWT 登录认证
- [x] 用户列表接口与 MyBatis-Plus
- [x] MySQL 初始化数据、Docker Compose
- [ ] 完整 RBAC（角色、菜单、按钮权限、数据范围）
- [ ] 部门树、字典、参数配置
- [ ] 操作日志、登录日志、文件中心
- [ ] 单元测试、集成测试、CI/CD
"# AdminFlow" 
