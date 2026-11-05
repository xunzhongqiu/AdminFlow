# API reference

All responses use `{ code, message, data }`.

## Authentication
### POST `/api/auth/login`
```json
{"username":"admin","password":"Admin@123456"}
```
Returns `data.accessToken`, `data.tokenType`, `data.expiresIn`, `data.user`.

### GET `/api/auth/me`
Requires `Authorization: Bearer <token>`.

## Dashboard
### GET `/api/dashboard/overview`
Returns user totals, enabled users and demo KPI values.

## Users
### GET `/api/system/users?page=1&size=10&keyword=admin`
Returns `records`, `total`, `page`, `size`.

Note: user write operations and full RBAC APIs are planned, not yet implemented in this starter release.
