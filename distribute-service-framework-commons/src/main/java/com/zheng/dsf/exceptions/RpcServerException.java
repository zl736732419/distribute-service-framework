package com.zheng.dsf.exceptions;

/**
 * rpc服务器异常信息
 * @Author zhenglian
 * @Date 2018/12/10
 */
public class RpcServerException extends RpcException {
    public RpcServerException(Throwable e) {
        super(e);
    }
    
    public RpcServerException(Integer code, String message) {
        super(code, message);
    }

    public RpcServerException(Integer code, String message, Throwable e) {
        super(code, message, e);
    }

    public RpcServerException(ExceptionCode code, Throwable e) {
        super(code, e);
    }

    public RpcServerException(String message, Throwable e) {
        super(message, e);
    }
}
