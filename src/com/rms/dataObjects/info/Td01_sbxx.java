package com.rms.dataObjects.info;

import java.util.Date;

/**
 * @description:
 * 设备信息表
 * @class name:com.rms.dataObjects.info.Td01_sbxx
 * @author Administrator Jul 26, 2011
 * @hibernate.class table="Td01_sbxx"
 */
public class Td01_sbxx {
	/**
	 *
	 */
	private Long id;

	/**
	 *机房信息标识
	 */
	private Long jfxx_id;

	/**
	 *设备名称
	 */
	private String sbmc;

	/**
	 *设备型号
	 */
	private String sbxh;

	/**
	 *设备规格
	 */
	private String sbgg;
	
	/**
	 *机架尺寸
	 */
	private String jjcc;

	/**
	 *厂家
	 */
	private String cj;

	/**
	 *供电方式：交流、直流、高压直流
	 */
	private String gdfs;

	/**
	 *所属专业
	 */
	private String sszy;

	/**
	 *安装位置
	 */
	private String azwz;

	/**
	 *建设性质：新建、扩容、缩容、搬迁、拆除
	 */
	private String jsxz;

	/**
	 *安装日期
	 */
	private Date azrq;

	/**
	 *施工单位
	 */
	private String sgdw;

	/**
	 *负责人
	 */
	private String fzr;

	/**
	 *联系电话
	 */
	private String lxdh;

	/**
	 *备注
	 */
	private String bz;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Td01_NUM"
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
	 * @hibernate.property column="jfxx_id"
	 * @return Returns the jfxx_id.
	 */
	public Long getJfxx_id() {
		return jfxx_id;
	}

	/**
	 * @param jfxx_id The jfxx_id to set.
	 */
	public void setJfxx_id(Long jfxx_id) {
		this.jfxx_id = jfxx_id;
	}

	/**
	 * @hibernate.property column="sbmc"
	 * @return Returns the sbmc.
	 */
	public String getSbmc() {
		return sbmc;
	}

	/**
	 * @param sbmc The sbmc to set.
	 */
	public void setSbmc(String sbmc) {
		this.sbmc = sbmc;
	}

	/**
	 * @hibernate.property column="sbxh"
	 * @return Returns the sbxh.
	 */
	public String getSbxh() {
		return sbxh;
	}

	/**
	 * @param sbxh The sbxh to set.
	 */
	public void setSbxh(String sbxh) {
		this.sbxh = sbxh;
	}

	/**
	 * @hibernate.property column="jjcc"
	 * @return Returns the jjcc.
	 */
	public String getJjcc() {
		return jjcc;
	}

	/**
	 * @param jjcc The jjcc to set.
	 */
	public void setJjcc(String jjcc) {
		this.jjcc = jjcc;
	}

	/**
	 * @hibernate.property column="cj"
	 * @return Returns the cj.
	 */
	public String getCj() {
		return cj;
	}

	/**
	 * @param cj The cj to set.
	 */
	public void setCj(String cj) {
		this.cj = cj;
	}

	/**
	 * @hibernate.property column="gdfs"
	 * @return Returns the gdfs.
	 */
	public String getGdfs() {
		return gdfs;
	}

	/**
	 * @param gdfs The gdfs to set.
	 */
	public void setGdfs(String gdfs) {
		this.gdfs = gdfs;
	}

	/**
	 * @hibernate.property column="sszy"
	 * @return Returns the sszy.
	 */
	public String getSszy() {
		return sszy;
	}

	/**
	 * @param sszy The sszy to set.
	 */
	public void setSszy(String sszy) {
		this.sszy = sszy;
	}

	/**
	 * @hibernate.property column="azwz"
	 * @return Returns the azwz.
	 */
	public String getAzwz() {
		return azwz;
	}

	/**
	 * @param azwz The azwz to set.
	 */
	public void setAzwz(String azwz) {
		this.azwz = azwz;
	}

	/**
	 * @hibernate.property column="jsxz"
	 * @return Returns the jsxz.
	 */
	public String getJsxz() {
		return jsxz;
	}

	/**
	 * @param jsxz The jsxz to set.
	 */
	public void setJsxz(String jsxz) {
		this.jsxz = jsxz;
	}

	/**
	 * @hibernate.property column="azrq"
	 * @return Returns the azrq.
	 */
	public Date getAzrq() {
		return azrq;
	}

	/**
	 * @param azrq The azrq to set.
	 */
	public void setAzrq(Date azrq) {
		this.azrq = azrq;
	}

	/**
	 * @hibernate.property column="sgdw"
	 * @return Returns the sgdw.
	 */
	public String getSgdw() {
		return sgdw;
	}

	/**
	 * @param sgdw The sgdw to set.
	 */
	public void setSgdw(String sgdw) {
		this.sgdw = sgdw;
	}

	/**
	 * @hibernate.property column="fzr"
	 * @return Returns the fzr.
	 */
	public String getFzr() {
		return fzr;
	}

	/**
	 * @param fzr The fzr to set.
	 */
	public void setFzr(String fzr) {
		this.fzr = fzr;
	}

	/**
	 * @hibernate.property column="lxdh"
	 * @return Returns the lxdh.
	 */
	public String getLxdh() {
		return lxdh;
	}

	/**
	 * @param lxdh The lxdh to set.
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	/**
	 * @hibernate.property column="bz"
	 * @return Returns the bz.
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bz The bz to set.
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @hibernate.property column="sbgg"
	 * @return Returns the sbgg.
	 */
	public String getSbgg() {
		return sbgg;
	}

	public void setSbgg(String sbgg) {
		this.sbgg = sbgg;
	}

	
}
