package com.rms.dataObjects.base;

import java.io.Serializable;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Tc08_jlgtpzb"
 */

public class Tc08_jlgtpzb implements Serializable {
	private static final long serialVersionUID = -5048936868414348848L;
	private Long id;
	private Long module_id;
	private String fieldname;
	private Long xh;
	private Long lx;
	private String tablename;
	private String comments;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="TC08_NUM"
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
	 * @hibernate.property column="fieldname"
	 * @return Returns the fieldname.
	 */
	public String getFieldname() {
		return fieldname;
	}

	/**
	 * @param fieldname
	 *            The fieldname to set.
	 */
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	/**
	 * @hibernate.property column="xh"
	 * @return Returns the xh.
	 */
	public Long getXh() {
		return xh;
	}

	/**
	 * @param xh
	 *            The xh to set.
	 */
	public void setXh(Long xh) {
		this.xh = xh;
	}

	/**
	 * @hibernate.property column="lx"
	 * @return Returns the lx.
	 */
	public Long getLx() {
		return lx;
	}

	/**
	 * @param lx
	 *            The lx to set.
	 */
	public void setLx(Long lx) {
		this.lx = lx;
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

	/**
	 * @hibernate.property column="comments"
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

}
