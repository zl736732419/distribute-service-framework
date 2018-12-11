package com.zheng.dsf.exceptions;

/**
 * rpc异常信息
 * @Author zhenglian
 * @Date 2018/12/10
 */
public class RpcException extends RuntimeException {
    private Integer code;
    private String message;

    public RpcException(Throwable e) {
        super(e);
    }

    public RpcException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    public RpcException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public RpcException(Integer code, String message, Throwable e) {
        super(message, e);
        this.code = code;
        this.message = message;
    }

    public RpcException(ExceptionCode code, Throwable e) {
        super(code.getValue(), e);
        this.code = code.getKey();
        this.message = code.getValue();
    }
}
