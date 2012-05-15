package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * 工作委托
 * @class name:com.netsky.base.dataObjects.Ta28_work_trust
 * @author Administrator May 4, 2010
 * @hibernate.class table="Ta28_work_trust"
 */
public class Ta28_work_trust implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1565922163100824524L;

	/**
	 * 标识id
	 */
	private Long id;

	/**
	 * 委托方用户ID
	 */
	private Long from_userId;
	
	/**
	 * 委托方用户名称
	 */
	private String from_userName;
	
	/**
	 * 受托方用户ID
	 */
	private Long to_userId;
	
	/**
	 * 受托方用户名称
	 */
	private String to_userName;
	
	
	/**
	 * 委托开始时间
	 */
	private Date start_time;
	
	/**
	 * 委托结束时间
	 */
	private Date end_time;
	
	/**
	 * 委托原因
	 */
	private String remark;
	
	
	/**
	 * @hibernate.property column="from_userId"
	 * @return Returns the from_userId.
	 */
	public Long getFrom_userId() {
		return from_userId;
	}

	/**
	 * @param from_userId The from_userId to set.
	 */
	public void setFrom_userId(Long from_userId) {
		this.from_userId = from_userId;
	}

	/**
	 * @hibernate.property column="from_userName"
	 * @return Returns the from_userName.
	 */
	public String getFrom_userName() {
		return from_userName;
	}

	/**
	 * @param from_userName The from_userName to set.
	 */
	public void setFrom_userName(String from_userName) {
		this.from_userName = from_userName;
	}

	/**
	 * @hibernate.property column="to_userId"
	 * @return Returns the to_userId.
	 */
	public Long getTo_userId() {
		return to_userId;
	}

	/**
	 * @param to_userId The to_userId to set.
	 */
	public void setTo_userId(Long to_userId) {
		this.to_userId = to_userId;
	}

	/**
	 * @hibernate.property column="to_userName"
	 * @return Returns the to_userName.
	 */
	public String getTo_userName() {
		return to_userName;
	}

	/**
	 * @param to_userName The to_userName to set.
	 */
	public void setTo_userName(String to_userName) {
		this.to_userName = to_userName;
	}

	/**
	 * @hibernate.property column="start_time"
	 * @return Returns the start_time.
	 */
	public Date getStart_time() {
		return start_time;
	}

	/**
	 * @param start_time The start_time to set.
	 */
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	/**
	 * @hibernate.property column="end_time"
	 * @return Returns the end_time.
	 */
	public Date getEnd_time() {
		return end_time;
	}

	/**
	 * @param end_time The end_time to set.
	 */
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	/**
	 * @hibernate.property column="remark"
	 * @return Returns the remark.
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark The remark to set.
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta26_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
