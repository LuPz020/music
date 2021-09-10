package com.lupz.music.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author LuPz
 * @since 2021-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    /**
     * 0歌曲 1歌单
     */
    private Boolean type;

    private Integer songId;

    private Integer songListId;

    private String content;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private Integer up;


}
