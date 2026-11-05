package io.adminflow.modules.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

@TableName("sys_user")
public class SysUser {
    @TableId(type = IdType.AUTO) private Long id;
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private String password;
    private Integer status;
    private LocalDateTime createTime;
    @TableLogic private Integer deleted;
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getUsername(){return username;} public void setUsername(String v){username=v;}
    public String getNickname(){return nickname;} public void setNickname(String v){nickname=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
    public String getPassword(){return password;} public void setPassword(String v){password=v;}
    public Integer getStatus(){return status;} public void setStatus(Integer v){status=v;}
    public LocalDateTime getCreateTime(){return createTime;} public void setCreateTime(LocalDateTime v){createTime=v;}
    public Integer getDeleted(){return deleted;} public void setDeleted(Integer v){deleted=v;}
}
