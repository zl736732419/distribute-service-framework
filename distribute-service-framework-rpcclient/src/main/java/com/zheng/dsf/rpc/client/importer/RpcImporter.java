package com.zheng.dsf.rpc.client.importer;

import com.zheng.dsf.rpc.domain.HostPort;

/**
 * rpc客户端远程连接请求
 *
 * @Author zhenglian
 * @Date 2018/12/11
 */
public interface RpcImporter<R, T> {
    R importer(T rpcService, HostPort address);
}
