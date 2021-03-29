package cn.coderap.business.controller;

import cn.coderap.server.dto.ResponseDto;
import cn.coderap.server.exception.ValidatorException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 1、@ControllerAdvice是Controller增强器，可以对Controller做统一处理，如异常处理、数据处理等
 * 2、只要代码中有抛异常，且抛出来的异常是ValidatorException，就会被@ExceptionHandler(value = ValidatorException.class)拦截到，
 * 并执行相应的方法
 * Created by yw
 * 2021/3/29
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = ValidatorException.class)
    @ResponseBody
    public ResponseDto validatorExceptionHandler(ValidatorException e) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(false);
        responseDto.setMessage(e.getMessage());
        return responseDto;
    }
}
