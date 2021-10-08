package com.paic.thread.demo.communication;

import lombok.Data;

/**
 * @Author: Admin
 * @Date: 2021/10/7 18:43
 * @Description: �̼߳�ͨѶ��������01
 *
 * ʲô�Ƕ��̼߳�ͨѶ��
 * ����̲߳���ͬһ����Դ������ÿ���̶߳�����һ��
 *
 * sleep��wait����
 * 	��ͬ�����������ߣ�ֻ����synchronized��ʹ�ã�������lock��ʹ�ã����ն��ǵ���jvm����native����
 * 	��1��sleep����thread��wait����object
 * 	��2��sleep�����ͷ�����Դ��wait���ͷ�����Դ
 * 	��3��sleep��������ʱ���Զ����У�wait��Ҫnotify��notifyAll����
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
            synchronized (dto) {    //��֤�̰߳�ȫ
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

                if (count == 0) {   //ż��
                    dto.setName("С��");
                    dto.setGender("Ů");
                } else {    //����
                    dto.setName("��ʢ��");
                    dto.setGender("��");
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
    public boolean flag=false;    //true��ʾ���������ѣ�false��ʾ����������
}
