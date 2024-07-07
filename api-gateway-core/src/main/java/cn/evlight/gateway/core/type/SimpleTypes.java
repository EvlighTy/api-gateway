package cn.evlight.gateway.core.type;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 简单类型
 * @Author: evlight
 * @Date: 2024/7/4
 */
public class SimpleTypes {

    private static final Set<String> SIMPLE_TYPES = new HashSet<>();

    static {
        SIMPLE_TYPES.add(String.class.getName());
        SIMPLE_TYPES.add(Byte.class.getName());
        SIMPLE_TYPES.add(Short.class.getName());
        SIMPLE_TYPES.add(Character.class.getName());
        SIMPLE_TYPES.add(Integer.class.getName());
        SIMPLE_TYPES.add(Long.class.getName());
        SIMPLE_TYPES.add(Float.class.getName());
        SIMPLE_TYPES.add(Double.class.getName());
        SIMPLE_TYPES.add(Boolean.class.getName());
        SIMPLE_TYPES.add(Date.class.getName());
        SIMPLE_TYPES.add(Class.class.getName());
        SIMPLE_TYPES.add(BigInteger.class.getName());
        SIMPLE_TYPES.add(BigDecimal.class.getName());
    }

    public static boolean isSimpleType(String clazzName){
        return SIMPLE_TYPES.contains(clazzName);
    }

}
