package com.zheng.dsf.rpc.server.invoker;

import com.zheng.dsf.exceptions.ExceptionCode;
import com.zheng.dsf.exceptions.RpcServerException;
import com.zheng.dsf.rpc.domain.RpcService;
import com.zheng.dsf.rpc.server.registry.PropertiesServiceRegistry;
import com.zheng.dsf.rpc.server.registry.ServiceRegistry;
import com.zheng.dsf.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 简单服务调用接口
 *
 * @Author zhenglian
 * @Date 2018/12/11
 */
public class SimpleRpcInvoker implements RpcInvoker<Object> {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private ServiceRegistry registry = new PropertiesServiceRegistry();

    @Override
    public Object invoke(RpcService rpcService) throws RpcServerException {
        if (StringUtil.isEmpty(rpcService)) {
            log.error("rpc service invoke failed, invoke service is empty");
        }
        String interfaceName = rpcService.getServiceSimpleName();
        String serviceCls = registry.getService(interfaceName);
        if (StringUtil.isEmpty(serviceCls)) {
            throw new RpcServerException(ExceptionCode.RPC_SERVICE_NOT_FOUND_EXCEPTION.getKey(),
                    ExceptionCode.RPC_SERVICE_NOT_FOUND_EXCEPTION.getValue()+" 接口名:["+interfaceName+"]");
        }
        String methodName = rpcService.getMethodName();
        Class<?>[] parameterTypes = rpcService.getParameterTypes();
        Object[] arguments = rpcService.getArguments();
        Class<?> clazz;
        Method method;
        Object instance;
        try {
            clazz = Class.forName(serviceCls);
            instance = clazz.newInstance();
            method = clazz.getMethod(methodName, parameterTypes);
        } catch (Exception e) {
            throw new RpcServerException(ExceptionCode.RPC_SERVICE_INSTANCE_EXCEPTION, e);
        }
        if (StringUtil.isEmpty(method)) {
            throw new RpcServerException(ExceptionCode.RPC_METHOD_NOT_EXIST_EXCEPTION.getKey(),
                    "no method named [" + methodName + "] find for class [" + interfaceName + "].");
        }
        Object result;
        try {
             result = method.invoke(instance, arguments);
        } catch (Exception e) {
            throw new RpcServerException(ExceptionCode.RPC_METHOD_INVOKE_EXCEPTION, e);
        }
        return result;
    }
}
