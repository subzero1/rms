package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * @author mengying 2009-12-11
 * @hibernate.class table="Ta09_menu"
 */

public class Ta09_menu  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4937705594103136137L;

	/**
	 * 标识
	 */
	private Long id;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 链接地址
	 */
	private String url;

	/**
	 * 链接目标
	 */
	private String target;

	/**
	 * 链接目标涉及到dom对象ID
	 */
	private String rel;
	
	/**
	 * 上级菜单
	 */
	private Long up_id;
	
	/**
	 * 排序
	 */
	private Long seq;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="S07_NUM"
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
	 * @hibernate.property column="url"
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @hibernate.property column="target"
	 * @return Returns the target.
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target The target to set.
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @hibernate.property column="rel"
	 * @return Returns the rel.
	 */
	public String getRel() {
		return rel;
	}

	/**
	 * @param rel The rel to set.
	 */
	public void setRel(String rel) {
		this.rel = rel;
	}
	
	/**
	 * @hibernate.property column="up_id"
	 * @return Returns the up_id.
	 */
	public Long getUp_id() {
		return up_id;
	}

	/**
	 * @param name The up_id to set.
	 */
	public void setUp_id(Long up_id) {
		this.up_id = up_id;
	}
	
	/**
	 * @hibernate.property column="seq"
	 * @return Returns the seq.
	 */
	public Long getSeq() {
		return seq;
	}

	/**
	 * @param name The seq to set.
	 */
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	
}
