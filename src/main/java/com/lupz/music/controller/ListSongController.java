package com.lupz.music.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lupz.music.common.result.Result;
import com.lupz.music.entity.ListSong;
import com.lupz.music.service.ListSongService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 歌单包含歌曲列表 前端控制器
 * </p>
 *
 * @author LuPz
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/listSong")
@RequiredArgsConstructor
public class ListSongController {
    final private ListSongService listSongService;

    @PostMapping("/add")
    public Result addListSong(@RequestBody ListSong listSong){
        if (listSongService.save(listSong)) {
           return Result.succ(null);
        }
        return Result.fail("增加失败");
    }

    //根据歌单id查询
    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable("id") Integer songLIstId){
       return Result.succ(listSongService.list(new QueryWrapper<ListSong>().eq("song_list_id",songLIstId)));
    }

    //删除歌单里的歌曲
    @DeleteMapping("delete")
    public Result delete(@RequestParam("songId") Integer songId,
    @RequestParam("songListId")Integer songListId){
        if(listSongService.remove(new QueryWrapper<ListSong>()
                .eq("song_id",songId)
                .eq("song_list_id",songListId))){
            return Result.succ(null);
        }
        return Result.fail("删除失败");
    }
}
