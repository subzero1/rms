package com.rms.dataObjects.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-12-21
 * @hibernate.class table="Tc10_hzdw_khpz"
 */

public class Tc10_hzdw_khpz implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 121969779948379104L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tc10_num"
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
 * 考核名称
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
 * 间隔天数
 */
 private Long jgts;

 /**
 * @hibernate.property column="jgts"
 * @return Returns the jgts.
 */
 public Long getJgts() {
    return jgts;
 }

 public void setJgts(Long jgts) {
    this.jgts = jgts;
  }

 /**
 * 下次考核时间
 */
 private Date xckhsj;

 /**
 * @hibernate.property column="xckhsj"
 * @return Returns the xckhsj.
 */
 public Date getXckhsj() {
    return xckhsj;
 }

 public void setXckhsj(Date xckhsj) {
    this.xckhsj = xckhsj;
  }

 /**
 * 最后一次考核时间
 */
 private Date zhkhsj;

 /**
 * @hibernate.property column="zhkhsj"
 * @return Returns the zhkhsj.
 */
 public Date getZhkhsj() {
    return zhkhsj;
 }

 public void setZhkhsj(Date zhkhsj) {
    this.zhkhsj = zhkhsj;
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

 /**
 * 1 有效 0 无效
 */
 private Long useflag;

 /**
 * @hibernate.property column="useflag"
 * @return Returns the useflag.
 */
 public Long getUseflag() {
    return useflag;
 }

 public void setUseflag(Long useflag) {
    this.useflag = useflag;
  }
 
 /**
 * 单位类别
 */
private String dwlb;

public String getDwlb() {
	return dwlb;
}

public void setDwlb(String dwlb) {
	this.dwlb = dwlb;
}

private Long dfts;

public Long getDfts() {
	return dfts;
}

public void setDfts(Long dfts) {
	this.dfts = dfts;
}


}
