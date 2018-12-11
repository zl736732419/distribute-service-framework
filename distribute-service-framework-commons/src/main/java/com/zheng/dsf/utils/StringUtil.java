package com.zheng.dsf.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 通用工具类
 * @Author zhenglian
 * @Date 2018/12/10
 */
public class StringUtil {
    /**
     * 判断给定对象是否为空
     * @param obj 任意类型对象
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        }
        // 字符串
        if (obj instanceof CharSequence) {
            CharSequence string = (CharSequence) obj;
            return string.length() == 0;
        }
        // 集合
        if (obj instanceof Collection) {
            Collection collection = (Collection) obj;
            return collection.isEmpty();
        }
        // map
        if (obj instanceof Map) {
            Map map = (Map) obj;
            return map.isEmpty();
        }
        // 数组
        if (obj instanceof Object[]) {
            Object[] arr = (Object[]) obj;
            if (arr.length == 0) {
                return true;
            } else {
                for (Object item : arr) {
                    if (isNotEmpty(item)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
}
