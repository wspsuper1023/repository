package com.paic.thread.demo.communication;

import lombok.Data;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Admin
 * @Date: 2021/10/7 18:43
 * @Description: 线程间通讯课堂例子01
 *
 * synchronized与lock锁的区别？
 * （1）synchronized自动化内置锁：效率低，扩展性不高（不能自定义，不灵活），程序异常会自动释放锁；lock手动锁：灵活性好，效率高，必须手动释放锁
 *
 */
public class LockDemo {

    public static void main(String[] args) {
        LockDto lockDto = new LockDto();
        Condition condition = lockDto.condition;

        LockInput lockInput = new LockInput(lockDto, condition);
        LockOut lockOut = new LockOut(lockDto, condition);

        lockInput.start();
        lockOut.start();
    }
}

class LockOut extends Thread {
    private LockDto lockDto;
    private Condition condition;

    LockOut(LockDto lockDto, Condition condition) {
        this.lockDto = lockDto;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (true) {

            try {
                lockDto.lock.lock();
                if (!lockDto.flag) {
                    condition.await();
                }

                System.out.println(lockDto.name + "," + lockDto.gender);
                lockDto.flag=false;
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lockDto.lock.unlock();
            }
        }
    }
}

class LockInput extends Thread {
    private LockDto lockDto;
    private Condition condition;

    LockInput(LockDto lockDto, Condition condition) {
        this.lockDto = lockDto;
        this.condition = condition;
    }

    @Override
    public void run() {
        int count = 0;

        while (true) {

            try {
                lockDto.lock.lock();
                if (lockDto.flag) {
                    condition.await();
                }

                if (count == 0) {   //偶数
                    lockDto.setName("小红");
                    lockDto.setGender("女");
                } else {    //奇数
                    lockDto.setName("余盛军");
                    lockDto.setGender("男");
                }

                count = (count + 1) % 2;
                lockDto.flag = true;
                condition.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lockDto.lock.unlock();
            }
        }
    }
}

@Data
class LockDto {
    String name;
    String gender;
    boolean flag = false;    //true表示消费者消费；false表示生产者生产
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
}
