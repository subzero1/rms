package com.netsky.base.flow.vo;

import java.util.Date;

/**
 * @description:
 * 
 * @class name:com.netsky.base.flow.vo.Project_info
 * @author Administrator Jul 28, 2011
 * @hibernate.class table="Project_info" 
 */

public class ProjectInf {

	/**
	 * 标识
	 */
	private Long id;

	/**
	 *发文部门
	 */
	private String fwbm;

	/**
	 *表单编号
	 */
	private String bdbh;

	/**
	 *创建人
	 */
	private String cjr;

	/**
	 *创建人电话
	 */
	private String cjrdh;

	/**
	 *创建日期
	 */
	private Date cjrq;

	/**
	 *项目名称
	 */
	private String xmmc;

	/**
	 *建设性质
	 */
	private String jsxz;

	/**
	 *所属专业
	 */
	private String sszy;

	/**
	 *备注
	 */
	private String bz;

	/**
	 * @hibernate.id generator-class="assigned"
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
	 * @hibernate.property column="fwbm"
	 * @return Returns the fwbm.
	 */
	public String getFwbm() {
		return fwbm;
	}

	/**
	 * @param fwbm The fwbm to set.
	 */
	public void setFwbm(String fwbm) {
		this.fwbm = fwbm;
	}

	/**
	 * @hibernate.property column="bdbh"
	 * @return Returns the bdbh.
	 */
	public String getBdbh() {
		return bdbh;
	}

	/**
	 * @param bdbh The bdbh to set.
	 */
	public void setBdbh(String bdbh) {
		this.bdbh = bdbh;
	}

	/**
	 * @hibernate.property column="cjr"
	 * @return Returns the cjr.
	 */
	public String getCjr() {
		return cjr;
	}

	/**
	 * @param cjr The cjr to set.
	 */
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	/**
	 * @hibernate.property column="cjrdh"
	 * @return Returns the cjrdh.
	 */
	public String getCjrdh() {
		return cjrdh;
	}

	/**
	 * @param cjrdh The cjrdh to set.
	 */
	public void setCjrdh(String cjrdh) {
		this.cjrdh = cjrdh;
	}

	/**
	 * @hibernate.property column="cjrq"
	 * @return Returns the cjrq.
	 */
	public Date getCjrq() {
		return cjrq;
	}

	/**
	 * @param cjrq The cjrq to set.
	 */
	public void setCjrq(Date cjrq) {
		this.cjrq = cjrq;
	}

	/**
	 * @hibernate.property column="xmmc"
	 * @return Returns the xmmc.
	 */
	public String getXmmc() {
		return xmmc;
	}

	/**
	 * @param xmmc The xmmc to set.
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	/**
	 * @hibernate.property column="jsxz"
	 * @return Returns the jsxz.
	 */
	public String getJsxz() {
		return jsxz;
	}

	/**
	 * @param jsxz The jsxz to set.
	 */
	public void setJsxz(String jsxz) {
		this.jsxz = jsxz;
	}

	/**
	 * @hibernate.property column="sszy"
	 * @return Returns the sszy.
	 */
	public String getSszy() {
		return sszy;
	}

	/**
	 * @param sszy The sszy to set.
	 */
	public void setSszy(String sszy) {
		this.sszy = sszy;
	}

	/**
	 * @hibernate.property column="bz"
	 * @return Returns the bz.
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bz The bz to set.
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

}
