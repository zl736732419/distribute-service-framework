package com.zheng.dsf.rpc.domain;

import java.io.Serializable;

/**
 * 接口实体信息
 * @Author zhenglian
 * @Date 2018/12/11
 */
public class RpcService implements Serializable {
    /**
     * 接口名称
     */
    private String interfaceName;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 方法参数类型
     */
    private Class<?>[] parameterTypes;
    /**
     * 方法参数实例
     */
    private Object[] arguments;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }
    
    public static class Builder {
        private RpcService service;
        
        public Builder() {
            this.service = new RpcService();
        }
        
        public Builder interfaceName(String interfaceName) {
            this.service.setInterfaceName(interfaceName);
            return this;
        }

        public Builder methodName(String methodName) {
            this.service.setMethodName(methodName);
            return this;
        }

        public Builder parameterTypes(Class<?>[] parameterTypes) {
            this.service.setParameterTypes(parameterTypes);
            return this;
        }

        public Builder arguments(Object[] arguments) {
            this.service.setArguments(arguments);
            return this;
        }
    }
}
