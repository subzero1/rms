package com.netsky.base.dataObjects;

import java.io.Serializable;

import com.netsky.base.annotation.LogDiaryField;
import com.netsky.base.annotation.LogDiaryType;

/**
 * @author mengying 2009-12-11
 * @hibernate.class table="Ta05_group"
 */
@LogDiaryType
public class Ta05_group implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5118851640493093244L;

	/**
	 * 标识
	 */
	@LogDiaryField
	private Long id;
	
	/**
	 * 群组级别
	 */
	@LogDiaryField
	private Integer grade;
	
	/**
	 * 群组名称
	 */
	@LogDiaryField
	private String name;
	
	/**
	 * 上一级标识
	 */
	@LogDiaryField
	private Long up_id;
	/**
	 * 群组描述
	 */
	@LogDiaryField
	private String dis;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta05_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="grade"
	 * @return the grade
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * @hibernate.property column="name"
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property column="up_id"
	 * @return the up_id
	 */
	public Long getUp_id() {
		return up_id;
	}

	/**
	 * @param up_id the up_id to set
	 */
	public void setUp_id(Long up_id) {
		this.up_id = up_id;
	}
	/**
	 * @hibernate.property column="dis"
	 * @return dis
	 */
	public String getDis() {
		return dis;
	}
	/**
	 * @param dis
	 */
	public void setDis(String dis) {
		this.dis = dis;
	}
	

}
