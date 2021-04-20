package cn.imjeffpan.collection.spring.controller;

import cn.imjeffpan.collection.spring.config.EnvironmentContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.imjeffpan.collection.spring.config.AppConfigKey;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jeff Pan
 * @since 2019-01-13
 */
@RestController
public class SimpleWebApi {


    @GetMapping("test")
    public Map<String, Object> test() {
        Map<String, Object> result = new HashMap<>(1);
        result.put("SPRING_APPLICATION_NAME", EnvironmentContext.getStringValue(AppConfigKey.SPRING_APPLICATION_NAME));
        return result;
    }

}
