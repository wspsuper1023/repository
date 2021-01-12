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
    public boolean flag = false;    //true��ʾ�������̵߳ȴ������������ѣ�false��ʾ�����߽��������������ߵȴ�
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
     * ����
     * ����ʱdto��ֵ����ʤ��    ��
     * ż��ʱdto��ֵ������ʦ    Ů
     */
    @Override
    public void run() {
        int count = 0;

        while (true) {
            try {
                //��ʼ����
                lockDto.lock.lock();

                if (lockDto.flag) {
                    //�ȴ�������object�����wait
                    condition.await();
                }

                if (count == 0) {
                    lockDto.userName = "����ʦ";
                    lockDto.sex = "Ů";
                } else {
                    lockDto.userName = "��ʤ��";
                    lockDto.sex = "��";
                }
                count = (count + 1) % 2;
                lockDto.flag = true;

                //���ѣ�����object�����notify
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //�ͷ���
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
                //��ʼ����
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
