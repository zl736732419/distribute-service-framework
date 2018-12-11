package com.zheng.dsf.rpc.server.exporter.task;

import com.zheng.dsf.exceptions.ExceptionCode;
import com.zheng.dsf.exceptions.RpcServerException;
import com.zheng.dsf.utils.StringUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 服务发布任务
 *
 * @Author zhenglian
 * @Date 2018/12/10
 */
public class ExporterTask implements Runnable {
    private Socket socket;

    public ExporterTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream input = null;
        ObjectOutputStream output = null;

        try {
            input = new ObjectInputStream(socket.getInputStream());
            // TODO parse client request
        } catch (Exception e) {
            throw new RpcServerException(ExceptionCode.RPC_EXPORTER_HANDLE_EXCEPTION, e);
        } finally {
            try {
                if (StringUtil.isNotEmpty(input)) {
                    input.close();
                }
                if (StringUtil.isNotEmpty(output)) {
                    output.close();
                }
            } catch (IOException e) {
                throw new RpcServerException(ExceptionCode.RPC_STREAM_CLOSE_EXCEPTION, e);
            }
        }
    }
}
