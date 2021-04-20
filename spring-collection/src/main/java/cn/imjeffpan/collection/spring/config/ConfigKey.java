package cn.imjeffpan.collection.spring.config;

/**
 * 配置类型
 * @author Jeff Pan
 * @since 2019-01-13
 */
public interface ConfigKey {

    /**
     * 得到配置键
     * @return 配置键
     */
    String getKey();

    /**
     * 得到配置描述
     * @return 配置描述
     */
    String getDesc();

}
