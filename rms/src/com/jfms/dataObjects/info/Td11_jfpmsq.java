package com.jfms.dataObjects.info;

import java.util.Date;

/**
 * @description: 设备信息表
 * @class name:com.jfms.dataObjects.info.Td11_jfpmsq
 * @author Administrator Jul 26, 2011
 * @hibernate.class table="Td11_jfpmsq"
 */
public class Td11_jfpmsq {
	/**
	 *关联ID，（当变更时关联申请单）
	 */
	private Long ref_id;

	/**
	 *
	 */
	private Long id;

	/**
	 *
	 */
	private Long project_id;

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
	 *设计单位
	 */
	private String sjdw;
	
	/**
	 *设计人员
	 */
	private String sjry;
	
	/**
	 *设计时限
	 */
	private Long sjsx;
	
	/**
	 *是否中止
	 */
	private String sfzz;

	/**
	 * @hibernate.property column="ref_id"
	 * @return Returns the ref_id.
	 */
	public Long getRef_id() {
		return ref_id;
	}

	/**
	 * @param ref_id The ref_id to set.
	 */
	public void setRef_id(Long ref_id) {
		this.ref_id = ref_id;
	}

	/**
	 * @hibernate.property column="sjdw"
	 * @return Returns the sjdw.
	 */
	public String getSjdw() {
		return sjdw;
	}

	/**
	 * @param sjdw The sjdw to set.
	 */
	public void setSjdw(String sjdw) {
		this.sjdw = sjdw;
	}
	
	/**
	 * @hibernate.property column="sjry"
	 * @return Returns the sjry.
	 */
	public String getSjry() {
		return sjry;
	}

	public void setSjry(String sjry) {
		this.sjry = sjry;
	}
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Td11_NUM"
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
	 * @hibernate.property column="project_id"
	 * @return the project_id
	 */
	public Long getProject_id() {
		return project_id;
	}

	/**
	 * @param project_id the project_id to set
	 */
	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	/**
	 * @hibernate.property column="fwbm"
	 * @return the fwbm
	 */
	public String getFwbm() {
		return fwbm;
	}

	/**
	 * @param fwbm the fwbm to set
	 */
	public void setFwbm(String fwbm) {
		this.fwbm = fwbm;
	}

	/**
	 * @hibernate.property column="bdbh"
	 * @return the bdbh
	 */
	public String getBdbh() {
		return bdbh;
	}

	/**
	 * @param bdbh the bdbh to set
	 */
	public void setBdbh(String bdbh) {
		this.bdbh = bdbh;
	}

	/**
	 * @hibernate.property column="cjr"
	 * @return the cjr
	 */
	public String getCjr() {
		return cjr;
	}

	/**
	 * @param cjr the cjr to set
	 */
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	/**
	 * @hibernate.property column="cjrdh"
	 * @return the cjrdh
	 */
	public String getCjrdh() {
		return cjrdh;
	}

	/**
	 * @param cjrdh the cjrdh to set
	 */
	public void setCjrdh(String cjrdh) {
		this.cjrdh = cjrdh;
	}

	/**
	 * @hibernate.property column="cjrq"
	 * @return the cjrq
	 */
	public Date getCjrq() {
		return cjrq;
	}

	/**
	 * @param cjrq the cjrq to set
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
	 * @param xmmc
	 *            The xmmc to set.
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
	 * @param jsxz
	 *            The jsxz to set.
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
	 * @param sszy
	 *            The sszy to set.
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
	 * @param bz
	 * The bz to set.
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @hibernate.property column="sjsx"
	 * @return Returns the sjsx.
	 */
	public Long getSjsx() {
		return sjsx;
	}

	public void setSjsx(Long sjsx) {
		this.sjsx = sjsx;
	}

	/**
	 * @hibernate.property column="sfzz"
	 * @return Returns the sfzz.
	 */
	public String getSfzz() {
		return sfzz;
	}

	public void setSfzz(String sfzz) {
		this.sfzz = sfzz;
	}
	

}
