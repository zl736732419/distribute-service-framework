package com.zheng.dsf.rpc.server.exporter;

import com.zheng.dsf.exceptions.ExceptionCode;
import com.zheng.dsf.exceptions.RpcServerException;
import com.zheng.dsf.rpc.domain.HostPort;
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
    public void export(HostPort address) throws RpcServerException {
        if (StringUtil.isEmpty(address)) {
            throw new RpcServerException(ExceptionCode.RPC_SERVER_PARAMS_INVALID.getKey(), 
                    "invalid params to start rpc server, address: [" + address + "]");
        }
        doExporter(address);
    }

    protected abstract void doExporter(HostPort address) throws RpcServerException;
}
