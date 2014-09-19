package com.wh.web;

import java.util.HashMap;
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

import com.wh.entity.Address;
import com.wh.entity.Contragent;
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

    @RequestMapping({ "/", "" })
    public String contractor(HttpSession session) {
	return "contractor";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public DataTableModel getList(@RequestParam("draw") Integer draw, @RequestParam("length") Integer length,
	    @RequestParam("start") Integer start) {
	return new DataTableModel(contragentService.findAll(), draw, length, start, Contragent.class);
    }

    @RequestMapping({ "/add" })
    public String add(Map<String, Object> map) {
	List<Address> list = dictionaryService.getAdresses();
	map.put("addressList", list);
	map.put("actionType", "save");
	return "addContragent";
    }

    @RequestMapping({ "/save" })
    public String addContragent(HttpSession session, @RequestParam("value") String value,
	    @RequestParam(value = "address1", required = false) Long address1,
	    @RequestParam(value = "address2", required = false) Long address2,
	    @RequestParam(value = "address3", required = false) Long address3,
	    @RequestParam(value = "address4", required = false) Long address4,
	    @RequestParam(value = "address5", required = false) Long address5) {
	contragentService.save(value, address1, address2, address3, address4, address5);
	return REDIRECT;
    }

    @RequestMapping({ "getAdresses" })
    // TODO > move
    public @ResponseBody Map<Long, String> getContragentAddresses(@RequestParam("contragentId") Long contragentId) {
	Contragent contragent = contragentService.find(contragentId);
	Map<Long, String> map = new HashMap<Long, String>(contragent.getAddressList().size());
	for (Address entity : contragent.getAddressList()) {
	    map.put(entity.getAddressId(), entity.getFullAddress());
	}
	return map;
    }

    @RequestMapping({ "/edit/{id}" })
    public String edit(@PathVariable("id") Long id, Map<String, Object> map) {
	Contragent contragent = contragentService.find(id);
	map.put("contragent", contragent);
	int i = 0;
	for (Address address : contragent.getAddressList()) {
	    map.put("address" + i++, address.getAddressId());
	}
	List<Address> list = dictionaryService.getAdresses();
	map.put("addressList", list);
	map.put("actionType", "../update");
	return "addContragent";
    }

    @RequestMapping({ "update" })
    public String update(@RequestParam("id") Long id, @RequestParam("value") String value,
	    @RequestParam(value = "address1", required = false) Long address1,
	    @RequestParam(value = "address2", required = false) Long address2,
	    @RequestParam(value = "address3", required = false) Long address3,
	    @RequestParam(value = "address4", required = false) Long address4,
	    @RequestParam(value = "address5", required = false) Long address5) {
	contragentService.save(id, value, address1, address2, address3, address4, address5);
	return REDIRECT;
    }

    @RequestMapping({ "delete" })
    public @ResponseBody Boolean deleteContragent(@RequestParam("id") Long contragentId) {
	return contragentService.delete(contragentId);
    }

}
