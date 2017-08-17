package com.tower.entity;

public class SignUp implements java.io.Serializable {
	private String username;
	private String psd1;
	private String psd2;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPsd1() {
		return psd1;
	}

	public void setPsd1(String psd1) {
		this.psd1 = psd1;
	}

	public String getPsd2() {
		return psd2;
	}

	public void setPsd2(String psd2) {
		this.psd2 = psd2;
	}

}
