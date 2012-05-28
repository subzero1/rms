package com.rms.controller.search;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netsky.base.baseObject.PropertyInject;
import com.netsky.base.baseObject.ResultObject;
import com.netsky.base.service.ExceptionService;
import com.netsky.base.service.QueryService;
import com.netsky.base.utils.convertUtil;
/**
 * 工程查询与报表各类选择处理类
 * 
 * @author Chiang
 * 
 */
@Controller
public class KeySelect {

	@Autowired
	private QueryService queryService;

	@Autowired
	private ExceptionService exceptionService;

	/**
	 * @return the queryService
	 */
	public QueryService getQueryService() {
		return queryService;
	}

	/**
	 * @param queryService
	 *            the queryService to set
	 */
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	/**
	 * @return the exceptionService
	 */
	public ExceptionService getExceptionService() {
		return exceptionService;
	}

	/**
	 * @param exceptionService
	 *            the exceptionService to set
	 */
	public void setExceptionService(ExceptionService exceptionService) {
		this.exceptionService = exceptionService;
	}


	@RequestMapping("/search/keySelect.do")
	public ModelAndView keySelect(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<String> result = new ArrayList<String>();
			String type = convertUtil.toString(request.getParameter("type"));
			if (type.equals("jdxz")) {
				/*
				 * 局点性质
				 */
				String HSql = "select tc01 from Tc01_property tc01 where type='局点性质' order by id";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add(PropertyInject.getProperty(ro.get("tc01"), "name") + "");
				}
			}
			else if(type.equals("ssqy")){
				/*
				 * 所属区域
				 */
				String HSql = "select tc03 from Tc02_area tc02 where flag like'%[2]%' order by id ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add(PropertyInject.getProperty(ro.get("tc02"), "name") + "");
				}
			}
			else if(type.equals("dept")){
				/*
				 *登记部门
				 */
				String HSql = "select ta01 from Ta01_dept ta01 order by id ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add(PropertyInject.getProperty(ro.get("ta01"), "name") + "");
				}
			}
			else if(type.equals("sfbz")){
				/*
				 * 是否标志
				 */
				result.add("是");
				result.add("否");
			}
			else if(type.equals("cqlx")){
				/*
				 * 超期类型
				 */
				result.add("超期");
				result.add("将超期");
			}
			else if(type.equals("sq_jsxz")){
				/*
				 * 建设性质
				 */
				String HSql = "select distinct jsxz as jsxz from Tzsqqk ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("jsxz"));
				}
			}
			else if(type.equals("sq_sjdw")){
				/*
				 * 设计单位
				 */
				String HSql = "select distinct sjdw as sjdw from Tzsqqk ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("sjdw"));
				}
			}
			else if(type.equals("sq_sqbm")){
				/*
				 * 所属部门
				 */
				String HSql = "select distinct sqbm as sqbm from Tzsqqk ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("sqbm"));
				}
			}
			else if(type.equals("sq_sszy")){
				/*
				 * 所属专业
				 */
				String HSql = "select distinct sszy as sszy from Tzsqqk ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("sszy"));
				}
			}
			else if(type.equals("sq_zt")){
				/*
				 * 状态
				 */
				String HSql = "select distinct zt as zt from Tzsqqk ";
				ResultObject ro = queryService.search(HSql);
				while (ro.next()) {
					result.add((String)ro.get("zt"));
				}
			}
			
			request.setAttribute("result", result);
		} catch (Exception e) {
			return exceptionService.exceptionControl(KeySelect.class.getName(), "选择基础多选项", e);
		}
		return new ModelAndView("/WEB-INF/jsp/search/keySelect.jsp");
	}
}

