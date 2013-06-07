package com.rms.base.util;



public class MessageMain {

	/**
	 * 
	 * @param args void
	 */
	public static void main(String[] args) {
 MobileMessage message=new MobileMessageImpl();
 System.out.println("短信發送狀態:"+message.sendMsg("測試短哦時間哦奧熬過熬過建軍節哦啊個傲嬌敖漢敖漢信" +
 		"測試短哦時間哦奧熬過熬過建軍節哦啊個傲嬌敖漢敖漢信", "13702085735"));
	}
	

}
