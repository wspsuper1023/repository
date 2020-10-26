package com.paic.thread;

import java.util.ArrayList;
import java.util.List;
import com.paic.entity.UserEntity;
import com.paic.util.ListUtils;

class UserThread implements Runnable {
	
	private List<UserEntity> userList;
	
	public UserThread(List<UserEntity> userList) {
		this.userList = userList;
	}

	@Override
	public void run() {
		for (UserEntity userEntity : userList) {
			System.out.println(Thread.currentThread().getName()+","+userEntity.toString());
		}
	}
	
}

public class BatchSms {

	public static void main(String[] args) {
		//1����ʼ������
		List<UserEntity> list = initUser();
		
		//2������ÿ���̷߳������ʹ�С
		int pageSize = 20;
		
		//3������ÿ���߳���Ҫ�����ܵ�����
		List<List<UserEntity>> splitList = ListUtils.splitList(list, pageSize);
		
		for (int j = 0; j < splitList.size(); j++) {
//			List<UserEntity> list2 = splitList.get(j);
//			UserThread userthread = new UserThread(list2);
//			Thread thread = new Thread(userthread, "�߳�"+j);
//			thread.start();
//			System.out.println();
		}
		//4�����з�������
		
	}
	
	private static List<UserEntity> initUser() {
		List<UserEntity> userList = new ArrayList<>();
		for (int i = 0; i < 110; i++) {
			userList.add(new UserEntity("userId:"+i, "userName:"+i));
		}
		return userList;
	}
}
