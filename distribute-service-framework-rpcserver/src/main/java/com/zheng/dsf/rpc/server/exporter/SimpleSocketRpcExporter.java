package com.zheng.dsf.rpc.server.exporter;

import com.zheng.dsf.exceptions.RpcServerException;
import com.zheng.dsf.rpc.server.exporter.task.ExporterTask;
import com.zheng.dsf.utils.StringUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用传统socket编程实现的服务发布者
 * @Author zhenglian
 * @Date 2018/12/10
 */
public class SimpleSocketRpcExporter extends AbstractRpcExporter {

    private ExecutorService executor = Executors.newFixedThreadPool(NCPUS);
    
    @Override
    protected void doExporter(String host, Integer port) throws RpcServerException {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket();
            ss.bind(new InetSocketAddress(host, port));
            Socket socket;
            while (true) {
                // 等待客户端连接
                log.info("server started. waiting for client request...");
                socket = ss.accept();
                executor.execute(new ExporterTask(socket));
            }
        } catch (IOException e) {
            throw new RpcServerException(e);
        } finally {
            if (StringUtil.isNotEmpty(ss)) {
                try {
                    ss.close();
                } catch (IOException e) {
                    throw new RpcServerException(e);
                }
            }
        }
        
    }
}
