package com.rms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-03-18
 * @hibernate.class table="Td56_event"
 */

public class Td56_event implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 204802580124099456L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td56_num"
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
 * 环节
 */
 private String node;

 /**
 * @hibernate.property column="node"
 * @return Returns the node.
 */
 public String getNode() {
    return node;
 }

 public void setNode(String node) {
    this.node = node;
  }

 /**
 * 事件名称
 */
 private String sjmc;

 /**
 * @hibernate.property column="sjmc"
 * @return Returns the sjmc.
 */
 public String getSjmc() {
    return sjmc;
 }

 public void setSjmc(String sjmc) {
    this.sjmc = sjmc;
  }

 /**
 * 工单状态
 */
 private String gdzt;

 /**
 * @hibernate.property column="gdzt"
 * @return Returns the gdzt.
 */
 public String getGdzt() {
    return gdzt;
 }

 public void setGdzt(String gdzt) {
    this.gdzt = gdzt;
  }

 /**
 * 接收人
 */
 private String jsr;

 /**
 * @hibernate.property column="jsr"
 * @return Returns the jsr.
 */
 public String getJsr() {
    return jsr;
 }

 public void setJsr(String jsr) {
    this.jsr = jsr;
  }

 /**
 * 处理人
 */
 private String clr;

 /**
 * @hibernate.property column="clr"
 * @return Returns the clr.
 */
 public String getClr() {
    return clr;
 }

 public void setClr(String clr) {
    this.clr = clr;
  }

 /**
 * 完成时间
 */
 private String wcsj;

 /**
 * @hibernate.property column="wcsj"
 * @return Returns the wcsj.
 */
 public String getWcsj() {
    return wcsj;
 }

 public void setWcsj(String wcsj) {
    this.wcsj = wcsj;
  }

 /**
 * 处理结果
 */
 private String cljg;

 /**
 * @hibernate.property column="cljg"
 * @return Returns the cljg.
 */
 public String getCljg() {
    return cljg;
 }

 public void setCljg(String cljg) {
    this.cljg = cljg;
  }


}
