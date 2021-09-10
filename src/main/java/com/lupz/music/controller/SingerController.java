package com.lupz.music.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lupz.music.common.result.Result;
import com.lupz.music.entity.Singer;
import com.lupz.music.service.SingerService;
import com.lupz.music.utils.Consts;
import com.lupz.music.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 歌手表 前端控制器
 * </p>
 *
 * @author LuPz
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/singer")
@RequiredArgsConstructor
public class SingerController {
    final SingerService service;

    //添加歌手
    @PostMapping("/add")
    public Result addSinger(@RequestBody @Validated Singer singer){
        if(service.save(singer)){
            return Result.succ(null);
        }
        return Result.fail("添加失败");
    }

    //修改歌手
    @PutMapping("/update")
    public Result updateSinger(@RequestBody @Validated Singer singer){
        if(service.saveOrUpdate(singer)){
            return Result.succ(null);
        }
        return Result.fail("修改失败");
    }

    //删除歌手
    @DeleteMapping("/delete")
    public Result deleteSinger(HttpServletRequest request){
        Integer id = Integer.valueOf(request.getParameter("id"));
        Singer singer = service.getById(Long.valueOf(id));
        FileUtils.deleteRes(singer);
        if(service.removeById(Long.valueOf(id))){
            return Result.succ(null);
        }
        return Result.fail("删除失败");
    }

    //根据主键查询对象
    @GetMapping("/selectOne")
    public Result selectOne(@RequestBody Integer id){
        if(service.getById(id)!=null){
            return Result.succ(service.getById(id));
        }
        return Result.fail("歌手不存在");
    }

    //查询所有
    @GetMapping("/selectAll")
    public Result selectAll(){
        return Result.succ(service.list());
    }

    //根据名字模糊查询
    @GetMapping("/singerOfName")
    public Result singerOfName(@RequestBody String name){
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",'%'+name+'%');
        List<Singer> singers= service.list(queryWrapper);
        if(singers.isEmpty()){
            return Result.fail("找不到所查歌手");
        }
        return Result.succ(singers);
    }

    //根据性别查询
    @GetMapping("/singerOfSex/{sex}")
    public Result singerOfSex(@PathVariable("sex") Integer sex){
        if((service.list(new LambdaQueryWrapper<Singer>().eq(Singer::getSex,sex)).isEmpty())){
            return Result.fail("没有这个性别的歌手");
        }
        return Result.succ(service.list(new LambdaQueryWrapper<Singer>().eq(Singer::getSex,sex)));
    }

    //更新歌手图片
    @PostMapping("/updateSingerPic")
    public Result updateSingerPic(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id){
        Singer nSinger = service.getById(Long.valueOf(id));
        FileUtils.deleteRes(nSinger);
        Result result = FileUtils.uploadFile(file, Consts.SINGERIMG);
        String storeAvatorPath = (String) result.getData();
        nSinger.setPic(storeAvatorPath);
        if(service.saveOrUpdate(nSinger)){
            return Result.succ(storeAvatorPath);
        }
            return Result.fail("修改失败");
        }
}
