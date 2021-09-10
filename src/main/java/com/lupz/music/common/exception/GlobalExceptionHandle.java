package com.lupz.music.common.exception;


import com.lupz.music.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: music
 * @description:
 * @author: LuPz
 * @create: 2021-08-15 20:48
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e){
        log.error("实体校验异常：----------------------");
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.fail(objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value= RuntimeException.class)
    public Result handler(RuntimeException e){
        log.error("运行时异常：----------------------");
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value= IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e){
        log.error("Assert异常：----------------------");
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = DuplicateKeyException.class)
    public Result handler(DuplicateKeyException e){
        log.error("SQL索引异常：-------------------");
        return Result.fail("请勿重复操作");
    }
}
