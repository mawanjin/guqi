/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.service;

import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.modules.weixin.vo.ReceiveMsgPlain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinSubscriber;
import com.thinkgem.jeesite.modules.weixin.dao.WeixinSubscriberDao;

/**
 * 已关注微信用户Service
 * @author mawj
 * @version 2016-10-31
 */
@Service
@Transactional(readOnly = true)
public class WeixinSubscriberService extends CrudService<WeixinSubscriberDao, WeixinSubscriber> {

	public WeixinSubscriber get(String id) {
		return super.get(id);
	}
	
	public List<WeixinSubscriber> findList(WeixinSubscriber weixinSubscriber) {
		return super.findList(weixinSubscriber);
	}
	
	public Page<WeixinSubscriber> findPage(Page<WeixinSubscriber> page, WeixinSubscriber weixinSubscriber) {
		return super.findPage(page, weixinSubscriber);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinSubscriber weixinSubscriber) {
		super.save(weixinSubscriber);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinSubscriber weixinSubscriber) {
		super.delete(weixinSubscriber);
	}


	@Transactional(readOnly = false)
	public void save(ReceiveMsgPlain receiveMsg){
		WeixinSubscriber subscriber = new WeixinSubscriber();
		subscriber.setFromUserName(receiveMsg.getFromUserName());
		List<WeixinSubscriber> existsList = findList(subscriber);
		if(existsList!=null&&existsList.size()>0){
			return;
		}

		try{
			subscriber.setCreateTime(new Date(Long.parseLong(receiveMsg.getCreateTime())*1000));
		}catch (Exception e){
			subscriber.setCreateTime(new Date());
		}

		save(subscriber);
	}
}