/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.service;

import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.weixin.vo.ReceiveMsgPlain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinMsg;
import com.thinkgem.jeesite.modules.weixin.dao.WeixinMsgDao;

/**
 * 所有微信的消息收发表Service
 * @author mawj
 * @version 2016-10-28
 */
@Service
@Transactional(readOnly = true)
public class WeixinMsgService extends CrudService<WeixinMsgDao, WeixinMsg> {

	public WeixinMsg get(String id) {
		return super.get(id);
	}
	
	public List<WeixinMsg> findList(WeixinMsg weixinMsg) {
		return super.findList(weixinMsg);
	}
	
	public Page<WeixinMsg> findPage(Page<WeixinMsg> page, WeixinMsg weixinMsg) {
		return super.findPage(page, weixinMsg);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinMsg weixinMsg) {
		super.save(weixinMsg);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinMsg weixinMsg) {
		super.delete(weixinMsg);
	}

	@Transactional(readOnly = false)
	public void save(ReceiveMsgPlain msgPlain){
		WeixinMsg msg = new WeixinMsg();

		msg.setToUserName(msgPlain.getToUserName());
		msg.setContent(msgPlain.getContent());
		try{
			msg.setCreateTime(new Date(Long.parseLong(msgPlain.getCreateTime())*1000));
		}catch (Exception e){}

		msg.setDescription(msgPlain.getDescription());
		msg.setFormat(msgPlain.getFormat());
		msg.setFromUserName(msgPlain.getFromUserName());
		msg.setLabel(msgPlain.getLabel());
		msg.setLocationX(msgPlain.getLocation_X());
		msg.setLocationY(msgPlain.getLocation_Y());
		msg.setMediaId(msgPlain.getMediaId());
		msg.setMsgType(msgPlain.getMsgType());
		msg.setMsgId(msgPlain.getMsgId());
		msg.setPicUrl(msgPlain.getPicUrl());
		msg.setScale(msgPlain.getScale());
		msg.setTitle(msgPlain.getTitle());
		msg.setUrl(msgPlain.getUrl());
		save(msg);
	}
}