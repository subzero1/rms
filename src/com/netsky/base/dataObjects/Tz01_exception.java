package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * 异常记录表
 * 
 * @author Chiang 2010-01-06
 * @hibernate.class table="Tz01_exception"
 */
public class Tz01_exception implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 309362086709996610L;

	/**
	 * ID 标识
	 */
	private Long id;

	/**
	 * OPTIME DATE 操作时间
	 */
	private Date optime;
	/**
	 * OPCLASS VARCHAR2(100),操作类
	 */
	private String opclass;

	/**
	 * NAME VARCHAR2(2000),异常名称
	 */
	private String name;
	/**
	 * STACKTRACE VARCHAR2(2000)异常堆栈
	 */
	private String stacktrace;
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tz01_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @hibernate.property column="optime"
	 * @return the optime
	 */
	public Date getOptime() {
		return optime;
	}
	/**
	 * @param optime the optime to set
	 */
	public void setOptime(Date optime) {
		this.optime = optime;
	}
	/**
	 * @hibernate.property column="opclass"
	 * @return the opclass
	 */
	public String getOpclass() {
		return opclass;
	}
	/**
	 * @param opclass the opclass to set
	 */
	public void setOpclass(String opclass) {
		this.opclass = opclass;
	}
	/**
	 * @hibernate.property column="name"
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @hibernate.property column="stacktrace"
	 * @return the stacktrace
	 */
	public String getStacktrace() {
		return stacktrace;
	}
	/**
	 * @param stacktrace the stacktrace to set
	 */
	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}
	
}
