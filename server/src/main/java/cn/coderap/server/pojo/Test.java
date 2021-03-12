package cn.coderap.server.pojo;

/**
 * 搭建公共服务模块server的必要性：这里的server不仅包括common，还包括一些业务
 * 1、具体说，如果每个模块都用到了Test类，那么就需要将Test拷贝多份，如果业务扩展了，
 * 需要对表加一个字段，这时如果要保持实体类和表结构一致，则所有模块的实体类都要改，费时费力。
 * 2、如果哪个模块用到新的字段，就修改哪个实体类，时间长了，很多的实体类和表对应不上，不利于使用mybatis代码生成器
 * 3、此外，service、mapper以及mapper.xml都可能出现不一致，因此这些都放到server模块中
 * Created by yw
 * 2021/3/11
 */
public class Test {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
