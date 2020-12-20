package com.netease.cloud.nsf.demo.stock.viewer.web.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.netease.cloud.nsf.agent.core.circuitbreaker.NsfRateLimiterInvokeException;
//import com.netease.cloud.nsf.agent.core.exception.NsfRateLimiterException;
import com.netease.cloud.nsf.demo.stock.viewer.web.entity.HttpResponse;
import com.netease.cloud.nsf.demo.stock.viewer.web.entity.Stock;
import com.netease.cloud.nsf.demo.stock.viewer.web.manager.LogManager;
import com.netease.cloud.nsf.demo.stock.viewer.web.service.IStockService;


/**
 * @author Chen Jiahan | chenjiahan@corp.netease.com
 */
@Controller
public class PanelController {

    private static Logger log = LoggerFactory.getLogger(PanelController.class);

    @Autowired
    IStockService stockService;

    @GetMapping(value = {"" , "/index"})
    public String indexPage(){
        return "index";
    }
    
    @GetMapping(value = "/stocks", produces = "application/json")
    @ResponseBody
    public HttpResponse getStockList(@RequestParam(name = "delay", required = false, defaultValue = "0") int delay) {

        List<Stock> stocks;
        try {
            stocks = stockService.getStockList(delay);
        } catch (Exception e) {
            log.warn("get stock list failed ...");
            log.warn(e.getMessage());
            return handleExceptionResponse(e);
        }
        return new HttpResponse(stocks);
    }

    @GetMapping(value = "/advices/hot", produces = "application/json")
    @ResponseBody
    public HttpResponse getHotAdvice() {

        List<Stock> stocks;
        try {
            stocks = stockService.getHotStockAdvice();
        } catch (Exception e) {
        	e.printStackTrace();
            log.warn("get hot stock advice failed ...");
            return handleExceptionResponse(e);
        }
        return new HttpResponse(stocks);
    }

    @GetMapping("/stocks/{stockId}")
    @ResponseBody
    public Stock getStockById(@PathVariable String stockId) {

        Stock stock = null;
        try {
            stock = stockService.getStockById(stockId);
        } catch (Exception e) {
        	e.printStackTrace();
            log.warn("get stock[{}] info failed ...", stockId);
        }
        return stock;
    }

    @GetMapping("/logs")
    @ResponseBody
    public HttpResponse getHttpLog() {
    	return new HttpResponse(LogManager.logs());
    }
    
    @GetMapping("/logs/clear")
    @ResponseBody
    public HttpResponse clearLogs() {
    	LogManager.clear();
    	return new HttpResponse("clear logs success");
    }
    
    @GetMapping("/echo/advisor")
    @ResponseBody
    public HttpResponse echoAdvisor(HttpServletRequest request) {
    	log.info("/echo/advisor invoked");
    	String result = stockService.echoAdvisor();
    	LogManager.put(UUID.randomUUID().toString(), result);
    	return new HttpResponse(result);
    }
    
    @GetMapping("/echo/provider")
    @ResponseBody
    public HttpResponse echoProvider(HttpServletRequest request) {
    	log.info("/echo/provider invoked");
    	String result = stockService.echoProvider();
    	LogManager.put(UUID.randomUUID().toString(), result);
    	return new HttpResponse(result);
    }
    
    @GetMapping("/health")
    @ResponseBody
    public String health() {
    	return "I am good!";
    }
    
    @RequestMapping("/deepInvoke")
    @ResponseBody
    public String deepInvoke(@RequestParam int times) {
    	return stockService.deepInvoke(times);
    }
    
    private HttpResponse handleExceptionResponse(Exception e) {
        NsfExceptionUtil.NsfExceptionWrapper nsfException = NsfExceptionUtil.parseException(e);
        log.error(nsfException.getThrowable().getMessage());
        if(nsfException.getType() == NsfExceptionUtil.NsfExceptionType.NORMAL_EXCEPTION){
            return new HttpResponse(nsfException.getThrowable().getMessage());
        }
        return new HttpResponse(nsfException.getType().getDesc());
    }

}