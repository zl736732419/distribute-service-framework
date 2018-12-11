package com.zheng.dsf.rpc.server.responser;

import com.zheng.dsf.exceptions.ExceptionCode;
import com.zheng.dsf.exceptions.RpcServerException;
import com.zheng.dsf.utils.StringUtil;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * rpc接口调用响应
 * @Author zhenglian
 * @Date 2018/12/11
 */
public class SimpleRpcResponser implements RpcResponser {
    @Override
    public void response(Object result, OutputStream output) throws RpcServerException {
        if (StringUtil.isEmpty(result) || StringUtil.isEmpty(output)) {
            return;
        }
        ObjectOutputStream objOutput;
        try {
            objOutput = new ObjectOutputStream(output);
            objOutput.writeObject(result);
        } catch (IOException e) {
            throw new RpcServerException(ExceptionCode.RPC_METHOD_RESPONSE_EXCEPTION, e);
        }
    }
}
