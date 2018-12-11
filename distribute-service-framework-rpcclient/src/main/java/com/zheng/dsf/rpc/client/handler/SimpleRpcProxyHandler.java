package com.zheng.dsf.rpc.client.handler;

import com.zheng.dsf.rpc.domain.HostPort;
import com.zheng.dsf.rpc.domain.RpcService;
import com.zheng.dsf.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * rpc服务代理处理类
 * @Author zhenglian
 * @Date 2018/12/11
 */
public class SimpleRpcProxyHandler<T> implements InvocationHandler {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private T service;
    private HostPort address;
    
    public SimpleRpcProxyHandler(T service, HostPort address) {
        this.service = service;
        this.address = address;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1.构造远程接口调用参数
        RpcService rpcService = buildRpcService(proxy, method, args);
        // 2.socket调用
        
        
        return null;
    }

    private RpcService buildRpcService(Object proxy, Method method, Object[] args) {
        String interfaceName = proxy.getClass().getName();
        String methodName = method.getName();
        log.info("proxy invoke {}.{}, args:[{}].", interfaceName, methodName, StringUtil.toString(args));
        Class<?>[] parameterTypes = method.getParameterTypes();
        RpcService rpcService = new RpcService.Builder()
                .interfaceName(interfaceName)
                .methodName(methodName)
                .parameterTypes(parameterTypes)
                .arguments(args)
                .build();
        return rpcService;
    }
}
