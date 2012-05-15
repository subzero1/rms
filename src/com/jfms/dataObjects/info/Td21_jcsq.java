package com.jfms.dataObjects.info;

import java.util.Date;

/**
 * @description: 机房信息表
 * @class name:com.jfms.dataObjects.info.Td21_jcsq
 * @author Administrator Jul 26, 2011
 * @hibernate.class table="Td21_jcsq"
 */
public class Td21_jcsq {
	/**
	 * 
	 */
	private Long id;

	/**
	 *机房标识
	 */
	private Long jf_id;
	
	/**
	 * 机房名称
	 */
	private String jfmc;

	/**
	 * 局点名称
	 */
	private String jdmc;
	
	/**
	 * 申请时间
	 */
	private Date sqsj;

	/**
	 * 申请人
	 */
	private String sqr;

	/**
	 * 错误说明
	 */
	private String cwsm;
	

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Td21_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @hibernate.property column="jf_id"
	 * @return Returns the jf_id.
	 */
	public Long getJf_id() {
		return jf_id;
	}

	/**
	 * @param jf_id The jf_id to set.
	 */
	public void setJf_id(Long jf_id) {
		this.jf_id = jf_id;
	}

	/**
	 * @hibernate.property column="jfmc"
	 * @return Returns the jfmc.
	 */
	public String getJfmc() {
		return jfmc;
	}

	/**
	 * @param jfmc
	 *            The jfmc to set.
	 */
	public void setJfmc(String jfmc) {
		this.jfmc = jfmc;
	}
	
	
	/**
	 * @hibernate.property column="jdmc"
	 * @return Returns the jdmc.
	 */
	public String getJdmc() {
		return jdmc;
	}

	/**
	 * @param jdmc
	 *            The jdmc to set.
	 */
	public void setJdmc(String jdmc) {
		this.jdmc = jdmc;
	}

	/**
	 * @hibernate.property column="sqsj"
	 * @return Returns the sqsj.
	 */
	public Date getSqsj() {
		return sqsj;
	}

	/**
	 * @param sqsj
	 *            The sqsj to set.
	 */
	public void setSqsj(Date sqsj) {
		this.sqsj = sqsj;
	}

	/**
	 * @hibernate.property column="sqr"
	 * @return Returns the sqr.
	 */
	public String getSqr() {
		return sqr;
	}

	/**
	 * @param sqr
	 *            The sqr to set.
	 */
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}

	/**
	 * @hibernate.property column="cwsm"
	 * @return Returns the cwsm.
	 */
	public String getCwsm() {
		return cwsm;
	}

	/**
	 * @param cwsm
	 *            The cwsm to set.
	 */
	public void setCwsm(String cwsm) {
		this.cwsm = cwsm;
	}
	
}
