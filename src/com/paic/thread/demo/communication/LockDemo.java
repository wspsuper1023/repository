package com.paic.thread.demo.communication;

import lombok.Data;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Admin
 * @Date: 2021/10/7 18:43
 * @Description: �̼߳�ͨѶ��������01
 *
 * synchronized��lock��������
 * ��1��synchronized�Զ�����������Ч�ʵͣ���չ�Բ��ߣ������Զ��壬�����������쳣���Զ��ͷ�����lock�ֶ���������Ժã�Ч�ʸߣ������ֶ��ͷ���
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

                if (count == 0) {   //ż��
                    lockDto.setName("С��");
                    lockDto.setGender("Ů");
                } else {    //����
                    lockDto.setName("��ʢ��");
                    lockDto.setGender("��");
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
    boolean flag = false;    //true��ʾ���������ѣ�false��ʾ����������
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
}
