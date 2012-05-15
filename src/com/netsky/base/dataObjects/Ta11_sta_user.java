package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author mengying 2009-12-11
 * @hibernate.class table="Ta11_sta_user"
 */

public class Ta11_sta_user implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7306608771071253138L;

	/**
	 * 岗位标识ta02.id
	 */
	private Long station_id;
	
	/**
	 * 用户标识ta03.id
	 */
	private Long user_id;
	/**
	 * id
	 */
	private Long id;

	/**
	 * @hibernate.property column="station_id"
	 * @return Returns the id.
	 */
	public Long getStation_id() {
		return station_id;
	}

	/**
	 * @param station_id the station_id to set
	 */
	public void setStation_id(Long station_id) {
		this.station_id = station_id;
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
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta11_NUM"
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
