package com.tower.entity;

public class StationInfo implements java.io.Serializable {
	private String id;
	private String cntower;
	private String cnmobile;
	private String cntelecom;
	private String cnunicom;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCntower() {
		return cntower;
	}

	public void setCntower(String cntower) {
		this.cntower = cntower;
	}

	public String getCnmobile() {
		return cnmobile;
	}

	public void setCnmobile(String cnmobile) {
		this.cnmobile = cnmobile;
	}

	public String getCntelecom() {
		return cntelecom;
	}

	public void setCntelecom(String cntelecom) {
		this.cntelecom = cntelecom;
	}

	public String getCnunicom() {
		return cnunicom;
	}

	public void setCnunicom(String cnunicom) {
		this.cnunicom = cnunicom;
	}

	@Override
	public String toString() {
		return "StationInfo [id=" + id + ", cntower=" + cntower + ", cnmobile=" + cnmobile + ", cntelecom=" + cntelecom
				+ ", cnunicom=" + cnunicom + "]";
	}

}
