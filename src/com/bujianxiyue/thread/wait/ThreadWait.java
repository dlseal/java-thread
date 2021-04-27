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
 * @date 2021/4/1 9:21
 * @version 3.0
 */
public class ThreadWait {
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new Runnable1(),"t1");
        Thread t2 = new Thread(new Runnable2(),"t2");
        t1.start();
        t2.start();
        while (true) {

        }

    }
    static class Runnable1 implements Runnable {

        @Override
        public void run() {
            synchronized (resourceA){
                System.out.println("runnable1 get resourceA");
                System.out.println("runnable1 try to get resourceB");
                synchronized (resourceB){
                    System.out.println("runnable1 get resourceB");
                    System.out.println("runnable1 try to get this");
                    synchronized (this) {
                        try {
                            this.wait();
                            System.out.println("runable1 waiting");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

        }
    }

    static class Runnable2 implements Runnable {

        @Override
        public void run() {
            synchronized (resourceA){
                System.out.println("runnable2 get resourceA");
                System.out.println("runnable2 try to get resourceB");
                synchronized (resourceB){
                    System.out.println("runnable2 get resourceB");
                    System.out.println("runnable2 try to get this");
                    synchronized (this) {
                        try {
                            this.wait();
                            System.out.println("runable2 waiting");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }

}
