/**
 * @projectName java-thread
 * @package com.bujianxiyue.thread.wait
 * @className com.bujianxiyue.thread.wait.ObjectWait
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.bujianxiyue.thread.wait;

/**
 * ObjectWait
 * @description
 * @author duliang-1
 * @date 2021/4/1 14:53
 * @version 3.0
 */
public class ObjectWait {
    private static volatile Object object = new Object();
    public static void main(String[] args) throws InterruptedException {
//        ObjectWait ow = new ObjectWait();
//        ow.run();
        String file = "/DZJZ/上诉状.pdf";
        String res = file.replaceAll("^(/+)", "").replaceAll("^(\\+)","");
        System.out.println(res);
    }

    public void run() throws InterruptedException {
        synchronized (object) {
            object.wait(0);
        }
    }
}
