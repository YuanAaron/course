package cn.coderap.server.exception;

/**
 * 自定义异常：可以继承RuntimeException或Exception。一般项目内部的业务异常用RuntimeException，不需要try catch;如果时开发一些框架或工具类，
 * 明确告诉外部需要做异常处理的，可以用Exception。此外，还需要考虑事务中的异常处理，后续再介绍。
 */
public class ValidatorException extends RuntimeException{

    public ValidatorException(String message) {
        super(message);
    }
}
