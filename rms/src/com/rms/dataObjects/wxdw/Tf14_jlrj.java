package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tf14_jlrj"
 */

public class Tf14_jlrj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 437237503692780672L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf14_num"
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
 * 工程标识
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
 * 用户标识
 */
 private Long user_id;

 /**
 * @hibernate.property column="user_id"
 * @return Returns the user_id.
 */
 public Long getUser_id() {
    return user_id;
 }

 public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

 /**
 * 记录日期
 */
 private Date create_date;

 /**
 * @hibernate.property column="create_date"
 * @return Returns the create_date.
 */
 public Date getCreate_date() {
    return create_date;
 }

 public void setCreate_date(Date create_date) {
    this.create_date = create_date;
  }

 /**
 * 天气
 */
 private String tq;

 /**
 * @hibernate.property column="tq"
 * @return Returns the tq.
 */
 public String getTq() {
    return tq;
 }

 public void setTq(String tq) {
    this.tq = tq;
  }

 /**
 * 施工进度
 */
 private String jd;

 /**
 * @hibernate.property column="jd"
 * @return Returns the jd.
 */
 public String getJd() {
    return jd;
 }

 public void setJd(String jd) {
    this.jd = jd;
  }

 /**
 * 材料问题
 */
 private String clwt;

 /**
 * @hibernate.property column="clwt"
 * @return Returns the clwt.
 */
 public String getClwt() {
    return clwt;
 }

 public void setClwt(String clwt) {
    this.clwt = clwt;
  }

 /**
 * 工程质量
 */
 private String gczl;

 /**
 * @hibernate.property column="gczl"
 * @return Returns the gczl.
 */
 public String getGczl() {
    return gczl;
 }

 public void setGczl(String gczl) {
    this.gczl = gczl;
  }

 /**
 * 发现问题及处理措施
 */
 private String wtjcs;

 /**
 * @hibernate.property column="wtjcs"
 * @return Returns the wtjcs.
 */
 public String getWtjcs() {
    return wtjcs;
 }

 public void setWtjcs(String wtjcs) {
    this.wtjcs = wtjcs;
  }

 /**
 * 其它
 */
 private String qt;

 /**
 * @hibernate.property column="qt"
 * @return Returns the qt.
 */
 public String getQt() {
    return qt;
 }

 public void setQt(String qt) {
    this.qt = qt;
  }


}
