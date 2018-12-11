package com.zheng.dsf.exceptions;

import com.zheng.dsf.utils.StringUtil;

import java.util.Objects;

/**
 * 异常码
 * @Author zhenglian
 * @Date 2018/12/10
 */
public enum ExceptionCode {
    // rpc server exception code
    RPC_SERVER_PARAMS_INVALID(1000, "rpc服务器参数错误"),
    RPC_STREAM_EXCEPTION(1001, "rpc数据流异常"),
    RPC_EXPORTER_HANDLE_EXCEPTION(1002, "rpc服务发布者处理异常"),
    RPC_MESSAGE_SERIALIZE_EXCEPTION(1003, "rpc序列化消息异常"),
    RPC_METHOD_NOT_EXIST_EXCEPTION(1004, "rpc服务端找不到指定方法"),
    RPC_METHOD_INVOKE_EXCEPTION(1005, "rpc服务端方法调用失败"),
    RPC_METHOD_RESPONSE_EXCEPTION(1006, "rpc响应结果失败"),
    RPC_CONFIG_RESOURCE_LOAD_EXCEPTION(1007, "rpc配置文件加载异常"),
    RPC_SERVICE_NOT_FOUND_EXCEPTION(1008, "找不到服务接口异常"),
    RPC_SERVICE_INSTANCE_EXCEPTION(1009, "服务接口实例化异常"),
    
    // rpc client exception code
    RPC_CLIENT_HANDLE_RESULT_EXCEPTION(2000, "rpc客户端处理结果失败")
    
    ;
    
    private Integer key;
    private String value;
    
    ExceptionCode(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static ExceptionCode findByKey(Integer key) {
        if (StringUtil.isEmpty(key)) {
            return null;
        }
        for (ExceptionCode code : ExceptionCode.values()) {
            if (Objects.equals(code.getKey(), key)) {
                return code;
            }
        }
        return null;
    }
    
}
