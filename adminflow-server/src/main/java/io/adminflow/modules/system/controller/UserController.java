package io.adminflow.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.adminflow.common.ApiResponse;
import io.adminflow.modules.system.entity.SysUser;
import io.adminflow.modules.system.mapper.SysUserMapper;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/system/users")
public class UserController {
    private final SysUserMapper mapper;
    public UserController(SysUserMapper mapper) { this.mapper=mapper; }
    @GetMapping
    public ApiResponse<Map<String,Object>> list(@RequestParam(defaultValue="1") long page,
        @RequestParam(defaultValue="10") long size, @RequestParam(required=false) String keyword) {
        Page<SysUser> p = new Page<>(page, Math.min(size, 100));
        LambdaQueryWrapper<SysUser> q = new LambdaQueryWrapper<SysUser>()
            .select(SysUser::getId, SysUser::getUsername, SysUser::getNickname, SysUser::getEmail,
                SysUser::getPhone, SysUser::getStatus, SysUser::getCreateTime)
            .and(keyword != null && !keyword.isBlank(), w -> w.like(SysUser::getUsername, keyword)
                .or().like(SysUser::getNickname, keyword))
            .orderByDesc(SysUser::getId);
        mapper.selectPage(p,q);
        return ApiResponse.ok(Map.of("records",p.getRecords(),"total",p.getTotal(),"page",p.getCurrent(),"size",p.getSize()));
    }
}
