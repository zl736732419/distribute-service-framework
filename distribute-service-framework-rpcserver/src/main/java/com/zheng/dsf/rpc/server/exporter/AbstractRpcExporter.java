package com.zheng.dsf.rpc.server.exporter;

import com.zheng.dsf.exceptions.ExceptionCode;
import com.zheng.dsf.exceptions.RpcServerException;
import com.zheng.dsf.utils.RuntimeUtil;
import com.zheng.dsf.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author zhenglian
 * @Date 2018/12/10
 */
public abstract class AbstractRpcExporter implements RpcExporter {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * cpu核数
     */
    protected static final Integer NCPUS = RuntimeUtil.getProcessors();

    @Override
    public void export(String host, Integer port) throws RpcServerException {
        if (StringUtil.isEmpty(host)
                || StringUtil.isEmpty(port)) {
            throw new RpcServerException(ExceptionCode.RPC_SERVER_PARAMS_INVALID.getKey(), 
                    "invalid params to start rpc server, host: [" + host + "], port: [" + port + "]");
        }
        doExporter(host, port);
    }

    protected abstract void doExporter(String host, Integer port) throws RpcServerException;
}
