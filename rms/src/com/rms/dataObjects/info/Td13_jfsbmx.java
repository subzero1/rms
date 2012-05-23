package com.rms.dataObjects.info;

import java.util.Date;

/**
 * @description:
 * 设备信息表
 * @class name:com.rms.dataObjects.info.Td13_jfsbmx
 * @author Administrator Jul 26, 2011
 * @hibernate.class table="Td13_jfsbmx"
 */
public class Td13_jfsbmx {
	/**
	 *
	 */
	private Long id;

	/**
	 *
	 */
	private Long project_id;

	/**
	 *申请单标识
	 */
	private Long sqd_id;

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
	 *单位
	 */
	private String dw;
	
	/**
	 *数量
	 */
	private Double sl;

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
	 * @hibernate.generator-param name="sequence" value="Ta12_NUM"
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
	 * @hibernate.property column="project_id"
	 * @return Returns the project_id.
	 */
	public Long getProject_id() {
		return project_id;
	}

	/**
	 * @param project_id The project_id to set.
	 */
	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	/**
	 * @hibernate.property column="sqd_id"
	 * @return Returns the sqd_id.
	 */
	public Long getSqd_id() {
		return sqd_id;
	}

	/**
	 * @param sqd_id The sqd_id to set.
	 */
	public void setSqd_id(Long sqd_id) {
		this.sqd_id = sqd_id;
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
	 * @hibernate.property column="sbgg"
	 * @return Returns the sbgg.
	 */
	public String getSbgg() {
		return sbgg;
	}

	/**
	 * @param sbgg The sbgg to set.
	 */
	public void setSbgg(String sbgg) {
		this.sbgg = sbgg;
	}

	/**
	 * @hibernate.property column="dw"
	 * @return Returns the dw.
	 */
	public String getDw() {
		return dw;
	}

	/**
	 * @param dw The dw to set.
	 */
	public void setDw(String dw) {
		this.dw = dw;
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
	 * @hibernate.property column="sl"
	 * @return Returns the sl.
	 */
	public Double getSl() {
		return sl;
	}

	/**
	 * @param sl The sl to set.
	 */
	public void setSl(Double sl) {
		this.sl = sl;
	}

}
