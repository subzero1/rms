package com.rms.dataObjects.base;

/**
 * @description:
 * 表单下拉选维护
 * @class name:com.rms.dataObjects.base.Tc01_property
 * @author Administrator Jul 26, 2011
 * @hibernate.class table="Tc01_property"
 */
public class Tc01_property {
	/**
	 *标识
	 */
	private Long id;

	/**
	 *属性名称
	 */
	private String name;

	/**
	 *属性分类
	 */
	private String type;
	
	/**
	 *标识
	 */
	private String flag;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tc01_NUM"
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
	 * @hibernate.property column="flag"
	 * @return Returns the flag.
	 */
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
