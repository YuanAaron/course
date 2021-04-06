package cn.coderap.builder.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DbUtil {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/course";
            String user = "oshacker";
            String pass = "oshacker";
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 获得表注释
     * @param tableName
     * @return
     * @throws Exception
     */
    public static String getTableComment(String tableName) throws Exception {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select table_comment from information_schema.tables Where table_name = '" + tableName + "'");
        String tableNameCH = "";
        if (rs != null) {
            while(rs.next()) {
                tableNameCH = rs.getString("table_comment");
                break;
            }
        }
        rs.close();
        stmt.close();
        conn.close();
        System.out.println("表名：" + tableNameCH);
        return tableNameCH;
    }

    /**
     * 获得所有列信息
     * 流程：根据表名获取所有的字段信息，再将字段信息填充到Field类中，得到Field列表（之后将Field列表变量传入模板）
     * @param tableName
     * @return
     * @throws Exception
     */
    public static List<Field> getColumnByTableName(String tableName) throws Exception {
        List<Field> fieldList = new ArrayList<>();
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("show full columns from `" + tableName + "`");
        if (rs != null) {
            while(rs.next()) {
                String columnName = rs.getString("Field");
                String type = rs.getString("Type");
                String comment = rs.getString("Comment");
                String nullAble = rs.getString("Null");
                Field field = new Field();
                field.setName(columnName);
                field.setNameHump(lineToHump(columnName));
                field.setNameBigHump(lineToBigHump(columnName));
                field.setType(type);
                field.setJavaType(DbUtil.sqlTypeToJavaType(rs.getString("Type")));
                field.setComment(comment);
                if (comment.contains("|")) {
                    field.setNameCn(comment.substring(0, comment.indexOf("|")));
                } else {
                    field.setNameCn(comment);
                }
                field.setNullAble("YES".equals(nullAble));

                //约定：当length>0时，表示需要对length做校验；当length=0时，表示不需要做校验

                //下面的代码表示只对varchar类型进行长度校验(char类型并没有进行长度校验)
                //解释：char类型一般用于固定长度的字段，常见的有id字段和枚举字段，id字段不需要校验（新增时后端会给id赋值，修改时后端id一定有值），
                // 枚举字段一般会有下拉框，不是手动输入的，不需要校验。
                if (type.toUpperCase().contains("varchar".toUpperCase())) {
                    //比如varchar(60)
                    String strLenght = type.substring(type.indexOf("(") + 1, type.length() - 1);
                    field.setLength(Integer.valueOf(strLenght));
                } else {
                    field.setLength(0);
                }
                fieldList.add(field);
            }
        }
        rs.close();
        stmt.close();
        conn.close();
        System.out.println("列信息：" + fieldList);
        return fieldList;
    }

    /**
     * 下划线转小驼峰
     */
    public static String lineToHump(String str){
        Pattern linePattern = Pattern.compile("_(\\w)");
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 下划线转大驼峰
     */
    public static String lineToBigHump(String str){
        String s = lineToHump(str);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 数据库类型转为Java类型
     */
    public static String sqlTypeToJavaType(String sqlType) {
        if (sqlType.toUpperCase().contains("varchar".toUpperCase())
                || sqlType.toUpperCase().contains("char".toUpperCase())
                || sqlType.toUpperCase().contains("text".toUpperCase())) {
            return "String";
        } else if (sqlType.toUpperCase().contains("datetime".toUpperCase())) {
            return "Date";
        } else if (sqlType.toUpperCase().contains("int".toUpperCase())) {
            return "Integer";
        } else if (sqlType.toUpperCase().contains("long".toUpperCase())) {
            return "Long";
        } else if (sqlType.toUpperCase().contains("decimal".toUpperCase())) {
            return "BigDecimal";
        } else {
            return "String";
        }
    }
}
