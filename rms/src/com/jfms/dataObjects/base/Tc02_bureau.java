package com.jfms.dataObjects.base;

/**
 * @description:
 * 局点信息
 * @class name:com.jfms.dataObjects.base.Tc02_bureau
 * @author Administrator Jul 26, 2011
 * @hibernate.class table="Tc02_bureau"
 */
public class Tc02_bureau {
	/**
	 *标识
	 */
	private Long id;

	/**
	 *局点名称
	 */
	private String name;

	/**
	 *局点性质
	 */
	private String type;

	/**
	 *所属地区
	 */
	private String p_area;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tc02_NUM"
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
	 * @hibernate.property column="type"
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @hibernate.property column="p_area"
	 * @return Returns the p_area.
	 */
	public String getP_area() {
		return p_area;
	}

	/**
	 * @param p_area The p_area to set.
	 */
	public void setP_area(String p_area) {
		this.p_area = p_area;
	}

}
