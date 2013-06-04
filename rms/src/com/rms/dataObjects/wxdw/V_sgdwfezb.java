package com.rms.dataObjects.wxdw;

import java.io.Serializable;

public class V_sgdwfezb implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long wxdw_id;
	private String zy;
	private String dq;
	private Long nd;
	private Double v1;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWxdw_id() {
		return wxdw_id;
	}

	public void setWxdw_id(Long wxdw_id) {
		this.wxdw_id = wxdw_id;
	}

	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

	public String getDq() {
		return dq;
	}

	public void setDq(String dq) {
		this.dq = dq;
	} 


	public Long getNd() {
		return nd;
	}

	public void setNd(Long nd) {
		this.nd = nd;
	}

	public Double getV1() {
		return v1;
	}

	public void setV1(Double v1) {
		this.v1 = v1;
	}

}
