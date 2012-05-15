package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author mengying 2010-1-8
 * @hibernate.class table="Ta12_sta_role"
 */

public class Ta12_sta_role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 412037805207171284L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * 岗位标识ta02.id
	 */
	private Long station_id;
	
	/**
	 * 角色标识ta04.id
	 */
	private Long role_id;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta12_NUM"
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
	 * @hibernate.property column="station_id"
	 * @return the station_id
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
	 * @hibernate.property column="role_id"
	 * @return the role_id
	 */
	public Long getRole_id() {
		return role_id;
	}

	/**
	 * @param role_id the role_id to set
	 */
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
}
