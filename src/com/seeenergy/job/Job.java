package com.seeenergy.job;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.seeenergy.model.M5DataBeopdataGhkj;
import com.seeenergy.service.MysqlBufferService;

public class Job implements ApplicationListener<ContextRefreshedEvent> {

	private static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
	
	@Autowired
	public MysqlBufferService service;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			public void run() {
				try {
					System.out.println("insert list.size():run=========");
					//从mongoDB取出最新一条数据 0,15,30,45
					Map<String, Object> map = service.getLastRtData("test");
					List<M5DataBeopdataGhkj> lists = service.getLastRtDataMongoDB("test");
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}, 1, 5, TimeUnit.MINUTES);
	}
	
}
