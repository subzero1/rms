package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author CT 2010-03-04
 * @hibernate.class table="Ta23_personalization"
 */
public class Ta23_personalization implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8955366942800514950L;
	/**
	 * id
	 */
	private Long id;
	/**
	 * 用户id
	 */
	private Long user_id;
	/**
	 * 个性化对象标识
	 */
	private Long po_id;
	/**
	 * 类别标识：1，快捷菜单po_id对应ta04.id
	 */
	private Integer type;
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta23_NUM"
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
	/**
	 * @hibernate.property column = "user_id"
	 * @return user_id
	 */
	public Long getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	/**
	 * @hibernate.property column="po_id"
	 * @return po_id
	 */
	public Long getPo_id() {
		return po_id;
	}
	/**
	 * @param po_id
	 */
	public void setPo_id(Long po_id) {
		this.po_id = po_id;
	}
	/**
	 * @hibernate.property column="type"
	 * @return type
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * @param type
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
}
