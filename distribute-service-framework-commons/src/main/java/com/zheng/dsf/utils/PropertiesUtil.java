package com.zheng.dsf.utils;

import com.zheng.dsf.exceptions.ExceptionCode;
import com.zheng.dsf.exceptions.RpcServerException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author zhenglian
 * @Date 2018/12/11
 */
public class PropertiesUtil {
    
    public static Map<String, String> loadProperties(String configFile) {
        if (StringUtil.isEmpty(configFile)) {
            return null;
        }
        InputStream input = PropertiesUtil.class.getClassLoader().getResourceAsStream(configFile);
        if (StringUtil.isEmpty(input)) {
            return null;
        }

        Properties props = new Properties();
        try {
            props.load(input);
        } catch (IOException e) {
            throw new RpcServerException(ExceptionCode.RPC_CONFIG_RESOURCE_LOAD_EXCEPTION, e);
        }
        Map<String, String> map = new HashMap<>();
        props.keySet().stream()
                .filter(key -> StringUtil.isNotEmpty(key))
                .forEach(key -> {
                    String k = (String) key;
                    String value = props.getProperty(k);
                    map.put(k, value);
                });
        return map;
    }
}
