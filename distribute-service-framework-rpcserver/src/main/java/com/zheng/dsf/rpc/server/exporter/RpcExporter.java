package com.zheng.dsf.rpc.server.exporter;

import com.zheng.dsf.exceptions.RpcServerException;
import com.zheng.dsf.rpc.domain.HostPort;

/**
 * 服务发布者接口
 * @Author zhenglian
 * @Date 2018/12/10
 */
public interface RpcExporter {
    void export(HostPort address) throws RpcServerException;
}
