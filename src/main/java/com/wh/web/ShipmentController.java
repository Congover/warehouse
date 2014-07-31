package com.wh.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/shipment")
public class ShipmentController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShipmentController.class);
	
		
	@RequestMapping({"/", ""})
	public String shipment(HttpSession session) {
		return "shipment";
	}
}
