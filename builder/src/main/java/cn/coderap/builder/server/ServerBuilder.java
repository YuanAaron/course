package cn.coderap.builder.server;

import cn.coderap.builder.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 注意：每次执行生成器，都会重新生成service和controller，只是service生成后没变化，所以没在changelist中显示
 * Created by yw
 * 2021/3/30
 */
public class ServerBuilder {

    static String toServicePath = "server\\src\\main\\java\\cn\\coderap\\server\\service\\"; //要生成的文件的路径
    static String toControllerPath = "business\\src\\main\\java\\cn\\coderap\\business\\controller\\admin\\";

    public static void main(String[] args) throws IOException, TemplateException {
        String Domain = "Section";
        String domain = "section";
        String tableNameCn = "小节";
        Map<String,Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);
        map.put("tableNameCn", tableNameCn);

        //生成service
        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.builder(toServicePath + Domain + "Service.java", map);

        //生成controller
        FreemarkerUtil.initConfig("controller.ftl");
        FreemarkerUtil.builder(toControllerPath + Domain + "Controller.java", map);
    }
}
