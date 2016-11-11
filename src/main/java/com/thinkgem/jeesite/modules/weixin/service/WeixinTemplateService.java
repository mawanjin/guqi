/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.service;

import java.util.List;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.modules.weixin.vo.TemplateItemEMS;
import com.thinkgem.jeesite.modules.weixin.vo.WeixinTemplateMsg;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinTemplate;
import com.thinkgem.jeesite.modules.weixin.dao.WeixinTemplateDao;

/**
 * 模板消息Service
 * @author mawj
 * @version 2016-11-11
 */
@Service
@Transactional(readOnly = true)
public class WeixinTemplateService extends CrudService<WeixinTemplateDao, WeixinTemplate> {

	public WeixinTemplate get(String id) {
		return super.get(id);
	}
	
	public List<WeixinTemplate> findList(WeixinTemplate weixinTemplate) {
		return super.findList(weixinTemplate);
	}
	
	public Page<WeixinTemplate> findPage(Page<WeixinTemplate> page, WeixinTemplate weixinTemplate) {
		return super.findPage(page, weixinTemplate);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinTemplate weixinTemplate) {

		//转换成msg
		WeixinTemplateMsg msg = new WeixinTemplateMsg();
		msg.setUrl(weixinTemplate.getUrl());
		msg.setTemplateId(weixinTemplate.getTemplateId());
		msg.setTopcolor("#000033");
		msg.setTouser(weixinTemplate.getOpenid());
		if(weixinTemplate.getTemplateId().equals("Hz9CBDxAX8iwbTxWXu3ec9DXGhe4U_iOGsWtCpml9Pg")){
			TemplateItemEMS itemEMS = new TemplateItemEMS();
			itemEMS.getFirst().setValue(weixinTemplate.getTitle());
			itemEMS.setDelivername(weixinTemplate.getDeliverName());
			itemEMS.setOrdername(weixinTemplate.getOrderName());
			itemEMS.setProductName(weixinTemplate.getProductName());
			itemEMS.setProductCount(weixinTemplate.getProductCount());
			itemEMS.setRemark(weixinTemplate.getRemark());
			msg.setData(itemEMS);
		}
		weixinTemplate.setMsg(JsonMapper.toJsonString(msg));
		super.save(weixinTemplate);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinTemplate weixinTemplate) {
		super.delete(weixinTemplate);
	}

	/**
	 * 将msg里的json转换成页面所需要的格式
	 * @param template
     */
	public void wrapForPage(WeixinTemplate template){
		if(template == null)return;
		if(template.getTemplateId().equals("Hz9CBDxAX8iwbTxWXu3ec9DXGhe4U_iOGsWtCpml9Pg")){
			WeixinTemplateMsg<TemplateItemEMS> msg = (WeixinTemplateMsg<TemplateItemEMS>) JsonMapper.getInstance().fromJson(template.getMsg(),JsonMapper.getInstance().createCollectionType(WeixinTemplateMsg.class,TemplateItemEMS.class));
			TemplateItemEMS itemEMS = (TemplateItemEMS) msg.getData();
			template.setOpenid(msg.getTouser());
			template.setDeliverName(itemEMS.getDelivername());
			template.setProductName(itemEMS.getProductName());
			template.setProductCount(itemEMS.getProductCount());
			template.setRemark(itemEMS.getRemark());
			template.setOrderName(itemEMS.getOrdername());
			template.setTitle(itemEMS.getFirst().getValue());
		}
	}
}