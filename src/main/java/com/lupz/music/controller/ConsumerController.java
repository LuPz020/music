package com.lupz.music.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lupz.music.common.result.Result;
import com.lupz.music.dto.LoginDto;
import com.lupz.music.entity.Admin;
import com.lupz.music.entity.Consumer;
import com.lupz.music.service.AdminService;
import com.lupz.music.service.ConsumerService;
import com.lupz.music.utils.Consts;
import com.lupz.music.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端用户表 前端控制器
 * </p>
 *
 * @author LuPz
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class ConsumerController {
    final private ConsumerService consumerService;

    //增加用户
    @PostMapping("/add")
    public Result addConsumer( @Validated  @RequestBody Consumer consumer){
        consumer.setAvator("img/avatorImg/jjyavator.png");
        Assert.isNull(consumerService.getOne(
                new QueryWrapper<Consumer>().eq("user_name",consumer.getUserName())),
                "该用户名已被注册");
        if(consumerService.save(consumer)){
            return Result.succ(null);
        }
        return Result.fail("新增用户失败");
    }
    //修改用户
    @PutMapping("update")
    public Result update(@Validated @RequestBody Consumer consumer){
        if(consumerService.saveOrUpdate(consumer)){
            return Result.succ(null);
        }
        return Result.fail("修改失败");
    }

    //删除用户
    @DeleteMapping("delete")
    public Result delete(HttpServletRequest request){
        if(consumerService.removeById(Long.valueOf(request.getParameter("id")))){
            return Result.succ(null);
        }
        return Result.fail("删除失败");
    }

    //查询所有用户
    @GetMapping("allConsumer")
    public Result allConsumer(){
        return Result.succ(consumerService.list());
    }

    //更新前端用户的图片
    @PostMapping("updateImg")
    public Result updateImg(@RequestParam("file")MultipartFile file,@RequestParam("id")Integer id){
        Consumer byId = consumerService.getById(Long.valueOf(id));
        FileUtils.deleteRes(byId);
        Result result = FileUtils.uploadFile(file, Consts.AVATOR);
        String avatorUrl = (String) result.getData();
        byId.setAvator(avatorUrl);
        if(consumerService.saveOrUpdate(byId)){
            return Result.succ(avatorUrl);
        }
        return Result.fail("更新图片失败");

    }

    @PostMapping("/login")
    public Result Login(@Validated @RequestBody LoginDto loginDto){
        Consumer consumer = consumerService.getOne(new QueryWrapper<Consumer>().eq("user_name", loginDto.getName()));
        //如果用户名不存在才会输入message
        Assert.notNull(consumer,"用户名不存在");
        if(consumer.getPassword().equals(loginDto.getPassword())){
            return Result.succ(consumer);
        }
        return Result.fail("密码错误");

    }

    @GetMapping("selectOne/{id}")
    public Result selectOneConsumer(@PathVariable("id")Integer id){
        return Result.succ(consumerService.getById(id));
    }

}
