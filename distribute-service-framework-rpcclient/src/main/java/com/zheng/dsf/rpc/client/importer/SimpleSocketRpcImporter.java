package com.zheng.dsf.rpc.client.importer;

import com.zheng.dsf.exceptions.ExceptionCode;
import com.zheng.dsf.exceptions.RpcClientException;
import com.zheng.dsf.rpc.domain.HostPort;
import com.zheng.dsf.rpc.domain.RpcService;
import com.zheng.dsf.rpc.serializer.RpcMessageSerializer;
import com.zheng.dsf.rpc.serializer.StreamRpcMessageSerializer;
import com.zheng.dsf.utils.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * rpc简单客户端远程连接请求
 *
 * @Author zhenglian
 * @Date 2018/12/11
 */
public class SimpleSocketRpcImporter implements RpcImporter<Object, RpcService> {
    private RpcMessageSerializer<byte[], RpcService> serializer = new StreamRpcMessageSerializer();
    
    @Override
    public Object importer(RpcService rpcService, HostPort address) {
        if (StringUtil.isEmpty(rpcService) || StringUtil.isEmpty(address)) {
            return null;
        }
        Socket socket = new Socket();
        String host = address.getHost();
        Integer port = address.getPort();
        Integer timeout = getTimeout();
        InputStream input = null;
        OutputStream output = null;
        try {
            socket.connect(new InetSocketAddress(host, port), timeout);
            input = socket.getInputStream();
            output = socket.getOutputStream(); 
            // 发送远程调用请求
            writeRequest(output, rpcService);
            // 获取远程调用结果
            Object result = readResponse(input);
            return result;
        } catch (IOException e) {
            throw new RpcClientException(ExceptionCode.RPC_STREAM_EXCEPTION, e);
        } catch (ClassNotFoundException e) {
            throw new RpcClientException(ExceptionCode.RPC_CLIENT_HANDLE_RESULT_EXCEPTION, e);
        } finally {
            if (StringUtil.isEmpty(input)) {
                try {
                    input.close();
                } catch (IOException e) {
                    throw new RpcClientException(ExceptionCode.RPC_STREAM_EXCEPTION, e);
                }
            }
            if (StringUtil.isEmpty(output)) {
                try {
                    output.close();
                } catch (IOException e) {
                    throw new RpcClientException(ExceptionCode.RPC_STREAM_EXCEPTION, e);
                }
            }
        }
    }

    private Object readResponse(InputStream input) throws IOException, ClassNotFoundException {
        ObjectInputStream objInput = new ObjectInputStream(input);
        Object result = objInput.readObject();
        return result;

    }

    private void writeRequest(OutputStream output, RpcService rpcService) throws IOException {
        byte[] bytes = serializer.serialize(rpcService);
        output.write(bytes);
        output.flush();
    }

    private Integer getTimeout() {
        // TODO 从配置文件获取
        return 10000;
    }
}
