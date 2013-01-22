package com.rms.dataObjects.wxdw;

public class Tf30_wxry {
	/**
	 * 标识
	 */
	private Long id;
	/**姓名
	 * 
	 */
	private String name;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 移动电话
	 */
	private String mobile;
	/**
	 * 身份证
	 */
	private String sfz;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 图片路径
	 */
	private Long img;
	/**
	 * 备注
	 */
	private String bz;
	/**
	 * 
	 */
	private Long wxdw_id;
	/**
	 * 状态
	 */
	private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSfz() {
		return sfz;
	}
	public void setSfz(String sfz) {
		this.sfz = sfz;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getImg() {
		return img;
	}
	public void setImg(Long img) {
		this.img = img;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Long getWxdw_id() {
		return wxdw_id;
	}
	public void setWxdw_id(Long wxdw_id) {
		this.wxdw_id = wxdw_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 概预算证
	 */
	private String gysz;
	
	/**
	 * 安全员证
	 */
	private String aqyz;
	
	/**
	 * 监理证
	 */
	private String jlz;
	
	/**
	 * 登高证
	 */
	private String dgz;
	
	/**
	 * 电工证 
	 */
	private String ec;

	public String getGysz() {
		return gysz;
	}

	public void setGysz(String gysz) {
		this.gysz = gysz;
	}

	public String getAqyz() {
		return aqyz;
	}

	public void setAqyz(String aqyz) {
		this.aqyz = aqyz;
	}

	public String getJlz() {
		return jlz;
	}

	public void setJlz(String jlz) {
		this.jlz = jlz;
	}

	public String getDgz() {
		return dgz;
	}

	public void setDgz(String dgz) {
		this.dgz = dgz;
	}

	public String getEc() {
		return ec;
	}

	public void setEc(String ec) {
		this.ec = ec;
	}

	
}
