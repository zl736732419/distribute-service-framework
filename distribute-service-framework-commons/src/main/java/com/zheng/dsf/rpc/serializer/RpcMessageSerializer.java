package com.zheng.dsf.rpc.serializer;

import com.zheng.dsf.exceptions.RpcException;

import java.io.InputStream;

/**
 * 消息序列化
 * @Author zhenglian
 * @Date 2018/12/10
 */
public interface RpcMessageSerializer<R, T> {
    /**
     * 反序列化给定对象
     * @param input
     * @return
     * @throws RpcException
     */
    T deserialize(InputStream input) throws RpcException;

    /**
     * 将对象序列化成字节数组
     * @param t
     * @return
     */
    R serialize(T t);
}
