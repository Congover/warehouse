package com.wh.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wh.entity.Contragent;
import com.wh.model.DataTableModel;
import com.wh.service.ContragentService;

@Controller
@RequestMapping(value = "/contractor")
public class ContractorController {
	
	private static final Logger logger = LoggerFactory.getLogger(ContractorController.class);
	
	@Autowired
	ContragentService contragentService;
	
		
	@RequestMapping({"/", ""})
	public String contractor(HttpSession session) {
		return "contractor";
	}
	
	@RequestMapping("/getList")
	public @ResponseBody DataTableModel getList(@RequestParam("draw") Integer draw, @RequestParam("length") Integer length, @RequestParam("start") Integer start) {
		List<Contragent> list = contragentService.findAll();
		
		return new DataTableModel(list, draw, length, start);
		
	}

}
