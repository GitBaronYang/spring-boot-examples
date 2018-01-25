package com.yang.basicmodel.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/** 
* @author yf 获取获取泛型参数的类型
* @E-mail: 
* @date 创建时间：2017年12月5日 下午1:57:36 
* @version 1.0   
*/
public class GenericsUtils {
    public static Class getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    public static Class getSuperClassGenricType(Class clazz, int index) throws IndexOutOfBoundsException {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }
}
