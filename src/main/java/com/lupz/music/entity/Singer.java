package com.lupz.music.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 歌手表
 * </p>
 *
 * @author LuPz
 * @since 2021-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_singer")
public class Singer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotBlank(message = "歌手姓名为空")
    private String name;

    /**
     * 1男0女
     */
    private Integer sex;

    private String pic;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    private String location;

    private String introduction;


}
