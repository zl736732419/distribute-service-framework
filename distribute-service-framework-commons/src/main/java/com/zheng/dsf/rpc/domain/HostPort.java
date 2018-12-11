package com.zheng.dsf.rpc.domain;

import com.zheng.dsf.utils.StringUtil;

/**
 * rpc服务地址
 * @Author zhenglian
 * @Date 2018/12/11
 */
public class HostPort {
    private static final String DEFAULT_HOST = "localhost";
    private static final Integer DEFAULT_PORT = 8989;
    /**
     * 主机名
     */
    private String host = DEFAULT_HOST;
    /**
     * 端口号
     */
    private Integer port = DEFAULT_PORT;

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public static class Builder {
        private HostPort address;
        
        public Builder() {
            address = new HostPort();
        }
        
        public Builder host(String host) {
            if (StringUtil.isNotEmpty(host)) {
                address.host = host;
            }
            return this;
        }

        public Builder port(Integer port) {
            if (StringUtil.isNotEmpty(port)) {
                address.port = port;
            }
            return this;
        }
        
        public HostPort build() {
            return address;
        }
    }

    @Override
    public String toString() {
        return "HostPort{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
