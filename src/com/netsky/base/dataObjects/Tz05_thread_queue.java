package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zy 2012-5-30
 * @hibernate.class table="tz05_thread_queue"
 */
public class Tz05_thread_queue implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5550724252993706138L;
	private Long id;
	private String servicename;
	private String parameters;
	private Date inserttime;
	private Date handletime;
	private String batchno;
	private String status;
	private String type;
	private String remark;

	public Tz05_thread_queue() {
		this.parameters = "{}";// JSON串
		this.status = "未处理";
	}

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="TZ05_NUM"
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
	 * @hibernate.property column="servicename"
	 * @return Returns the servicename.
	 */
	public String getServicename() {
		return servicename;
	}

	/**
	 * @param servicename
	 *            The servicename to set.
	 */
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	/**
	 * @hibernate.property column="parameters"
	 * @return Returns the parameters.
	 */
	public String getParameters() {
		return parameters;
	}

	/**
	 * @param parameters
	 *            The parameters to set.
	 */
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	/**
	 * @hibernate.property column="inserttime"
	 * @return Returns the inserttime.
	 */
	public Date getInserttime() {
		return inserttime;
	}

	/**
	 * @param inserttime
	 *            The inserttime to set.
	 */
	public void setInserttime(Date inserttime) {
		this.inserttime = inserttime;
	}

	/**
	 * @hibernate.property column="handletime"
	 * @return Returns the handletime.
	 */
	public Date getHandletime() {
		return handletime;
	}

	/**
	 * @param handletime
	 *            The handletime to set.
	 */
	public void setHandletime(Date handletime) {
		this.handletime = handletime;
	}

	/**
	 * @hibernate.property column="batchno"
	 * @return Returns the batchno.
	 */
	public String getBatchno() {
		return batchno;
	}

	/**
	 * @param batchno
	 *            The batchno to set.
	 */
	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	/**
	 * @hibernate.property column="status"
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @hibernate.property column="type"
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @hibernate.property column="remark"
	 * @return Returns the remark.
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            The remark to set.
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
