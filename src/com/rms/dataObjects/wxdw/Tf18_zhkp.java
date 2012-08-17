package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * 日常考核
 * 
 * @hibernate.class table="Tf18_zhkp"
 */

public class Tf18_zhkp implements Serializable {

	private static final long serialVersionUID = -158106927953160423L;
	private Long id;
	private Long wxdw_id;
	private String wxdw_lb;
	private String wxdw_mc;
	private String zylb;
	private String zyqy;
	private Double zhdf;
	private Long xms;
	private Double hte;
	private Double jse;
	private Double wgl;
	private Double cql;
	private Double jsl;
	private Date dfsj;
	private Long isdefaulta;
	private Long isdefaultb;
	private Double ascore;
	private Double bscore;
	private Double cscore;
	private Date lastdfsj;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="TF18_NUM"
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
	 * @hibernate.property column="wxdw_lb"
	 * @return Returns the wxdw_lb.
	 */
	public String getWxdw_lb() {
		return wxdw_lb;
	}

	/**
	 * @param wxdw_lb
	 *            The wxdw_lb to set.
	 */
	public void setWxdw_lb(String wxdw_lb) {
		this.wxdw_lb = wxdw_lb;
	}

	/**
	 * @hibernate.property column="wxdw_mc"
	 * @return Returns the wxdw_mc.
	 */
	public String getWxdw_mc() {
		return wxdw_mc;
	}

	/**
	 * @param wxdw_mc
	 *            The wxdw_mc to set.
	 */
	public void setWxdw_mc(String wxdw_mc) {
		this.wxdw_mc = wxdw_mc;
	}

	/**
	 * @hibernate.property column="zylb"
	 * @return Returns the zylb.
	 */
	public String getZylb() {
		return zylb;
	}

	/**
	 * @param zylb
	 *            The zylb to set.
	 */
	public void setZylb(String zylb) {
		this.zylb = zylb;
	}

	/**
	 * @hibernate.property column="zyqy"
	 * @return Returns the zyqy.
	 */
	public String getZyqy() {
		return zyqy;
	}

	/**
	 * @param zyqy
	 *            The zyqy to set.
	 */
	public void setZyqy(String zyqy) {
		this.zyqy = zyqy;
	}

	/**
	 * @hibernate.property column="zhdf"
	 * @return Returns the zhdf.
	 */
	public Double getZhdf() {
		return zhdf;
	}

	/**
	 * @param zhdf
	 *            The zhdf to set.
	 */
	public void setZhdf(Double zhdf) {
		this.zhdf = zhdf;
	}

	/**
	 * @hibernate.property column="xms"
	 * @return Returns the xms.
	 */
	public Long getXms() {
		return xms;
	}

	/**
	 * @param xms
	 *            The xms to set.
	 */
	public void setXms(Long xms) {
		this.xms = xms;
	}

	/**
	 * @hibernate.property column="hte"
	 * @return Returns the hte.
	 */
	public Double getHte() {
		return hte;
	}

	/**
	 * @param hte
	 *            The hte to set.
	 */
	public void setHte(Double hte) {
		this.hte = hte;
	}

	/**
	 * @hibernate.property column="jse"
	 * @return Returns the jse.
	 */
	public Double getJse() {
		return jse;
	}

	/**
	 * @param jse
	 *            The jse to set.
	 */
	public void setJse(Double jse) {
		this.jse = jse;
	}

	/**
	 * @hibernate.property column="wgl"
	 * @return Returns the wgl.
	 */
	public Double getWgl() {
		return wgl;
	}

	/**
	 * @param wgl
	 *            The wgl to set.
	 */
	public void setWgl(Double wgl) {
		this.wgl = wgl;
	}

	/**
	 * @hibernate.property column="cql"
	 * @return Returns the cql.
	 */
	public Double getCql() {
		return cql;
	}

	/**
	 * @param cql
	 *            The cql to set.
	 */
	public void setCql(Double cql) {
		this.cql = cql;
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
	 * @hibernate.property column="dfsj"
	 * @return Returns the dfsj.
	 */
	public Date getDfsj() {
		return dfsj;
	}

	/**
	 * @param dfsj
	 *            The dfsj to set.
	 */
	public void setDfsj(Date dfsj) {
		this.dfsj = dfsj;
	}

	/**
	 * @hibernate.property column="isdefaulta"
	 * @return Returns the isdefaulta.
	 */
	public Long getIsdefaulta() {
		return isdefaulta;
	}

	/**
	 * @param isdefaulta
	 *            The isdefaulta to set.
	 */
	public void setIsdefaulta(Long isdefaulta) {
		this.isdefaulta = isdefaulta;
	}

	/**
	 * @hibernate.property column="isdefaultb"
	 * @return Returns the isdefaultb.
	 */
	public Long getIsdefaultb() {
		return isdefaultb;
	}

	/**
	 * @param isdefaultb
	 *            The isdefaultb to set.
	 */
	public void setIsdefaultb(Long isdefaultb) {
		this.isdefaultb = isdefaultb;
	}

	/**
	 * @hibernate.property column="ascore"
	 * @return Returns the ascore.
	 */
	public Double getAscore() {
		return ascore;
	}

	/**
	 * @param ascore
	 *            The ascore to set.
	 */
	public void setAscore(Double ascore) {
		this.ascore = ascore;
	}

	/**
	 * @hibernate.property column="bscore"
	 * @return Returns the bscore.
	 */
	public Double getBscore() {
		return bscore;
	}

	/**
	 * @param bscore
	 *            The bscore to set.
	 */
	public void setBscore(Double bscore) {
		this.bscore = bscore;
	}

	/**
	 * @hibernate.property column="cscore"
	 * @return Returns the cscore.
	 */
	public Double getCscore() {
		return cscore;
	}

	/**
	 * @param cscore
	 *            The cscore to set.
	 */
	public void setCscore(Double cscore) {
		this.cscore = cscore;
	}

	/**
	 * @hibernate.property column="lastdfsj"
	 * @return Returns the lastdfsj.
	 */
	public Date getLastdfsj() {
		return lastdfsj;
	}

	/**
	 * @param lastdfsj
	 *            The lastdfsj to set.
	 */
	public void setLastdfsj(Date lastdfsj) {
		this.lastdfsj = lastdfsj;
	}

}
