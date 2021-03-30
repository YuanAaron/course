package cn.coderap.builder.server;

import cn.coderap.builder.util.DbUtil;
import cn.coderap.builder.util.Field;
import cn.coderap.builder.util.FreemarkerUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;

/**
 * 1、注意：每次执行生成器，都会重新生成service和controller，只是service生成后没变化，所以没在changelist中显示
 * 2、dto的生成需要知道所有字段，每个字段的类型需要映射成java类型，并且要把字段变成小驼峰和大驼峰，比如course_id变成courseId、CourseId。
 * 3、新表生成代码时，generatorConfig.xml得改动（tableName、domainObjectName），ServerBuilder的代码也要改动（Domain、domain以及tableNameCn）。
 * 其实，这样的修改也是可以接受的，但是既然写了代码生成器，为了进一步减少配置，可以将上面两项配置整合在一起。
 * 4、优化：ServerBuilder的配置不再是写死的，而是去读generatorConfig.xml的配置，这样就只需要该generatorConfig.xml即可
 * Created by yw
 * 2021/3/30
 */
public class ServerBuilder {

    static String MODULE = "business";
    static String toServicePath = "server\\src\\main\\java\\cn\\coderap\\server\\service\\"; //要生成的文件的路径
    static String toControllerPath = MODULE + "\\src\\main\\java\\cn\\coderap\\" + MODULE + "\\controller\\admin\\";
    static String toDtoPath = "server\\src\\main\\java\\cn\\coderap\\server\\dto\\";
    static String generatorConfigPath = "server\\src\\main\\resources\\generator\\generatorConfig.xml";

    public static void main(String[] args) throws Exception {
        String module = MODULE;

        Element tableElement = getFirstTableNode(generatorConfigPath);
        String Domain = tableElement.attributeValue("domainObjectName");
        String domain = Domain.substring(0,1).toLowerCase() + Domain.substring(1);
        String tableName = tableElement.attributeValue("tableName"); //tableName可能有下划线，比如course_section，这时domain为courseSection
        String tableNameCn = DbUtil.getTableComment(tableName);

        List<Field> fieldList = DbUtil.getColumnByTableName(tableName); //domain为courseSection时，这里的tableName就不等于domain
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

    /**
     * 取第一个“table”的节点
     * @param generatorConfigPath
     * @return
     * @throws DocumentException
     */
    private static Element getFirstTableNode(String generatorConfigPath) throws DocumentException {
        File file = new File(generatorConfigPath);
        SAXReader reader=new SAXReader();
        //读取xml文件到Document中
        Document doc=reader.read(file);
        //获取xml文件的根节点
        Element rootElement=doc.getRootElement();
        //读取context节点
        Element contextElement = rootElement.element("context");
        return contextElement.elementIterator("table").next();
    }
}
