package io.adminflow.modules.auth.controller;

import io.adminflow.common.ApiResponse;
import io.adminflow.modules.auth.dto.LoginRequest;
import io.adminflow.modules.auth.service.AuthService;
import io.adminflow.modules.system.entity.SysUser;
import io.adminflow.modules.system.mapper.SysUserMapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final SysUserMapper userMapper;
    public AuthController(AuthService authService, SysUserMapper userMapper) {
        this.authService=authService; this.userMapper=userMapper;
    }
    @PostMapping("/login")
    public ApiResponse<Map<String,Object>> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.ok(authService.login(request.username(), request.password()));
    }
    @GetMapping("/me")
    public ApiResponse<Map<String,Object>> me(Authentication authentication) {
        SysUser u = userMapper.selectOne(new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<SysUser>()
            .eq(SysUser::getUsername, authentication.getName()));
        return ApiResponse.ok(Map.of("id",u.getId(),"username",u.getUsername(),"nickname",u.getNickname(),"email",u.getEmail()));
    }
}
