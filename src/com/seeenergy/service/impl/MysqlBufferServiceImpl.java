package com.seeenergy.service.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seeenergy.model.M5DataBeopdataGhkj;
import com.seeenergy.service.MysqlBufferService;

@Transactional
@Service("MysqlBufferService")
public class MysqlBufferServiceImpl implements MysqlBufferService {
	@Resource(name = "net_template2")
	private JdbcTemplate nettemplate;
	@Resource(name = "mongoTemplate")
	private MongoTemplate mongoTemplate;
	
	@Override
	public Map<String, Object> getLastRtData(String pointname) {
		// TODO Auto-generated method stub
		String sql = "select * from store where pointname = '"+pointname+"' order by time desc LIMIT 1";
        try {
        	Map<String, Object>  map = nettemplate.queryForMap(sql);
        	return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	@Override
	public List<M5DataBeopdataGhkj> getLastRtDataMongoDB(String pointname) {
		// TODO Auto-generated method stub
		try {
        	List<M5DataBeopdataGhkj>  maps = mongoTemplate.findAll(M5DataBeopdataGhkj.class);
        	return maps;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
}
