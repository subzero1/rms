package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tf02_sgd"
 */

public class Tf02_sgd implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 732632588701686400L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf02_num"
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
 * 施工单位标识
 */
 private Long sgdw_id;

 /**
 * @hibernate.property column="sgdw_id"
 * @return Returns the sgdw_id.
 */
 public Long getSgdw_id() {
    return sgdw_id;
 }

 public void setSgdw_id(Long sgdw_id) {
    this.sgdw_id = sgdw_id;
  }

 /**
 * 施工队名称
 */
 private String mc;

 /**
 * @hibernate.property column="mc"
 * @return Returns the mc.
 */
 public String getMc() {
    return mc;
 }

 public void setMc(String mc) {
    this.mc = mc;
  }

 /**
 * 备注
 */
 private String bz;

 /**
 * @hibernate.property column="bz"
 * @return Returns the bz.
 */
 public String getBz() {
    return bz;
 }

 public void setBz(String bz) {
    this.bz = bz;
  }


}
