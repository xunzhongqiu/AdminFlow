package io.adminflow.modules.dashboard.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.adminflow.common.ApiResponse;
import io.adminflow.modules.system.entity.SysUser;
import io.adminflow.modules.system.mapper.SysUserMapper;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final SysUserMapper mapper;
    public DashboardController(SysUserMapper mapper){this.mapper=mapper;}
    @GetMapping("/overview")
    public ApiResponse<Map<String,Object>> overview() {
        long total = mapper.selectCount(new QueryWrapper<>());
        long enabled = mapper.selectCount(new QueryWrapper<SysUser>().eq("status",1));
        return ApiResponse.ok(Map.of("userTotal",total,"userEnabled",enabled,"todayVisits",1286,"pendingTasks",18));
    }
}
