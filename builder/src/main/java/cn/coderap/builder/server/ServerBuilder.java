package cn.coderap.builder.server;

import cn.coderap.builder.util.DbUtil;
import cn.coderap.builder.util.Field;
import cn.coderap.builder.util.FreemarkerUtil;

import java.util.*;

/**
 * 1、注意：每次执行生成器，都会重新生成service和controller，只是service生成后没变化，所以没在changelist中显示
 * 2、dto的生成需要知道所有字段，每个字段的类型需要映射成java类型，并且要把字段变成小驼峰和大驼峰，比如course_id变成courseId、CourseId。
 * Created by yw
 * 2021/3/30
 */
public class ServerBuilder {

    static String MODULE = "business";
    static String toServicePath = "server\\src\\main\\java\\cn\\coderap\\server\\service\\"; //要生成的文件的路径
    static String toControllerPath = MODULE + "\\src\\main\\java\\cn\\coderap\\" + MODULE + "\\controller\\admin\\";
    static String toDtoPath = "server\\src\\main\\java\\cn\\coderap\\server\\dto\\";

    public static void main(String[] args) throws Exception {
        String Domain = "Section";
        String domain = "section";
        String tableNameCn = "小节";
        String module = MODULE;
        List<Field> fieldList = DbUtil.getColumnByTableName(domain);
        Set<String> typeSet = getJavaTypes(fieldList);
        Map<String,Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);
        map.put("tableNameCn", tableNameCn);
        map.put("module", module);
        map.put("fieldList", fieldList);
        map.put("typeSet", typeSet);

        //生成service
        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.builder(toServicePath + Domain + "Service.java", map);

        //生成controller
        FreemarkerUtil.initConfig("controller.ftl");
        FreemarkerUtil.builder(toControllerPath + Domain + "Controller.java", map);

        //生成dto
        FreemarkerUtil.initConfig("dto.ftl");
        FreemarkerUtil.builder(toDtoPath + Domain + "Dto.java", map);
    }

    /**
     * 获取所有的Java类型（生成import语句），使用Set去重（一种类型只需import一次，所以用set去重）
     */
    private static Set<String> getJavaTypes(List<Field> fieldList) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            set.add(field.getJavaType());
        }
        return set;
    }
}
