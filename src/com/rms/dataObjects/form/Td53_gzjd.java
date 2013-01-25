package com.rms.dataObjects.form;

import java.io.Serializable;

public class Td53_gzjd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 595699794281467531L;

	/**
	 * 标识
	 */
	private Long id;

	/**
	 * 工程信息标识
	 */
	private Long gcxx_id;

	/**
	 * 进度项名称
	 */
	private String jd_name;

	/**
	 * 进度状态
	 */
	private String jd_status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGcxx_id() {
		return gcxx_id;
	}

	public void setGcxx_id(Long gcxx_id) {
		this.gcxx_id = gcxx_id;
	}

	public String getJd_name() {
		return jd_name;
	}

	public void setJd_name(String jd_name) {
		this.jd_name = jd_name;
	}

	public String getJd_status() {
		return jd_status;
	}

	public void setJd_status(String jd_status) {
		this.jd_status = jd_status;
	}

}
