package com.zheng.dsf.rpc.server.service.impl;

import com.zheng.dsf.utils.StringUtil;
import com.zheng.dsf.rpc.services.EchoService;

/**
 * @Author zhenglian
 * @Date 2018/12/10
 */
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String string) {
        return StringUtil.isEmpty(string) ? "I'm OK." : string + " --> I'am Ok";
    }
}
