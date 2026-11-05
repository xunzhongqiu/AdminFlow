package io.adminflow.modules.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.adminflow.modules.system.entity.SysUser;
import io.adminflow.modules.system.mapper.SysUserMapper;
import io.adminflow.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class AuthService {
    private final SysUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public AuthService(SysUserMapper userMapper, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userMapper=userMapper; this.passwordEncoder=passwordEncoder; this.jwtService=jwtService;
    }
    public Map<String,Object> login(String username, String password) {
        SysUser user = userMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
        if (user == null || user.getStatus() == null || user.getStatus() != 1 ||
            !passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("用户名或密码错误");
        }
        return Map.of("accessToken", jwtService.generate(user.getUsername()), "tokenType", "Bearer",
            "expiresIn", 7200, "user", Map.of("id", user.getId(), "username", user.getUsername(),
                "nickname", user.getNickname(), "email", user.getEmail()));
    }
}
