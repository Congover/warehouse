package com.wh.web;

import java.util.List;
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

import com.wh.entity.Incoming;
import com.wh.model.DataTableModel;
import com.wh.service.ContragentService;
import com.wh.service.DictionaryService;
import com.wh.service.IncomingService;

@Controller
@RequestMapping(value = "/incoming")
public class IncomingController {

    private static final Logger log = LoggerFactory.getLogger(IncomingController.class);

    private static final String REDIRECT = "redirect:/incoming";

    @Autowired
    private IncomingService incomingService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private ContragentService contragentService;

    @RequestMapping({ "/", "" })
    public String incoming(HttpSession session) {
	return "incoming";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public DataTableModel getList(@RequestParam("draw") Integer draw, @RequestParam("length") Integer length,
	    @RequestParam("start") Integer start) {
	List<Incoming> list = incomingService.findAll();
	return new DataTableModel(list, draw, length, start, Incoming.class);

    }

    @RequestMapping({ "/add" })
    public String add(Map<String, Object> map) {
	map.put("productList", dictionaryService.getProducts());
	map.put("storeList", dictionaryService.getStories());
	map.put("contragentList", contragentService.findAll());
	return "addIncoming";
    }

    @RequestMapping({ "/save" })
    public String addIncoming(HttpSession session, @RequestParam("date") String date,
	    @RequestParam("contragent") Long contragentId, @RequestParam("product") Long productId,
	    @RequestParam("productCount") Double productCount, @RequestParam("store") Long storeId,
	    @RequestParam("comment") String comment) {
	incomingService.save(date, contragentId, productId, productCount, storeId, comment);
	return REDIRECT;
    }

    @RequestMapping({ "/edit/{id}" })
    public String edit(@PathVariable("id") Long id, Map<String, Object> map) {
	map.put("incoming", incomingService.find(id));
	map.put("storeList", dictionaryService.getStories());
	map.put("contragentList", contragentService.findAll());
	return "editIncoming";
    }

    @RequestMapping({ "update" })
    public String update(@RequestParam("id") Long id, @RequestParam("date") String date,
	    @RequestParam("contragent") Long contragentId, @RequestParam("store") Long storeId,
	    @RequestParam("comment") String comment) {
	incomingService.update(id, date, contragentId, storeId, comment);
	return REDIRECT;
    }

}
