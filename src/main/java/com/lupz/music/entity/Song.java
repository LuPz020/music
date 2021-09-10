package com.lupz.music.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 歌曲表
 * </p>
 *
 * @author LuPz
 * @since 2021-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_song")
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer singerId;

    private String name;

    private String introduction;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 歌曲图片
     */
    private String pic;

    /**
     * 歌词
     */
    private String lyric;

    private String url;


}
