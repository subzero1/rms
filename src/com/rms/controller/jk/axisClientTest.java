package com.rms.controller.jk;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.w3c.dom.*;

import com.rms.webservice.client.zbres.Zbres2RmsForFeedbackStub;

import com.netsky.base.baseObject.HibernateQueryBuilder;
import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.dataObjects.Ta03_user;
import com.netsky.base.dataObjects.Tb03_relation;
import com.netsky.base.dataObjects.Tb12_opernode;
import com.netsky.base.flow.component.Send;
import com.netsky.base.flow.service.FlowService;
import com.netsky.base.flow.utils.MapUtil;
import com.netsky.base.flow.utils.convertUtil;
import com.netsky.base.service.QueryService;
import com.netsky.base.service.SaveFormCodeService;
import com.netsky.base.service.SaveService; 
import com.netsky.base.utils.RegExp;
import com.rms.dataObjects.jk.Ti03_xqly;
import com.rms.dataObjects.form.Td00_gcxx;
import com.rms.serviceImpl.jk.CcatsidaWSC;
import com.rms.serviceImpl.jk.CcatsidaXMLParser;

/**
 * @description: PSS与综合调度中心接口
 * @class name:com.netsky.pss.controller.jk.PssCcatsida
 * @author Administrator May 27, 2010
 */
@Controller
public class axisClientTest {

		public static void main(String[] args)throws Exception{
			
			/**
			 * 以下为axis2 1_6的客户端调用方式
			 */
//			Zbres2RmsForFeedbackStub zs = new Zbres2RmsForFeedbackStub();
//			Zbres2RmsForFeedbackStub.Feedback zf = new Zbres2RmsForFeedbackStub.Feedback();
//			zf.setInParam("hello leexy");
//			Zbres2RmsForFeedbackStub.FeedbackResponse zr = zs.feedback(zf);
//			System.out.println(zr.get_return());
			
		}
	
}
