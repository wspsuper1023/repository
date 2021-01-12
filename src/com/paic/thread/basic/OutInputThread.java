package com.paic.thread.basic;

/**
 * @Author: Admin
 * @Date: 2021/1/11 20:00
 * @Description:    多线程共享资源
 *
 * wait的作用：让当前线程从运行线程变为休眠状态，释放锁资源
 * notify的作用：让当前线程从休眠状态变为运行状态，缓冲
 * 同步中才能使用
 *
 * 
 */
public class OutInputThread {
    public static void main(String[] args) {
        Dto dto = new Dto();
        Input input = new Input(dto);
        Output output = new Output(dto);

        input.start();
        output.start();
    }
}

class Dto {
    public String userName;
    public String sex;
    public boolean flag = false;    //true表示生产者线程等待，消费者消费；false表示生产者进行生产，消费者等待
}

class Input extends Thread {
    Dto dto;

    public Input(Dto dto) {
        this.dto = dto;
    }

    /**
     * 需求：
     * 奇数时Res赋值：余胜军    男
     * 偶数时Res赋值：仓老师    女
     */
    @Override
    public void run() {
        int count = 0;

        while (true) {
            synchronized (dto) {
                if (dto.flag) {
                    try {
                        dto.wait(); //让当前线程从运行状态变为休眠状态
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
                if (count == 0) {
                    dto.userName = "仓老师";
                    dto.sex = "女";
                } else {
                    dto.userName = "余胜军";
                    dto.sex = "男";
                }
                count = (count + 1) % 2;
                dto.flag = true;
                dto.notify();
            }
        }
    }
}

class Output extends Thread {
    Dto dto;

    public Output(Dto dto) {
        this.dto = dto;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (dto) {
                if (!dto.flag) {
                    try {
                        dto.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(dto.userName + "," + dto.sex);
                dto.flag = false;
                dto.notify();
            }
        }
    }
}
