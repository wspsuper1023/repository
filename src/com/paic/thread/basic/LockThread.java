package com.paic.thread.basic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Admin
 * @Date: 2021/1/11 21:39
 * @Description:
 */
public class LockThread {
    public static void main(String[] args) {
        LockDto lockDto = new LockDto();
        Condition condition = lockDto.lock.newCondition();
        LockInput input = new LockInput(lockDto, condition);
        LockOutput output = new LockOutput(lockDto, condition);

        input.start();
        output.start();
    }
}

class LockDto {
    public String userName;
    public String sex;
    public boolean flag = false;    //true表示生产者线程等待，消费者消费；false表示生产者进行生产，消费者等待
    public Lock lock = new ReentrantLock();
}

class LockInput extends Thread {
    private LockDto lockDto;
    private Condition condition;

    public LockInput(LockDto lockDto, Condition condition) {
        this.lockDto = lockDto;
        this.condition = condition;
    }

    /**
     * 需求：
     * 奇数时dto赋值：余胜军    男
     * 偶数时dto赋值：仓老师    女
     */
    @Override
    public void run() {
        int count = 0;

        while (true) {
            try {
                //开始上锁
                lockDto.lock.lock();

                if (lockDto.flag) {
                    //等待：类似object对象的wait
                    condition.await();
                }

                if (count == 0) {
                    lockDto.userName = "仓老师";
                    lockDto.sex = "女";
                } else {
                    lockDto.userName = "余胜军";
                    lockDto.sex = "男";
                }
                count = (count + 1) % 2;
                lockDto.flag = true;

                //唤醒：类似object对象的notify
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //释放锁
                lockDto.lock.unlock();
            }
        }
    }
}

class LockOutput extends Thread {
    private LockDto lockDto;
    private Condition condition;

    public LockOutput(LockDto lockDto, Condition condition) {
        this.lockDto = lockDto;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (true) {

            try {
                //开始上锁
                lockDto.lock.lock();

                if (!lockDto.flag) {
                    condition.await();
                }

                System.out.println(lockDto.userName + "," + lockDto.sex);
                lockDto.flag = false;

                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lockDto.lock.unlock();
            }
        }
    }
}
