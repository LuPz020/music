package com.lupz.music.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.lupz.music.common.result.Result;
import com.lupz.music.entity.Song;
import com.lupz.music.service.SongService;
import com.lupz.music.utils.Consts;
import com.lupz.music.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 歌曲表 前端控制器
 * </p>
 *
 * @author LuPz
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/song")
@RequiredArgsConstructor
public class SongController {
    final SongService songService;

    @PostMapping("/add")
    public Result addSong( Song song, @RequestParam("file") MultipartFile file){
            Result result = FileUtils.uploadFile(file,Consts.ADDSONG);
            String storeUrlPath = (String) result.getData();
            song.setUrl(storeUrlPath);
            song.setPic("/img/songImg/jjy.png");
            if(songService.saveOrUpdate(song)){
                return Result.succ(storeUrlPath);
            }
            return Result.fail("上传失败");

    }

    @GetMapping("/detail/{id}")
    public Result songOfSingerId(@PathVariable("id") Integer id){
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("singer_id",id);
        List<Song> list = songService.list(queryWrapper);
        return Result.succ(list);
    }

    @PutMapping("/update")
    public Result updateSinger(@RequestBody Song song){
        if(songService.saveOrUpdate(song)){
            return Result.succ(null);
        }
        return Result.fail("修改失败");
    }

    //删除歌曲
    @DeleteMapping("/delete")
    public Result deleteSinger(HttpServletRequest request){
        Integer id = Integer.valueOf(request.getParameter("id"));
        Song Nsong = songService.getById(Long.valueOf(id));
        FileUtils.deleteRes(Nsong,Consts.FILE);
        FileUtils.deleteRes(Nsong,Consts.IMG);
        if(songService.removeById(Long.valueOf(id))){
            return Result.succ(null);
        }
        return Result.fail("删除失败");
    }

    //更新歌曲图片
    @PostMapping("/updateSongPic")
    public Result updateSongPic(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id){
        Song Nsong = songService.getById(Long.valueOf(id));
        FileUtils.deleteRes(Nsong,Consts.IMG);
        Result result = FileUtils.uploadFile(file,Consts.SONGIMG);
        String storeAvatorPath = (String) result.getData();
        Nsong.setPic(storeAvatorPath);
        if(songService.saveOrUpdate(Nsong)){
            return Result.succ(storeAvatorPath);
        }
            return Result.fail("修改失败");
    }

    //更新歌曲文件
    @PostMapping("/updateSongUrl")
    public Result updateSongUrl(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id){
        Song Nsong = songService.getById(Long.valueOf(id));
        FileUtils.deleteRes(Nsong,Consts.FILE);
       Result result = FileUtils.uploadFile(file, Consts.SONG);
        String storeAvatorPath = (String) result.getData();
       Song song = new Song();
       song.setUrl(storeAvatorPath);
       song.setId(id);
       if(songService.saveOrUpdate(song)){
           return Result.succ(storeAvatorPath);
       }
            return Result.fail("修改失败");
        }
        //根据歌曲id 查询歌曲对象
        @GetMapping("/songDetail/{id}")
         public Result songDetail(@PathVariable("id")Integer songId){
             return Result.succ(songService.getById(Long.valueOf(songId)));
        }

    //根据歌名 查询歌曲对象
    @GetMapping("/songName/{name}")
    public Result songName(@PathVariable("name")String name){
        return Result.succ(songService.list(new QueryWrapper<Song>().eq("name",name)));
    }

    //根据歌名 查询歌曲对象
    @GetMapping("/songLikeName/{name}")
    public Result songLikeName(@PathVariable("name")String name){
        return Result.succ(songService.list(new QueryWrapper<Song>().like("name",name)));
    }


    //获取歌曲总数
    @GetMapping("getAllSongCount")
    public Result getAllSongCount(){
        return Result.succ(songService.list());
    }
 }
