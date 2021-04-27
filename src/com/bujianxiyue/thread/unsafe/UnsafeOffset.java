/**
 * @projectName java-thread
 * @package com.bujianxiyue.thread.unsafe
 * @className com.bujianxiyue.thread.unsafe.UnsafeOffset
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.bujianxiyue.thread.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * UnsafeOffset
 * @description
 * @author duliang-1
 * @date 2021/4/15 8:46
 * @version 1
 */
public class UnsafeOffset {
    private int s;
    private int s2;
    private int value;

    public UnsafeOffset(){
        this(99);
    }

    public UnsafeOffset(int value){
        this.value = value;
    }

    public int value(){
        return value;
    }

    private static Unsafe getUnsafe() throws Exception{
        Class<?> unsafeClass = Unsafe.class;
        for (Field declaredField : unsafeClass.getDeclaredFields()) {
            if ("theUnsafe".equals(declaredField.getName())){
                declaredField.setAccessible(true);
                return (Unsafe) declaredField.get(null);
            }
        }
        throw new IllegalAccessException("no declared field: theUnsafe");
    }

    public static void main(String[] args) throws Exception {

        Unsafe unsafe = UnsafeOffset.getUnsafe();
        Field filed = UnsafeOffset.class.getDeclaredField("value");
        long value = unsafe.objectFieldOffset(filed);
        System.out.println(value);

    }
}
