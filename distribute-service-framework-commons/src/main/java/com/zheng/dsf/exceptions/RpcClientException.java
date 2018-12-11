package com.zheng.dsf.exceptions;

/**
 * rpc客户端异常信息
 * @Author zhenglian
 * @Date 2018/12/10
 */
public class RpcClientException extends RpcException {
    public RpcClientException(Throwable e) {
        super(e);
    }

    public RpcClientException(Integer code, String message) {
        super(code, message);
    }

    public RpcClientException(Integer code, String message, Throwable e) {
        super(code, message, e);
    }

    public RpcClientException(ExceptionCode code, Throwable e) {
        super(code, e);
    }

    public RpcClientException(String message, Throwable e) {
        super(message, e);
    }
}
