package com.rms.dataObjects.form;

import java.io.Serializable;

public class Td54_gzjdx implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6325105305105017657L;

	private Long id;
	
	private String jdx_key;

	private String jdx_value;
	
	private Long type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJdx_key() {
		return jdx_key;
	}

	public void setJdx_key(String jdx_key) {
		this.jdx_key = jdx_key;
	}

	public String getJdx_value() {
		return jdx_value;
	}

	public void setJdx_value(String jdx_value) {
		this.jdx_value = jdx_value;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	} 

}
