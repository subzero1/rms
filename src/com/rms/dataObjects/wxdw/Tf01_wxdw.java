package com.rms.dataObjects.wxdw;

import java.io.Serializable;

/**
 * @author mengying 2009-12-11
 * @hibernate.class table="tf01_wxdw"
 */
public class Tf01_wxdw implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 6958067704854242044L;

	/**
	 * 标识
	 */
	private Long id;

	/**
	 * 名称
	 */
	private String mc;

	/**
	 * real名称
	 */
	private String realname;

	/**
	 * 单位地址
	 */
	private String dwdz;

	/**
	 * 资质等级
	 */
	private String zzdj;

	/**
	 * 营业执照号
	 */
	private String yyzzh;

	/**
	 * 证书号
	 */
	private String zsh;

	/**
	 * 法人
	 */
	private String fr;

	/**
	 * 外协类别（设计，施工，监理，审计）
	 */
	private String lb;

	/**
	 * 承接区域（同td00_zhxx.ssdq,多选）
	 */
	private String qy;

	/**
	 * 承接专业(同td00_zhxx.gclb，多选)
	 */
	private String zy;

	/**
	 * 状态（是否使用）
	 */
	private String zt;

	/**
	 * 备注
	 */
	private String bz;

	/**
	 * 结算系数
	 */
	private Double jsxs;

	/**
	 * 最大承接工日
	 */
	private Double zdcjgr;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="TF01_NUM"
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
	 * @hibernate.property column="mc"
	 * @return the mc
	 */
	public String getMc() {
		return mc;
	}

	/**
	 * @param mc
	 *            the mc to set
	 */

	public void setMc(String mc) {
		this.mc = mc;
	}

	/**
	 * @hibernate.property column="dwdz"
	 * @return the dwdz
	 */

	public String getDwdz() {
		return dwdz;
	}

	/**
	 * @param dwdz
	 *            the dwdz to set
	 */

	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
	}

	/**
	 * @hibernate.property column="zzdj"
	 * @return the zzdj
	 */

	public String getZzdj() {
		return zzdj;
	}

	/**
	 * @param zzdj
	 *            the zzdj to set
	 */

	public void setZzdj(String zzdj) {
		this.zzdj = zzdj;
	}

	/**
	 * @hibernate.property column="yyzzh"
	 * @return the yyzzh
	 */

	public String getYyzzh() {
		return yyzzh;
	}

	/**
	 * @param yyzzh
	 *            the yyzzh to set
	 */

	public void setYyzzh(String yyzzh) {
		this.yyzzh = yyzzh;
	}

	/**
	 * @hibernate.property column="fr"
	 * @return the fr
	 */

	public String getFr() {
		return fr;
	}

	/**
	 * @param fr
	 *            the fr to set
	 */

	public void setFr(String fr) {
		this.fr = fr;
	}

	/**
	 * @hibernate.property column="lb"
	 * @return the lb
	 */

	public String getLb() {
		return lb;
	}

	/**
	 * @param lb
	 *            the lb to set
	 */

	public void setLb(String lb) {
		this.lb = lb;
	}

	/**
	 * @hibernate.property column="qy"
	 * @return the qy
	 */

	public String getQy() {
		return qy;
	}

	/**
	 * @param qy
	 *            the qy to set
	 */

	public void setQy(String qy) {
		this.qy = qy;
	}

	/**
	 * @hibernate.property column="zy"
	 * @return the zy
	 */

	public String getZy() {
		return zy;
	}

	/**
	 * @param zy
	 *            the zy to set
	 */

	public void setZy(String zy) {
		this.zy = zy;
	}

	/**
	 * @hibernate.property column="zt"
	 * @return the zt
	 */

	public String getZt() {
		return zt;
	}

	/**
	 * @param zt
	 *            the zt to set
	 */

	public void setZt(String zt) {
		this.zt = zt;
	}

	/**
	 * @hibernate.property column="bz"
	 * @return the bz
	 */

	public String getBz() {
		return bz;
	}

	/**
	 * @param bz
	 *            the bz to set
	 */

	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @hibernate.property column="zsh"
	 * @return Returns the zsh.
	 */
	public String getZsh() {
		return zsh;
	}

	/**
	 * @param zsh
	 *            The zsh to set.
	 */
	public void setZsh(String zsh) {
		this.zsh = zsh;
	}

	/**
	 * @hibernate.property column="jsxs"
	 * @return Returns the jsxs.
	 */
	public Double getJsxs() {
		return jsxs;
	}

	/**
	 * @param jsxs
	 *            The jsxs to set.
	 */
	public void setJsxs(Double jsxs) {
		this.jsxs = jsxs;
	}

	/**
	 * @hibernate.property column="zdcjgr"
	 * @return Returns the zdcjgr.
	 */
	public Double getZdcjgr() {
		return zdcjgr;
	}

	/**
	 * @param zdcjgr
	 *            The zdcjgr to set.
	 */
	public void setZdcjgr(Double zdcjgr) {
		this.zdcjgr = zdcjgr;
	}

	/**
	 * @hibernate.property column="realname"
	 * @return Returns the realname.
	 */
	public String getRealname() {
		return realname;
	}

	/**
	 * @param realname
	 *            The realname to set.
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

}
