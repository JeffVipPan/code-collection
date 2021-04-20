package cn.mrdear.collection.spring.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 从Spring环境中获取到对应的键值对
 * @author Jeff Pan
 * @since 2019-01-13
 */
@Configuration
public class EnvironmentContext implements EnvironmentAware {

    /**
     * Spring的环境配置
     */
    private static Environment environment;

    /**
     * 根据配置Key获取对应的值
     * @param key 配置的key键
     * @return 值
     */
    public static String getStringValue(ConfigKey key) {
        if (null == key) {
            return null;
        }
        return environment.getProperty(key.getKey());
    }

    /**
     * 根据对应的key获取配置值
     * @param key 配置键
     * @param defaultValue 当值不存在时,返回该默认
     * @return 值
     */
    public static String getStringValue(ConfigKey key, String defaultValue) {
        if (null == key) {
            return defaultValue;
        }
        String value = environment.getProperty(key.getKey());
        return null == value ? defaultValue : value;
    }


    @Override
    public void setEnvironment(Environment env) {
        environment = env;
    }

}
