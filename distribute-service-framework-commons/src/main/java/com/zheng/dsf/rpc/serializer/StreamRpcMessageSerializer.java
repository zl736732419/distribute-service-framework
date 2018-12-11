package com.zheng.dsf.rpc.serializer;

import com.zheng.dsf.exceptions.RpcException;
import com.zheng.dsf.rpc.domain.RpcService;
import com.zheng.dsf.utils.StringUtil;

import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * 基于流的消息序列化
 *
 * @Author zhenglian
 * @Date 2018/12/10
 */
public class StreamRpcMessageSerializer implements RpcMessageSerializer<RpcService>{
    @Override
    public RpcService deserialize(InputStream input) throws RpcException {
        if (StringUtil.isEmpty(input)) {
            return null;
        }

        RpcService service;
        ObjectInputStream objInput;
        try {
             objInput = new ObjectInputStream(input);
            String interfaceName = objInput.readUTF();
            String methodName = objInput.readUTF();
            Class<?>[] parameterTypes = (Class<?>[]) objInput.readObject();
            Object[] arguments = (Object[]) objInput.readObject();
            service = new RpcService.Builder()
                    .interfaceName(interfaceName)
                    .methodName(methodName)
                    .parameterTypes(parameterTypes)
                    .arguments(arguments)
                    .build();
        } catch (Exception e) {
            throw new RpcException(e);
        }
        return service;
    }
}
