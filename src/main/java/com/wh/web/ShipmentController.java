package com.wh.web;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wh.model.BaseDataTableModel;
import com.wh.model.ShipmentDataTableModel;
import com.wh.service.ContragentService;
import com.wh.service.DictionaryService;
import com.wh.service.ShipmentService;

@Controller
@RequestMapping(value = "/shipment")
public class ShipmentController {

    private static final Logger logger = LoggerFactory.getLogger(ShipmentController.class);

    private static final String REDIRECT = "redirect:/shipment";

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private ContragentService contragentService;

    @RequestMapping({ "/", "" })
    public String shipment(HttpSession session) {
	return "shipment";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public BaseDataTableModel<?> getList(@RequestParam("draw") Integer draw, @RequestParam("length") Integer length,
	    @RequestParam("start") Integer start) {
	return new ShipmentDataTableModel(shipmentService.findAll(), draw, length, start);
    }

    @RequestMapping({ "/add" })
    public String add(Map<String, Object> map) {
	map.put("productList", dictionaryService.getAvailibleProductForPacking());
	map.put("storeList", dictionaryService.getStories());
	map.put("transportList", dictionaryService.getTransports());
	map.put("contragentList", contragentService.findAll());
	return "addShimpent";
    }

    @RequestMapping({ "/save" })
    public String addIncoming(HttpSession session, @RequestParam("date") String date,
	    @RequestParam("contragent") Long contragentId, @RequestParam("product") Long productId,
	    @RequestParam("productCount") Integer productCount, @RequestParam("store") Long storeId,
	    @RequestParam("transport") Long transportId, @RequestParam("address") Long addressId,
	    @RequestParam("comment") String comment, @RequestParam("paymentType") Boolean paymentType) {
	shipmentService.save(date, contragentId, productId, productCount, storeId, transportId, addressId, paymentType,
		comment);
	return REDIRECT;
    }

    @RequestMapping({ "/edit/{id}" })
    public String edit(@PathVariable("id") Long id, Map<String, Object> map) {
	map.put("shipment", shipmentService.find(id));
	map.put("storeList", dictionaryService.getStories());
	map.put("transportList", dictionaryService.getTransports());
	map.put("contragentList", contragentService.findAll());
	return "editShipment";
    }

    @RequestMapping({ "update" })
    public String update(@RequestParam("id") Long id, @RequestParam("date") String date,
	    @RequestParam("contragent") Long contragentId, @RequestParam("store") Long storeId,
	    @RequestParam("transport") Long transportId, @RequestParam("address") Long addressId,
	    @RequestParam("comment") String comment, @RequestParam("paymentType") Boolean paymentType) {
	shipmentService.update(id, date, contragentId, storeId, transportId, addressId, paymentType, comment);
	return REDIRECT;
    }
}
