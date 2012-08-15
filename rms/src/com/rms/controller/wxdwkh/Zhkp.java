package com.rms.controller.wxdwkh;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseDao.Dao;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveService;
import com.rms.dataObjects.form.Td01_xmxx;
import com.rms.dataObjects.wxdw.Tf18_zhkp;

/**
 * @description:综合考评
 * 
 * @class name:com.rms.controller.wxdwkh.Zhkp
 * @author Chiang Aug 10, 2012
 */
@Controller
public class Zhkp {
	/**
	 * 异常捕捉
	 */
	@Autowired
	private ExceptionService exceptionService;

	/**
	 * 查询服务
	 */
	@Autowired
	private QueryService queryService;

	/**
	 * 保存服务
	 */
	@Autowired
	private SaveService saveService;

	@Autowired
	private Dao dao;

	@RequestMapping("/wxdwkh/zhkpController.do")
	public ModelAndView zhkpController() throws Exception {
		Session session = dao.getHibernateSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		try {
			// 判断是否需要执行该动作
			Date date = new Date();
			String now = new SimpleDateFormat("yyyy-MM-dd").format(date);
			List<String> tmpList = (List<String>) dao.search("select name from Tc01_property where type='考评接转分值'");
			Double defaulta = convertUtil.toDouble(tmpList.isEmpty() ? null : tmpList.get(0), 80D);
			tmpList = (List<String>) dao.search("select name from Tc01_property where type='最后考评时间'");
			String zhkpsj = tmpList.isEmpty() ? (date.getYear() + "-01-01") : tmpList.get(0);
			tmpList = (List<String>) dao.search("select name from Tc01_property where type='考评间隔'");
			Long kpjg = convertUtil.toLong(tmpList.isEmpty() ? null : tmpList.get(0), 90L);
			if (kpjg > (date.getTime() - new SimpleDateFormat("yyyy-MM-dd").parse(zhkpsj).getTime()) / 1000 / 3600 / 24) {
				System.out.println("不到考评日");
				return null;
			}
			List<Object[]> ObjectsList = (List<Object[]>) session
					.createSQLQuery(
							"select tf01.id,tf01.mc,tf01.lb,"
									+ "t2.sum1 as b,"
									+ "(select t3.zhdf from Tf18_zhkp t3,(select max(dfsj) as dfsj, wxdw_id from Tf18_zhkp group by wxdw_id) t4 where t3.wxdw_id = t4.wxdw_id and t3.dfsj = t4.dfsj and t3.wxdw_id = tf01.id) as a,"
									+ "(select sum(tf17.jkfz) from Tf17_rckh tf17 where tf17.wxdw_id=tf01.id and qrsj>=to_date('"
									+ zhkpsj
									+ "','yyyy-MM-dd') and qrsj<to_date('"
									+ now
									+ "','yyyy-MM-dd')) as c,"
									+ "(trim(' ' from getzyqybyid(tf01.id))) as dq,"
									+ "(trim(' ' from getzyzybyid(tf01.id))) as zy"
									+ " from tf01_wxdw tf01 "
									+ "left join (select sum(t1.sum1) as sum1, t1.sgdw as sgdw from "
									+ "(select td00.sgdw as sgdw, t0.sum1 as sum1 from td00_gcxx td00 left join "
									+ "(select sum(zdfz * jgfz) as sum1, project_id, lb from tf16_xmkhdf where lb = 'sg' and pfsj>=to_date('"
									+ zhkpsj
									+ "','yyyy-MM-dd') and pfsj<to_date('"
									+ now
									+ "','yyyy-MM-dd') group by project_id, lb) t0 on t0.project_id = td00.id) t1 group by t1.sgdw) t2 on t2.sgdw = tf01.mc where tf01.lb = '施工'")
					.list();
			for (Object[] o : ObjectsList) {
				Tf18_zhkp tf18 = new Tf18_zhkp();
				tf18.setWxdw_id(((BigDecimal) o[0]).longValue());
				tf18.setWxdw_mc(convertUtil.toString(o[1]));
				tf18.setWxdw_lb(convertUtil.toString(o[2]));
				tf18.setZyqy(convertUtil.toString(o[6]));
				tf18.setZylb(convertUtil.toString(o[7]));
				Double a = convertUtil.toDouble(o[4], defaulta);
				tf18.setAscore(a);
				if (o[4] == null) {
					tf18.setIsdefaulta(1L);
				}
				Double b = convertUtil.toDouble(o[3], defaulta);
				tf18.setBscore(b);
				if (o[3] == null) {
					tf18.setIsdefaultb(1L);
				}
				Double c = convertUtil.toDouble(o[5], 0D);
				tf18.setCscore(c);
				tf18.setDfsj(date);
				tf18.setZhdf(a * 0.3 + b * 0.7 - c);
				List<Td01_xmxx> td01List = (List<Td01_xmxx>) dao.search("from Td01_xmxx td01 where sgdw='"
						+ convertUtil.toString(o[1]) + "' and" + " cjrq>=to_date('"
						+ new SimpleDateFormat("yyyy").format(date) + "-01-01" + "','yyyy-MM-dd') and cjrq<to_date('"
						+ now + "','yyyy-MM-dd')");
				Long xms = new Long(td01List.size());
				tf18.setXms(xms);
				if (xms == 0L) {
					tf18.setHte(0D);
					tf18.setJse(0D);
					tf18.setWgl(0D);
					tf18.setCql(0D);
					tf18.setJsl(0D);
				} else {
					Double hte = 0D;
					Double jse = 0D;
					int wgs = 0;
					int cqs = 0;
					int jss = 0;
					for (Td01_xmxx td01 : td01List) {
						hte += td01.getSghtje();
						jse += td01.getSs_je();
						if (td01.getSjjgsj() != null) {
							wgs++;
						}
						if (td01.getJhkgsj() != null
								&& td01.getJhjgsj() != null
								&& td01.getSjkgsj() != null
								&& (td01.getJhjgsj().getTime() - td01.getJhkgsj().getTime()) < ((td01.getSjjgsj() == null ? date
										: td01.getSjjgsj()).getTime() - td01.getSjkgsj().getTime())) {
							cqs++;
						}
						if (td01.getJssj() != null) {
							jss++;
						}
					}
					int size = td01List.size();
					tf18.setWgl((double) wgs / size);
					tf18.setCql((double) cqs / size);
					tf18.setJsl((double) jss / size);
				}
				session.save(tf18);
			}
			session.createQuery("update Tc01_property set name='" + now + "' where type='最后考评时间'").executeUpdate();
			session.flush();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return null;
	}
}
