/**
 * @projectName java-thread
 * @package com.bujianxiyue.thread.wait
 * @className com.bujianxiyue.thread.wait.ThreadWait
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.bujianxiyue.thread.wait;

/**
 * ThreadWait
 * @description
 * @author duliang-1
 * @date 2021/4/1 8:48
 * @version 3.0
 */
public class LockWait {
    private static  volatile Object resourceA = new Object();
    private static  volatile Object resourceB = new Object();

    public static void main(String[] args) throws Exception{
        Thread threadA = new Thread(() -> {
            try {
                synchronized (resourceA){
                    System.out.println("threadA get resourceA");
                    System.out.println("threadA try to get resourceB");
                    synchronized (resourceB){
                        System.out.println("theadA get resourceB");
                        resourceA.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                Thread.sleep(1000);
                synchronized (resourceA){
                    System.out.println("threadB get resourceA");
                    System.out.println("threadB try to get resourceB");
                    synchronized (resourceB){
                        System.out.println("theadB get resourceB");
                        resourceA.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }
}
