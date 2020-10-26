package com.paic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.paic.dto.China;
import com.paic.service.WhiteNameService;
import com.paic.util.DaoUtil;

public class WhiteNameServiceImpl implements WhiteNameService {
	
	@Override
	public List<China> getProvinceList() {
		String sqlId = "select t.id id, t.name name,  t.pid from china t where t.id is not null";
		List<China> list = new ArrayList<China>();
		
		try {
			List<Map<String, Object>> queryMapList = DaoUtil.getDao().queryProvinceList(sqlId, null);
			for (Map<String, Object> map : queryMapList) {
				String id = (String) map.get("id");
				String name = (String) map.get("name");
				String pId = (String) map.get("pId");
				China china = new China(id, name, pId);
				list.add(china);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
