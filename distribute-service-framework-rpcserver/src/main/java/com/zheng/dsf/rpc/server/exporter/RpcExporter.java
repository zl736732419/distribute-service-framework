package com.zheng.dsf.rpc.server.exporter;

import com.zheng.dsf.exceptions.RpcServerException;

/**
 * 服务发布者接口
 * @Author zhenglian
 * @Date 2018/12/10
 */
public interface RpcExporter {
    void export(String host, Integer port) throws RpcServerException;
}
