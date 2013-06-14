package com.rms.base.util;

public interface MobileMessage {
	public void connect();
	public void close();
	public String sendMsg(String content,String reader_tel);
}
