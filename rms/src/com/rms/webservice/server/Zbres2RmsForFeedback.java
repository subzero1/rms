package com.rms.webservice.server;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.netsky.base.utils.convertUtil;
import com.rms.dataObjects.form.Td01_xmxx;

public class Zbres2RmsForFeedback {
	public String feedback(String inParam)throws Exception{
		if(inParam == null){
			
		}
		// 分析出参，并获得 $processInstId
		SAXBuilder builder = null;
		Document doc = null;
		Element root = null;

		builder = new SAXBuilder();
		Reader in = new StringReader(inParam);
		try {
			doc = builder.build(in);
		} catch (IOException ioe) {
			throw new Exception(ioe);
		} catch (JDOMException jdome) {
			throw new Exception(jdome);
		}
		in.close();

		try {
			root = doc.getRootElement();
			String checkType = root.getChild("checkType").getText();
			String checkResult = root.getChild("checkResult").getText();
			
		} catch (Exception e) {
			//processInstId = "-1";
			throw e;
		} finally {
			// 保存processInstId至TD00.flowInstId
//			if (processInstId != null && !processInstId.equals("-1")) {
//				project_id = dataMap.get("project_id");
//				Td00_gcxx td00 = (Td00_gcxx) queryService.searchById(Td00_gcxx.class, convertUtil
//						.toLong(project_id));
//				td00.setFlowInstId(convertUtil.toLong(processInstId));
//				saveService.save(td00);
//				startFlow(dataMap);
//			}
		}
		return null;
	}
}
