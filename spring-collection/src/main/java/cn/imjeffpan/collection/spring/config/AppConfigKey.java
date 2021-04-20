package cn.imjeffpan.collection.spring.config;

/**
 * @author Jeff Pan
 * @since 2019-01-13
 */
public enum AppConfigKey implements ConfigKey {

    SPRING_APPLICATION_NAME("spring.application.name","应用名称")
    ;


    public final String key;

    public final String desc;

    AppConfigKey(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
