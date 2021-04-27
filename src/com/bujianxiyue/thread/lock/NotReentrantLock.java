/**
 * @projectName java-thread
 * @package com.bujianxiyue.thread.lock
 * @className com.bujianxiyue.thread.lock.NotReentrantLock
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.bujianxiyue.thread.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * NotReentrantLock
 * @description 不可重入锁实现
 * @author duliang-1
 * @date 2021/4/14 9:08
 * @version neimenggu_demo
 */
public class NotReentrantLock {

    private volatile AtomicReference owner = new AtomicReference<Thread>();

    public void lock(){
        Thread thread = Thread.currentThread();
        for (; ; ) {
            if (!owner.compareAndSet(null,thread)){
                return;
            }
        }
    }

    public void unlock(){
        Thread thread = Thread.currentThread();
        owner.compareAndSet(thread,null);
    }
}
