package com.dba.message;

import java.net.URLEncoder;

/**
 * 验证码通知短信接口
 * 
 * @ClassName: IndustrySMS
 * @Description: 验证码通知短信接口
 *
 */
public class IndustrySMS
{
	private static String operation = "/industrySMS/sendSMS";
	private static String accountSid = Config.ACCOUNT_SID;
	private static String smsContent = "【云海股份】您的验证码是";
	private static String smsContent1 = "，30分钟输入有效。";

	/**
	 * 验证码通知短信
	 */
	public void execute(String to,int code)
	{
		String tmpSmsContent = null;
		System.out.println(smsContent+code+smsContent1);
		try{
	      tmpSmsContent = URLEncoder.encode(smsContent+code+smsContent1, "UTF-8");
	    }catch(Exception e){
	      
	    }
	    String url = Config.BASE_URL + operation;
	    String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + tmpSmsContent
	        + HttpUtil.createCommonParam();

	    // 提交请求
	    String result = HttpUtil.post(url, body);
	    System.out.println("result:" + System.lineSeparator() + result);
	}
}
