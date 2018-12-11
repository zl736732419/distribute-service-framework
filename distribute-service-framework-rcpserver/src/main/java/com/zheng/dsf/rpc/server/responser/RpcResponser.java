package com.zheng.dsf.rpc.server.responser;

import com.zheng.dsf.exceptions.RpcServerException;

import java.io.OutputStream;

/**
 * rpc接口调用响应
 * @Author zhenglian
 * @Date 2018/12/11
 */
public interface RpcResponser {
    void response(Object result, OutputStream output) throws RpcServerException;
}
