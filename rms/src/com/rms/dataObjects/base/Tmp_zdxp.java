package com.rms.dataObjects.base;

import java.io.Serializable;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="tmp_zdxp"
 */

public class Tmp_zdxp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9120427109609263810L;
	/**
	 * 标识
	 */
	private Long id;
	private String dj;
	private Double pm;
	private Long zhdf;
	private Double jsl;
	private Double jhfezb;
	private Long wxdw_id;
	private Long batch_no;
	private Long project_id;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="zdxp_num"
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
	 * @hibernate.property column="pm"
	 * @return Returns the pm.
	 */
	public Double getPm() {
		return pm;
	}

	/**
	 * @param pm
	 *            The pm to set.
	 */
	public void setPm(Double pm) {
		this.pm = pm;
	}

	/**
	 * @hibernate.property column="zhdf"
	 * @return Returns the zhdf.
	 */
	public Long getZhdf() {
		return zhdf;
	}

	/**
	 * @param zhdf
	 *            The zhdf to set.
	 */
	public void setZhdf(Long zhdf) {
		this.zhdf = zhdf;
	}

	/**
	 * @hibernate.property column="jsl"
	 * @return Returns the jsl.
	 */
	public Double getJsl() {
		return jsl;
	}

	/**
	 * @param jsl
	 *            The jsl to set.
	 */
	public void setJsl(Double jsl) {
		this.jsl = jsl;
	}

	/**
	 * @hibernate.property column="jhfezb"
	 * @return Returns the jhfezb.
	 */
	public Double getJhfezb() {
		return jhfezb;
	}

	/**
	 * @param jhfezb
	 *            The jhfezb to set.
	 */
	public void setJhfezb(Double jhfezb) {
		this.jhfezb = jhfezb;
	}

	/**
	 * @hibernate.property column="wxdw_id"
	 * @return Returns the wxdw_id.
	 */
	public Long getWxdw_id() {
		return wxdw_id;
	}

	/**
	 * @param wxdw_id
	 *            The wxdw_id to set.
	 */
	public void setWxdw_id(Long wxdw_id) {
		this.wxdw_id = wxdw_id;
	}

	/**
	 * @hibernate.property column="batch_no"
	 * @return Returns the batch_no.
	 */
	public Long getBatch_no() {
		return batch_no;
	}

	/**
	 * @param batch_no
	 *            The batch_no to set.
	 */
	public void setBatch_no(Long batch_no) {
		this.batch_no = batch_no;
	}

	/*
	 * 决算数量
	 */
	private Long jssl;
	
	/*
	 * 项目数量
	 */
	private Long xmsl;
	
	/*
	 * 综合得分排名
	 */
	private Long zhdfpm;
	
	/*
	 * 决算率排名
	 */
	private Long jslpm;
	
	/*
	 * 实际份额占比
	 */
	private Double sjfezb;
	
	/*
	 * 份额偏差率
	 */
	private Double fepcl;
	
	/*
	 * 在建项目数
	 */
	private Long zjgcs;
	
	/*
	 * 最大项目数
	 */
	private Long zdgcs;

	public Long getJssl() {
		return jssl;
	}

	public void setJssl(Long jssl) {
		this.jssl = jssl;
	}

	public Long getXmsl() {
		return xmsl;
	}

	public void setXmsl(Long xmsl) {
		this.xmsl = xmsl;
	}

	public Long getZhdfpm() {
		return zhdfpm;
	}

	public void setZhdfpm(Long zhdfpm) {
		this.zhdfpm = zhdfpm;
	}

	public Long getJslpm() {
		return jslpm;
	}

	public void setJslpm(Long jslpm) {
		this.jslpm = jslpm;
	}

	public Double getSjfezb() {
		return sjfezb;
	}

	public void setSjfezb(Double sjfezb) {
		this.sjfezb = sjfezb;
	}

	public Double getFepcl() {
		return fepcl;
	}

	public void setFepcl(Double fepcl) {
		this.fepcl = fepcl;
	}

	public Long getProject_id() {
		return project_id;
	}

	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	public Long getZjgcs() {
		return zjgcs;
	}

	public void setZjgcs(Long zjgcs) {
		this.zjgcs = zjgcs;
	}

	public Long getZdgcs() {
		return zdgcs;
	}

	public void setZdgcs(Long zdgcs) {
		this.zdgcs = zdgcs;
	}
}
