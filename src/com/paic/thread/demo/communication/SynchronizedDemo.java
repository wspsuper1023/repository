package com.paic.thread.demo.communication;

import lombok.Data;

/**
 * @Author: Admin
 * @Date: 2021/10/7 18:43
 * @Description: 线程间通讯课堂例子01
 *
 * 什么是多线程间通讯？
 * 多个线程操作同一个资源，但是每个线程动作不一样
 *
 * sleep与wait区别？
 * 	相同：都是做休眠，只能在synchronized中使用，不能在lock锁使用，最终都是调用jvm级的native方法
 * 	（1）sleep属于thread，wait属于object
 * 	（2）sleep不会释放锁资源，wait会释放锁资源
 * 	（3）sleep到了休眠时间自动运行；wait需要notify或notifyAll唤醒
 */
public class SynchronizedDemo {

    public static void main(String[] args) {
        Dto dto = new Dto();
        Input input = new Input(dto);
        Out out = new Out(dto);
        input.start();

        out.start();
    }
}

class Out extends Thread {
    private Dto dto;

    Out(Dto dto) {
        this.dto = dto;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (dto) {    //保证线程安全
                if (!dto.flag) {
                    try {
                        dto.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(dto.name + "," + dto.gender);
                dto.flag=false;
                dto.notify();
            }
        }
    }
}

class Input extends Thread {
    private Dto dto;

    Input(Dto dto) {
        this.dto = dto;
    }

    @Override
    public void run() {
        int count = 0;

        while (true) {
            synchronized (dto) {
                if (dto.flag) {
                    try {
                        dto.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (count == 0) {   //偶数
                    dto.setName("小红");
                    dto.setGender("女");
                } else {    //奇数
                    dto.setName("余盛军");
                    dto.setGender("男");
                }
                count = (count + 1) % 2;

                dto.flag=true;
                dto.notify();
            }
        }
    }
}

@Data
class Dto {
    public String name;
    public String gender;
    public boolean flag=false;    //true表示消费者消费；false表示生产者生产
}
