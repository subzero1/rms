package com.rms.dataObjects.base;

import java.io.Serializable;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="tmp_zdxp"
 */

public class Tmp_zdxp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9120427109609263810L;
	/**
	 * 标识
	 */
	private Long id;
	private String dj;
	private Double pm;
	private Long zhdf;
	private Double jsl;
	private Double jhfezb;
	private Long wxdw_id;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="zdxp_num"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="dj"
	 * @return Returns the dj.
	 */
	public String getDj() {
		return dj;
	}

	/**
	 * @param dj
	 *            The dj to set.
	 */
	public void setDj(String dj) {
		this.dj = dj;
	}

	/**
	 * @hibernate.property column="pm"
	 * @return Returns the pm.
	 */
	public Double getPm() {
		return pm;
	}

	/**
	 * @param pm
	 *            The pm to set.
	 */
	public void setPm(Double pm) {
		this.pm = pm;
	}

	/**
	 * @hibernate.property column="zhdf"
	 * @return Returns the zhdf.
	 */
	public Long getZhdf() {
		return zhdf;
	}

	/**
	 * @param zhdf
	 *            The zhdf to set.
	 */
	public void setZhdf(Long zhdf) {
		this.zhdf = zhdf;
	}

	/**
	 * @hibernate.property column="jsl"
	 * @return Returns the jsl.
	 */
	public Double getJsl() {
		return jsl;
	}

	/**
	 * @param jsl
	 *            The jsl to set.
	 */
	public void setJsl(Double jsl) {
		this.jsl = jsl;
	}

	/**
	 * @hibernate.property column="jhfezb"
	 * @return Returns the jhfezb.
	 */
	public Double getJhfezb() {
		return jhfezb;
	}

	/**
	 * @param jhfezb
	 *            The jhfezb to set.
	 */
	public void setJhfezb(Double jhfezb) {
		this.jhfezb = jhfezb;
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

}
