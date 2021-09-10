package com.lupz.music.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lupz.music.common.result.Result;
import com.lupz.music.dto.LoginDto;
import com.lupz.music.entity.Admin;
import com.lupz.music.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author LuPz
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    final private AdminService adminService;

    //判断是否登录成功
    @PostMapping("/login")
    public Result loginStatus(HttpSession session,
                             @RequestBody @Validated LoginDto loginDto){
        Admin admin  = adminService.getOne(new QueryWrapper<Admin>().eq("name", loginDto.getName()));
        //如果用户名不存在才会输入message
        Assert.notNull(admin,"用户名不存在");
        if(admin.getPassword().equals(loginDto.getPassword())){
            return Result.succ(null);
        }
        return Result.fail("密码错误");

    }
}
