package com.rms.base.util;

public interface MobileMessage {
	public void connect();
	public void close();
	public String sendMsg(String content,String sender_name, String reader_tel,String reader_name );
	public String sendMsg(String content,String sender_name,String reader_tel);
	public String sendMsg(String content,String reader_tel);
}
