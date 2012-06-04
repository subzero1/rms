package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tf15_khxwh"
 */

public class Tf15_khxwh implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 775132191597806080L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf15_num"
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
 * 考核项
 */
 private String khx;

 /**
 * @hibernate.property column="khx"
 * @return Returns the khx.
 */
 public String getKhx() {
    return khx;
 }

 public void setKhx(String khx) {
    this.khx = khx;
  }

 /**
 * 描述
 */
 private String ms;

 /**
 * @hibernate.property column="ms"
 * @return Returns the ms.
 */
 public String getMs() {
    return ms;
 }

 public void setMs(String ms) {
    this.ms = ms;
  }

 /**
 * 考核分值
 */
 private Long fz;

 /**
 * @hibernate.property column="fz"
 * @return Returns the fz.
 */
 public Long getFz() {
    return fz;
 }

 public void setFz(Long fz) {
    this.fz = fz;
  }

 /**
 * 计算方式
 */
 private String jsfs;

 /**
 * @hibernate.property column="jsfs"
 * @return Returns the jsfs.
 */
 public String getJsfs() {
    return jsfs;
 }

 public void setJsfs(String jsfs) {
    this.jsfs = jsfs;
  }


}
