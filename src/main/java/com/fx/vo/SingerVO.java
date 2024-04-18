package com.fx.vo;

import java.io.Serializable;

public class SingerVO implements Serializable{
	private String name;
	private String adamid;
	private String avatar;
	private boolean verfied;
	private String weburl;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdamid() {
		return adamid;
	}
	public void setAdamid(String adamid) {
		this.adamid = adamid;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public boolean isVerfied() {
		return verfied;
	}
	public void setVerfied(boolean verfied) {
		this.verfied = verfied;
	}
	public String getWeburl() {
		return weburl;
	}
	public void setWeburl(String weburl) {
		this.weburl = weburl;
	}
}
