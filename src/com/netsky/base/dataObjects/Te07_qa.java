package com.netsky.base.dataObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lx 2010-07-14
 * @hibernate.class table="Te07_qa"
 */
public class Te07_qa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5334561840001719241L;
	/**
	 * id 标识 
	 */
	private Long id;
	/**
	 * 问题
	 */
    private String question;
    /**
     * 回答
     */
    private String answer;
    /**
     * 排序号
     */
    private Long ord;
    /**
     * 创建日期
     */
    private Date cjrq;
    /**
     * 排序号
     */
    private Long dj;
    
    /**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="Te07_NUM"
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
	 * @hibernate.property column="question"
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * @hibernate.property column="answer"
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/**
	 * @hibernate.property column="ord"
	 * @return the ord
	 */
	public Long getOrd() {
		return ord;
	}
	/**
	 * @param ord the ord to set
	 */
	public void setOrd(Long ord) {
		this.ord = ord;
	}
	/**
	 * @hibernate.property column="cjrq"
	 * @return the cjrq
	 */
	public Date getCjrq() {
		return cjrq;
	}
	/**
	 * @param cjrq the cjrq to set
	 */
	public void setCjrq(Date cjrq) {
		this.cjrq = cjrq;
	}
	/**
	 * @hibernate.property column="dj"
	 * @return the dj
	 */
	public Long getDj() {
		return dj;
	}
	/**
	 * @param dj the dj to set
	 */
	public void setDj(Long dj) {
		this.dj = dj;
	}
	
}

