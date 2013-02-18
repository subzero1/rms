package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * 日常考核
 * 
 * @hibernate.class table="Tf17_rckh"
 */

public class Tf17_rckh implements Serializable {

	private static final long serialVersionUID = 8784281934867736850L;
	private Long id;
	private Long wxdw_id;
	private String wxdw_lb;
	private String wxdw_mc;
	private Long khry_id;
	private String khry_name;
	private String khyy;
	private String khlb;
	private Double fkje;
	private Double jkfz;
	private String khjg;
	private Date qrsj;
	private Long wzdl;
	private Date khsj;
	private String khdz;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="TF17_NUM"
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
	 * @hibernate.property column="khry_id"
	 * @return Returns the khry_id.
	 */
	public Long getKhry_id() {
		return khry_id;
	}

	/**
	 * @param khry_id
	 *            The khry_id to set.
	 */
	public void setKhry_id(Long khry_id) {
		this.khry_id = khry_id;
	}

	/**
	 * @hibernate.property column="khry_name"
	 * @return Returns the khry_name.
	 */
	public String getKhry_name() {
		return khry_name;
	}

	/**
	 * @param khry_name
	 *            The khry_name to set.
	 */
	public void setKhry_name(String khry_name) {
		this.khry_name = khry_name;
	}

	/**
	 * @hibernate.property column="khyy"
	 * @return Returns the khyy.
	 */
	public String getKhyy() {
		return khyy;
	}

	/**
	 * @param khyy
	 *            The khyy to set.
	 */
	public void setKhyy(String khyy) {
		this.khyy = khyy;
	}

	/**
	 * @hibernate.property column="khlb"
	 * @return Returns the khlb.
	 */
	public String getKhlb() {
		return khlb;
	}

	/**
	 * @param khlb
	 *            The khlb to set.
	 */
	public void setKhlb(String khlb) {
		this.khlb = khlb;
	}

	/**
	 * @hibernate.property column="fkje"
	 * @return Returns the fkje.
	 */
	public Double getFkje() {
		return fkje;
	}

	/**
	 * @param fkje
	 *            The fkje to set.
	 */
	public void setFkje(Double fkje) {
		this.fkje = fkje;
	}

	/**
	 * @hibernate.property column="jkfz"
	 * @return Returns the jkfz.
	 */
	public Double getJkfz() {
		return jkfz;
	}

	/**
	 * @param jkfz
	 *            The jkfz to set.
	 */
	public void setJkfz(Double jkfz) {
		this.jkfz = jkfz;
	}

	/**
	 * @hibernate.property column="khjg"
	 * @return Returns the khjg.
	 */
	public String getKhjg() {
		return khjg;
	}

	/**
	 * @param khjg
	 *            The khjg to set.
	 */
	public void setKhjg(String khjg) {
		this.khjg = khjg;
	}

	/**
	 * @hibernate.property column="qrsj"
	 * @return Returns the qrsj.
	 */
	public Date getQrsj() {
		return qrsj;
	}

	/**
	 * @param qrsj
	 *            The qrsj to set.
	 */
	public void setQrsj(Date qrsj) {
		this.qrsj = qrsj;
	}

	/**
	 * @hibernate.property column="wzdl"
	 * @return Returns the wzdl.
	 */
	public Long getWzdl() {
		return wzdl;
	}

	/**
	 * @param wzdl
	 *            The wzdl to set.
	 */
	public void setWzdl(Long wzdl) {
		this.wzdl = wzdl;
	}

	/**
	 * @hibernate.property column="khsj"
	 * @return Returns the khsj.
	 */
	public Date getKhsj() {
		return khsj;
	}

	/**
	 * @param khsj
	 *            The khsj to set.
	 */
	public void setKhsj(Date khsj) {
		this.khsj = khsj;
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
	 * @hibernate.property column="khdz"
	 * @return Returns the khdz.
	 */
	public String getKhdz() {
		return khdz;
	}

	public void setKhdz(String khdz) {
		this.khdz = khdz;
	}

	private Long khnf;
	
	private Long khyf;

	/**
	 * @hibernate.property column="khnf"
	 * @return Returns the khnf.
	 */
	public Long getKhnf() {
		return khnf;
	}

	public void setKhnf(Long khnf) {
		this.khnf = khnf;
	}

	/**
	 * @hibernate.property column="khyf"
	 * @return Returns the khyf.
	 */
	public Long getKhyf() {
		return khyf;
	}

	public void setKhyf(Long khyf) {
		this.khyf = khyf;
	}
	
	
}
