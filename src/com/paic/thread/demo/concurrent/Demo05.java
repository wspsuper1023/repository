package com.paic.thread.demo.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Admin
 * @Date: 2021/10/10 20:09
 * @Description:    Java������/�������п�������-05
 *      ConcurrentLinkedDeque�� ��һ���޽��̰߳�ȫ���У�ͨ�������ķ�ʽʵ�ָ߲���״̬�µĸ����ܡ�
 *
 *      ConcurrentLinkedDeque��ArrayBlockingQueue������ǰ�����������޽磻���߸պ��෴��
 *      �н���ָ�������ޣ���ʼ��ʱ�ͻ��޶�������һ���޶����ɸı䡣
 */
public class Demo05 {

    public static void main(String[] args) throws InterruptedException {
        //��ѭ�Ƚ��ȳ���������ԭ�򣬶��в�����nullԪ��
        ConcurrentLinkedDeque<String> concurrentLinkedDeque = new ConcurrentLinkedDeque<>();
        concurrentLinkedDeque.add("����");    //add��offer���������������Ԫ�أ�ConcurrentLinkedDeque�ж���û����
        concurrentLinkedDeque.add("����");
        concurrentLinkedDeque.add("��ʤ��");

        //pull��peek����ȡԪ�أ���������ǰ�߻�ɾԪ�أ����߲���
        System.out.println(concurrentLinkedDeque.size());
        System.out.println(concurrentLinkedDeque.poll());   //�����ߵ���������ֻʣ������
        System.out.println(concurrentLinkedDeque.size());
        System.out.println(concurrentLinkedDeque.peek());   //ȡԪ�أ�������Ӷ�����ɾ����Ԫ��
        System.out.println(concurrentLinkedDeque.size());


        System.out.println("---------------------------------ArrayBlockingQueue---------------------------------------------------");
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);    //�н���У�����Ϊ3
        arrayBlockingQueue.add("����");
        arrayBlockingQueue.add("����");
        arrayBlockingQueue.add("����");

        //����������
        boolean offer = arrayBlockingQueue.offer("����", 2, TimeUnit.SECONDS);    //2����������ӳ��������ǻ����ʧ�ܣ���Ϊ��������ֻ��3
        System.out.println(arrayBlockingQueue.size());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.size());


        /**
         *  �������������м�飺
         *      1��ArrayBlockingQueue���н磬�Ƚ��ȳ���
         *      2��LinkedBlockingQueue���Ƚ��ȳ������д�С���ÿ�ѡ�������ʼ��ʱָ����С�������б߽磬�����ָ��������û�߽磻
         *          ˵���ޱ߽磬ʵ�����Ĭ�ϴ�СInteger.MAX_VALUE���������ڲ�ʵ����һ������
         *      3��PriorityBlockingQueue���޽磬����nullԪ�أ�
         *          ����ʵ�� java.lang.Comparable�ӿڣ��������ȼ������������ǰ��ն�����ӿڵ�ʵ��������ģ�
         *          ���Դ�PriorityBlockingQueue���һ��������Iterator�������������������֤�������ȼ�˳����е�����
         *      4��SynchronousQueue��������һ��Ԫ�ء���һ���̷߳���һ��Ԫ�غ�ͻ����������Ǹ�Ԫ�ر���һ���߳�����
         * */
        ArrayBlockingQueue<String> arrays = new ArrayBlockingQueue<String>(3);
        arrays.add("����");
        arrays.add("�ž�");
        arrays.add("�ž�");
        // �����������
        arrays.offer("����", 1, TimeUnit.SECONDS);

        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<String>(3);
        linkedBlockingQueue.add("����");
        linkedBlockingQueue.add("����");
        linkedBlockingQueue.add("����");
        System.out.println(linkedBlockingQueue.size());
    }
}
