package com.lupz.music.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.lupz.music.common.result.Result;
import com.lupz.music.entity.Song;
import com.lupz.music.entity.SongList;
import com.lupz.music.service.SongListService;
import com.lupz.music.utils.Consts;
import com.lupz.music.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 歌单 前端控制器
 * </p>
 *
 * @author LuPz
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/songList")
@RequiredArgsConstructor
public class SongListController {
    final private SongListService songListService;
    //增加歌单
    @PostMapping("/add")
    public Result addSongList(@RequestBody SongList songList){
        songList.setPic("/img/songListImg/test.jpg");
        if(songListService.save(songList)){
            return Result.succ(null);
        }
        return  Result.fail("上传失败");
    }
    //更新歌单
    @PutMapping("/update")
    public Result updateSongList(@RequestBody SongList songList){
        if(songListService.saveOrUpdate(songList)) {
            return Result.succ(null);

        }
        return Result.fail(" 更新失败");
    }
    //删除歌单
    @DeleteMapping("/delete")
    public Result deleteSinger(HttpServletRequest request){
        Integer id = Integer.valueOf(request.getParameter("id"));
        SongList nSongList = songListService.getById(Long.valueOf(id));
        FileUtils.deleteRes(nSongList);
        if(songListService.removeById(request.getParameter("id"))){
            return Result.succ(null);
        }
        return Result.fail("删除失败");
    }
    //根据主键查询
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(HttpServletRequest request){
        Integer id = Integer.valueOf(request.getParameter("id"));
        return Result.succ(songListService.getById(Long.valueOf(id)));
    }
    //查询所有歌单
    @GetMapping("/allSongList")
    public Result allSongList(){
        return Result.succ(songListService.list());
    }
    //根据歌单精确标题查询
    @GetMapping("/songListOfTitle")
    public Result songListOfTitle(HttpServletRequest request){
        String title = request.getParameter("title").trim();
        return Result.succ(songListService.getOne(new QueryWrapper<SongList>().eq("title",title)));
    }

    //根据歌单模糊标题查询
    @GetMapping("/likeTitle/{title}")
    public Result likeTitle(@PathVariable("title") String title){
        return Result.succ(songListService.list(new QueryWrapper<SongList>().like("title",title)));
    }

    //根据风格模糊查询
    @GetMapping("/likeStyle/{style}")
    public Result likeStyle(@PathVariable("style") String style){
        return Result.succ(songListService.list(new QueryWrapper<SongList>().like("style",style)));
    }

    //更新歌单图片
    @PostMapping("/updateSongListPic")
    public Result updateSongListPic(@RequestParam("file")MultipartFile file,@RequestParam("id")Integer id){
        SongList songList = songListService.getById(Long.valueOf(id));
        FileUtils.deleteRes(songList);
        Result result = FileUtils.uploadFile(file, Consts.SONGLISTIMG);
        String storePath = (String) result.getData();

        songList.setPic(storePath);
        if(songListService.saveOrUpdate(songList)){
            return Result.succ(storePath);
        }
        return Result.fail("更新失败");
    }
}
