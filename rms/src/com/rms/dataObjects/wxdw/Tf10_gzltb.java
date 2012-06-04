package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tf10_gzltb"
 */

public class Tf10_gzltb implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 417579416931116672L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf10_num"
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
 * 
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
 * 填报数量
 */
 private Long tbsl;

 /**
 * @hibernate.property column="tbsl"
 * @return Returns the tbsl.
 */
 public Long getTbsl() {
    return tbsl;
 }

 public void setTbsl(Long tbsl) {
    this.tbsl = tbsl;
  }

 /**
 * 填报日期
 */
 private Date tbrq;

 /**
 * @hibernate.property column="tbrq"
 * @return Returns the tbrq.
 */
 public Date getTbrq() {
    return tbrq;
 }

 public void setTbrq(Date tbrq) {
    this.tbrq = tbrq;
  }

 /**
 * 填报人
 */
 private String tbr;

 /**
 * @hibernate.property column="tbr"
 * @return Returns the tbr.
 */
 public String getTbr() {
    return tbr;
 }

 public void setTbr(String tbr) {
    this.tbr = tbr;
  }

 /**
 * 时间进度
 */
 private Long sjjd;

 /**
 * @hibernate.property column="sjjd"
 * @return Returns the sjjd.
 */
 public Long getSjjd() {
    return sjjd;
 }

 public void setSjjd(Long sjjd) {
    this.sjjd = sjjd;
  }

 /**
 * 填报进度
 */
 private Long tbjd;

 /**
 * @hibernate.property column="tbjd"
 * @return Returns the tbjd.
 */
 public Long getTbjd() {
    return tbjd;
 }

 public void setTbjd(Long tbjd) {
    this.tbjd = tbjd;
  }

 /**
 * 进度描述
 */
 private String jdms;

 /**
 * @hibernate.property column="jdms"
 * @return Returns the jdms.
 */
 public String getJdms() {
    return jdms;
 }

 public void setJdms(String jdms) {
    this.jdms = jdms;
  }


}
