package com.fx.vo;

import java.io.Serializable;
import java.util.List;

public class CountryDataVO implements Serializable{
	private List<CountryVO> data;

	public List<CountryVO> getData() {
		return data;
	}

	public void setData(List<CountryVO> data) {
		this.data = data;
	}	
	
}
