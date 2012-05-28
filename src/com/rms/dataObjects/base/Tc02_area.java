package com.rms.dataObjects.base;

/**
 * @description:
 * 局点信息
 * @class name:com.rms.dataObjects.base.Tc02_area
 * @author Administrator Jul 26, 2011
 * @hibernate.class table="Tc02_area"
 */
public class Tc02_area {
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
	private String flag;


	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Tc03_NUM"
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
	 * @hibernate.property column="flag"
	 * @return Returns the flag.
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag The flag to set.
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	
}
