package com.netsky.base.dataObjects;

import java.io.Serializable;

/**
 * 节点选择人员范围配置表
 * @author lee.xiangyu
 * @create 2010-12-13
 * @hibernate.class table="Ta33_tszf"
 */
public class Ta33_tszf implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2753563739180234780L;
	/**
	 * id
	 */
	private Long id;
	
	/**
	 * 替换前字符
	 */
	private String thqzf;
	
	/**
	 * 替换后字符
	 */
	private String thhzf;
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Ta33_NUM"
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @hibernate.property column="thqzf"
	 * @return thqzf
	 */
	public String getThqzf() {
		return thqzf;
	}
	public void setThqzf(String thqzf) {
		this.thqzf = thqzf;
	}
	
	/**
	 * @hibernate.property column="thhzf"
	 * @return thhzf
	 */
	public String getThhzf() {
		return thhzf;
	}
	public void setThhzf(String thhzf) {
		this.thhzf = thhzf;
	}
	
	
}
