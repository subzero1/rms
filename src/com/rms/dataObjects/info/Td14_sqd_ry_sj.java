package com.rms.dataObjects.info;

import java.util.Date;

/**
 * @description:
 * 申请单人员和时间信息
 * @class name:com.rms.dataObjects.info.Td14_sqd_ry_sj
 * @author lee.xiangyu Apu 10, 2012
 * @hibernate.class table="Td14_sqd_ry_sj"
 */
public class Td14_sqd_ry_sj {
	/**
	 *
	 */
	private Long id;

	/**
	 *
	 */
	private Long project_id;


	/**
	 *项目管理员
	 */
	private String xmgly;

	/**
	 *工程管理员
	 */
	private String gcgly;
	
	/**
	 *规划管理员
	 */
	private String ghgly;
	
	/**
	 *设计管理员
	 */
	private String sjgly;
	
	/**
	 *设计人员
	 */
	private String sjry;
	
	
	/**
	 *需求提出时间
	 */
	private Date xqqcsj;

	/**
	 *图纸下载时间
	 */
	private Date tzxzsj;
	
	/**
	 *图纸上传时间
	 */
	private Date tzscsj;
	
	/**
	 *图纸替换时间
	 */
	private Date tzthsj;
	
	/**
	 *状态
	 */
	private String zt;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Td14_NUM"
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
	 * @hibernate.property column="project_id"
	 * @return Returns the project_id.
	 */
	public Long getProject_id() {
		return project_id;
	}

	/**
	 * @param project_id The project_id to set.
	 */
	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}


	/**
	 * @hibernate.property column="xmgly"
	 * @return Returns the xmgly.
	 */
	public String getXmgly() {
		return xmgly;
	}

	public void setXmgly(String xmgly) {
		this.xmgly = xmgly;
	}

	/**
	 * @hibernate.property column="gcgly"
	 * @return Returns the gcgly.
	 */
	public String getGcgly() {
		return gcgly;
	}

	public void setGcgly(String gcgly) {
		this.gcgly = gcgly;
	}

	/**
	 * @hibernate.property column="ghgly"
	 * @return Returns the ghgly.
	 */
	public String getGhgly() {
		return ghgly;
	}

	public void setGhgly(String ghgly) {
		this.ghgly = ghgly;
	}

	/**
	 * @hibernate.property column="sjgly"
	 * @return Returns the sjgly.
	 */
	public String getSjgly() {
		return sjgly;
	}

	public void setSjgly(String sjgly) {
		this.sjgly = sjgly;
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
	 * @hibernate.property column="xqqcsj"
	 * @return Returns the xqqcsj.
	 */
	public Date getXqqcsj() {
		return xqqcsj;
	}

	public void setXqqcsj(Date xqqcsj) {
		this.xqqcsj = xqqcsj;
	}

	/**
	 * @hibernate.property column="tzxzsj"
	 * @return Returns the tzxzsj.
	 */
	public Date getTzxzsj() {
		return tzxzsj;
	}

	public void setTzxzsj(Date tzxzsj) {
		this.tzxzsj = tzxzsj;
	}

	/**
	 * @hibernate.property column="tzscsj"
	 * @return Returns the tzscsj.
	 */
	public Date getTzscsj() {
		return tzscsj;
	}

	public void setTzscsj(Date tzscsj) {
		this.tzscsj = tzscsj;
	}

	/**
	 * @hibernate.property column="tzthsj"
	 * @return Returns the tzthsj.
	 */
	public Date getTzthsj() {
		return tzthsj;
	}

	public void setTzthsj(Date tzthsj) {
		this.tzthsj = tzthsj;
	}

	/**
	 * @hibernate.property column="zt"
	 * @return Returns the zt.
	 */
	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}
}
