package com.zheng.dsf.rpc.server;

import com.zheng.dsf.rpc.domain.HostPort;
import com.zheng.dsf.rpc.server.exporter.SimpleSocketRpcExporter;

/**
 * rpc服务启动类
 * @Author zhenglian
 * @Date 2018/12/10
 */
public class ServerStartup {
    public static void main(String[] args) {
        HostPort address = new HostPort.Builder().build();
        new SimpleSocketRpcExporter().export(address);
    }
}
