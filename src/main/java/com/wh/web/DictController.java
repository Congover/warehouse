package com.wh.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wh.entity.Address;
import com.wh.entity.Product;
import com.wh.entity.Store;
import com.wh.entity.Transport;
import com.wh.model.DataTableModel;
import com.wh.service.DictionaryService;

@Controller
@RequestMapping(value = "/dictionaries")
public class DictController {

    private static final String REDIRECT = "redirect:/dictionaries";

    @Autowired
    DictionaryService dictionaryService;

    @RequestMapping({ "/", "" })
    public String dictionaries(HttpSession session) {
	String dictType = (String) session.getAttribute("dict");
	if (StringUtils.isEmpty(dictType)) {
	    session.setAttribute("dict", "address");
	}
	return "dictionaries";
    }

    @RequestMapping({ "/address" })
    public String address(HttpSession session) {
	session.setAttribute("dict", "address");
	return REDIRECT;
    }

    @RequestMapping({ "/transport" })
    public String transport(HttpSession session) {
	session.setAttribute("dict", "transport");
	return REDIRECT;
    }

    @RequestMapping({ "/store" })
    public String store(HttpSession session) {
	session.setAttribute("dict", "store");
	return REDIRECT;
    }

    @RequestMapping({ "/product" })
    public String product(HttpSession session) {
	session.setAttribute("dict", "product");
	return REDIRECT;
    }

    // TODO change java to 1.8, and user switch by string
    @RequestMapping({ "/getList" })
    public @ResponseBody DataTableModel getList(@RequestParam("draw") Integer draw,
	    @RequestParam("length") Integer length, @RequestParam("start") Integer start, HttpSession session) {
	String dictValue = (String) session.getAttribute("dict");
	if (StringUtils.isEmpty(dictValue)) {
	    session.setAttribute("dict", "address");
	    List<Address> list = dictionaryService.getAdresses();
	    return new DataTableModel(list, draw, length, start, Address.class);
	}
	if (dictValue.equals("address")) {
	    List<Address> list = dictionaryService.getAdresses();
	    return new DataTableModel(list, draw, length, start, Address.class);
	}
	if (dictValue.equals("transport")) {
	    List<Transport> list = dictionaryService.getTransports();
	    return new DataTableModel(list, draw, length, start, Transport.class);
	}
	if (dictValue.equals("product")) {
	    List<Product> list = dictionaryService.getProducts();
	    return new DataTableModel(list, draw, length, start, Product.class);
	}
	if (dictValue.equals("store")) {
	    List<Store> list = dictionaryService.getStories();
	    return new DataTableModel(list, draw, length, start, Store.class);
	}
	return null;
    }

    @RequestMapping({ "/add" })
    public String add(Map<String, Object> map) {
	map.put("actionType", "addDictionary");
	return "addDict";
    }

    @RequestMapping({ "/addDictionary" })
    public String addDictionary(HttpSession session, @RequestParam("value") String value) {
	String dictValue = (String) session.getAttribute("dict");
	if (dictValue.equals("address")) {
	    dictionaryService.create(Address.class, value);
	}
	if (dictValue.equals("transport")) {
	    dictionaryService.create(Transport.class, value);
	}
	if (dictValue.equals("product")) {
	    dictionaryService.create(Product.class, value);
	    ;
	}
	if (dictValue.equals("store")) {
	    dictionaryService.create(Store.class, value);
	}
	return REDIRECT;
    }

    @RequestMapping({ "/edit/{id}" })
    public String edit(@PathVariable("id") Long id, Map<String, Object> map, HttpSession session) {
	String dictValue = (String) session.getAttribute("dict");
	map.put("dictionary", dictionaryService.find(id, dictValue));
	map.put("actionType", "../update");
	return "addDict";
    }

    @RequestMapping({ "update" })
    public String update(@RequestParam("id") Long id, @RequestParam("value") String value, HttpSession session) {
	dictionaryService.update(id, value, (String) session.getAttribute("dict"));
	return REDIRECT;
    }

    @RequestMapping({ "delete" })
    public @ResponseBody Boolean deleteContragent(@RequestParam("id") Long id, HttpSession session) {
	return dictionaryService.delete(id, (String) session.getAttribute("dict"));
    }

}
