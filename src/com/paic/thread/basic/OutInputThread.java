package com.paic.thread.basic;

/**
 * @Author: Admin
 * @Date: 2021/1/11 20:00
 * @Description:    ���̹߳�����Դ
 *
 * wait�����ã��õ�ǰ�̴߳������̱߳�Ϊ����״̬���ͷ�����Դ
 * notify�����ã��õ�ǰ�̴߳�����״̬��Ϊ����״̬������
 * ͬ���в���ʹ��
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
    public boolean flag = false;    //true��ʾ�������̵߳ȴ������������ѣ�false��ʾ�����߽��������������ߵȴ�
}

class Input extends Thread {
    Dto dto;

    public Input(Dto dto) {
        this.dto = dto;
    }

    /**
     * ����
     * ����ʱRes��ֵ����ʤ��    ��
     * ż��ʱRes��ֵ������ʦ    Ů
     */
    @Override
    public void run() {
        int count = 0;

        while (true) {
            synchronized (dto) {
                if (dto.flag) {
                    try {
                        dto.wait(); //�õ�ǰ�̴߳�����״̬��Ϊ����״̬
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
                if (count == 0) {
                    dto.userName = "����ʦ";
                    dto.sex = "Ů";
                } else {
                    dto.userName = "��ʤ��";
                    dto.sex = "��";
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
