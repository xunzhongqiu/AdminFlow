# Architecture

## Layering
- `common`: API response and global exception handling
- `config`: Spring Security, CORS and infrastructure configuration
- `security`: JWT issuance and request authentication
- `modules/auth`: authentication use cases
- `modules/system`: user and system administration
- `modules/dashboard`: overview APIs

## Request flow
Browser -> Nginx/Vite proxy -> Spring Security JWT filter -> Controller -> Service -> MyBatis-Plus Mapper -> MySQL.

## Extension rules
1. Controllers validate and shape HTTP requests; business logic belongs in services.
2. Never return password hashes or internal secrets from APIs.
3. Use DTOs for create/update requests and VO records for responses.
4. Use database migrations (Flyway recommended) after the first release.
5. Add permission checks at the service/API layer; hiding frontend menu items is not authorization.
