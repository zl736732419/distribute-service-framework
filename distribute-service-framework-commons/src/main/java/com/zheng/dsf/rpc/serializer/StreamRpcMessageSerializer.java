package com.zheng.dsf.rpc.serializer;

import com.zheng.dsf.exceptions.ExceptionCode;
import com.zheng.dsf.exceptions.RpcException;
import com.zheng.dsf.exceptions.RpcServerException;
import com.zheng.dsf.rpc.domain.RpcService;
import com.zheng.dsf.utils.StringUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 基于流的消息序列化
 *
 * @Author zhenglian
 * @Date 2018/12/10
 */
public class StreamRpcMessageSerializer implements RpcMessageSerializer<byte[], RpcService> {
    @Override
    public RpcService deserialize(InputStream input) throws RpcException {
        if (StringUtil.isEmpty(input)) {
            return null;
        }

        RpcService service;
        ObjectInputStream objInput;
        try {
            objInput = new ObjectInputStream(input);
            String serviceSimpleName = objInput.readUTF();
            String methodName = objInput.readUTF();
            Class<?>[] parameterTypes = (Class<?>[]) objInput.readObject();
            Object[] arguments = (Object[]) objInput.readObject();
            service = new RpcService.Builder()
                    .serviceSimpleName(serviceSimpleName)
                    .methodName(methodName)
                    .parameterTypes(parameterTypes)
                    .arguments(arguments)
                    .build();
        } catch (Exception e) {
            throw new RpcException(e);
        }
        return service;
    }

    @Override
    public byte[] serialize(RpcService service) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ObjectOutputStream objOutput = null;
        try {
            objOutput = new ObjectOutputStream(output);
            // 类名
            objOutput.writeUTF(service.getServiceSimpleName());
            // 方法名
            objOutput.writeUTF(service.getMethodName());
            // 参数类型数组
            objOutput.writeObject(service.getParameterTypes());
            // 参数
            objOutput.writeObject(service.getArguments());
            return output.toByteArray();
        } catch (IOException e) {
            throw new RpcException(ExceptionCode.RPC_STREAM_EXCEPTION, e);
        } finally {
            if (StringUtil.isNotEmpty(objOutput)) {
                try {
                    objOutput.close();
                } catch (IOException e) {
                    throw new RpcServerException(ExceptionCode.RPC_STREAM_EXCEPTION, e);
                }
            }
        }
    }


}
