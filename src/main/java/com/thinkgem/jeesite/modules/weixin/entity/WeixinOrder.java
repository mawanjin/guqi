/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订单基础表Entity
 * @author mawj
 * @version 2016-11-11
 */
public class WeixinOrder extends DataEntity<WeixinOrder> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;		// 订单号
	private String orderType;		// 订单类型
	private String customerPhone;		// 客户电话
	private String customerNickName;		// 昵称
	private String customerCompany;		// 所属企业
	private String firstKf;		// 初始接单人员
	private String status;		// 订单状态
	private String priceCustomer;		// 给到客户的报价
	private String priceExpert;		// 给到设计 人员的报价
	private Date expireTime;		// 截稿日期
	private String auctionType;		// 发布类型
	private WeixinExpert expert;		// 选定的专家
	private WeixinSupplier supplier;		// 选定的供应商
	private String purchaseType;		// 采购类型
	
	public WeixinOrder() {
		super();
	}

	public WeixinOrder(String id){
		super(id);
	}

	@Length(min=0, max=45, message="订单号长度必须介于 0 和 45 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=1, message="订单类型长度必须介于 0 和 1 之间")
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	@Length(min=0, max=45, message="客户电话长度必须介于 0 和 45 之间")
	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	@Length(min=0, max=45, message="昵称长度必须介于 0 和 45 之间")
	public String getCustomerNickName() {
		return customerNickName;
	}

	public void setCustomerNickName(String customerNickName) {
		this.customerNickName = customerNickName;
	}
	
	@Length(min=0, max=45, message="所属企业长度必须介于 0 和 45 之间")
	public String getCustomerCompany() {
		return customerCompany;
	}

	public void setCustomerCompany(String customerCompany) {
		this.customerCompany = customerCompany;
	}
	
	@Length(min=0, max=45, message="初始接单人员长度必须介于 0 和 45 之间")
	public String getFirstKf() {
		return firstKf;
	}

	public void setFirstKf(String firstKf) {
		this.firstKf = firstKf;
	}
	
	@Length(min=0, max=11, message="订单状态长度必须介于 0 和 11 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=45, message="给到客户的报价长度必须介于 0 和 45 之间")
	public String getPriceCustomer() {
		return priceCustomer;
	}

	public void setPriceCustomer(String priceCustomer) {
		this.priceCustomer = priceCustomer;
	}
	
	@Length(min=0, max=45, message="给到设计 人员的报价长度必须介于 0 和 45 之间")
	public String getPriceExpert() {
		return priceExpert;
	}

	public void setPriceExpert(String priceExpert) {
		this.priceExpert = priceExpert;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	
	@Length(min=0, max=1, message="发布类型长度必须介于 0 和 1 之间")
	public String getAuctionType() {
		return auctionType;
	}

	public void setAuctionType(String auctionType) {
		this.auctionType = auctionType;
	}
	
	@Length(min=0, max=64, message="选定的专家长度必须介于 0 和 64 之间")
	public WeixinExpert getExpert() {
		return expert;
	}

	public void setExpert(WeixinExpert expert) {
		this.expert = expert;
	}
	
	@Length(min=0, max=64, message="选定的供应商长度必须介于 0 和 64 之间")
	public WeixinSupplier getSupplier() {
		return supplier;
	}

	public void setSupplier(WeixinSupplier supplier) {
		this.supplier = supplier;
	}
	
	@Length(min=0, max=1, message="采购类型长度必须介于 0 和 1 之间")
	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	
}