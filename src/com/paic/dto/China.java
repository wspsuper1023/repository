package com.paic.dto;

public class China {

	public String id;
	
	public String name;
	
	public String pId;

	public China(String id, String name, String pId) {
		super();
		this.id = id;
		this.name = name;
		this.pId = pId;
	}

	public China() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	@Override
	public String toString() {
		return "ProvinceDto [id=" + id + ", name=" + name + ", pId=" + pId + "]";
	}
	
}
