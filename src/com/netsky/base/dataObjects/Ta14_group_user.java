package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author mengying 2009-12-11
 * @hibernate.class table="Ta14_group_user"
 */
public class Ta14_group_user implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 263868527554155503L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 群组标识ta05.id
	 */
	private Long group_id;
	
	/**
	 * 用户标识ta03.id
	 */
	private Long user_id;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta14_NUM"
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
	 * @hibernate.property column="group_id"
	 * @return the group_id
	 */
	public Long getGroup_id() {
		return group_id;
	}

	/**
	 * @param group_id the group_id to set
	 */
	public void setGroup_id(Long group_id) {
		this.group_id = group_id;
	}

	/**
	 * @hibernate.property column="user_id"
	 * @return the user_id
	 */
	public Long getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


}
