package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author mengying 2009-12-11
 * @hibernate.class table="Ta04_role"
 */

public class Ta04_role  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4937705594103136137L;


	/**
	 * 标识
	 */
	private Long id;

	/**
	 * 姓名
	 */
	private String name;


	/**
	 * 备注
	 */
	private String remark;




	/**
	 * 模块标识ta06_id
	 */
	private String menu_id;
 
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta04_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="name"
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @hibernate.property column="menu_id"
	 * @return Returns the menu_id.
	 */
	public String getMenu_id() {
		return menu_id;
	}

	/**
	 * @param menu_id The menu_id to set.
	 */
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	

}
