package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-12-25
 * @hibernate.class table="Te12_wdcs"
 */

public class Te12_wdcs implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 557487435145927104L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="te12_num"
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
 * 文档标识
 */
 private Long doc_id;

 /**
 * @hibernate.property column="doc_id"
 * @return Returns the doc_id.
 */
 public Long getDoc_id() {
    return doc_id;
 }

 public void setDoc_id(Long doc_id) {
    this.doc_id = doc_id;
  }

 /**
 * 操作人
 */
 private String czr;

 /**
 * @hibernate.property column="czr"
 * @return Returns the czr.
 */
 public String getCzr() {
    return czr;
 }

 public void setCzr(String czr) {
    this.czr = czr;
  }

 /**
 * 操作时间
 */
 private Date czsj;

 /**
 * @hibernate.property column="czsj"
 * @return Returns the czsj.
 */
 public Date getCzsj() {
    return czsj;
 }

 public void setCzsj(Date czsj) {
    this.czsj = czsj;
  }

 /**
 * 操作类型（下载、查看）
 */
 private String czlx;

 /**
 * @hibernate.property column="czlx"
 * @return Returns the czlx.
 */
 public String getCzlx() {
    return czlx;
 }

 public void setCzlx(String czlx) {
    this.czlx = czlx;
  }


}
