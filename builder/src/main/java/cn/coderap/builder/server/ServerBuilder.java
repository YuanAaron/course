package cn.coderap.builder.server;

import cn.coderap.builder.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * Created by yw
 * 2021/3/30
 */
public class ServerBuilder {

    static String toPath = "builder\\src\\main\\java\\cn\\coderap\\builder\\test\\"; //要生成的文件的路径

    public static void main(String[] args) throws IOException, TemplateException {
        FreemarkerUtil.initConfig("test.ftl");
        FreemarkerUtil.builder(toPath + "Test.java");
    }
}
