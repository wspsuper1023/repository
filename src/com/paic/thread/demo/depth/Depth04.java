package com.paic.thread.demo.depth;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Admin
 * @Date: 2021/10/31 11:26
 * @Description: CAS无锁机制
 */
public class Depth04 {

    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger();
        integer.incrementAndGet();
    }

}
