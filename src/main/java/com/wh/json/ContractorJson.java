package com.wh.json;

import com.wh.entity.Contragent;

public class ContractorJson {
	private String name;	
	
	public ContractorJson(Contragent contractor) {
		this.setName(contractor.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
