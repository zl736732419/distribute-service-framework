package com.zheng.dsf.rpc.server.registry;

import com.zheng.dsf.utils.PropertiesUtil;
import com.zheng.dsf.utils.StringUtil;

import java.util.Map;

/**
 * 通过properties配置文件本机实现服务注册
 * @Author zhenglian
 * @Date 2018/12/11
 */
public class PropertiesServiceRegistry implements ServiceRegistry {
    /**
     * 保存服务注册信息
     */
    private Map<String, String> services;
    /**
     * 服务注册配置文件名称
     */
    private String configFile = "services.properties";
    
    public PropertiesServiceRegistry() {
        registry();
    }
    
    @Override
    public void registry() {
        services = PropertiesUtil.loadProperties(configFile);
    }

    @Override
    public String getService(String serviceName) {
        if (StringUtil.isEmpty(services)) {
            return null;
        }
        return services.get(serviceName);
    }
}
