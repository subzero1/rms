package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tf05_wxdw_dygx"
 */

public class Tf05_wxdw_dygx implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 446010405762996416L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf05_num"
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
 * 类型(qyzy：区域专业；fezb：份额占比；zdgcs：最大工程数；gljye：关联交易额；sgd：施工队)
 */
 private String lb;

 /**
 * @hibernate.property column="lb"
 * @return Returns the lb.
 */
 public String getLb() {
    return lb;
 }

 public void setLb(String lb) {
    this.lb = lb;
  }

 /**
 * 外协单位tf01.ID (当lb='sgd'时，施工队标识tf02.id)
 */
 private Long wxdw_id;

 /**
 * @hibernate.property column="wxdw_id"
 * @return Returns the wxdw_id.
 */
 public Long getWxdw_id() {
    return wxdw_id;
 }

 public void setWxdw_id(Long wxdw_id) {
    this.wxdw_id = wxdw_id;
  }

 /**
 * 专业
 */
 private String zy;

 /**
 * @hibernate.property column="zy"
 * @return Returns the zy.
 */
 public String getZy() {
    return zy;
 }

 public void setZy(String zy) {
    this.zy = zy;
  }

 /**
 * 作业区域
 */
 private String dq;

 /**
 * @hibernate.property column="dq"
 * @return Returns the dq.
 */
 public String getDq() {
    return dq;
 }

 public void setDq(String dq) {
    this.dq = dq;
  }

 /**
 * 年度
 */
 private Long nd;

 /**
 * @hibernate.property column="nd"
 * @return Returns the nd.
 */
 public Long getNd() {
    return nd;
 }

 public void setNd(Long nd) {
    this.nd = nd;
  }

 /**
 * 扩展字段1
 */
 private Long v1;

 /**
 * @hibernate.property column="v1"
 * @return Returns the v1.
 */
 public Long getV1() {
    return v1;
 }

 public void setV1(Long v1) {
    this.v1 = v1;
  }

 /**
 * 扩展字段2
 */
 private String v2;

 /**
 * @hibernate.property column="v2"
 * @return Returns the v2.
 */
 public String getV2() {
    return v2;
 }

 public void setV2(String v2) {
    this.v2 = v2;
  }


}
