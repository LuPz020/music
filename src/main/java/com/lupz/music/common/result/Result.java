package com.lupz.music.common.result;

import lombok.Data;

/**
 * @program: music
 * @description:
 * @author: LuPz
 * @create: 2021-08-15 14:22
 **/
@Data
public class Result {
    private int code;
    private String msg;
    private Object data;

    public static Result succ(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result fail(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result fail(String msg,Object data){
        return fail(2,msg,data);
    }

    public static Result fail(String msg){
        return fail(2,msg,null);
    }

    public static Result succ(Object data){
        return succ(1,"操作成功",data);
    }


}
