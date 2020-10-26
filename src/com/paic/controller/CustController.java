package com.paic.controller;

import java.util.List;
import com.paic.constant.Constant;
import com.paic.dto.China;
import com.paic.service.impl.WhiteNameServiceImpl;

public class CustController {
	
	public WhiteNameServiceImpl whiteNameService = new WhiteNameServiceImpl();

	public String whiteNameController() {
		
		List<China> provinceList = whiteNameService.getProvinceList();
		for (China provinceDto : provinceList) {
			System.out.println(provinceDto.getId()+ " "+provinceDto.getName()+ " "+provinceDto.getpId());
			System.out.println("-----------");
		}
		
		return Constant.SUCCESS;
	}
	
}
