package com.fx.vo;

import java.io.Serializable;

public class StudentVO implements Serializable{
	private Integer studentId;
	private String studentName;
	
	public StudentVO(Integer studentId, String studentName) {
		this.studentId = studentId;
		this.studentName = studentName;
	}
	
	public StudentVO() {}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}
