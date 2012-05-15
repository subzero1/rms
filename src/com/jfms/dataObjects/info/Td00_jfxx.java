package com.jfms.dataObjects.info;

import java.util.Date;

/**
 * @description: 机房信息表
 * @class name:com.jfms.dataObjects.info.Td00_jfxx
 * @author Administrator Jul 26, 2011
 * @hibernate.class table="Td00_jfxx"
 */
public class Td00_jfxx {
	/**
	 * 
	 */
	private Long id;

	/**
	 * 机房名称
	 */
	private String jfmc;

	/**
	 * 登记部门
	 */
	private String djbm;

	/**
	 * 登记日期
	 */
	private Date djrq;

	/**
	 * 登记人员
	 */
	private String djry;

	/**
	 * 联系电话
	 */
	private String lxdh;

	/**
	 * 局点名称
	 */
	private String jdmc;

	/**
	 * 局点性质
	 */
	private String jdxz;

	/**
	 * 所属区域
	 */
	private String ssqy;

	/**
	 * 坐落地点
	 */
	private String zldd;

	/**
	 * 经纬度
	 */
	private String jwd;

	/**
	 * 建设日期
	 */
	private Date jsrq;

	/**
	 * 机房面积
	 */
	private String jfmj;

	/**
	 * 机房所在楼层
	 */
	private String jfszlc;

	/**
	 * 机房用途
	 */
	private String jfyt;

	/**
	 * 管理人员
	 */
	private String glry;

	/**
	 * 管理人员电话
	 */
	private String glylxdh;

	/**
	 * 备注
	 */
	private String bz;
	
	/**
	 * 线路图最后更新时间
	 */
	private Date xzt_gxsj;
	
	/**
	 * 申请标识，null 或 0： 此机房图纸还未替换过，1：此机房图纸走过申请流程，且被替换过
	 */
	private Integer sq_flag;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Td00_NUM"
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
	 * @hibernate.property column="jfmc"
	 * @return Returns the jfmc.
	 */
	public String getJfmc() {
		return jfmc;
	}

	/**
	 * @param jfmc
	 *            The jfmc to set.
	 */
	public void setJfmc(String jfmc) {
		this.jfmc = jfmc;
	}

	/**
	 * @hibernate.property column="djbm"
	 * @return Returns the djbm.
	 */
	public String getDjbm() {
		return djbm;
	}

	/**
	 * @param djbm
	 *            The djbm to set.
	 */
	public void setDjbm(String djbm) {
		this.djbm = djbm;
	}

	/**
	 * @hibernate.property column="djrq"
	 * @return Returns the djrq.
	 */
	public Date getDjrq() {
		return djrq;
	}

	/**
	 * @param djrq
	 *            The djrq to set.
	 */
	public void setDjrq(Date djrq) {
		this.djrq = djrq;
	}

	/**
	 * @hibernate.property column="djry"
	 * @return Returns the djry.
	 */
	public String getDjry() {
		return djry;
	}

	/**
	 * @param djry
	 *            The djry to set.
	 */
	public void setDjry(String djry) {
		this.djry = djry;
	}

	/**
	 * @hibernate.property column="lxdh"
	 * @return Returns the lxdh.
	 */
	public String getLxdh() {
		return lxdh;
	}

	/**
	 * @param lxdh
	 *            The lxdh to set.
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	/**
	 * @hibernate.property column="jdmc"
	 * @return Returns the jdmc.
	 */
	public String getJdmc() {
		return jdmc;
	}

	/**
	 * @param jdmc
	 *            The jdmc to set.
	 */
	public void setJdmc(String jdmc) {
		this.jdmc = jdmc;
	}

	/**
	 * @hibernate.property column="jdxz"
	 * @return Returns the jdxz.
	 */
	public String getJdxz() {
		return jdxz;
	}

	/**
	 * @param jdxz
	 *            The jdxz to set.
	 */
	public void setJdxz(String jdxz) {
		this.jdxz = jdxz;
	}

	/**
	 * @hibernate.property column="ssqy"
	 * @return Returns the ssqy.
	 */
	public String getSsqy() {
		return ssqy;
	}

	/**
	 * @param ssqy
	 *            The ssqy to set.
	 */
	public void setSsqy(String ssqy) {
		this.ssqy = ssqy;
	}

	/**
	 * @hibernate.property column="zldd"
	 * @return Returns the zldd.
	 */
	public String getZldd() {
		return zldd;
	}

	/**
	 * @param zldd
	 *            The zldd to set.
	 */
	public void setZldd(String zldd) {
		this.zldd = zldd;
	}

	/**
	 * @hibernate.property column="jwd"
	 * @return Returns the jwd.
	 */
	public String getJwd() {
		return jwd;
	}

	/**
	 * @param jwd
	 *            The jwd to set.
	 */
	public void setJwd(String jwd) {
		this.jwd = jwd;
	}

	/**
	 * @hibernate.property column="jsrq"
	 * @return Returns the jsrq.
	 */
	public Date getJsrq() {
		return jsrq;
	}

	/**
	 * @param jsrq
	 *            The jsrq to set.
	 */
	public void setJsrq(Date jsrq) {
		this.jsrq = jsrq;
	}

	/**
	 * @hibernate.property column="jfmj"
	 * @return Returns the jfmj.
	 */
	public String getJfmj() {
		return jfmj;
	}

	/**
	 * @param jfmj
	 *            The jfmj to set.
	 */
	public void setJfmj(String jfmj) {
		this.jfmj = jfmj;
	}

	/**
	 * @hibernate.property column="jfszlc"
	 * @return Returns the jfszlc.
	 */
	public String getJfszlc() {
		return jfszlc;
	}

	/**
	 * @param jfszlc
	 *            The jfszlc to set.
	 */
	public void setJfszlc(String jfszlc) {
		this.jfszlc = jfszlc;
	}

	/**
	 * @hibernate.property column="jfyt"
	 * @return Returns the jfyt.
	 */
	public String getJfyt() {
		return jfyt;
	}

	/**
	 * @param jfyt
	 *            The jfyt to set.
	 */
	public void setJfyt(String jfyt) {
		this.jfyt = jfyt;
	}

	/**
	 * @hibernate.property column="glry"
	 * @return Returns the glry.
	 */
	public String getGlry() {
		return glry;
	}

	/**
	 * @param glry
	 *            The glry to set.
	 */
	public void setGlry(String glry) {
		this.glry = glry;
	}

	/**
	 * @hibernate.property column="glylxdh"
	 * @return Returns the glylxdh.
	 */
	public String getGlylxdh() {
		return glylxdh;
	}

	/**
	 * @param glylxdh
	 *            The glylxdh to set.
	 */
	public void setGlylxdh(String glylxdh) {
		this.glylxdh = glylxdh;
	}

	/**
	 * @hibernate.property column="bz"
	 * @return Returns the bz.
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bz
	 *            The bz to set.
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @hibernate.property column="xzt_gxsj"
	 * @return Returns the xzt_gxsj.
	 */
	public Date getXzt_gxsj() {
		return xzt_gxsj;
	}

	public void setXzt_gxsj(Date xzt_gxsj) {
		this.xzt_gxsj = xzt_gxsj;
	}

}
