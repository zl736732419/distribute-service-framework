package com.zheng.dsf.rpc.client.service;

import com.zheng.dsf.rpc.client.proxy.RpcServiceProxy;
import com.zheng.dsf.rpc.domain.HostPort;
import com.zheng.dsf.rpc.services.EchoService;
import org.junit.Test;

/**
 * @Author zhenglian
 * @Date 2018/12/11
 */
public class EchoServiceTest {
    @Test
    public void echo() {
        HostPort address = new HostPort.Builder().build();
        EchoService echoService = new RpcServiceProxy<EchoService>().getService(EchoService.class, address);
        String result = echoService.echo("what's your name?");
        System.out.println(result);
    }
}
