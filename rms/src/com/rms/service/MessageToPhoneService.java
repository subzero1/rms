package com.rms.service;

public interface MessageToPhoneService {
	public String sendMessageToPhone(String content,String sender_name,String additionTel, String reader_tel,String reader_name);
	public void dxjl(String fsr,String jsr,String title,String content,String state);
}
