package com.wh.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wh.entity.Shipment;
import com.wh.model.DataTableModel;
import com.wh.service.ContragentService;
import com.wh.service.DictionaryService;
import com.wh.service.ShipmentService;

@Controller
@RequestMapping(value = "/shipment")
public class ShipmentController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShipmentController.class);

	private static final String REDIRECT = "redirect:/shipment";
	
	@Autowired
	ShipmentService shipmentService;
	
	@Autowired
	DictionaryService dictionaryService;
	
	@Autowired
	ContragentService contragentService;
	
		
	@RequestMapping({"/", ""})
	public String shipment(HttpSession session) {
		return "shipment";
	}
	
	@RequestMapping("/getList")
	public @ResponseBody DataTableModel getList(@RequestParam("draw") Integer draw, @RequestParam("length") Integer length, @RequestParam("start") Integer start) {
		return new DataTableModel(shipmentService.findAll(), draw, length, start, Shipment.class);
	}
	
	@RequestMapping({"/add"})
	public String add(Map<String, Object> map) {
		map.put("productList", dictionaryService.getProducts());
		map.put("storeList", dictionaryService.getStories());
		map.put("transportList", dictionaryService.getTransports());
		map.put("contragentList", contragentService.findAll());
		return "addShimpent";
	}
	
	@RequestMapping({"/save"})
	public String addIncoming(HttpSession session, @RequestParam("date") String date, @RequestParam("contragent") Long contragentId,
			@RequestParam("product") Long productId, @RequestParam("productCount") Double productCount, @RequestParam("store") Long storeId,
			@RequestParam("transport") Long transportId, @RequestParam("address") Long addressId, @RequestParam("comment") String comment,
			@RequestParam("paymentType") Boolean paymentType) {
		shipmentService.save(date, contragentId, productId, productCount, storeId, transportId, addressId, paymentType, comment);
		return REDIRECT;
	}
}
