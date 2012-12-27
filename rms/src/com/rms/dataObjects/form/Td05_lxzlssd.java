package com.rms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-12-27
 * @hibernate.class table="Td05_lxzlssd"
 */

public class Td05_lxzlssd implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 908362914378390016L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td05_num"
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
 * 工程ID
 */
 private Long project_id;

 /**
 * @hibernate.property column="project_id"
 * @return Returns the project_id.
 */
 public Long getProject_id() {
    return project_id;
 }

 public void setProject_id(Long project_id) {
    this.project_id = project_id;
  }

 /**
 * 发文部门
 */
 private String fwbm;

 /**
 * @hibernate.property column="fwbm"
 * @return Returns the fwbm.
 */
 public String getFwbm() {
    return fwbm;
 }

 public void setFwbm(String fwbm) {
    this.fwbm = fwbm;
  }

 /**
 * 表单编号
 */
 private String bdbh;

 /**
 * @hibernate.property column="bdbh"
 * @return Returns the bdbh.
 */
 public String getBdbh() {
    return bdbh;
 }

 public void setBdbh(String bdbh) {
    this.bdbh = bdbh;
  }

 /**
 * 创建人
 */
 private String cjr;

 /**
 * @hibernate.property column="cjr"
 * @return Returns the cjr.
 */
 public String getCjr() {
    return cjr;
 }

 public void setCjr(String cjr) {
    this.cjr = cjr;
  }

 /**
 * 创建人电话
 */
 private String cjrdh;

 /**
 * @hibernate.property column="cjrdh"
 * @return Returns the cjrdh.
 */
 public String getCjrdh() {
    return cjrdh;
 }

 public void setCjrdh(String cjrdh) {
    this.cjrdh = cjrdh;
  }

 /**
 * 创建日期
 */
 private Date cjrq;

 /**
 * @hibernate.property column="cjrq"
 * @return Returns the cjrq.
 */
 public Date getCjrq() {
    return cjrq;
 }

 public void setCjrq(Date cjrq) {
    this.cjrq = cjrq;
  }


}
