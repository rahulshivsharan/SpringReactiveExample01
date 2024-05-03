package com.fx.vo;

import java.io.Serializable;
import java.util.List;

public class StudentDataVO implements Serializable{
	private List<StudentVO> list;
	
	public StudentDataVO() {}
	
	public StudentDataVO(List<StudentVO> list) {
		this.list = list;
	}

	public List<StudentVO> getList() {
		return list;
	}

	public void setList(List<StudentVO> list) {
		this.list = list;
	}
}
