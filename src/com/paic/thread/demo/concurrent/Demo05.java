package com.paic.thread.demo.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Admin
 * @Date: 2021/10/10 20:09
 * @Description:    Java并发包/并发队列课堂例子-05
 *      ConcurrentLinkedDeque： 是一个无界线程安全队列，通过无锁的方式实现高并发状态下的高性能。
 *
 *      ConcurrentLinkedDeque与ArrayBlockingQueue的区别：前者无阻塞，无界；后者刚好相反。
 *      有界是指容量有限，初始化时就会限定容量，一旦限定不可改变。
 */
public class Demo05 {

    public static void main(String[] args) throws InterruptedException {
        //遵循先进先出，后进后出原则，队列不允许null元素
        ConcurrentLinkedDeque<String> concurrentLinkedDeque = new ConcurrentLinkedDeque<>();
        concurrentLinkedDeque.add("张三");    //add和offer都是往队列里添加元素，ConcurrentLinkedDeque中二者没区别
        concurrentLinkedDeque.add("李四");
        concurrentLinkedDeque.add("余胜军");

        //pull与peek都是取元素，区别在于前者会删元素，后者不会
        System.out.println(concurrentLinkedDeque.size());
        System.out.println(concurrentLinkedDeque.poll());   //先拿走的是张三，只剩下李四
        System.out.println(concurrentLinkedDeque.size());
        System.out.println(concurrentLinkedDeque.peek());   //取元素，但不会从队列中删除该元素
        System.out.println(concurrentLinkedDeque.size());


        System.out.println("---------------------------------ArrayBlockingQueue---------------------------------------------------");
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);    //有界队列，界限为3
        arrayBlockingQueue.add("张三");
        arrayBlockingQueue.add("李四");
        arrayBlockingQueue.add("王五");

        //可阻塞队列
        boolean offer = arrayBlockingQueue.offer("陈六", 2, TimeUnit.SECONDS);    //2秒钟以内添加陈六，但是会添加失败，因为队列容量只有3
        System.out.println(arrayBlockingQueue.size());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.size());


        /**
         *  其他的阻塞队列简介：
         *      1、ArrayBlockingQueue：有界，先进先出。
         *      2、LinkedBlockingQueue：先进先出。队列大小配置可选；如果初始化时指定大小，它就有边界，如果不指定，它就没边界；
         *          说是无边界，实则采用默认大小Integer.MAX_VALUE的容量，内部实现是一个链表。
         *      3、PriorityBlockingQueue：无界，允许null元素；
         *          必须实现 java.lang.Comparable接口，队列优先级的排序规则就是按照对这个接口的实现来定义的；
         *          可以从PriorityBlockingQueue获得一个迭代器Iterator，但这个迭代器并不保证按照优先级顺序进行迭代。
         *      4、SynchronousQueue：仅容纳一个元素。当一个线程放入一个元素后就会阻塞，除非该元素被另一个线程消费
         * */
        ArrayBlockingQueue<String> arrays = new ArrayBlockingQueue<String>(3);
        arrays.add("李四");
        arrays.add("张军");
        arrays.add("张军");
        // 添加阻塞队列
        arrays.offer("张三", 1, TimeUnit.SECONDS);

        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<String>(3);
        linkedBlockingQueue.add("张三");
        linkedBlockingQueue.add("李四");
        linkedBlockingQueue.add("李四");
        System.out.println(linkedBlockingQueue.size());
    }
}
