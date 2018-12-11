package com.zheng.dsf.exceptions;

/**
 * rpc服务器异常信息
 * @Author zhenglian
 * @Date 2018/12/10
 */
public class RpcServerException extends RuntimeException {
    private Integer code;
    private String message;
    
    public RpcServerException(Throwable e) {
        super(e);
    }
    
    public RpcServerException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public RpcServerException(Integer code, String message, Throwable e) {
        super(message, e);
        this.code = code;
        this.message = message;
    }

    public RpcServerException(ExceptionCode code, Throwable e) {
        super(code.getValue(), e);
        this.code = code.getKey();
        this.message = code.getValue();
    }
}
