package com.rms.dataObjects.base;

/**
 * @description:
 * 基础设备
 * @class name:com.rms.dataObjects.base.Tc04_jcsb
 * @author Administrator Jul 26, 2011
 * @hibernate.class table="Tc04_jcsb"
 */
public class Tc04_jcsb {
	/**
	 *标识
	 */
	private Long id;

	/**
	 *设备名称
	 */
	private String sbmc;

	/**
	 *设备型号
	 */
	private String sbxh;
	
	/**
	 *厂家
	 */
	private String cj;


	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tc04_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="sbmc"
	 * @return Returns the sbmc.
	 */
	public String getSbmc() {
		return sbmc;
	}

	/**
	 * @param sbmc The sbmc to set.
	 */
	public void setSbmc(String sbmc) {
		this.sbmc = sbmc;
	}

	/**
	 * @hibernate.property column="sbxh"
	 * @return Returns the sbxh.
	 */
	public String getSbxh() {
		return sbxh;
	}

	/**
	 * @param sbxh The sbxh to set.
	 */
	public void setSbxh(String sbxh) {
		this.sbxh = sbxh;
	}
	
	/**
	 * @hibernate.property column="cj"
	 * @return Returns the cj.
	 */
	public String getCj() {
		return cj;
	}

	/**
	 * @param cj The cj to set.
	 */
	public void setCj(String cj) {
		this.cj = cj;
	}

	
}
