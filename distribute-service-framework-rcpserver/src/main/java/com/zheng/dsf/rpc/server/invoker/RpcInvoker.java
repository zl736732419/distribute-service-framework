package com.zheng.dsf.rpc.server.invoker;

import com.zheng.dsf.exceptions.RpcServerException;
import com.zheng.dsf.rpc.domain.RpcService;

/**
 * rpc服务调用接口
 * @Author zhenglian
 * @Date 2018/12/11
 */
public interface RpcInvoker<T> {
    T invoke(RpcService rpcService) throws RpcServerException;
}
