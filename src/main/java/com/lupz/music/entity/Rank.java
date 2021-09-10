package com.lupz.music.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 评价
 * </p>
 *
 * @author LuPz
 * @since 2021-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_rank")
public class Rank implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer songListId;

    private Integer consumerId;

    private Float score;


}
