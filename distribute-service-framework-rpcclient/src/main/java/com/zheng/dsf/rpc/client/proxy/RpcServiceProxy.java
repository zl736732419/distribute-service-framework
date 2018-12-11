package com.zheng.dsf.rpc.client.proxy;

import com.zheng.dsf.rpc.client.handler.SimpleRpcProxyHandler;
import com.zheng.dsf.rpc.domain.HostPort;
import com.zheng.dsf.utils.StringUtil;

import java.lang.reflect.Proxy;

/**
 * rpc服务代理
 *
 * @Author zhenglian
 * @Date 2018/12/11
 */
public class RpcServiceProxy {

    /**
     * 加载rpc服务地址, 从配置文件中读取
     * @return
     */
    private HostPort loadAddress() {
        // TODO 这里为了测试，直接new一个
        return new HostPort.Builder().build();
    }
    
    public <T> T getService(T service) {
        if (StringUtil.isEmpty(service)) {
            return null;
        }
        ClassLoader loader = service.getClass().getClassLoader();
        Class[] interfaces = (Class[]) service.getClass().getGenericInterfaces();
        HostPort address = loadAddress();
        T proxyService = (T) Proxy.newProxyInstance(loader, interfaces, new SimpleRpcProxyHandler<>(service, address));
        return proxyService;
    }
}
