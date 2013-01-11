package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tf16_xmkhdf"
 */

public class Tf16_xmkhdf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 997724612444671360L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="tf16_num"
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
 * 考核项
 */
 private String khx;

 /**
 * @hibernate.property column="khx"
 * @return Returns the khx.
 */
 public String getKhx() {
    return khx;
 }

 public void setKhx(String khx) {
    this.khx = khx;
  }

 /**
 * 描述
 */
 private String ms;

 /**
 * @hibernate.property column="ms"
 * @return Returns the ms.
 */
 public String getMs() {
    return ms;
 }

 public void setMs(String ms) {
    this.ms = ms;
  }

 /**
 * 最大分值
 */
 private Long zdfz;

 /**
 * @hibernate.property column="zdfz"
 * @return Returns the zdfz.
 */
 public Long getZdfz() {
    return zdfz;
 }

 public void setZdfz(Long zdfz) {
    this.zdfz = zdfz;
  }

 /**
 * 结果选项：优、 良、中 、 差 
 */
 private String jgxx;

 /**
 * @hibernate.property column="jgxx"
 * @return Returns the jgxx.
 */
 public String getJgxx() {
    return jgxx;
 }

 public void setJgxx(String jgxx) {
    this.jgxx = jgxx;
  }

 /**
 * 结果分值比例 优：1 良 0.8 中 0.6 差 0.4
 */
 private Double jgfz;

 /**
 * @hibernate.property column="jgfz"
 * @return Returns the jgfz.
 */
 public Double getJgfz() {
    return jgfz;
 }

 public void setJgfz(Double jgfz) {
    this.jgfz = jgfz;
  }

 /**
 * 项目标识
 */
 private Long project_id;

 /**
 * @hibernate.property column="project_id"
 * @return Returns the project_id.
 */
 public Long getProject_id() {
    return project_id;
 }

 public void setProject_id(Long project_id) {
    this.project_id = project_id;
  }

 /**
 * 评分人员名称
 */
 private String pfry;

 /**
 * @hibernate.property column="pfry"
 * @return Returns the pfry.
 */
 public String getPfry() {
    return pfry;
 }

 public void setPfry(String pfry) {
    this.pfry = pfry;
  }

 /**
 * 评分时间
 */
 private Date pfsj;

 /**
 * @hibernate.property column="pfsj"
 * @return Returns the pfsj.
 */
 public Date getPfsj() {
    return pfsj;
 }

 public void setPfsj(Date pfsj) {
    this.pfsj = pfsj;
  }

 /**
 * 计算方式
 */
 private String jsfs;

 /**
 * @hibernate.property column="jsfs"
 * @return Returns the jsfs.
 */
 public String getJsfs() {
    return jsfs;
 }

 public void setJsfs(String jsfs) {
    this.jsfs = jsfs;
  }

 /**
  * 类别
  * */
 private String lb;

/**
 * @hibernate.property column="lb"
 * @return Returns the lb.
 */
public String getLb() {
	return lb;
}

/**
 * @param lb The lb to set.
 */
public void setLb(String lb) {
	this.lb = lb;
}

/**
 * 罚款金额
 */
private Long fkje;

/**
 * 说明
 */
private String explain;

public Long getFkje() {
	return fkje;
}

public void setFkje(Long fkje) {
	this.fkje = fkje;
}

public String getExplain() {
	return explain;
}

public void setExplain(String explain) {
	this.explain = explain;
}




}
