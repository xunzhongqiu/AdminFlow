# Database design

The initial schema includes:
- `sys_user`: user account and profile
- `sys_role`: role definitions
- `sys_user_role`: user-role relation
- `sys_menu`: directory/menu/button permission nodes
- `sys_role_menu`: role-menu relation
- `sys_dept`: organization tree
- `sys_dict_type`, `sys_dict_data`: dictionary
- `sys_config`: system parameters
- `sys_operation_log`, `sys_login_log`: audit records

RBAC relation:
`sys_user` N:M `sys_role` N:M `sys_menu`.

Production recommendations:
- Add foreign keys or enforce referential integrity in service transactions.
- Add `tenant_id` only if multi-tenancy is a confirmed requirement.
- Keep audit tables append-only and define retention/archival policy.
- Add Flyway migrations and avoid editing already-applied SQL in production.
