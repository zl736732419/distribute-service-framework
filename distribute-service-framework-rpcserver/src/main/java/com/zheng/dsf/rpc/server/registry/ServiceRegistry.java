package com.zheng.dsf.rpc.server.registry;

/**
 * 服务注册中心，管理服务器当前已经暴露的服务接口
 * @Author zhenglian
 * @Date 2018/12/11
 */
public interface ServiceRegistry {
    /**
     * 服务注册
     */
    void registry();

    /**
     * 查询给定服务接口类
     * @param serviceName
     * @return
     */
    String getService(String serviceName);
}
