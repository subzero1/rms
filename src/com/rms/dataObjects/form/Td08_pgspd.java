package com.rms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2013-02-22
 * @hibernate.class table="Td08_pgspd"
 */

public class Td08_pgspd implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 50738083715363928L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td08_num"
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
 * 工程ID
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
 * 发文部门
 */
 private String fwbm;

 /**
 * @hibernate.property column="fwbm"
 * @return Returns the fwbm.
 */
 public String getFwbm() {
    return fwbm;
 }

 public void setFwbm(String fwbm) {
    this.fwbm = fwbm;
  }

 /**
 * 表单编号
 */
 private String bdbh;

 /**
 * @hibernate.property column="bdbh"
 * @return Returns the bdbh.
 */
 public String getBdbh() {
    return bdbh;
 }

 public void setBdbh(String bdbh) {
    this.bdbh = bdbh;
  }

 /**
 * 创建人
 */
 private String cjr;

 /**
 * @hibernate.property column="cjr"
 * @return Returns the cjr.
 */
 public String getCjr() {
    return cjr;
 }

 public void setCjr(String cjr) {
    this.cjr = cjr;
  }

 /**
 * 创建人电话
 */
 private String cjrdh;

 /**
 * @hibernate.property column="cjrdh"
 * @return Returns the cjrdh.
 */
 public String getCjrdh() {
    return cjrdh;
 }

 public void setCjrdh(String cjrdh) {
    this.cjrdh = cjrdh;
  }

 /**
 * 创建日期
 */
 private Date cjrq;

 /**
 * @hibernate.property column="cjrq"
 * @return Returns the cjrq.
 */
 public Date getCjrq() {
    return cjrq;
 }

 public void setCjrq(Date cjrq) {
    this.cjrq = cjrq;
  }

 /**
 * 系统选择单位
 */
 private String xtxzdw;

 /**
 * @hibernate.property column="xtxzdw"
 * @return Returns the xtxzdw.
 */
 public String getXtxzdw() {
    return xtxzdw;
 }

 public void setXtxzdw(String xtxzdw) {
    this.xtxzdw = xtxzdw;
  }

 /**
 * 实际选择单位
 */
 private String sjxzdw;

 /**
 * @hibernate.property column="sjxzdw"
 * @return Returns the sjxzdw.
 */
 public String getSjxzdw() {
    return sjxzdw;
 }

 public void setSjxzdw(String sjxzdw) {
    this.sjxzdw = sjxzdw;
  }

 /**
 * 手动派工原因
 */
 private String sdxpyy;

 /**
 * @hibernate.property column="sdxpyy"
 * @return Returns the sdxpyy.
 */
 public String getSdxpyy() {
    return sdxpyy;
 }

 public void setSdxpyy(String sdxpyy) {
    this.sdxpyy = sdxpyy;
  }

 /**
 * 
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
 * 审批标识
 */
 private Long sp_flag;

 /**
 * @hibernate.property column="sp_flag"
 * @return Returns the sp_flag.
 */
 public Long getSp_flag() {
    return sp_flag;
 }

 public void setSp_flag(Long sp_flag) {
    this.sp_flag = sp_flag;
  }

 /**
 * 审批完成后项目管理员查看
 */
 private Long ck_flag;

 /**
 * @hibernate.property column="ck_flag"
 * @return Returns the ck_flag.
 */
 public Long getCk_flag() {
    return ck_flag;
 }

 public void setCk_flag(Long ck_flag) {
    this.ck_flag = ck_flag;
  }


}
