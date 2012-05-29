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
	 *地区编码
	 */
	private String code;
	
	/**
	 *地区名称
	 */
	private String name;

	/**
	 *地区子集
	 */
	private String inc_names;
	
	/**
	 *功能类别[1][2][3]...
	 */
	private String type;
	
	/**
	 *顺序
	 */
	private Integer seq;

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
	 * @hibernate.property column="code"
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @hibernate.property column="inc_names"
	 * @return Returns the inc_names.
	 */
	public String getInc_names() {
		return inc_names;
	}

	public void setInc_names(String inc_names) {
		this.inc_names = inc_names;
	}

	/**
	 * @hibernate.property column="type"
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @hibernate.property column="seq"
	 * @return Returns the seq.
	 */
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
}
