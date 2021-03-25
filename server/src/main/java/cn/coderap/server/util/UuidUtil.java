package cn.coderap.server.util;

import java.util.UUID;

/**
 * 为什么不用自增id?
 * 1、id是连续的，容易被探测；
 * 2、需要id+1次查询才能得到id的值
 * 3、分布式存储中，id会重复出现
 *
 * uuid是根据机器、时间等多个维度生成的32位16进制数，有生之年不会重复。
 * 这里在uuid的基础上，封装出了8位的短uuid。
 */
public class UuidUtil {

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };


    /**
     * 获取短UUID
     * @return
     */
    public static String getShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UuidUtil.getUuid();
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]); // 对62取余
        }
        return shortBuffer.toString();

    }

    /**
     * 获得32位UUID
     */
    public static String getUuid(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(getShortUuid());
    }
}
