package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.*;
/**
 * @author mengying 2009-12-11
 * @hibernate.class table="Te02_jlfk"
 */

public class Te02_jlfk implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 640068954394578422L;

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 
	 */
	private Long project_id;
	
	/**
	 * 
	 */
	private Long module_id;
	
	/**
	 * 
	 */
	private Long user_id;
	
	/**
	 * 
	 */
	private String yj;
	
	/**
	 * 
	 */
	private Date time;
	
	/**
	 * 
	 */
	private Long document_id;
	
	/**
	 * 标识谁读过
	 */
	private String read_flag;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Te02_NUM"
	 * @return Returns the id.
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="project_id"
	 * @return Returns the project_id.
	 */
	public Long getProject_id() {
		return project_id;
	}

	/**
	 * @param project_id The project_id to set.
	 */
	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	/**
	 * @hibernate.property column="module_id"
	 * @return Returns the module_id.
	 */
	public Long getModule_id() {
		return module_id;
	}

	/**
	 * @param module_id The module_id to set.
	 */
	public void setModule_id(Long module_id) {
		this.module_id = module_id;
	}


	/**
	 * @hibernate.property column="yj"
	 * @return Returns the yj.
	 */
	public String getYj() {
		return yj;
	}

	/**
	 * @param yj The yj to set.
	 */
	public void setYj(String yj) {
		this.yj = yj;
	}

	/**
	 * @hibernate.property column="time"
	 * @return Returns the time.
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time The time to set.
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @hibernate.property column="document_id"
	 * @return Returns the document_id.
	 */
	public Long getDocument_id() {
		return document_id;
	}

	/**
	 * @param document_id The document_id to set.
	 */
	public void setDocument_id(Long document_id) {
		this.document_id = document_id;
	}

	/**
	 * @hibernate.property column="read_flag"
	 * @return Returns the read_flag.
	 */
	public String getRead_flag() {
		return read_flag;
	}

	/**
	 * @param read_flag The read_flag to set.
	 */
	public void setRead_flag(String read_flag) {
		this.read_flag = read_flag;
	}

	/**
	 * @hibernate.property column="user_id"
	 * @return Returns the user_id.
	 */
	public Long getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id The user_id to set.
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}



}
