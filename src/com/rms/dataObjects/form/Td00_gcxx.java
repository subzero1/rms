package com.rms.dataObjects.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cmp_auto 2012-06-04
 * @hibernate.class table="Td00_gcxx"
 */

public class Td00_gcxx implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 358619373513707456L;

	/**
	 * 标识
	 */
	private Long id;

	/**
	 * @hibernate.id generator-class="sequence"
	 * @hibernate.generator-param name="sequence" value="td00_num"
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
	 * 项目ID
	 */
	private Long glgc_id;

	/**
	 * @hibernate.property column="glgc_id"
	 * @return Returns the glgc_id.
	 */
	public Long getGlgc_id() {
		return glgc_id;
	}

	public void setGlgc_id(Long glgc_id) {
		this.glgc_id = glgc_id;
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
	 * 需求部门
	 */
	private String xqbm;

	/**
	 * @hibernate.property column="xqbm"
	 * @return Returns the xqbm.
	 */
	public String getXqbm() {
		return xqbm;
	}

	public void setXqbm(String xqbm) {
		this.xqbm = xqbm;
	}

	/**
	 * 勘察反馈时限
	 */
	private Long kcfksj;

	/**
	 * @hibernate.property column="kcfksj"
	 * @return Returns the kcfksj.
	 */
	public Long getKcfksj() {
		return kcfksj;
	}

	public void setKcfksj(Long kcfksj) {
		this.kcfksj = kcfksj;
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
	 * 设计时限
	 */
	private Long sjsx;

	/**
	 * @hibernate.property column="sjsx"
	 * @return Returns the sjsx.
	 */
	public Long getSjsx() {
		return sjsx;
	}

	public void setSjsx(Long sjsx) {
		this.sjsx = sjsx;
	}

	/**
	 * 进度填报周期
	 */
	private Long jdtbzq;

	/**
	 * @hibernate.property column="jdtbzq"
	 * @return Returns the jdtbzq.
	 */
	public Long getJdtbzq() {
		return jdtbzq;
	}

	public void setJdtbzq(Long jdtbzq) {
		this.jdtbzq = jdtbzq;
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
	 * 日志填报周期
	 */
	private Long rztbzq;

	/**
	 * @hibernate.property column="rztbzq"
	 * @return Returns the rztbzq.
	 */
	public Long getRztbzq() {
		return rztbzq;
	}

	public void setRztbzq(Long rztbzq) {
		this.rztbzq = rztbzq;
	}

	/**
	 * 项目管理员
	 */
	private String xmgly;

	/**
	 * @hibernate.property column="xmgly"
	 * @return Returns the xmgly.
	 */
	public String getXmgly() {
		return xmgly;
	}

	public void setXmgly(String xmgly) {
		this.xmgly = xmgly;
	}

	/**
	 * 设计要求
	 */
	private String sjyq;

	/**
	 * @hibernate.property column="sjyq"
	 * @return Returns the sjyq.
	 */
	public String getSjyq() {
		return sjyq;
	}

	public void setSjyq(String sjyq) {
		this.sjyq = sjyq;
	}

	/**
	 * 设计反馈
	 */
	private String sjfk;

	/**
	 * @hibernate.property column="sjfk"
	 * @return Returns the sjfk.
	 */
	public String getSjfk() {
		return sjfk;
	}

	public void setSjfk(String sjfk) {
		this.sjfk = sjfk;
	}

	/**
	 * 施工要求
	 */
	private String sgyq;

	/**
	 * @hibernate.property column="sgyq"
	 * @return Returns the sgyq.
	 */
	public String getSgyq() {
		return sgyq;
	}

	public void setSgyq(String sgyq) {
		this.sgyq = sgyq;
	}

	/**
	 * 施工反馈
	 */
	private String sgfk;

	/**
	 * @hibernate.property column="sgfk"
	 * @return Returns the sgfk.
	 */
	public String getSgfk() {
		return sgfk;
	}

	public void setSgfk(String sgfk) {
		this.sgfk = sgfk;
	}

	/**
	 * 监理要求
	 */
	private String jlyq;

	/**
	 * @hibernate.property column="jlyq"
	 * @return Returns the jlyq.
	 */
	public String getJlyq() {
		return jlyq;
	}

	public void setJlyq(String jlyq) {
		this.jlyq = jlyq;
	}

	/**
	 * 预算金额
	 */
	private Double ys_je;

	/**
	 * @hibernate.property column="ys_je"
	 * @return Returns the ys_je.
	 */
	public Double getYs_je() {
		return ys_je;
	}

	public void setYs_je(Double ys_je) {
		this.ys_je = ys_je;
	}

	/**
	 * 预算建安费
	 */
	private Double ys_jaf;

	/**
	 * @hibernate.property column="ys_jaf"
	 * @return Returns the ys_jaf.
	 */
	public Double getYs_jaf() {
		return ys_jaf;
	}

	public void setYs_jaf(Double ys_jaf) {
		this.ys_jaf = ys_jaf;
	}

	/**
	 * 预算材料费
	 */
	private Double ys_clf;

	/**
	 * @hibernate.property column="ys_clf"
	 * @return Returns the ys_clf.
	 */
	public Double getYs_clf() {
		return ys_clf;
	}

	public void setYs_clf(Double ys_clf) {
		this.ys_clf = ys_clf;
	}

	/**
	 * 预算人工费
	 */
	private Double ys_rgf;

	/**
	 * @hibernate.property column="ys_rgf"
	 * @return Returns the ys_rgf.
	 */
	public Double getYs_rgf() {
		return ys_rgf;
	}

	public void setYs_rgf(Double ys_rgf) {
		this.ys_rgf = ys_rgf;
	}

	/**
	 * 预算技工工日
	 */
	private Double ys_jggr;

	/**
	 * @hibernate.property column="ys_jggr"
	 * @return Returns the ys_jggr.
	 */
	public Double getYs_jggr() {
		return ys_jggr;
	}

	public void setYs_jggr(Double ys_jggr) {
		this.ys_jggr = ys_jggr;
	}

	/**
	 * 预算普工工日
	 */
	private Double ys_pggr;

	/**
	 * @hibernate.property column="ys_pggr"
	 * @return Returns the ys_pggr.
	 */
	public Double getYs_pggr() {
		return ys_pggr;
	}

	public void setYs_pggr(Double ys_pggr) {
		this.ys_pggr = ys_pggr;
	}

	/**
	 * 预算设备费
	 */
	private Double ys_sbf;

	/**
	 * @hibernate.property column="ys_sbf"
	 * @return Returns the ys_sbf.
	 */
	public Double getYs_sbf() {
		return ys_sbf;
	}

	public void setYs_sbf(Double ys_sbf) {
		this.ys_sbf = ys_sbf;
	}

	/**
	 * 预算机械费
	 */
	private Double ys_jxf;

	/**
	 * @hibernate.property column="ys_jxf"
	 * @return Returns the ys_jxf.
	 */
	public Double getYs_jxf() {
		return ys_jxf;
	}

	public void setYs_jxf(Double ys_jxf) {
		this.ys_jxf = ys_jxf;
	}

	/**
	 * 预算仪表费
	 */
	private Double ys_ybf;

	/**
	 * @hibernate.property column="ys_ybf"
	 * @return Returns the ys_ybf.
	 */
	public Double getYs_ybf() {
		return ys_ybf;
	}

	public void setYs_ybf(Double ys_ybf) {
		this.ys_ybf = ys_ybf;
	}

	/**
	 * 预算设计费
	 */
	private Double ys_sjf;

	/**
	 * @hibernate.property column="ys_sjf"
	 * @return Returns the ys_sjf.
	 */
	public Double getYs_sjf() {
		return ys_sjf;
	}

	public void setYs_sjf(Double ys_sjf) {
		this.ys_sjf = ys_sjf;
	}

	/**
	 * 预算监理费
	 */
	private Double ys_jlf;

	/**
	 * @hibernate.property column="ys_jlf"
	 * @return Returns the ys_jlf.
	 */
	public Double getYs_jlf() {
		return ys_jlf;
	}

	public void setYs_jlf(Double ys_jlf) {
		this.ys_jlf = ys_jlf;
	}

	/**
	 * 仪表其它费
	 */
	private Double ys_qtf;

	/**
	 * @hibernate.property column="ys_qtf"
	 * @return Returns the ys_qtf.
	 */
	public Double getYs_qtf() {
		return ys_qtf;
	}

	public void setYs_qtf(Double ys_qtf) {
		this.ys_qtf = ys_qtf;
	}

	/**
	 * 预算施工费
	 */
	private Double ys_sgf;

	/**
	 * @hibernate.property column="ys_sgf"
	 * @return Returns the ys_sgf.
	 */
	public Double getYs_sgf() {
		return ys_sgf;
	}

	public void setYs_sgf(Double ys_sgf) {
		this.ys_sgf = ys_sgf;
	}

	/**
	 * 监理反馈
	 */
	private String jlfk;

	/**
	 * @hibernate.property column="jlfk"
	 * @return Returns the jlfk.
	 */
	public String getJlfk() {
		return jlfk;
	}

	public void setJlfk(String jlfk) {
		this.jlfk = jlfk;
	}

	/**
	 * 变更技工工日
	 */
	private Double bg_jggr;

	/**
	 * @hibernate.property column="bg_jggr"
	 * @return Returns the bg_jggr.
	 */
	public Double getBg_jggr() {
		return bg_jggr;
	}

	public void setBg_jggr(Double bg_jggr) {
		this.bg_jggr = bg_jggr;
	}

	/**
	 * 变更普工工日
	 */
	private Double bg_pggr;

	/**
	 * @hibernate.property column="bg_pggr"
	 * @return Returns the bg_pggr.
	 */
	public Double getBg_pggr() {
		return bg_pggr;
	}

	public void setBg_pggr(Double bg_pggr) {
		this.bg_pggr = bg_pggr;
	}

	/**
	 * 变更金额
	 */
	private Double bg_je;

	/**
	 * @hibernate.property column="bg_je"
	 * @return Returns the bg_je.
	 */
	public Double getBg_je() {
		return bg_je;
	}

	public void setBg_je(Double bg_je) {
		this.bg_je = bg_je;
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
	private String cjr;

	/**
	 * @hibernate.property column="cjr"
	 * @return Returns the cjr.
	 */
	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
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
	 * 需求提出时间
	 */
	private Date cjrq;

	/**
	 * @hibernate.property column="cjrq"
	 * @return Returns the cjrq.
	 */
	public Date getCjrq() {
		return cjrq;
	}

	public void setCjrq(Date cjrq) {
		this.cjrq = cjrq;
	}

	/**
	 * 要求完成时间
	 */
	private Date yqwcsj;

	/**
	 * @hibernate.property column="yqwcsj"
	 * @return Returns the yqwcsj.
	 */
	public Date getYqwcsj() {
		return yqwcsj;
	}

	public void setYqwcsj(Date yqwcsj) {
		this.yqwcsj = yqwcsj;
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
	 * 施工质量（优 良 中 差）
	 * 
	 */
	private Long sgzldf;

	/**
	 * @hibernate.property column="sgzldf"
	 * @return Returns the sgzldf.
	 */
	public Long getSgzldf() {
		return sgzldf;
	}

	public void setSgzldf(Long sgzldf) {
		this.sgzldf = sgzldf;
	}

	/**
	 * 材料管理（优 良 中 差）
	 * 
	 */
	private Long clgldf;

	/**
	 * @hibernate.property column="clgldf"
	 * @return Returns the clgldf.
	 */
	public Long getClgldf() {
		return clgldf;
	}

	public void setClgldf(Long clgldf) {
		this.clgldf = clgldf;
	}

	/**
	 * 工期得分
	 */
	private Long gqdf;

	/**
	 * @hibernate.property column="gqdf"
	 * @return Returns the gqdf.
	 */
	public Long getGqdf() {
		return gqdf;
	}

	public void setGqdf(Long gqdf) {
		this.gqdf = gqdf;
	}

	/**
	 * 验收次数
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
	 * 维护资料（优 良 中 差）
	 * 
	 */
	private Long whzldf;

	/**
	 * @hibernate.property column="whzldf"
	 * @return Returns the whzldf.
	 */
	public Long getWhzldf() {
		return whzldf;
	}

	public void setWhzldf(Long whzldf) {
		this.whzldf = whzldf;
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
	 * 当年累计投资完成额
	 */
	private Double dnljtzwce;

	/**
	 * @hibernate.property column="dnljtzwce"
	 * @return Returns the dnljtzwce.
	 */
	public Double getDnljtzwce() {
		return dnljtzwce;
	}

	public void setDnljtzwce(Double dnljtzwce) {
		this.dnljtzwce = dnljtzwce;
	}

	/**
	 * 累计投资完成额
	 */
	private Double ljtzwce;

	/**
	 * @hibernate.property column="ljtzwce"
	 * @return Returns the ljtzwce.
	 */
	public Double getLjtzwce() {
		return ljtzwce;
	}

	public void setLjtzwce(Double ljtzwce) {
		this.ljtzwce = ljtzwce;
	}

	/**
	 * 施工进度百分比（*工作量填报）
	 */
	private Double wcbfb;

	/**
	 * @hibernate.property column="wcbfb"
	 * @return Returns the wcbfb.
	 */
	public Double getWcbfb() {
		return wcbfb;
	}

	public void setWcbfb(Double wcbfb) {
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
	 * 手动派工原因
	 */
	private String sdpgyy;

	/**
	 * @hibernate.property column="sdpgyy"
	 * @return Returns the sdpgyy.
	 */
	public String getSdpgyy() {
		return sdpgyy;
	}

	/**
	 * @param sdpgyy
	 *            The sdpgyy to set.
	 */
	public void setSdpgyy(String sdpgyy) {
		this.sdpgyy = sdpgyy;
	}

	/**
	 * 目标库标识
	 * 
	 */
	private Long mbk_id;

	/**
	 * @hibernate.property column="mbk_id"
	 * @return Returns the mbk_id.
	 */
	public Long getMbk_id() {
		return mbk_id;
	}

	public void setMbk_id(Long mbk_id) {
		this.mbk_id = mbk_id;
	}

	/**
	 * 施工进度
	 */
	private Double sgjd;

	/**
	 * @hibernate.property column="sgjd"
	 * @return Returns the sgjd.
	 */
	public Double getSgjd() {
		return sgjd;
	}

	/**
	 * @param sgjd
	 *            The sgjd to set.
	 */
	public void setSgjd(Double sgjd) {
		this.sgjd = sgjd;
	}

	private String xmglydh;

	/**
	 * @hibernate.property column="xmglydh"
	 * @return Returns the xmglydh.
	 */
	public String getXmglydh() {
		return xmglydh;
	}

	public void setXmglydh(String xmglydh) {
		this.xmglydh = xmglydh;
	}

	/**
	 * 验收人员
	 */
	private String ysry;
	
	/**
	 * 验收部门
	 */
	private String ysbm;
	
	/**
	 * 验收时间
	 */
	private Date yssj;

	public String getYsry() {
		return ysry;
	}

	public void setYsry(String ysry) {
		this.ysry = ysry;
	}

	public String getYsbm() {
		return ysbm;
	}

	public void setYsbm(String ysbm) {
		this.ysbm = ysbm;
	}

	public Date getYssj() {
		return yssj;
	}

	public void setYssj(Date yssj) {
		this.yssj = yssj;
	}
	
	/*
	 * 需求书标识
	 */
	private Long xqs_id;

	/**
	 * @hibernate.property column="xqs_id"
	 * @return Returns the xqs_id.
	 */
	public Long getXqs_id() {
		return xqs_id;
	}

	public void setXqs_id(Long xqs_id) {
		this.xqs_id = xqs_id;
	}
	
	private String zylry;
	
	private String zygly;
	
	private Date zyqrsj;
	
	private String zylrydh;

	/**
	 * @hibernate.property column="zylry"
	 * @return Returns the zylry.
	 */
	public String getZylry() {
		return zylry;
	}

	public void setZylry(String zylry) {
		this.zylry = zylry;
	}

	/**
	 * @hibernate.property column="zygly"
	 * @return Returns the zygly.
	 */
	public String getZygly() {
		return zygly;
	}

	public void setZygly(String zygly) {
		this.zygly = zygly;
	}

	/**
	 * @hibernate.property column="zyqrsj"
	 * @return Returns the zyqrsj.
	 */
	public Date getZyqrsj() {
		return zyqrsj;
	}

	public void setZyqrsj(Date zyqrsj) {
		this.zyqrsj = zyqrsj;
	}

	/**
	 * @hibernate.property column="zylrydh"
	 * @return Returns the zylrydh.
	 */
	public String getZylrydh() {
		return zylrydh;
	}

	public void setZylrydh(String zylrydh) {
		this.zylrydh = zylrydh;
	}
	
	private Integer sjysl;
	
	private Integer sgysl;
	
	private Integer jlysl;
	
	private Integer sgypf;

	/**
	 * @hibernate.property column="sjysl"
	 * @return Returns the sjysl.
	 */
	public Integer getSjysl() {
		return sjysl;
	}

	public void setSjysl(Integer sjysl) {
		this.sjysl = sjysl;
	}

	/**
	 * @hibernate.property column="sgysl"
	 * @return Returns the sgysl.
	 */
	public Integer getSgysl() {
		return sgysl;
	}

	public void setSgysl(Integer sgysl) {
		this.sgysl = sgysl;
	}

	/**
	 * @hibernate.property column="jlysl"
	 * @return Returns the jlysl.
	 */
	public Integer getJlysl() {
		return jlysl;
	}

	public void setJlysl(Integer jlysl) {
		this.jlysl = jlysl;
	}
	
	private Date zytjsj;
	
	private String zyyszt;
	
	private String ycystg;

	public Date getZytjsj() {
		return zytjsj;
	}

	public void setZytjsj(Date zytjsj) {
		this.zytjsj = zytjsj;
	}

	public String getZyyszt() {
		return zyyszt;
	}

	public void setZyyszt(String zyyszt) {
		this.zyyszt = zyyszt;
	}

	public String getYcystg() {
		return ycystg;
	}

	public void setYcystg(String ycystg) {
		this.ycystg = ycystg;
	}
	
	private String lxxx;
	
	private String a_adress;
	
	private String z_adress;
	
	private String ddgly;

	/**
	 * @hibernate.property column="lxxx"
	 * @return Returns the lxxx.
	 */
	public String getLxxx() {
		return lxxx;
	}

	public void setLxxx(String lxxx) {
		this.lxxx = lxxx;
	}

	/**
	 * @hibernate.property column="a_adress"
	 * @return Returns the a_adress.
	 */
	public String getA_adress() {
		return a_adress;
	}

	public void setA_adress(String a_adress) {
		this.a_adress = a_adress;
	}

	/**
	 * @hibernate.property column="z_adress"
	 * @return Returns the z_adress.
	 */
	public String getZ_adress() {
		return z_adress;
	}

	public void setZ_adress(String z_adress) {
		this.z_adress = z_adress;
	}

	/**
	 * @hibernate.property column="ddgly"
	 * @return Returns the ddgly.
	 */
	public String getDdgly() {
		return ddgly;
	}

	public void setDdgly(String ddgly) {
		this.ddgly = ddgly;
	}
	
	private String ddzt;

	/**
	 * @hibernate.property column="ddzt"
	 * @return Returns the ddzt.
	 */
	public String getDdzt() {
		return ddzt;
	}

	public void setDdzt(String ddzt) {
		this.ddzt = ddzt;
	}
	
	private String wcfgqbm;
	private String hdbz;
	private Date wcsj;
	private String gdztzt;
	private String sfts;
	private String sfcq;

	public String getWcfgqbm() {
		return wcfgqbm;
	}

	public void setWcfgqbm(String wcfgqbm) {
		this.wcfgqbm = wcfgqbm;
	}

	public String getHdbz() {
		return hdbz;
	}

	public void setHdbz(String hdbz) {
		this.hdbz = hdbz;
	}

	public Date getWcsj() {
		return wcsj;
	}

	public void setWcsj(Date wcsj) {
		this.wcsj = wcsj;
	}

	public String getGdztzt() {
		return gdztzt;
	}

	public void setGdztzt(String gdztzt) {
		this.gdztzt = gdztzt;
	}

	public String getSfts() {
		return sfts;
	}

	public void setSfts(String sfts) {
		this.sfts = sfts;
	}

	public String getSfcq() {
		return sfcq;
	}

	public void setSfcq(String sfcq) {
		this.sfcq = sfcq;
	}
	
	/**
	 * @hibernate.property column="yqgq"
	 * @return Returns the yqgq.
	 */
	private Long yqgq;

	public Long getYqgq() {
		return yqgq;
	}

	public void setYqgq(Long yqgq) {
		this.yqgq = yqgq;
	}
	
	private String p_gclb;

	public String getP_gclb() {
		return p_gclb;
	}

	public void setP_gclb(String p_gclb) {
		this.p_gclb = p_gclb;
	}
	
	private String glbm;

	public String getGlbm() {
		return glbm;
	}

	public void setGlbm(String glbm) {
		this.glbm = glbm;
	}
	
	private Long dxtzhzdw;

	public Long getDxtzhzdw() {
		return dxtzhzdw;
	}

	public void setDxtzhzdw(Long dxtzhzdw) {
		this.dxtzhzdw = dxtzhzdw;
	}
	
	private Long dxtzsjdwwc;

	public Long getDxtzsjdwwc() {
		return dxtzsjdwwc;
	}

	public void setDxtzsjdwwc(Long dxtzsjdwwc) {
		this.dxtzsjdwwc = dxtzsjdwwc;
	}
	
	private Long dxtzsgdwwc;

	public Long getDxtzsgdwwc() {
		return dxtzsgdwwc;
	}

	public void setDxtzsgdwwc(Long dxtzsgdwwc) {
		this.dxtzsgdwwc = dxtzsgdwwc;
	}
	
	private Double sghtje;

	public Double getSghtje() {
		return sghtje;
	}

	public void setSghtje(Double sghtje) {
		this.sghtje = sghtje;
	}

	public Integer getSgypf() {
		return sgypf;
	}

	public void setSgypf(Integer sgypf) {
		this.sgypf = sgypf;
	}
	
}
