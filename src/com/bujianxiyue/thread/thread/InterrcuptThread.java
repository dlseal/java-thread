/**
 * @projectName java-thread
 * @package com.bujianxiyue.thread.thread
 * @className com.bujianxiyue.thread.thread.InterrcuptThread
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.bujianxiyue.thread.thread;

/**
 * InterrcuptThread
 * @description
 * @author duliang-1
 * @date 2021/5/13 9:26
 * @version 1.0
 */
public class InterrcuptThread {
    public static void main(String[] args) {
        Thread t = new Thread(() ->{
            while (true){
                Thread.interrupted();
            }
        });

        t.start();
        System.out.println(t.isInterrupted());
        t.interrupt();
        System.out.println(t.isInterrupted());
    }
}
