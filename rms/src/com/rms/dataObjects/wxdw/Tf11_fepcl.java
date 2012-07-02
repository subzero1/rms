package com.rms.dataObjects.wxdw;

import java.io.Serializable;

/**
 * @author mengying 2009-12-11
 * @hibernate.class table="tf11_fepcl"
 */
public class Tf11_fepcl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6808405572319393329L;
	/**
	 * 标识
	 */
	private Long id;
	private String dj;
	private Double qzsx;
	private Double qzxx;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="TF11_NUM"
	 * @return Returns the id.
	 */

	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="dj"
	 * @return Returns the dj.
	 */
	public String getDj() {
		return dj;
	}

	/**
	 * @param dj
	 *            The dj to set.
	 */
	public void setDj(String dj) {
		this.dj = dj;
	}

	/**
	 * @hibernate.property column="qzsx"
	 * @return Returns the qzsx.
	 */
	public Double getQzsx() {
		return qzsx;
	}

	/**
	 * @param qzsx
	 *            The qzsx to set.
	 */
	public void setQzsx(Double qzsx) {
		this.qzsx = qzsx;
	}

	/**
	 * @hibernate.property column="qzxx"
	 * @return Returns the qzxx.
	 */
	public Double getQzxx() {
		return qzxx;
	}

	/**
	 * @param qzxx
	 *            The qzxx to set.
	 */
	public void setQzxx(Double qzxx) {
		this.qzxx = qzxx;
	}

}
