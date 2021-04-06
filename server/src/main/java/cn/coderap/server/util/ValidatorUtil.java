package cn.coderap.server.util;

import cn.coderap.server.exception.ValidatorException;
import org.springframework.util.StringUtils;

public class ValidatorUtil {

    /**
     * 空校验（null or ""）
     */
    public static void require(Object str, String fieldName) {
        if (StringUtils.isEmpty(str)) {
            //校验失败不再return false，用return false的设计也没有问题
            throw new ValidatorException(fieldName + "不能为空");
        }
    }

    /**
     * 长度校验
     */
    public static void length(String str, String fieldName, int min, int max) {
        //如果为空，就不进行长度校验（这样可以避免修改校验模板）
        if (StringUtils.isEmpty(str)) {
            return;
        }
        int length = 0;
        if (!StringUtils.isEmpty(str)) {
            length = str.length();
        }
        if (length < min || length > max) {
            throw new ValidatorException(fieldName + "长度" + min + "~" + max + "位");
        }
    }
}
