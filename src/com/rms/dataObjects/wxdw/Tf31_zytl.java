package com.rms.dataObjects.wxdw;

import java.io.Serializable;
import java.util.Date;

public class Tf31_zytl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7524956013264454972L;

	/**
	 * 标识
	 */
	private Long id;

	/**
	 * gis系统编号
	 */
	private String gis_no;

	/**
	 * 填录人姓名
	 */
	private String tlrxm;

	/**
	 * 填录人姓名
	 */
	private String ssdw;

	/**
	 * 年限
	 */
	private Double nx;
	
	/**
	 * 进入建设中心日期
	 */
	private Date in_time;

	/**
	 * 认证成绩
	 */
	private Double rzcj;
	
	/**
	 * 电话
	 */
	private String phone;
	
	/**
	 * 专长
	 */
	private String zc;
	
	/**
	 * 在册情况
	 */
	private String zcqk;
	
	/**
	 * 备注
	 */
	private String bz;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGis_no() {
		return gis_no;
	}

	public void setGis_no(String gis_no) {
		this.gis_no = gis_no;
	}

	public String getTlrxm() {
		return tlrxm;
	}

	public void setTlrxm(String tlrxm) {
		this.tlrxm = tlrxm;
	}

	public String getSsdw() {
		return ssdw;
	}

	public void setSsdw(String ssdw) {
		this.ssdw = ssdw;
	}

	public Date getIn_time() {
		return in_time;
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}

	public Double getRzcj() {
		return rzcj;
	}

	public void setRzcj(Double rzcj) {
		this.rzcj = rzcj;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZc() {
		return zc;
	}

	public void setZc(String zc) {
		this.zc = zc;
	}

	public String getZcqk() {
		return zcqk;
	}

	public void setZcqk(String zcqk) {
		this.zcqk = zcqk;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public Double getNx() {
		return nx;
	}

	public void setNx(Double nx) {
		this.nx = nx;
	}

}
