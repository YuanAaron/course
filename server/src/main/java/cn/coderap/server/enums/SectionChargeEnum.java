package cn.coderap.server.enums;

/**
 * 后端枚举的必要性：
 * 比如会员状态（正常、注销、冻结等），这些状态不是在页面上选择的（会员注册的时候，默认为正常）
 * Created by yw
 * 2021/4/9
 */
public enum SectionChargeEnum {

    //枚举这样设计的原因：枚举名称是给开发人员用的，code是给程序用的（前后端数据传输以及数据库的存储），desc是给用户用的
    CHARGE("C","收费"),
    FREE("F","免费");

    private String code;
    private String desc;

    SectionChargeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
