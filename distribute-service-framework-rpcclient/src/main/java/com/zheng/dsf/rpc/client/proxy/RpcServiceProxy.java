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
public class RpcServiceProxy<T> {

    public T getService(Class service, HostPort address) {
        if (StringUtil.isEmpty(service)) {
            return null;
        }
        ClassLoader loader = service.getClassLoader();
        T proxyService = (T) Proxy.newProxyInstance(loader, new Class<?>[] {service}, new SimpleRpcProxyHandler<>(address));
        return proxyService;
    }
}
