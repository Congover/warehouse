package com.wh.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wh.entity.Packing;
import com.wh.model.DataTableModel;
import com.wh.service.DictionaryService;
import com.wh.service.PackingService;

@Controller
@RequestMapping(value = "/packing")
public class PackingController {

	private static final String REDIRECT = "redirect:/packing";
	
	@Autowired
	PackingService packingService;
	
	@Autowired
	DictionaryService dictionaryService;
		
	@RequestMapping({"/", ""})
	public String packing(HttpSession session) {
		return "packing";
	}
	
	@RequestMapping("/getList")
	public @ResponseBody DataTableModel getList(@RequestParam("draw") Integer draw, @RequestParam("length") Integer length, @RequestParam("start") Integer start) {
		List<Packing> list = packingService.findAll();
		return new DataTableModel(list, draw, length, start, Packing.class);
	}
	
	@RequestMapping({"/add"})
	public String add(Map<String, Object> map) {
		map.put("productList", dictionaryService.getAvailibleProductForPacking());
		return "addPacking";
	}
	
	@RequestMapping({"/save"})
	public String addIncoming(HttpSession session, @RequestParam("date") String date, @RequestParam("product") Long productId, 
			@RequestParam("productCount") Double productCount) {
		//incomingService.save(date, contragentId, productId, productCount, storeId, comment);
		return REDIRECT;
	}

}
