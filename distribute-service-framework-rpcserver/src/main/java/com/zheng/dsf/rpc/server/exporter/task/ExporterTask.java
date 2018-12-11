package com.zheng.dsf.rpc.server.exporter.task;

import com.zheng.dsf.exceptions.ExceptionCode;
import com.zheng.dsf.exceptions.RpcException;
import com.zheng.dsf.exceptions.RpcServerException;
import com.zheng.dsf.rpc.domain.RpcService;
import com.zheng.dsf.rpc.serializer.RpcMessageSerializer;
import com.zheng.dsf.rpc.serializer.StreamRpcMessageSerializer;
import com.zheng.dsf.rpc.server.invoker.RpcInvoker;
import com.zheng.dsf.rpc.server.invoker.SimpleRpcInvoker;
import com.zheng.dsf.rpc.server.responser.RpcResponser;
import com.zheng.dsf.rpc.server.responser.SimpleRpcResponser;
import com.zheng.dsf.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 服务发布任务
 *
 * @Author zhenglian
 * @Date 2018/12/10
 */
public class ExporterTask implements Runnable {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private RpcMessageSerializer<RpcService> serializer = new StreamRpcMessageSerializer();
    private RpcInvoker invoker = new SimpleRpcInvoker();
    private RpcResponser responser = new SimpleRpcResponser();
    private Socket socket;
    
    public ExporterTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        log.info("handle client [{}] request...", socket);
        if (StringUtil.isEmpty(socket)) {
            log.error("client socket is null, handle ignore.");
            return;
        }
        InputStream input = null;
        OutputStream output = null;
        try {
            input = socket.getInputStream();
            output = socket.getOutputStream();
            // 1.获取客户端调用接口信息
            RpcService service = serializer.deserialize(input);
            if (StringUtil.isEmpty(service)) {
                throw new RpcServerException(ExceptionCode.RPC_MESSAGE_SERIALIZE_EXCEPTION.getKey(),
                        ExceptionCode.RPC_MESSAGE_SERIALIZE_EXCEPTION.getValue());
            }
            // 2.调用指定服务接口，获取返回结果
            Object result = invoker.invoke(service);
            // 3.响应到客户端
            responser.response(result, output);
        } catch (Exception e) {
            throw new RpcServerException(ExceptionCode.RPC_EXPORTER_HANDLE_EXCEPTION, e);
        } finally {
            if (StringUtil.isNotEmpty(input)) {
                try {
                    input.close();
                } catch (IOException e) {
                    throw new RpcException(e);
                }
            }
            if (StringUtil.isNotEmpty(output)) {
                try {
                    output.close();
                } catch (IOException e) {
                    throw new RpcException(e);
                }
            }
        }
    }
}
