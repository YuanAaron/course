package cn.coderap.business.controller;

import cn.coderap.business.controller.admin.ChapterController;
import cn.coderap.server.dto.ResponseDto;
import cn.coderap.server.exception.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = ValidatorException.class)
    @ResponseBody
    public ResponseDto validatorExceptionHandler(ValidatorException e) {
        //需将详细的错误日志记录到后台
        LOGGER.warn(e.getMessage());
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(false);
        //有时候我们的接口原本不是对外的，或只跟特定的第三方应用做对接，这时为了内部安全，不应该把参数的校验规则暴露出去，所以需要模糊返回信息。
        // 比如登录接口应该返回”用户名或密码错误“,而不是”用户名不存在“或”密码错误“，这样容易被探测。
        responseDto.setMessage("请求参数错误!");
        return responseDto;
    }
}
