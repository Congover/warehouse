package com.wh.web;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.wh.entity.Address;
import com.wh.entity.Contragent;
import com.wh.entity.Product;
import com.wh.entity.Store;
import com.wh.entity.Transport;
import com.wh.model.DataTableModel;
import com.wh.service.ContragentService;
import com.wh.service.DictionaryService;

@Controller
@RequestMapping(value = "/contractor")
public class ContractorController {
	
	private static final String REDIRECT = "redirect:/contractor";
	
	private static final Logger logger = LoggerFactory.getLogger(ContractorController.class);
	
	@Autowired
	ContragentService contragentService;
	
	@Autowired
	DictionaryService dictionaryService;
	
		
	@RequestMapping({"/", ""})
	public String contractor(HttpSession session) {
		return "contractor";
	}
	
	@RequestMapping("/getList")
	public @ResponseBody DataTableModel getList(@RequestParam("draw") Integer draw, @RequestParam("length") Integer length, @RequestParam("start") Integer start) {
		return new DataTableModel(contragentService.findAll(), draw, length, start, Contragent.class);
	}
	
	@RequestMapping({"/add"})
	public String add(Map<String, Object> map) {
		List<Address> list = dictionaryService.getAdresses();
		map.put("addressList", list);
		return "addContragent";
	}
	
	@RequestMapping({"/save"})
	public String addContragent(HttpSession session, @RequestParam("mainAddress") Long address, @RequestParam("value") String value) {
		contragentService.save(value, address);
		return REDIRECT;
	}
	
	@RequestMapping({"getAdresses"})
	//TODO > move
	public @ResponseBody Map<Long, String> getContragentAddresses(@RequestParam("contragentId") Long contragentId) {
		Contragent contragent = contragentService.find(contragentId);
		Map<Long, String> map = new HashMap<Long, String>(contragent.getAddressList().size());
		for(Address entity : contragent.getAddressList()) {
			map.put(entity.getAddressId(), entity.getFullAddress());
		}
		return map;
	}

}
