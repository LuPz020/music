package com.lupz.music.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @program: music
 * @description:
 * @author: LuPz
 * @create: 2021-08-18 14:14
 **/
@Component
public class MyMetaHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //相当于给metaObject 的 "createTime" 赋予 new Date()
        this.setFieldValByName("createTime", LocalDateTime.now(),metaObject);//实体类名字
        this.setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
    }
}
