/**
 * @projectName java-thread
 * @package com.bujianxiyue.thread.array
 * @className com.bujianxiyue.thread.array.ArrayCopy
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.bujianxiyue.thread.array;

/**
 * ArrayCopy
 * @description
 * @author duliang-1
 * @date 2021/4/21 9:07
 * @version neimenggu_demo
 */
public class ArrayCopy {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        int [] a = new int[5];
        System.arraycopy(arr,0,a,0,arr.length);
        for (int i : arr) {
            System.out.println(i);
        }
        System.out.println("1111111");
        System.out.println(a[4]);
    }
}
