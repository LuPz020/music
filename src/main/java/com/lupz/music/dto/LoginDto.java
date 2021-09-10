package com.lupz.music.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: music
 * @description:
 * @author: LuPz
 * @create: 2021-08-15 20:44
 **/
@Data
public class LoginDto {
    @NotBlank(message = "用户名为空")
    private String name;

    @NotBlank(message = "密码为空")
    private String password;
}
