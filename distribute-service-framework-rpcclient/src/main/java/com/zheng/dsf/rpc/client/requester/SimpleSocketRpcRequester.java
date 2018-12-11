package com.zheng.dsf.rpc.client.requester;

import com.zheng.dsf.exceptions.ExceptionCode;
import com.zheng.dsf.exceptions.RpcClientException;
import com.zheng.dsf.rpc.domain.HostPort;
import com.zheng.dsf.rpc.domain.RpcService;
import com.zheng.dsf.rpc.serializer.RpcMessageSerializer;
import com.zheng.dsf.rpc.serializer.StreamRpcMessageSerializer;
import com.zheng.dsf.utils.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * rpc简单客户端远程连接请求
 *
 * @Author zhenglian
 * @Date 2018/12/11
 */
public class SimpleSocketRpcRequester implements RpcRequester<Object, RpcService> {
    private RpcMessageSerializer<byte[], RpcService> serializer = new StreamRpcMessageSerializer();
    
    
    @Override
    public Object request(RpcService rpcService, HostPort address) {
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
            byte[] bytes = serializer.serialize(rpcService);
            output.write(bytes);
            output.flush();
            
        } catch (IOException e) {
            throw new RpcClientException(ExceptionCode.RPC_STREAM_EXCEPTION, e);
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

        return null;
    }

    private Integer getTimeout() {
        // TODO 从配置文件获取
        return 10000;
    }
}
