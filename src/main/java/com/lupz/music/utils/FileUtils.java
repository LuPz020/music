package com.lupz.music.utils;

import com.lupz.music.common.result.Result;
import com.lupz.music.entity.Consumer;
import com.lupz.music.entity.Singer;
import com.lupz.music.entity.Song;
import com.lupz.music.entity.SongList;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @program: music
 * @description:
 * @author: LuPz
 * @create: 2021-08-18 20:32
 **/
public class FileUtils {
    public static void  deleteRes(Object entity){
        if(entity instanceof Singer){
            Singer nSinger = (Singer) entity;
            delete(nSinger.getPic());
        }
        if(entity instanceof SongList){
            SongList songList = (SongList) entity;
            delete(songList.getPic());
        }
        if(entity instanceof Consumer){
            Consumer consumer = (Consumer) entity;
            delete(consumer.getAvator());
        }
    }
    public static void  deleteRes(Song song,String detail){
                if (detail.equals("img")) {
                    delete(song.getPic());
                } else if (detail.equals("file")) {
                    delete(song.getUrl());
                }
            }
    private static void delete(String url){
        String trueUrl = System.getProperty("user.dir")+System.getProperty("file.separator")
                +url;
        File file = new File(trueUrl);
        file.delete();
    }

    public static Result uploadFile(MultipartFile file, String type){
        if(file.isEmpty()){
            return Result.fail("操作失败");
        }
        String fileName = System.currentTimeMillis()+file.getOriginalFilename();
        String filePath="";
        String storePath= "";
        switch (type){
            case "addSong":
                fileName=System.currentTimeMillis()+file.getOriginalFilename().replaceAll(" ","");
                filePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"song"+
                        System.getProperty("file.separator");
                storePath="/song/"+fileName;
                break;
            case "song":
                filePath =System.getProperty("user.dir")+System.getProperty("file.separator")+"song"+
                        System.getProperty("file.separator");
                storePath="/song/"+fileName;
                break;
            case "songImg":
                filePath =System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+
                        System.getProperty("file.separator")+"songImg"+System.getProperty("file.separator");
                storePath="/img/songImg/"+fileName;
                break;
            case "singerImg":
                filePath =System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+
                        System.getProperty("file.separator")+"singerImg"+System.getProperty("file.separator");
                storePath="/img/singerImg/"+fileName;
                break;
            case "songListImg":
                filePath= System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+
                        System.getProperty("file.separator")+"songListImg"+System.getProperty("file.separator");
                storePath="/img/songListImg/"+fileName;
                break;
            case "avatorImg":
                filePath= System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+
                        System.getProperty("file.separator")+"avatorImg"+System.getProperty("file.separator");
                storePath="/img/avatorImg/"+fileName;
                break;
        }
        File file1 = new File(filePath);
        //如果文件路径不存在，新键文件夹
        if(!file1.exists()){
            file1.mkdir();
        }
        File dest = new File(filePath+fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.succ(storePath);
    }
}
