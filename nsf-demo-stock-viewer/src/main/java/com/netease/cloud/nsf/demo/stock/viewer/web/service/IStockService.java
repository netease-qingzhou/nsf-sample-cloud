package com.netease.cloud.nsf.demo.stock.viewer.web.service;

import java.util.List;

import com.netease.cloud.nsf.demo.stock.viewer.web.entity.Stock;


public interface IStockService {

	List<Stock> getStockList(int delay) throws Exception;
	
	Stock getStockById(String stockId) throws Exception;
	
	List<Stock> getHotStockAdvice() throws Exception;
	
	String echoAdvisor();
	
	String echoProvider();
	
	String deepInvoke(int times);
}
