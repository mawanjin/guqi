/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.web.front;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.utils.WiexinSignUtil;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinMsg;
import com.thinkgem.jeesite.modules.weixin.service.WeixinAPIService;
import com.thinkgem.jeesite.modules.weixin.service.WeixinMsgService;
import com.thinkgem.jeesite.modules.weixin.service.WeixinSubscriberService;
import com.thinkgem.jeesite.modules.weixin.vo.ReceiveMsgPlain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试Controller
 * @author ThinkGem
 * @version 2014-02-28
 */
@Controller
@RequestMapping(value = "${frontPath}/weixin_api")
public class WeixinAPIController extends BaseController {

	@Autowired
	WeixinAPIService weixinAPIService;

	@Autowired
	WeixinMsgService weixinMsgService;

	@Autowired
	WeixinSubscriberService weixinSubscriberService;

	/**
	 * 
	 * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。 
	 * @param timestamp 时间戳
	 * @param nonce 随机数 
	 * @param echostr 随机数 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public String get(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request) {
		
		System.out.println("=============================================== get start");
		for (Object o : request.getParameterMap().keySet()){
			System.out.println(o + " = " + request.getParameter((String)o));
		}
		System.out.println("=============================================== get end");
		
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
        if (WiexinSignUtil.checkSignature(signature, timestamp, nonce)) {  
        	return echostr;
        }

		return "-1";
	}

//	@RequestMapping(value = "", method = RequestMethod.POST)
//	@ResponseBody
//	public String post(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request) {
//		System.out.println("=============================================== post start");
//		for (Object o : request.getParameterMap().keySet()){
//			System.out.println(o + " = " + request.getParameter((String)o));
//		}
//		System.out.println("=============================================== post end");
//		StringBuilder result = new StringBuilder();
//		result.append("<xml>" +
//				"<ToUserName><![CDATA[toUser]]></ToUserName>" +
//				"<FromUserName><![CDATA[fromUser]]></FromUserName>" +
//				"<CreateTime>12345678</CreateTime>" +
//				"<MsgType><![CDATA[text]]></MsgType>" +
//				"<Content><![CDATA[你好]]></Content>" +
//				"</xml>");
//		return result.toString();
//	}


	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public String post(@RequestBody ReceiveMsgPlain receiveMsg, String signature, String timestamp, String nonce, String echostr, HttpServletRequest request) {
		if(receiveMsg.getMsgType()!=null&&receiveMsg.getMsgType().equals("event")){
			weixinSubscriberService.save(receiveMsg);
		}else {
			weixinMsgService.save(receiveMsg);
		}

		System.out.println("body="+receiveMsg);
		System.out.println("=============================================== post start");
		for (Object o : request.getParameterMap().keySet()){
			System.out.println(o + " = " + request.getParameter((String)o));
		}
		System.out.println("=============================================== post end");
		StringBuilder result = new StringBuilder();
		result.append("<xml>" +
				"<ToUserName><![CDATA[toUser]]></ToUserName>" +
				"<FromUserName><![CDATA[fromUser]]></FromUserName>" +
				"<CreateTime>12345678</CreateTime>" +
				"<MsgType><![CDATA[text]]></MsgType>" +
				"<Content><![CDATA[你好]]></Content>" +
				"</xml>");
		return result.toString();
	}

	@RequestMapping(value = "access_token", method = RequestMethod.GET)
	@ResponseBody
	public String getAccessToken(){
		return weixinAPIService.getOrNewToken();

	}

	/**
	 * 获取openid的第一步,跳转操作
	 * @return
     */
	@RequestMapping(value = "request_openuid_code", method = RequestMethod.GET)
	public String requestOpenIdCode(){
		return "redirect:"+weixinAPIService.getRequestOpenIDUrl();

	}

	/**
	 * 获取openid的第二步,是到code,再去请求openid
	 * @return
     */
	@RequestMapping(value = "request_openuid_code_callack", method = RequestMethod.GET)
	@ResponseBody
	public String requestOpenIdCodeCallback(@RequestParam(value = "code",required = true) String code){
		if(code.equals("123")){
			return weixinAPIService.getOpenIdByCode("0018gDcC1P4jm00twNeC1tuBcC18gDcD");
		}

		return weixinAPIService.getOpenIdByCode(code);
	}


	/**
	 * 下载图片/音频/视频
     * @return
     */
	@RequestMapping(value = "getMedia", method = RequestMethod.GET)
	public String getMedia(@RequestParam(value = "mediaId",required = true) String mediaId){
		String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="+weixinAPIService.getOrNewToken()+"&media_id="+mediaId;
		return "redirect:"+url ;
	}


}
