package cn.coderap.builder.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by yw
 * 2021/3/30
 */
public class FreemarkerUtil {

    static String ftlPath = "builder\\src\\main\\java\\cn\\coderap\\builder\\ftl\\"; //模板文件所在的路径
    static Template temp;

    public static void initConfig(String ftlName) throws IOException {
        //读模板
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setDirectoryForTemplateLoading(new File(ftlPath));
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_29));
        temp = cfg.getTemplate(ftlName);
    }

    public static void builder(String fileName, Map<String,Object> map) throws IOException, TemplateException {
        //根据模板生成文件
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        temp.process(map, bw);
        bw.flush();
        fw.close();
    }
}
