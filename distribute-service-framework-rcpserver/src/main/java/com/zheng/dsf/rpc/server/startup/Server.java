package com.zheng.dsf.rpc.server.startup;

import com.zheng.dsf.rpc.server.exporter.SimpleSocketRpcExporter;

/**
 * @Author zhenglian
 * @Date 2018/12/10
 */
public class Server {
    public static void main(String[] args) {
        new SimpleSocketRpcExporter().export("localhost", 8989);
    }
}
