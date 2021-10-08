package com.paic.thread.demo.concurrent;


import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Admin
 * @Date: 2021/10/8 21:51
 * @Description: Java并发包/并发队列课堂例子-01
 *  1、Vector线程安全，ArrayList线程不安全，但是效率高；
 *      Vector集合的add方法加了synchronized锁，ArrayList没加锁；
 *  2、hashTable与hashMap的区别？
 *      hashTable线程安全，hashMap不安全，看put源码
 */
public class Demo01 {
    public static void main(String[] args) {
        Vector vector = new Vector();
        vector.add("");

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("", "");

        //Collections.synchronizedCollection(hashMap);

        Map<String, Object> map = new ConcurrentHashMap<>();    //分段锁底层原理
    }
}
