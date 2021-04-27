/**
 * @projectName java-thread
 * @package com.bujianxiyue.thread.atomic
 * @className com.bujianxiyue.thread.atomic.AtomicLong
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.bujianxiyue.thread.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * AtomicLong
 * @description
 * @author duliang-1
 * @date 2021/4/8 8:42
 * @version 3.0
 */
public class DatomicLong {
    private static Integer[] one = {0,1,2,0,0,2,1,4,1,6};
    private static Integer[] two = {9,4,3,0,10,2,0,0,0,8};
    private static AtomicLong atomicLong = new AtomicLong();
    public static void main(String[] args) throws Exception{
       Thread t1 =  new Thread(()->{
            for (Integer i : one) {
                if (i.intValue() == 0){
                    atomicLong.incrementAndGet();
                }
            }
        });
        Thread t2 = new Thread(()->{
            for (Integer i : two) {
                if (i.intValue() == 0){
                    atomicLong.incrementAndGet();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(atomicLong);
    }

}
