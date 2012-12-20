package com.rms.dataObjects.base;

import java.io.Serializable;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tc09_jlgttzr"
 */

public class Tc09_jlgttzr implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -882696283807683617L;
	private Long id;
	private Long module_id;
	private String namefield;
	private String telfield;
	private String tablename;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="TC09_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="module_id"
	 * @return Returns the module_id.
	 */
	public Long getModule_id() {
		return module_id;
	}

	/**
	 * @param module_id
	 *            The module_id to set.
	 */
	public void setModule_id(Long module_id) {
		this.module_id = module_id;
	}

	/**
	 * @hibernate.property column="namefield"
	 * @return Returns the namefield.
	 */
	public String getNamefield() {
		return namefield;
	}

	/**
	 * @param namefield
	 *            The namefield to set.
	 */
	public void setNamefield(String namefield) {
		this.namefield = namefield;
	}

	/**
	 * @hibernate.property column="telfield"
	 * @return Returns the telfield.
	 */
	public String getTelfield() {
		return telfield;
	}

	/**
	 * @param telfield
	 *            The telfield to set.
	 */
	public void setTelfield(String telfield) {
		this.telfield = telfield;
	}

	/**
	 * @hibernate.property column="tablename"
	 * @return Returns the tablename.
	 */
	public String getTablename() {
		return tablename;
	}

	/**
	 * @param tablename
	 *            The tablename to set.
	 */
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

}
