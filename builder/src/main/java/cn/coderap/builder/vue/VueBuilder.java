package cn.coderap.builder.vue;

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
 * Created by yw
 * 2021/3/30
 */
public class VueBuilder {

    static String MODULE = "business";
    static String toVuePath = "admin\\src\\views\\admin\\";
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

        //生成Vue
        FreemarkerUtil.initConfig("vue.ftl");
        FreemarkerUtil.builder(toVuePath + domain + ".vue", map);
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
