package com.rms.base.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkage.netmsg.NetMsgclient;
import com.linkage.netmsg.server.ReceiveMsg;
import com.netsky.base.dataObjects.Te08_message;
import com.netsky.base.service.SaveService;
@Service("createMobileMessage")
public class MobileMessageImpl implements MobileMessage{

	private ReceiveMsg receiveMsg;
	private NetMsgclient client;
	private String url;
	private String username;
	private String password;
	private int portNum;
	
	/**
	 * 保存服务
	 */
	@Autowired
	private SaveService saveService;
	
	public void close() {
		client.closeConn();
	}

	public MobileMessageImpl(){
		super();
		this.client =new NetMsgclient();
		this.receiveMsg=new ReceiveMsgImp(); 
		this.connect();
	}
	public MobileMessageImpl(ReceiveMsg receiveMsg,NetMsgclient client){
		super();
		this.client =client;
		this.receiveMsg=receiveMsg;
	}
	public void connect() {

		client=client.initParameters("202.102.41.101", 9005, "025C00301969", "gc5977", receiveMsg);
		 try {
	            /*登录认证*/
	            boolean isLogin = client.anthenMsg(client);
	            if(isLogin) {
	            	System.out.println("login sucess");
	            } 
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }
	
	}

	public String sendMsg(String content, String sender_name,
			String reader_tel, String reader_name) {
		return(client.sendMsg(client, 0, reader_tel,content, 1));
	}

	public String sendMsg(String content, String sender_name, String reader_tel) {
		return null;
	}

	public String sendMsg(String content, String reader_tel) {
		return client.sendMsg(client, 0, reader_tel,content, 1);
	}

	public ReceiveMsg getReceiveMsg() {
		return this.receiveMsg;
	}
	

}
