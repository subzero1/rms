package com.rms.serviceImpl.jk;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;

public class CcatsidaWSC {
	public static String PROCEDURE_NAME = "cp_ord_pm_interface";
	public static String SERVICE_URL = "http://132.232.112.12/ccatsida/services/AsigService";
	public static String AUTHRIZ_USER = "bpel";
	public static String AUTHRIZ_PASS = "bpel";
	public static String REMOTE_METHOD = "executeProcedure";
	
	CcatsidaXMLParser xmlparser = null;
	
	public CcatsidaWSC(){
		
		xmlparser = new CcatsidaXMLParser();
	}
	
	/**
	 * ��WebService������������
	 * @param type
	 * @return
	 */
	public String processRequest(String type,String projectcode,String target) throws Exception{

		String requestparameter = xmlparser.getParameterXML(type,projectcode);
		
		if(target != null && target.length()>8){
			SERVICE_URL = target;
		}
		
		Service service = new Service();
		Call call = (Call)service.createCall();
		call.setTargetEndpointAddress(SERVICE_URL);
		call.setOperationName(REMOTE_METHOD);
		call.setUsername(AUTHRIZ_USER);
		call.setPassword(AUTHRIZ_PASS);
		call.setReturnType(XMLType.XSD_STRING);
		call.addParameter("param1",XMLType.XSD_STRING,ParameterMode.IN);
		call.addParameter("param2",XMLType.XSD_STRING,ParameterMode.IN);
		return (String)call.invoke(new Object[]{PROCEDURE_NAME,requestparameter});
	}
	
	public static void main(String[] args) {
		CcatsidaWSC wsc = new CcatsidaWSC();
		CcatsidaXMLParser parser = new CcatsidaXMLParser();
	    try {
	        // System.out.print(wsc.processRequest("2","SZC000207"))132.228.91.9;
	    	//
	        //System.out.print(wsc.processRequest("2","sz201003190708873","http://132.228.91.9/PFINTF/services/PFIntfWS"));
	        System.out.print(wsc.processRequest("2","sz201005280691128","http://132.228.163.235:9080/PFINTF/services/PFIntfWS"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Error:"+e.getMessage());
        }
	    
	}
}
