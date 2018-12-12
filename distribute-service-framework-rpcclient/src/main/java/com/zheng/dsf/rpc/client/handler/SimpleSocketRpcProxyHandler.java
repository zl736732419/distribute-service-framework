package com.zheng.dsf.rpc.client.handler;

import com.zheng.dsf.rpc.client.importer.RpcImporter;
import com.zheng.dsf.rpc.client.importer.SimpleSocketRpcImporter;
import com.zheng.dsf.rpc.domain.HostPort;
import com.zheng.dsf.rpc.domain.RpcService;
import com.zheng.dsf.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * rpc服务代理处理类
 * @Author zhenglian
 * @Date 2018/12/11
 */
public class SimpleSocketRpcProxyHandler implements InvocationHandler {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private RpcImporter importer = new SimpleSocketRpcImporter();
    
    private HostPort address;
    
    public SimpleSocketRpcProxyHandler(HostPort address) {
        this.address = address;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1.构造远程接口调用参数
        RpcService rpcService = buildRpcService(proxy, method, args);
        // 2.socket调用
        Object result = importer.importer(rpcService, address);
        return result;
    }

    private RpcService buildRpcService(Object proxy, Method method, Object[] args) {
        String serviceSimpleName = getServiceSimpleName(method);
        // 小写首字母
        String methodName = method.getName();
        log.info("proxy invoke {}.{}, args:[{}].", serviceSimpleName, methodName, StringUtil.toString(args));
        Class<?>[] parameterTypes = method.getParameterTypes();
        RpcService rpcService = new RpcService.Builder()
                .serviceSimpleName(serviceSimpleName)
                .methodName(methodName)
                .parameterTypes(parameterTypes)
                .arguments(args)
                .build();
        return rpcService;
    }

    private String getServiceSimpleName(Method method) {
        String serviceName = method.getDeclaringClass().getSimpleName();
        serviceName = StringUtils.uncapitalize(serviceName);
        return serviceName;
    }
}
