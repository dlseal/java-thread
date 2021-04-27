/**
 * @projectName java-thread
 * @package com.bujianxiyue.thread.aqs
 * @className com.bujianxiyue.thread.aqs.CustomLockProductAndConsumer
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.bujianxiyue.thread.aqs;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * CustomLockProductAndConsumer
 * @description
 * @author duliang-1
 * @date 2021/4/26 8:26
 * @version neimenggu_demo
 */
public class CustomLockProductAndConsumer {

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition notFull = lock.newCondition();
    private static final Condition notEmpty = lock.newCondition();
    private static final LinkedBlockingQueue queue = new LinkedBlockingQueue();
    private static final int POOL_SIZE = 10;

    public static void main(String[] args) {
        Thread pro = new Thread(() -> {
            lock.lock();
            try {
                while (POOL_SIZE == queue.size()) {
                    notEmpty.await();
                }
                queue.add("ele");
                System.out.println("add ele");
                notFull.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread con = new Thread(() -> {
            lock.lock();
            try {
                while (0 == queue.size()) {
                    notFull.await();
                }
                queue.poll();
                System.out.println("use ele");
                notEmpty.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        pro.start();
        con.start();
    }
}

