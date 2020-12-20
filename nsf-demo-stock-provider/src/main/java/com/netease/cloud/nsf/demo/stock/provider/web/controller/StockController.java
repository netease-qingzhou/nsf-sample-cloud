package com.netease.cloud.nsf.demo.stock.provider.web.controller;

import com.netease.cloud.nsf.demo.stock.provider.web.entity.Stock;
import com.netease.cloud.nsf.demo.stock.provider.web.service.IProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Chen Jiahan | chenjiahan@corp.netease.com
 */
@RestController
public class StockController {

	private static Logger log = LoggerFactory.getLogger(StockController.class);

	@Autowired
	IProviderService stockService;

	/**
	 * @param stockIds
	 *            以","分隔 , 单个id也可查询
	 * @return
	 * @throws InterruptedException 
	 */
	@GetMapping("/stocks/{stockIds}")
	public List<Stock> getStocksByIds(@PathVariable String stockIds, 
			@RequestParam(name = "delay", required = false, defaultValue = "0") int delay) throws InterruptedException {
		Thread.sleep(delay * 1000);
		log.info("get /stocks/{} success", stockIds);
		return stockService.getStocksByIds(stockIds);
	}

	@GetMapping("/stocks")
	public List<Stock> getAllStocks(@RequestParam(name = "delay", required = false, defaultValue = "0") int delay)
			throws InterruptedException {
		Thread.sleep(delay * 1000);
		return stockService.getAllStocks();
	}

	@Value("${spring.application.name}")
	String name;

	@GetMapping("/hi")
	public String greeting(HttpServletRequest request) {

		String host = request.getServerName();
		int port = request.getServerPort();

		return "greeing from " + name + "[" + host + ":" + port + "]" + System.lineSeparator();
	}

	@GetMapping("/echo")
	public String echo(HttpServletRequest request) {

		String host = request.getServerName();
		int port = request.getServerPort();

		return "echo from " + name + "[" + host + ":" + port + "]" + System.lineSeparator();
	}

	@PostMapping("echoPost")
	public Object echoPost(@RequestBody Object obj) {
		return obj;
	}

	@GetMapping("/health")
	@ResponseBody
	public String health() {
		return "I am good!";
	}
}
