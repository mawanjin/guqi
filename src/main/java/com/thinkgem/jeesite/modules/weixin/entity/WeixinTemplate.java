/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 模板消息Entity
 * @author mawj
 * @version 2016-11-11
 */
public class WeixinTemplate extends DataEntity<WeixinTemplate> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 模板名称
	private String openid;		// 用户
	private String templateId;		// 模板ID
	private String status;		// 状态
	private String msg;		// 消息内容
	private String url;		// 跳转链接
	private Date successDate;		// 送达时间

	//发货通知模板字段
	private String title;//标题
	private String deliverName;//快递公司
	private String orderName;//快递单号
	private String productName;//商品名称
	private String productCount;//商品数量
	private String remark;//备注


	public WeixinTemplate() {
		super();
	}

	public WeixinTemplate(String id){
		super(id);
	}

	@Length(min=0, max=45, message="模板名称长度必须介于 0 和 45 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=45, message="用户长度必须介于 0 和 45 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Length(min=0, max=45, message="模板ID长度必须介于 0 和 45 之间")
	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="消息内容长度必须介于 0 和 255 之间")
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSuccessDate() {
		return successDate;
	}

	public void setSuccessDate(Date successDate) {
		this.successDate = successDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDeliverName() {
		return deliverName;
	}

	public void setDeliverName(String deliverName) {
		this.deliverName = deliverName;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCount() {
		return productCount;
	}

	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}