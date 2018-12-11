package com.zheng.dsf.utils;

/**
 * @Author zhenglian
 * @Date 2018/12/10
 */
public class RuntimeUtil {
    /**
     * 获取当前cpu核数
     * @return
     */
    public static final Integer getProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }
}
