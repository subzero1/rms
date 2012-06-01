package com.rms.dataObjects.wxdw;

import java.io.Serializable;

/**
 * @author mengying 2009-12-11
 * @hibernate.class table="tf05_wxdw_dygx"
 */
public class Tf05_wxdw_dygx implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7032663529465286732L;
	/**
	 * 标识
	 */
	private Long id;

	/**
	 * 类型(qyzy：区域专业；fezb：份额占比；zdgcs：最大工程数；gljye：关联交易额；sgd：施工队)
	 */
	private String lb;

	/**
	 * 外协单位tf01.ID (当lb='sgd'时，施工队标识tf02.id)
	 */
	private Long wxdw_id;
	/**
	 * 专业
	 */
	private String zy;
	/**
	 * 作业区域
	 */
	private String dq;
	/**
	 * 年度
	 */
	private Long nd;
	/**
	 * 扩展字段1
	 */
	private String v1;
	/**
	 * 扩展字段2
	 */
	private String v2;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="TF05_NUM"
	 * @return Returns the id.
	 */

	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="lb"
	 * @return Returns the lb.
	 */
	public String getLb() {
		return lb;
	}

	/**
	 * @param lb
	 *            The lb to set.
	 */
	public void setLb(String lb) {
		this.lb = lb;
	}

	/**
	 * @hibernate.property column="wxdw_id"
	 * @return Returns the wxdw_id.
	 */
	public Long getWxdw_id() {
		return wxdw_id;
	}

	/**
	 * @param wxdw_id
	 *            The wxdw_id to set.
	 */
	public void setWxdw_id(Long wxdw_id) {
		this.wxdw_id = wxdw_id;
	}

	/**
	 * @hibernate.property column="zy"
	 * @return Returns the zy.
	 */
	public String getZy() {
		return zy;
	}

	/**
	 * @param zy
	 *            The zy to set.
	 */
	public void setZy(String zy) {
		this.zy = zy;
	}

	/**
	 * @hibernate.property column="dq"
	 * @return Returns the dq.
	 */
	public String getDq() {
		return dq;
	}

	/**
	 * @param dq
	 *            The dq to set.
	 */
	public void setDq(String dq) {
		this.dq = dq;
	}

	/**
	 * @hibernate.property column="nd"
	 * @return Returns the nd.
	 */
	public Long getNd() {
		return nd;
	}

	/**
	 * @param nd
	 *            The nd to set.
	 */
	public void setNd(Long nd) {
		this.nd = nd;
	}

	/**
	 * @hibernate.property column="v1"
	 * @return Returns the v1.
	 */
	public String getV1() {
		return v1;
	}

	/**
	 * @param v1
	 *            The v1 to set.
	 */
	public void setV1(String v1) {
		this.v1 = v1;
	}

	/**
	 * @hibernate.property column="v2"
	 * @return Returns the v2.
	 */
	public String getV2() {
		return v2;
	}

	/**
	 * @param v2
	 *            The v2 to set.
	 */
	public void setV2(String v2) {
		this.v2 = v2;
	}

}
