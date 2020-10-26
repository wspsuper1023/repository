package com.paic.dao;

import java.util.List;
import java.util.Map;

public interface DataSourceDao {
	
	public List<Map<String, Object>> queryProvinceList(String sqlId, Object obj);
	
	public List<Map<String, Object>> queryEmpInfo(String sqlId, Object obj);
	
}
