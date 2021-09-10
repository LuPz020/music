package com.lupz.music.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lupz.music.common.result.Result;
import com.lupz.music.entity.Collect;
import com.lupz.music.entity.Song;
import com.lupz.music.service.CollectService;
import com.lupz.music.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 收藏 前端控制器
 * </p>
 *
 * @author LuPz
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/collect")
@RequiredArgsConstructor
public class CollectController {
    final private CollectService collectService;
    final  private SongService songService;

    @PostMapping("/add")
    public Result addCollect(@RequestBody Collect collect) {
        if(collect.getSongId()!=null){
            Assert.isNull(collectService.getOne(new LambdaQueryWrapper<Collect>()
                    .eq(Collect::getUserId,collect.getUserId())
                    .eq(Collect::getSongId,collect.getSongId())),
                    "您已经收藏过这个歌曲");
        }
        if(collect.getSongListId()!=null){
            Assert.isNull(collectService.getOne(new LambdaQueryWrapper<Collect>()
                    .eq(Collect::getUserId,collect.getUserId())
                    .eq(Collect::getSongId,collect.getSongId())),
                    "您已经收藏过这个歌单");
        }
        if(collectService.save(collect)){
            return Result.succ(null);
        }
        return Result.fail("收藏失败");
    }

    @DeleteMapping("delete")
    public Result deleteCollect(HttpServletRequest request){
        Integer id = Integer.valueOf(request.getParameter("id"));
        if(collectService.removeById(id)){
            return Result.succ(null);
        }
        return Result.fail("取消收藏失败");
    }

    @GetMapping("getAllCollect")
    public Result getAllCollect(){
        return Result.succ(collectService.list());
    }

    @GetMapping("getOneCollect/{userid}")
    public Result getOneCollect(@PathVariable("userid") Integer userid){
        return Result.succ(collectService.list(new LambdaQueryWrapper<Collect>().eq(Collect::getUserId,userid)));
    }

    @GetMapping("getCollectSong/{userId}")
    public Result getCollectSong(@PathVariable("userId")Integer userId){
        List<Collect> list = collectService.list(new LambdaQueryWrapper<Collect>().eq(Collect::getUserId, userId));
        List<Song> songList = new ArrayList<>();
        for (Collect collect : list) {
            songList.add(songService.getById(collect.getSongId()));
        }
        return Result.succ(songList);
    }
}
