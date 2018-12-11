package com.zheng.dsf.exceptions;

import com.zheng.dsf.utils.StringUtil;

import java.util.Objects;

/**
 * 异常码
 * @Author zhenglian
 * @Date 2018/12/10
 */
public enum ExceptionCode {
    RPC_SERVER_PARAMS_INVALID(1000, "rpc服务器参数错误"),
    RPC_STREAM_CLOSE_EXCEPTION(1001, "rpc数据流关闭异常"),
    RPC_EXPORTER_HANDLE_EXCEPTION(1002, "rpc服务发布者处理异常")
    ;
    
    private Integer key;
    private String value;
    
    ExceptionCode(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static ExceptionCode findByKey(Integer key) {
        if (StringUtil.isEmpty(key)) {
            return null;
        }
        for (ExceptionCode code : ExceptionCode.values()) {
            if (Objects.equals(code.getKey(), key)) {
                return code;
            }
        }
        return null;
    }
    
}
