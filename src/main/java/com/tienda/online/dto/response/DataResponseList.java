package com.tienda.online.dto.response;

import java.util.List;

public class DataResponseList<T> {

	private List<T> data;
	private Integer size;
	
	public DataResponseList(List<T> data, Integer size) {
		this.data = data;
		this.size = size;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
	
}
