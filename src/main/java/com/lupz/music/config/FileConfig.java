package com.lupz.music.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: music
 * @description: 定位文件、头像地址
 * @author: LuPz
 * @create: 2021-08-15 22:50
 **/
@Configuration
public class FileConfig implements WebMvcConfigurer {
    @Override
    //歌手图片地址定位
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/singerImg/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+
                        System.getProperty("file.separator")+"singerImg"+System.getProperty("file.separator")
                //System.getProperty("file.separator")代表/    这样写是为了兼容linux
        );
        //歌曲图片地址定位
        registry.addResourceHandler("/img/songImg/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+
                        System.getProperty("file.separator")+"songImg"+System.getProperty("file.separator")
                //System.getProperty("file.separator")代表/    这样写是为了兼容linux
        );
        //歌单图片地址定位
        registry.addResourceHandler("/img/songListImg/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+
                        System.getProperty("file.separator")+"songListImg"+System.getProperty("file.separator")
                //System.getProperty("file.separator")代表/    这样写是为了兼容linux
        );
        //歌曲mp3地址定位
        registry.addResourceHandler("/song/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"song"+
                        System.getProperty("file.separator")
                //System.getProperty("file.separator")代表/    这样写是为了兼容linux
        );
        //用户头像地址定位
        registry.addResourceHandler("/img/avatorImg/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+
                        System.getProperty("file.separator")+"avatorImg"+System.getProperty("file.separator")
                //System.getProperty("file.separator")代表/    这样写是为了兼容linux
        );
    }
}
