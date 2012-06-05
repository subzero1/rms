package com.rms.dataObjects.wxdw;

import java.io.Serializable;

/**
 * @author mengying 2009-12-11
 * @hibernate.class table="tf02_sgd"
 */
public class Tf02_sgd implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 6958067704854242044L;

	/**
	 * 标识
	 */
	private Long id;
	private Long sgdw_id;
	private String mc;
	private String bz;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="TF02_NUM"
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
	 * @hibernate.property column="mc"
	 * @return Returns the mc.
	 */
	public String getMc() {
		return mc;
	}

	/**
	 * @param mc
	 *            The mc to set.
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}

	/**
	 * @hibernate.property column="sgdw_id"
	 * @return Returns the sgdw_id.
	 */
	public Long getSgdw_id() {
		return sgdw_id;
	}

	/**
	 * @param sgdw_id
	 *            The sgdw_id to set.
	 */
	public void setSgdw_id(Long sgdw_id) {
		this.sgdw_id = sgdw_id;
	}

	/**
	 * @hibernate.property column="bz"
	 * @return Returns the bz.
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bz
	 *            The bz to set.
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

}
