package com.paic.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.paic.dao.DataSourceDao;
import com.paic.util.DaoUtil;

public class DataSourceDaoImpl implements DataSourceDao {
	
	@Override
	public List<Map<String, Object>> queryProvinceList(String sql, Object obj) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = DaoUtil.getPreparedStatement(sql);
			resultSet = preparedStatement.executeQuery(sql);
			while (resultSet.next()) {
				Map<String, Object> resultMap = new HashMap<>();
				String id = resultSet.getString(1);  
                String name = resultSet.getString(2);  
                String pId = resultSet.getString(3);  
                resultMap.put("id", id);
                resultMap.put("name", name);
                resultMap.put("pId", pId);
				resultList.add(resultMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DaoUtil.release(null, null, preparedStatement, resultSet);
		}
		
		return resultList;
	}
	
	@Override
	public List<Map<String, Object>> queryEmpInfo(String sqlId, Object obj) {
		
		return null;
	}
	
}
