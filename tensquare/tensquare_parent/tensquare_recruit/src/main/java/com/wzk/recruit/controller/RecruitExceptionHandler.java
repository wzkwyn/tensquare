package com.wzk.recruit.controller;
/*
 @author 吴梓康
 @create 2019/3/16
*/

import com.wzk.entity.Result;
import com.wzk.entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/*
    公共异常处理类
 */
@RestControllerAdvice   //统一拦截注解
public class RecruitExceptionHandler {
    @ExceptionHandler(Exception.class)  //指定拦截的异常
    public Result error(Exception e) {
        System.out.println("出异常了："+e.getMessage());
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }
}
