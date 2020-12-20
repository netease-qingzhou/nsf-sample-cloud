package com.netease.cloud.nsf.demo.stock.advisor.web.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.netease.cloud.nsf.demo.stock.advisor.web.entity.Stock;
import com.netease.cloud.nsf.demo.stock.advisor.web.service.IAdvisorService;

@RestController
public class AdvisorController {

	@Autowired
	IAdvisorService advisorService;
	
	@GetMapping("/advices/hot")
	public List<Stock> getHotAdvice() throws Exception {
		return advisorService.getHotStocks();
	}
	
	@GetMapping("/hi")
	public List<String> greeting() {
		return advisorService.batchHi();
	}
	
	@Value("${spring.application.name}")
	String name;

	@GetMapping("/echo")
	public String echo(HttpServletRequest request) {
		
		String host = request.getServerName();
		int port = request.getServerPort();
		
		return "echo from " + name + "[" + host + ":" + port + "]" + System.lineSeparator();
	}
	
	@GetMapping("/health")
	@ResponseBody
	public String health() {
		return "I am good!";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public String register(@RequestBody String jsonString) {
		return "register json :\r\n" + jsonString;
	}

	@Value("${test:hi}")
	String test;

	@GetMapping("/test")
	public String TestApollo(){
		return test;
	}

	@GetMapping("/divide")
	public String divide(HttpServletRequest request) {
		return advisorService.divide(request);
	}
}
