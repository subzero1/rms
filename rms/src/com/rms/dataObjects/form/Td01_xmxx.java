package com.rms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Td01_xmxx"
 */

public class Td01_xmxx implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 333811677064548032L;

	/**
	 * 标识
	 */
	private Long id;
	
	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td01_num"
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
 * 设计审计开始时间
 */
 private Date sjshjkssj;

 /**
 * @hibernate.property column="sjshjkssj"
 * @return Returns the sjshjkssj.
 */
 public Date getSjshjkssj() {
    return sjshjkssj;
 }

 public void setSjshjkssj(Date sjshjkssj) {
    this.sjshjkssj = sjshjkssj;
  }

 /**
 * 设计审计结束时间
 */
 private Date sjshjjssj;

 /**
 * @hibernate.property column="sjshjjssj"
 * @return Returns the sjshjjssj.
 */
 public Date getSjshjjssj() {
    return sjshjjssj;
 }

 public void setSjshjjssj(Date sjshjjssj) {
    this.sjshjjssj = sjshjjssj;
  }

 /**
 * 当年累计投资完成额
 */
 private Long dnljtzwce;

 /**
 * @hibernate.property column="dnljtzwce"
 * @return Returns the dnljtzwce.
 */
 public Long getDnljtzwce() {
    return dnljtzwce;
 }

 public void setDnljtzwce(Long dnljtzwce) {
    this.dnljtzwce = dnljtzwce;
  }

 /**
 * 累计投资完成额
 */
 private Long ljtzwce;

 /**
 * @hibernate.property column="ljtzwce"
 * @return Returns the ljtzwce.
 */
 public Long getLjtzwce() {
    return ljtzwce;
 }

 public void setLjtzwce(Long ljtzwce) {
    this.ljtzwce = ljtzwce;
  }

 /**
 * 施工进度百分比（*工作量填报）
 */
 private Long wcbfb;

 /**
 * @hibernate.property column="wcbfb"
 * @return Returns the wcbfb.
 */
 public Long getWcbfb() {
    return wcbfb;
 }

 public void setWcbfb(Long wcbfb) {
    this.wcbfb = wcbfb;
  }

 /**
 * 设计调整前工期
 */
 private Date sjtzqgq;

 /**
 * @hibernate.property column="sjtzqgq"
 * @return Returns the sjtzqgq.
 */
 public Date getSjtzqgq() {
    return sjtzqgq;
 }

 public void setSjtzqgq(Date sjtzqgq) {
    this.sjtzqgq = sjtzqgq;
  }

 /**
 * 施工调整前工期
 */
 private Date sgtzqgq;

 /**
 * @hibernate.property column="sgtzqgq"
 * @return Returns the sgtzqgq.
 */
 public Date getSgtzqgq() {
    return sgtzqgq;
 }

 public void setSgtzqgq(Date sgtzqgq) {
    this.sgtzqgq = sgtzqgq;
  }

 /**
 * 初审提交时间
 */
 private Date cstjsj;

 /**
 * @hibernate.property column="cstjsj"
 * @return Returns the cstjsj.
 */
 public Date getCstjsj() {
    return cstjsj;
 }

 public void setCstjsj(Date cstjsj) {
    this.cstjsj = cstjsj;
  }

 /**
 * 送审日期
 */
 private Date ssrq;

 /**
 * @hibernate.property column="ssrq"
 * @return Returns the ssrq.
 */
 public Date getSsrq() {
    return ssrq;
 }

 public void setSsrq(Date ssrq) {
    this.ssrq = ssrq;
  }

 /**
 * 挂账日期
 */
 private Date gzrq;

 /**
 * @hibernate.property column="gzrq"
 * @return Returns the gzrq.
 */
 public Date getGzrq() {
    return gzrq;
 }

 public void setGzrq(Date gzrq) {
    this.gzrq = gzrq;
  }

 /**
 * 开票日期
 */
 private Date kprq;

 /**
 * @hibernate.property column="kprq"
 * @return Returns the kprq.
 */
 public Date getKprq() {
    return kprq;
 }

 public void setKprq(Date kprq) {
    this.kprq = kprq;
  }

 /**
 * 送审计日期
 */
 private Date ssjrq;

 /**
 * @hibernate.property column="ssjrq"
 * @return Returns the ssjrq.
 */
 public Date getSsjrq() {
    return ssjrq;
 }

 public void setSsjrq(Date ssjrq) {
    this.ssjrq = ssjrq;
  }

 /**
 * 设计方案通过次数（评分时使用）
 */
 private Long sjfatgcs;

 /**
 * @hibernate.property column="sjfatgcs"
 * @return Returns the sjfatgcs.
 */
 public Long getSjfatgcs() {
    return sjfatgcs;
 }

 public void setSjfatgcs(Long sjfatgcs) {
    this.sjfatgcs = sjfatgcs;
  }

 /**
 * 送审甲方材料费
 */
 private Long ss_clf_j;

 /**
 * @hibernate.property column="ss_clf_j"
 * @return Returns the ss_clf_j.
 */
 public Long getSs_clf_j() {
    return ss_clf_j;
 }

 public void setSs_clf_j(Long ss_clf_j) {
    this.ss_clf_j = ss_clf_j;
  }

 /**
 * 送审机械费
 */
 private Long ss_jxf;

 /**
 * @hibernate.property column="ss_jxf"
 * @return Returns the ss_jxf.
 */
 public Long getSs_jxf() {
    return ss_jxf;
 }

 public void setSs_jxf(Long ss_jxf) {
    this.ss_jxf = ss_jxf;
  }

 /**
 * 送审仪表费
 */
 private Long ss_ybf;

 /**
 * @hibernate.property column="ss_ybf"
 * @return Returns the ss_ybf.
 */
 public Long getSs_ybf() {
    return ss_ybf;
 }

 public void setSs_ybf(Long ss_ybf) {
    this.ss_ybf = ss_ybf;
  }

 /**
 * 送审其它费
 */
 private Long ss_qtf;

 /**
 * @hibernate.property column="ss_qtf"
 * @return Returns the ss_qtf.
 */
 public Long getSs_qtf() {
    return ss_qtf;
 }

 public void setSs_qtf(Long ss_qtf) {
    this.ss_qtf = ss_qtf;
  }

 /**
 * 送审施工费
 */
 private Long ss_sgf;

 /**
 * @hibernate.property column="ss_sgf"
 * @return Returns the ss_sgf.
 */
 public Long getSs_sgf() {
    return ss_sgf;
 }

 public void setSs_sgf(Long ss_sgf) {
    this.ss_sgf = ss_sgf;
  }

 /**
 * 送审监理费
 */
 private Long ss_jlf;

 /**
 * @hibernate.property column="ss_jlf"
 * @return Returns the ss_jlf.
 */
 public Long getSs_jlf() {
    return ss_jlf;
 }

 public void setSs_jlf(Long ss_jlf) {
    this.ss_jlf = ss_jlf;
  }

 /**
 * 送审设计费
 */
 private Long ss_sjf;

 /**
 * @hibernate.property column="ss_sjf"
 * @return Returns the ss_sjf.
 */
 public Long getSs_sjf() {
    return ss_sjf;
 }

 public void setSs_sjf(Long ss_sjf) {
    this.ss_sjf = ss_sjf;
  }

 /**
 * 送审金额
 */
 private Long ss_je;

 /**
 * @hibernate.property column="ss_je"
 * @return Returns the ss_je.
 */
 public Long getSs_je() {
    return ss_je;
 }

 public void setSs_je(Long ss_je) {
    this.ss_je = ss_je;
  }

 /**
 * 初审技工工日
 */
 private Long cs_jggr;

 /**
 * @hibernate.property column="cs_jggr"
 * @return Returns the cs_jggr.
 */
 public Long getCs_jggr() {
    return cs_jggr;
 }

 public void setCs_jggr(Long cs_jggr) {
    this.cs_jggr = cs_jggr;
  }

 /**
 * 初审普工工日
 */
 private Long cs_pggr;

 /**
 * @hibernate.property column="cs_pggr"
 * @return Returns the cs_pggr.
 */
 public Long getCs_pggr() {
    return cs_pggr;
 }

 public void setCs_pggr(Long cs_pggr) {
    this.cs_pggr = cs_pggr;
  }

 /**
 * 初审人工费
 */
 private Long cs_rgf;

 /**
 * @hibernate.property column="cs_rgf"
 * @return Returns the cs_rgf.
 */
 public Long getCs_rgf() {
    return cs_rgf;
 }

 public void setCs_rgf(Long cs_rgf) {
    this.cs_rgf = cs_rgf;
  }

 /**
 * 初审材料费
 */
 private Long cs_clf;

 /**
 * @hibernate.property column="cs_clf"
 * @return Returns the cs_clf.
 */
 public Long getCs_clf() {
    return cs_clf;
 }

 public void setCs_clf(Long cs_clf) {
    this.cs_clf = cs_clf;
  }

 /**
 * 初审
 */
 private Long cs_clf_j;

 /**
 * @hibernate.property column="cs_clf_j"
 * @return Returns the cs_clf_j.
 */
 public Long getCs_clf_j() {
    return cs_clf_j;
 }

 public void setCs_clf_j(Long cs_clf_j) {
    this.cs_clf_j = cs_clf_j;
  }

 /**
 * 甲方材料费
 */
 private Long cs_jxf;

 /**
 * @hibernate.property column="cs_jxf"
 * @return Returns the cs_jxf.
 */
 public Long getCs_jxf() {
    return cs_jxf;
 }

 public void setCs_jxf(Long cs_jxf) {
    this.cs_jxf = cs_jxf;
  }

 /**
 * 初审仪表费
 */
 private Long cs_ybf;

 /**
 * @hibernate.property column="cs_ybf"
 * @return Returns the cs_ybf.
 */
 public Long getCs_ybf() {
    return cs_ybf;
 }

 public void setCs_ybf(Long cs_ybf) {
    this.cs_ybf = cs_ybf;
  }

 /**
 * 初审其它费
 */
 private Long cs_qtf;

 /**
 * @hibernate.property column="cs_qtf"
 * @return Returns the cs_qtf.
 */
 public Long getCs_qtf() {
    return cs_qtf;
 }

 public void setCs_qtf(Long cs_qtf) {
    this.cs_qtf = cs_qtf;
  }

 /**
 * 初审施工费
 */
 private Long cs_sgf;

 /**
 * @hibernate.property column="cs_sgf"
 * @return Returns the cs_sgf.
 */
 public Long getCs_sgf() {
    return cs_sgf;
 }

 public void setCs_sgf(Long cs_sgf) {
    this.cs_sgf = cs_sgf;
  }

 /**
 * 初审监理费
 */
 private Long cs_jlf;

 /**
 * @hibernate.property column="cs_jlf"
 * @return Returns the cs_jlf.
 */
 public Long getCs_jlf() {
    return cs_jlf;
 }

 public void setCs_jlf(Long cs_jlf) {
    this.cs_jlf = cs_jlf;
  }

 /**
 * 初审设计费
 */
 private Long cs_sjf;

 /**
 * @hibernate.property column="cs_sjf"
 * @return Returns the cs_sjf.
 */
 public Long getCs_sjf() {
    return cs_sjf;
 }

 public void setCs_sjf(Long cs_sjf) {
    this.cs_sjf = cs_sjf;
  }

 /**
 * 初审金额
 */
 private Long cs_je;

 /**
 * @hibernate.property column="cs_je"
 * @return Returns the cs_je.
 */
 public Long getCs_je() {
    return cs_je;
 }

 public void setCs_je(Long cs_je) {
    this.cs_je = cs_je;
  }

 /**
 * 审定技工工日
 */
 private Long sd_jggr;

 /**
 * @hibernate.property column="sd_jggr"
 * @return Returns the sd_jggr.
 */
 public Long getSd_jggr() {
    return sd_jggr;
 }

 public void setSd_jggr(Long sd_jggr) {
    this.sd_jggr = sd_jggr;
  }

 /**
 * 审定普工工日
 */
 private Long sd_pggr;

 /**
 * @hibernate.property column="sd_pggr"
 * @return Returns the sd_pggr.
 */
 public Long getSd_pggr() {
    return sd_pggr;
 }

 public void setSd_pggr(Long sd_pggr) {
    this.sd_pggr = sd_pggr;
  }

 /**
 * 审定人工费
 */
 private Long sd_rgf;

 /**
 * @hibernate.property column="sd_rgf"
 * @return Returns the sd_rgf.
 */
 public Long getSd_rgf() {
    return sd_rgf;
 }

 public void setSd_rgf(Long sd_rgf) {
    this.sd_rgf = sd_rgf;
  }

 /**
 * 审定材料费
 */
 private Long sd_clf;

 /**
 * @hibernate.property column="sd_clf"
 * @return Returns the sd_clf.
 */
 public Long getSd_clf() {
    return sd_clf;
 }

 public void setSd_clf(Long sd_clf) {
    this.sd_clf = sd_clf;
  }

 /**
 * 审定甲方材料费
 */
 private Long sd_clf_j;

 /**
 * @hibernate.property column="sd_clf_j"
 * @return Returns the sd_clf_j.
 */
 public Long getSd_clf_j() {
    return sd_clf_j;
 }

 public void setSd_clf_j(Long sd_clf_j) {
    this.sd_clf_j = sd_clf_j;
  }

 /**
 * 审定机械费
 */
 private Long sd_jxf;

 /**
 * @hibernate.property column="sd_jxf"
 * @return Returns the sd_jxf.
 */
 public Long getSd_jxf() {
    return sd_jxf;
 }

 public void setSd_jxf(Long sd_jxf) {
    this.sd_jxf = sd_jxf;
  }

 /**
 * 审定仪表费
 */
 private Long sd_ybf;

 /**
 * @hibernate.property column="sd_ybf"
 * @return Returns the sd_ybf.
 */
 public Long getSd_ybf() {
    return sd_ybf;
 }

 public void setSd_ybf(Long sd_ybf) {
    this.sd_ybf = sd_ybf;
  }

 /**
 * 审定其它费
 */
 private Long sd_qtf;

 /**
 * @hibernate.property column="sd_qtf"
 * @return Returns the sd_qtf.
 */
 public Long getSd_qtf() {
    return sd_qtf;
 }

 public void setSd_qtf(Long sd_qtf) {
    this.sd_qtf = sd_qtf;
  }

 /**
 * 审定施工费
 */
 private Long sd_sgf;

 /**
 * @hibernate.property column="sd_sgf"
 * @return Returns the sd_sgf.
 */
 public Long getSd_sgf() {
    return sd_sgf;
 }

 public void setSd_sgf(Long sd_sgf) {
    this.sd_sgf = sd_sgf;
  }

 /**
 * 审定监理费
 */
 private Long sd_jlf;

 /**
 * @hibernate.property column="sd_jlf"
 * @return Returns the sd_jlf.
 */
 public Long getSd_jlf() {
    return sd_jlf;
 }

 public void setSd_jlf(Long sd_jlf) {
    this.sd_jlf = sd_jlf;
  }

 /**
 * 审定设计费
 */
 private Long sd_sjf;

 /**
 * @hibernate.property column="sd_sjf"
 * @return Returns the sd_sjf.
 */
 public Long getSd_sjf() {
    return sd_sjf;
 }

 public void setSd_sjf(Long sd_sjf) {
    this.sd_sjf = sd_sjf;
  }

 /**
 * 审定金额
 */
 private Long sd_je;

 /**
 * @hibernate.property column="sd_je"
 * @return Returns the sd_je.
 */
 public Long getSd_je() {
    return sd_je;
 }

 public void setSd_je(Long sd_je) {
    this.sd_je = sd_je;
  }

 /**
 * 工程说明
 */
 private String gcsm;

 /**
 * @hibernate.property column="gcsm"
 * @return Returns the gcsm.
 */
 public String getGcsm() {
    return gcsm;
 }

 public void setGcsm(String gcsm) {
    this.gcsm = gcsm;
  }

 /**
 * 施工单位
 */
 private String sgdw;

 /**
 * @hibernate.property column="sgdw"
 * @return Returns the sgdw.
 */
 public String getSgdw() {
    return sgdw;
 }

 public void setSgdw(String sgdw) {
    this.sgdw = sgdw;
  }

 /**
 * 施工队
 */
 private String sgd;

 /**
 * @hibernate.property column="sgd"
 * @return Returns the sgd.
 */
 public String getSgd() {
    return sgd;
 }

 public void setSgd(String sgd) {
    this.sgd = sgd;
  }

 /**
 * 设计单位
 */
 private String sjdw;

 /**
 * @hibernate.property column="sjdw"
 * @return Returns the sjdw.
 */
 public String getSjdw() {
    return sjdw;
 }

 public void setSjdw(String sjdw) {
    this.sjdw = sjdw;
  }

 /**
 * 监理单位
 */
 private String jldw;

 /**
 * @hibernate.property column="jldw"
 * @return Returns the jldw.
 */
 public String getJldw() {
    return jldw;
 }

 public void setJldw(String jldw) {
    this.jldw = jldw;
  }

 /**
 * 需求提出人
 */
 private String xqtcr;

 /**
 * @hibernate.property column="xqtcr"
 * @return Returns the xqtcr.
 */
 public String getXqtcr() {
    return xqtcr;
 }

 public void setXqtcr(String xqtcr) {
    this.xqtcr = xqtcr;
  }

 /**
 * 调配管理人员
 */
 private String tpgly;

 /**
 * @hibernate.property column="tpgly"
 * @return Returns the tpgly.
 */
 public String getTpgly() {
    return tpgly;
 }

 public void setTpgly(String tpgly) {
    this.tpgly = tpgly;
  }

 /**
 * 设计派工人员
 */
 private String sjpgry;

 /**
 * @hibernate.property column="sjpgry"
 * @return Returns the sjpgry.
 */
 public String getSjpgry() {
    return sjpgry;
 }

 public void setSjpgry(String sjpgry) {
    this.sjpgry = sjpgry;
  }

 /**
 * 设计审核人员
 */
 private String sjshry;

 /**
 * @hibernate.property column="sjshry"
 * @return Returns the sjshry.
 */
 public String getSjshry() {
    return sjshry;
 }

 public void setSjshry(String sjshry) {
    this.sjshry = sjshry;
  }

 /**
 * 设计人员
 */
 private String sjry;

 /**
 * @hibernate.property column="sjry"
 * @return Returns the sjry.
 */
 public String getSjry() {
    return sjry;
 }

 public void setSjry(String sjry) {
    this.sjry = sjry;
  }

 /**
 * 立项管理员
 */
 private String lxgly;

 /**
 * @hibernate.property column="lxgly"
 * @return Returns the lxgly.
 */
 public String getLxgly() {
    return lxgly;
 }

 public void setLxgly(String lxgly) {
    this.lxgly = lxgly;
  }

 /**
 * 项目经理
 */
 private String xmjl;

 /**
 * @hibernate.property column="xmjl"
 * @return Returns the xmjl.
 */
 public String getXmjl() {
    return xmjl;
 }

 public void setXmjl(String xmjl) {
    this.xmjl = xmjl;
  }

 /**
 * 集中派工人员
 */
 private String jzpgry;

 /**
 * @hibernate.property column="jzpgry"
 * @return Returns the jzpgry.
 */
 public String getJzpgry() {
    return jzpgry;
 }

 public void setJzpgry(String jzpgry) {
    this.jzpgry = jzpgry;
  }

 /**
 * 审计人员
 */
 private String shjry;

 /**
 * @hibernate.property column="shjry"
 * @return Returns the shjry.
 */
 public String getShjry() {
    return shjry;
 }

 public void setShjry(String shjry) {
    this.shjry = shjry;
  }

 /**
 * 复审人员
 */
 private String fsry;

 /**
 * @hibernate.property column="fsry"
 * @return Returns the fsry.
 */
 public String getFsry() {
    return fsry;
 }

 public void setFsry(String fsry) {
    this.fsry = fsry;
  }

 /**
 * 设计负责人
 */
 private String sjfzr;

 /**
 * @hibernate.property column="sjfzr"
 * @return Returns the sjfzr.
 */
 public String getSjfzr() {
    return sjfzr;
 }

 public void setSjfzr(String sjfzr) {
    this.sjfzr = sjfzr;
  }

 /**
 * 设计负责人电话
 */
 private String sjfzrdh;

 /**
 * @hibernate.property column="sjfzrdh"
 * @return Returns the sjfzrdh.
 */
 public String getSjfzrdh() {
    return sjfzrdh;
 }

 public void setSjfzrdh(String sjfzrdh) {
    this.sjfzrdh = sjfzrdh;
  }

 /**
 * 施工负责人
 */
 private String sgfzr;

 /**
 * @hibernate.property column="sgfzr"
 * @return Returns the sgfzr.
 */
 public String getSgfzr() {
    return sgfzr;
 }

 public void setSgfzr(String sgfzr) {
    this.sgfzr = sgfzr;
  }

 /**
 * 施工负责人电话
 */
 private String sgfzrdh;

 /**
 * @hibernate.property column="sgfzrdh"
 * @return Returns the sgfzrdh.
 */
 public String getSgfzrdh() {
    return sgfzrdh;
 }

 public void setSgfzrdh(String sgfzrdh) {
    this.sgfzrdh = sgfzrdh;
  }

 /**
 * 监理工程师
 */
 private String jlgcs;

 /**
 * @hibernate.property column="jlgcs"
 * @return Returns the jlgcs.
 */
 public String getJlgcs() {
    return jlgcs;
 }

 public void setJlgcs(String jlgcs) {
    this.jlgcs = jlgcs;
  }

 /**
 * 监理工程师电话
 */
 private String jlgcsdh;

 /**
 * @hibernate.property column="jlgcsdh"
 * @return Returns the jlgcsdh.
 */
 public String getJlgcsdh() {
    return jlgcsdh;
 }

 public void setJlgcsdh(String jlgcsdh) {
    this.jlgcsdh = jlgcsdh;
  }

 /**
 * 审计编号
 */
 private String shjbh;

 /**
 * @hibernate.property column="shjbh"
 * @return Returns the shjbh.
 */
 public String getShjbh() {
    return shjbh;
 }

 public void setShjbh(String shjbh) {
    this.shjbh = shjbh;
  }

 /**
 * 设计审计编号
 */
 private String sjshjbh;

 /**
 * @hibernate.property column="sjshjbh"
 * @return Returns the sjshjbh.
 */
 public String getSjshjbh() {
    return sjshjbh;
 }

 public void setSjshjbh(String sjshjbh) {
    this.sjshjbh = sjshjbh;
  }

 /**
 * 需求提出时间
 */
 private Date xqtcsj;

 /**
 * @hibernate.property column="xqtcsj"
 * @return Returns the xqtcsj.
 */
 public Date getXqtcsj() {
    return xqtcsj;
 }

 public void setXqtcsj(Date xqtcsj) {
    this.xqtcsj = xqtcsj;
  }

 /**
 * 设计派工时间
 */
 private Date sjpgsj;

 /**
 * @hibernate.property column="sjpgsj"
 * @return Returns the sjpgsj.
 */
 public Date getSjpgsj() {
    return sjpgsj;
 }

 public void setSjpgsj(Date sjpgsj) {
    this.sjpgsj = sjpgsj;
  }

 /**
 * 计划设计完成时间
 */
 private Date jhsjwcsj;

 /**
 * @hibernate.property column="jhsjwcsj"
 * @return Returns the jhsjwcsj.
 */
 public Date getJhsjwcsj() {
    return jhsjwcsj;
 }

 public void setJhsjwcsj(Date jhsjwcsj) {
    this.jhsjwcsj = jhsjwcsj;
  }

 /**
 * 设计提交时间
 */
 private Date sjtjsj;

 /**
 * @hibernate.property column="sjtjsj"
 * @return Returns the sjtjsj.
 */
 public Date getSjtjsj() {
    return sjtjsj;
 }

 public void setSjtjsj(Date sjtjsj) {
    this.sjtjsj = sjtjsj;
  }

 /**
 * 立项时间
 */
 private Date lxsj;

 /**
 * @hibernate.property column="lxsj"
 * @return Returns the lxsj.
 */
 public Date getLxsj() {
    return lxsj;
 }

 public void setLxsj(Date lxsj) {
    this.lxsj = lxsj;
  }

 /**
 * 施工派发时间
 */
 private Date sgpfsj;

 /**
 * @hibernate.property column="sgpfsj"
 * @return Returns the sgpfsj.
 */
 public Date getSgpfsj() {
    return sgpfsj;
 }

 public void setSgpfsj(Date sgpfsj) {
    this.sgpfsj = sgpfsj;
  }

 /**
 * 计划开工时间
 */
 private Date jhkgsj;

 /**
 * @hibernate.property column="jhkgsj"
 * @return Returns the jhkgsj.
 */
 public Date getJhkgsj() {
    return jhkgsj;
 }

 public void setJhkgsj(Date jhkgsj) {
    this.jhkgsj = jhkgsj;
  }

 /**
 * 计划竣工时间
 */
 private Date jhjgsj;

 /**
 * @hibernate.property column="jhjgsj"
 * @return Returns the jhjgsj.
 */
 public Date getJhjgsj() {
    return jhjgsj;
 }

 public void setJhjgsj(Date jhjgsj) {
    this.jhjgsj = jhjgsj;
  }

 /**
 * 实际开工时间
 */
 private Date sjkgsj;

 /**
 * @hibernate.property column="sjkgsj"
 * @return Returns the sjkgsj.
 */
 public Date getSjkgsj() {
    return sjkgsj;
 }

 public void setSjkgsj(Date sjkgsj) {
    this.sjkgsj = sjkgsj;
  }

 /**
 * 实际竣工时间
 */
 private Date sjjgsj;

 /**
 * @hibernate.property column="sjjgsj"
 * @return Returns the sjjgsj.
 */
 public Date getSjjgsj() {
    return sjjgsj;
 }

 public void setSjjgsj(Date sjjgsj) {
    this.sjjgsj = sjjgsj;
  }

 /**
 * 审计开始时间
 */
 private Date sjkssj;

 /**
 * @hibernate.property column="sjkssj"
 * @return Returns the sjkssj.
 */
 public Date getSjkssj() {
    return sjkssj;
 }

 public void setSjkssj(Date sjkssj) {
    this.sjkssj = sjkssj;
  }

 /**
 * 审计结束时间
 */
 private Date sjjssj;

 /**
 * @hibernate.property column="sjjssj"
 * @return Returns the sjjssj.
 */
 public Date getSjjssj() {
    return sjjssj;
 }

 public void setSjjssj(Date sjjssj) {
    this.sjjssj = sjjssj;
  }

 /**
 * 监理派发时间
 */
 private Date jlpfsj;

 /**
 * @hibernate.property column="jlpfsj"
 * @return Returns the jlpfsj.
 */
 public Date getJlpfsj() {
    return jlpfsj;
 }

 public void setJlpfsj(Date jlpfsj) {
    this.jlpfsj = jlpfsj;
  }

 /**
 * 工程阶段
 */
 private String gcjd;

 /**
 * @hibernate.property column="gcjd"
 * @return Returns the gcjd.
 */
 public String getGcjd() {
    return gcjd;
 }

 public void setGcjd(String gcjd) {
    this.gcjd = gcjd;
  }

 /**
 * 工程状态
 */
 private String gczt;

 /**
 * @hibernate.property column="gczt"
 * @return Returns the gczt.
 */
 public String getGczt() {
    return gczt;
 }

 public void setGczt(String gczt) {
    this.gczt = gczt;
  }

 /**
 * 所属年度
 */
 private Long ssnd;

 /**
 * @hibernate.property column="ssnd"
 * @return Returns the ssnd.
 */
 public Long getSsnd() {
    return ssnd;
 }

 public void setSsnd(Long ssnd) {
    this.ssnd = ssnd;
  }

 /**
 * 监理日志填报周期
 */
 private Long jlrjtbzq;

 /**
 * @hibernate.property column="jlrjtbzq"
 * @return Returns the jlrjtbzq.
 */
 public Long getJlrjtbzq() {
    return jlrjtbzq;
 }

 public void setJlrjtbzq(Long jlrjtbzq) {
    this.jlrjtbzq = jlrjtbzq;
  }

 /**
 * 施工进度填报周期（施工单位）
 */
 private Long sgjdtbzq;

 /**
 * @hibernate.property column="sgjdtbzq"
 * @return Returns the sgjdtbzq.
 */
 public Long getSgjdtbzq() {
    return sgjdtbzq;
 }

 public void setSgjdtbzq(Long sgjdtbzq) {
    this.sgjdtbzq = sgjdtbzq;
  }

 /**
 * 勘察反馈周期
 */
 private Long kcfkzq;

 /**
 * @hibernate.property column="kcfkzq"
 * @return Returns the kcfkzq.
 */
 public Long getKcfkzq() {
    return kcfkzq;
 }

 public void setKcfkzq(Long kcfkzq) {
    this.kcfkzq = kcfkzq;
  }

 /**
 * 验收次数（评分时使用）
 */
 private Long yscs;

 /**
 * @hibernate.property column="yscs"
 * @return Returns the yscs.
 */
 public Long getYscs() {
    return yscs;
 }

 public void setYscs(Long yscs) {
    this.yscs = yscs;
  }

 /**
 * 结算人
 */
 private String jsry;

 /**
 * @hibernate.property column="jsry"
 * @return Returns the jsry.
 */
 public String getJsry() {
    return jsry;
 }

 public void setJsry(String jsry) {
    this.jsry = jsry;
  }

 /**
 * 竣工资料岗
 */
 private String jgzlry;

 /**
 * @hibernate.property column="jgzlry"
 * @return Returns the jgzlry.
 */
 public String getJgzlry() {
    return jgzlry;
 }

 public void setJgzlry(String jgzlry) {
    this.jgzlry = jgzlry;
  }

 /**
 * 材料核销人员
 */
 private String clhxry;

 /**
 * @hibernate.property column="clhxry"
 * @return Returns the clhxry.
 */
 public String getClhxry() {
    return clhxry;
 }

 public void setClhxry(String clhxry) {
    this.clhxry = clhxry;
  }

 /**
 * 外协审计单位
 */
 private String shjdw;

 /**
 * @hibernate.property column="shjdw"
 * @return Returns the shjdw.
 */
 public String getShjdw() {
    return shjdw;
 }

 public void setShjdw(String shjdw) {
    this.shjdw = shjdw;
  }

 /**
 * 项目ID
 */
 private Long xm_id;

 /**
 * @hibernate.property column="xm_id"
 * @return Returns the xm_id.
 */
 public Long getXm_id() {
    return xm_id;
 }

 public void setXm_id(Long xm_id) {
    this.xm_id = xm_id;
  }

 /**
 * 工程编号
 */
 private String gcbh;

 /**
 * @hibernate.property column="gcbh"
 * @return Returns the gcbh.
 */
 public String getGcbh() {
    return gcbh;
 }

 public void setGcbh(String gcbh) {
    this.gcbh = gcbh;
  }

 /**
 * 工程名称
 */
 private String gcmc;

 /**
 * @hibernate.property column="gcmc"
 * @return Returns the gcmc.
 */
 public String getGcmc() {
    return gcmc;
 }

 public void setGcmc(String gcmc) {
    this.gcmc = gcmc;
  }

 /**
 * 所属地区
 */
 private String ssdq;

 /**
 * @hibernate.property column="ssdq"
 * @return Returns the ssdq.
 */
 public String getSsdq() {
    return ssdq;
 }

 public void setSsdq(String ssdq) {
    this.ssdq = ssdq;
  }

 /**
 * 工程类别
 */
 private String gclb;

 /**
 * @hibernate.property column="gclb"
 * @return Returns the gclb.
 */
 public String getGclb() {
    return gclb;
 }

 public void setGclb(String gclb) {
    this.gclb = gclb;
  }

 /**
 * 专业大类
 */
 private String zydl;

 /**
 * @hibernate.property column="zydl"
 * @return Returns the zydl.
 */
 public String getZydl() {
    return zydl;
 }

 public void setZydl(String zydl) {
    this.zydl = zydl;
  }

 /**
 * 专业小项
 */
 private String zyxx;

 /**
 * @hibernate.property column="zyxx"
 * @return Returns the zyxx.
 */
 public String getZyxx() {
    return zyxx;
 }

 public void setZyxx(String zyxx) {
    this.zyxx = zyxx;
  }

 /**
 * 切块大类
 */
 private String qkdl;

 /**
 * @hibernate.property column="qkdl"
 * @return Returns the qkdl.
 */
 public String getQkdl() {
    return qkdl;
 }

 public void setQkdl(String qkdl) {
    this.qkdl = qkdl;
  }

 /**
 * 切块小类
 */
 private String qkxl;

 /**
 * @hibernate.property column="qkxl"
 * @return Returns the qkxl.
 */
 public String getQkxl() {
    return qkxl;
 }

 public void setQkxl(String qkxl) {
    this.qkxl = qkxl;
  }

 /**
 * 建设性质
 */
 private String jsxz;

 /**
 * @hibernate.property column="jsxz"
 * @return Returns the jsxz.
 */
 public String getJsxz() {
    return jsxz;
 }

 public void setJsxz(String jsxz) {
    this.jsxz = jsxz;
  }

 /**
 * 建设地点
 */
 private String jsdd;

 /**
 * @hibernate.property column="jsdd"
 * @return Returns the jsdd.
 */
 public String getJsdd() {
    return jsdd;
 }

 public void setJsdd(String jsdd) {
    this.jsdd = jsdd;
  }

 /**
 * 预算类型
 */
 private String yslx;

 /**
 * @hibernate.property column="yslx"
 * @return Returns the yslx.
 */
 public String getYslx() {
    return yslx;
 }

 public void setYslx(String yslx) {
    this.yslx = yslx;
  }

 /**
 * 预算金额
 */
 private Long ys_je;

 /**
 * @hibernate.property column="ys_je"
 * @return Returns the ys_je.
 */
 public Long getYs_je() {
    return ys_je;
 }

 public void setYs_je(Long ys_je) {
    this.ys_je = ys_je;
  }

 /**
 * 预算建安费
 */
 private Long ys_jaf;

 /**
 * @hibernate.property column="ys_jaf"
 * @return Returns the ys_jaf.
 */
 public Long getYs_jaf() {
    return ys_jaf;
 }

 public void setYs_jaf(Long ys_jaf) {
    this.ys_jaf = ys_jaf;
  }

 /**
 * 预算材料费
 */
 private Long ys_clf;

 /**
 * @hibernate.property column="ys_clf"
 * @return Returns the ys_clf.
 */
 public Long getYs_clf() {
    return ys_clf;
 }

 public void setYs_clf(Long ys_clf) {
    this.ys_clf = ys_clf;
  }

 /**
 * 预算人工费
 */
 private Long ys_rgf;

 /**
 * @hibernate.property column="ys_rgf"
 * @return Returns the ys_rgf.
 */
 public Long getYs_rgf() {
    return ys_rgf;
 }

 public void setYs_rgf(Long ys_rgf) {
    this.ys_rgf = ys_rgf;
  }

 /**
 * 预算技工工日
 */
 private Long ys_jggr;

 /**
 * @hibernate.property column="ys_jggr"
 * @return Returns the ys_jggr.
 */
 public Long getYs_jggr() {
    return ys_jggr;
 }

 public void setYs_jggr(Long ys_jggr) {
    this.ys_jggr = ys_jggr;
  }

 /**
 * 预算普工工日
 */
 private Long ys_pggr;

 /**
 * @hibernate.property column="ys_pggr"
 * @return Returns the ys_pggr.
 */
 public Long getYs_pggr() {
    return ys_pggr;
 }

 public void setYs_pggr(Long ys_pggr) {
    this.ys_pggr = ys_pggr;
  }

 /**
 * 预算设备费
 */
 private Long ys_sbf;

 /**
 * @hibernate.property column="ys_sbf"
 * @return Returns the ys_sbf.
 */
 public Long getYs_sbf() {
    return ys_sbf;
 }

 public void setYs_sbf(Long ys_sbf) {
    this.ys_sbf = ys_sbf;
  }

 /**
 * 预算机械费
 */
 private Long ys_jxf;

 /**
 * @hibernate.property column="ys_jxf"
 * @return Returns the ys_jxf.
 */
 public Long getYs_jxf() {
    return ys_jxf;
 }

 public void setYs_jxf(Long ys_jxf) {
    this.ys_jxf = ys_jxf;
  }

 /**
 * 预算仪表费
 */
 private Long ys_ybf;

 /**
 * @hibernate.property column="ys_ybf"
 * @return Returns the ys_ybf.
 */
 public Long getYs_ybf() {
    return ys_ybf;
 }

 public void setYs_ybf(Long ys_ybf) {
    this.ys_ybf = ys_ybf;
  }

 /**
 * 预算设计费
 */
 private Long ys_sjf;

 /**
 * @hibernate.property column="ys_sjf"
 * @return Returns the ys_sjf.
 */
 public Long getYs_sjf() {
    return ys_sjf;
 }

 public void setYs_sjf(Long ys_sjf) {
    this.ys_sjf = ys_sjf;
  }

 /**
 * 预算监理费
 */
 private Long ys_jlf;

 /**
 * @hibernate.property column="ys_jlf"
 * @return Returns the ys_jlf.
 */
 public Long getYs_jlf() {
    return ys_jlf;
 }

 public void setYs_jlf(Long ys_jlf) {
    this.ys_jlf = ys_jlf;
  }

 /**
 * 仪表其它费
 */
 private Long ys_qtf;

 /**
 * @hibernate.property column="ys_qtf"
 * @return Returns the ys_qtf.
 */
 public Long getYs_qtf() {
    return ys_qtf;
 }

 public void setYs_qtf(Long ys_qtf) {
    this.ys_qtf = ys_qtf;
  }

 /**
 * 预算施工费
 */
 private Long ys_sgf;

 /**
 * @hibernate.property column="ys_sgf"
 * @return Returns the ys_sgf.
 */
 public Long getYs_sgf() {
    return ys_sgf;
 }

 public void setYs_sgf(Long ys_sgf) {
    this.ys_sgf = ys_sgf;
  }

 /**
 * 立项金额
 */
 private Long lxje;

 /**
 * @hibernate.property column="lxje"
 * @return Returns the lxje.
 */
 public Long getLxje() {
    return lxje;
 }

 public void setLxje(Long lxje) {
    this.lxje = lxje;
  }

 /**
 * 变更技工工日
 */
 private Long bg_jggr;

 /**
 * @hibernate.property column="bg_jggr"
 * @return Returns the bg_jggr.
 */
 public Long getBg_jggr() {
    return bg_jggr;
 }

 public void setBg_jggr(Long bg_jggr) {
    this.bg_jggr = bg_jggr;
  }

 /**
 * 变更普工工日
 */
 private Long bg_pggr;

 /**
 * @hibernate.property column="bg_pggr"
 * @return Returns the bg_pggr.
 */
 public Long getBg_pggr() {
    return bg_pggr;
 }

 public void setBg_pggr(Long bg_pggr) {
    this.bg_pggr = bg_pggr;
  }

 /**
 * 变更金额
 */
 private Long bg_je;

 /**
 * @hibernate.property column="bg_je"
 * @return Returns the bg_je.
 */
 public Long getBg_je() {
    return bg_je;
 }

 public void setBg_je(Long bg_je) {
    this.bg_je = bg_je;
  }

 /**
 * 送审技工工日
 */
 private Long ss_jggr;

 /**
 * @hibernate.property column="ss_jggr"
 * @return Returns the ss_jggr.
 */
 public Long getSs_jggr() {
    return ss_jggr;
 }

 public void setSs_jggr(Long ss_jggr) {
    this.ss_jggr = ss_jggr;
  }

 /**
 * 送审普工工日
 */
 private Long ss_pggr;

 /**
 * @hibernate.property column="ss_pggr"
 * @return Returns the ss_pggr.
 */
 public Long getSs_pggr() {
    return ss_pggr;
 }

 public void setSs_pggr(Long ss_pggr) {
    this.ss_pggr = ss_pggr;
  }

 /**
 * 送审人工费
 */
 private Long ss_rgf;

 /**
 * @hibernate.property column="ss_rgf"
 * @return Returns the ss_rgf.
 */
 public Long getSs_rgf() {
    return ss_rgf;
 }

 public void setSs_rgf(Long ss_rgf) {
    this.ss_rgf = ss_rgf;
  }

 /**
 * 送审材料费
 */
 private Long ss_clf;

 /**
 * @hibernate.property column="ss_clf"
 * @return Returns the ss_clf.
 */
 public Long getSs_clf() {
    return ss_clf;
 }

 public void setSs_clf(Long ss_clf) {
    this.ss_clf = ss_clf;
  }


}
