package com.paic.thread.security;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Admin
 * @Date: 2020/12/14 23:01
 * @Description:    AtomicInteger��֤����ԭ���ԣ��̰߳�ȫ
 */
public class VolatileNoAtomic extends Thread {
    //��Ҫ10���߳�ͬʱ���� static���εĵı�������ھ�̬����ֻ���һ�Σ������̶߳�����
    //private volatile static int count = 0;
    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            count.incrementAndGet();
        }
        System.out.println(getName() + "," + count.get());
    }

    public static void main(String[] args) {
        //����10���߳�
        VolatileNoAtomic[] volatileNoAtomicList = new VolatileNoAtomic[10];
        for (int i = 0; i < volatileNoAtomicList.length; i++) {
            volatileNoAtomicList[i] = new VolatileNoAtomic();
        }

        for (int i = 0; i < volatileNoAtomicList.length; i++) {
            volatileNoAtomicList[i].start();
        }
    }
}
