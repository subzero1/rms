package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author wangflan 2010-05-06
 * @hibernate.class table="TA31_WORKLIST_CFG"
 */

public class Ta31_worklist_cfg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8335678036204512826L;

	/**
	 * 标识
	 */
	private Long id;

	/**
	 * 用户ID
	 */
	private Long user_id;
	
	/**
	 * TA07.ID
	 */
	private Long field_id;

	/**
	 * 显示顺序
	 */
	private Integer ord;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta31_NUM"
	 * @return Returns the id.
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="user_id"
	 * @return the user_id
	 */
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	/**
	 * @hibernate.property column="field_id"
	 * @return the field_id
	 */
	public Long getField_id() {
		return field_id;
	}

	public void setField_id(Long field_id) {
		this.field_id = field_id;
	}

	/**
	 * @hibernate.property column="ord"
	 * @return the ord
	 */
	public Integer getOrd() {
		return ord;
	}

	public void setOrd(Integer ord) {
		this.ord = ord;
	}
}
