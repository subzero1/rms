package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-12-17
 * @hibernate.class table="Tf30_wxry"
 */

public class Tf30_wxry implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 199387944325883264L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tf30_wxry_num"
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
 * 姓名
 */
 private String name;

 /**
 * @hibernate.property column="name"
 * @return Returns the name.
 */
 public String getName() {
    return name;
 }

 public void setName(String name) {
    this.name = name;
  }

 /**
 * 性别
 */
 private String sex;

 /**
 * @hibernate.property column="sex"
 * @return Returns the sex.
 */
 public String getSex() {
    return sex;
 }

 public void setSex(String sex) {
    this.sex = sex;
  }

 /**
 * 移动电话
 */
 private String mobile;

 /**
 * @hibernate.property column="mobile"
 * @return Returns the mobile.
 */
 public String getMobile() {
    return mobile;
 }

 public void setMobile(String mobile) {
    this.mobile = mobile;
  }

 /**
 * 身份证
 */
 private String sfz;

 /**
 * @hibernate.property column="sfz"
 * @return Returns the sfz.
 */
 public String getSfz() {
    return sfz;
 }

 public void setSfz(String sfz) {
    this.sfz = sfz;
  }

 /**
 * 住址
 */
 private String address;

 /**
 * @hibernate.property column="address"
 * @return Returns the address.
 */
 public String getAddress() {
    return address;
 }

 public void setAddress(String address) {
    this.address = address;
  }

 /**
 * 图片url
 */
 private String img;

 /**
 * @hibernate.property column="img"
 * @return Returns the img.
 */
 public String getImg() {
    return img;
 }

 public void setImg(String img) {
    this.img = img;
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
 * 外协单位id
 */
 private Long wxdw_id;

 /**
 * @hibernate.property column="wxdw_id"
 * @return Returns the wxdw_id.
 */
 public Long getWxdw_id() {
    return wxdw_id;
 }

 public void setWxdw_id(Long wxdw_id) {
    this.wxdw_id = wxdw_id;
  }

 /**
 * 状态
 */
 private String status;

 /**
 * @hibernate.property column="status"
 * @return Returns the status.
 */
 public String getStatus() {
    return status;
 }

 public void setStatus(String status) {
    this.status = status;
  }


}
