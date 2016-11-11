package com.thinkgem.jeesite.modules.weixin.vo;

/**
 * Created by lala on 16/11/11.
 */
public class TemplateItemEMS {
    TemplateItemMeta first = new TemplateItemMeta();
    String delivername;
    String ordername;
    String productName;//商品名称
    String productCount;//商品数量
    String remark;

    public TemplateItemMeta getFirst() {
        return first;
    }

    public void setFirst(TemplateItemMeta first) {
        this.first = first;
    }

    public String getDelivername() {
        return delivername;
    }

    public void setDelivername(String delivername) {
        this.delivername = delivername;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
