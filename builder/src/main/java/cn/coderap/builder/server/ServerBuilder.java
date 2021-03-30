package cn.coderap.builder.server;

import cn.coderap.builder.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yw
 * 2021/3/30
 */
public class ServerBuilder {

    static String toServicePath = "server\\src\\main\\java\\cn\\coderap\\server\\service\\"; //要生成的文件的路径

    public static void main(String[] args) throws IOException, TemplateException {
        String Domain = "Section";
        String domain = "section";
        Map<String,Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);

        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.builder(toServicePath + Domain + "Service.java", map);
    }
}
