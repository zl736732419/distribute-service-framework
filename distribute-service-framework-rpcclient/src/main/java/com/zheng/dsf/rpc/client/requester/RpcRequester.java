package com.zheng.dsf.rpc.client.requester;

import com.zheng.dsf.rpc.domain.HostPort;

/**
 * rpc客户端远程连接请求
 *
 * @Author zhenglian
 * @Date 2018/12/11
 */
public interface RpcRequester<R, T> {
    R request(T rpcService, HostPort address);
}
