package com.thinkgem.jeesite.modules.weixin.vo;

/**
 * Created by lala on 16/11/11.
 */
public class TemplateItemEMS {
    TemplateItemMeta first = new TemplateItemMeta();
    TemplateItemMeta delivername = new TemplateItemMeta();
    TemplateItemMeta ordername = new TemplateItemMeta();
    TemplateItemMeta productName = new TemplateItemMeta();
    TemplateItemMeta productCount = new TemplateItemMeta();
    TemplateItemMeta remark = new TemplateItemMeta();

    public TemplateItemMeta getFirst() {
        return first;
    }

    public void setFirst(TemplateItemMeta first) {
        this.first = first;
    }

    public TemplateItemMeta getDelivername() {
        return delivername;
    }

    public void setDelivername(TemplateItemMeta delivername) {
        this.delivername = delivername;
    }

    public TemplateItemMeta getOrdername() {
        return ordername;
    }

    public void setOrdername(TemplateItemMeta ordername) {
        this.ordername = ordername;
    }

    public TemplateItemMeta getProductName() {
        return productName;
    }

    public void setProductName(TemplateItemMeta productName) {
        this.productName = productName;
    }

    public TemplateItemMeta getProductCount() {
        return productCount;
    }

    public void setProductCount(TemplateItemMeta productCount) {
        this.productCount = productCount;
    }

    public TemplateItemMeta getRemark() {
        return remark;
    }

    public void setRemark(TemplateItemMeta remark) {
        this.remark = remark;
    }
}
