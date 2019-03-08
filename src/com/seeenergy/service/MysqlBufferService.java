package com.seeenergy.service;

import java.util.List;
import java.util.Map;

import com.seeenergy.model.M5DataBeopdataGhkj;

public interface MysqlBufferService {
	public Map<String, Object> getLastRtData(String pointname);
	
	public List<M5DataBeopdataGhkj> getLastRtDataMongoDB(String pointname);
}
