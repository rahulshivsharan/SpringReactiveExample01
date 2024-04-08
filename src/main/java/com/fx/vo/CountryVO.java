package com.fx.vo;

import java.io.Serializable;
import java.util.List;

public class CountryVO implements Serializable{
	
	private String code;
	private String [] currencyCodes;
	private String name;
	private String wikiDataId;
	
	public CountryVO() {
		
	}
	
	public CountryVO(String code, String [] currencyCodes, String name, String wikiDataId) {
		this.code = code;
		this.currencyCodes = currencyCodes;
		this.name = name;
		this.wikiDataId = wikiDataId;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String[] getCurrencyCodes() {
		return currencyCodes;
	}
	
	public void setCurrencyCodes(String[] currencyCodes) {
		this.currencyCodes = currencyCodes;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getWikiDataId() {
		return wikiDataId;
	}
	
	public void setWikiDataId(String wikiDataId) {
		this.wikiDataId = wikiDataId;
	}
	
}
