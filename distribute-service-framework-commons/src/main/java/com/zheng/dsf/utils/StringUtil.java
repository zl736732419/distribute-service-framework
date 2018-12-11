package com.zheng.dsf.utils;

import com.zheng.dsf.Constants;

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

    /**
     * 判断对象是否不为空
     * @param obj
     * @return  [obj1, obj2,...]
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
    
    public static String toString(Object obj) {
        if (isEmpty(obj)) {
            return Constants.EMPTY_STRING;
        }
        StringBuilder builder = new StringBuilder();
        // 数组
        if (obj instanceof Object[]) {
            Object[] arr = (Object[]) obj;
            if (arr.length == 0) {
                return Constants.EMPTY_MIDDLE_BRACKET;
            } else {
                builder.append(Constants.LEFT_BRACKET).append(toString(arr[0]));
                if (arr.length > 1) {
                    for (int i = 1; i < arr.length; i++) {
                        builder.append(Constants.COMMA).append(toString(arr[i]));
                    }
                }
                builder.append(Constants.RIGHT_BRACKET);
                return builder.toString();
            }
        }
        // 其他类型直接返回toString结果
        return obj.toString();
    }
    
}
