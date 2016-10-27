/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.jxc.entity;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 商品类别Entity
 *
 * @author 马万进
 * @version 2016-07-23
 */
public class JxcProductkind extends TreeEntity<JxcProductkind> {

    private static final long serialVersionUID = 1L;
    private String name;        // 商品类别
    private JxcProductkind parent;        // 父级编号
    private String parentIds;        // VARCHAR(2000)
    private Integer sort;        // 排序

    public JxcProductkind() {
        super();
    }

    public JxcProductkind(String id) {
        super(id);
    }

    @Length(min = 0, max = 100, message = "商品类别长度必须介于 0 和 100 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonBackReference
    @NotNull(message = "父级编号不能为空")
    public JxcProductkind getParent() {
        return parent;
    }

    public void setParent(JxcProductkind parent) {
        this.parent = parent;
    }

    @Length(min = 1, max = 2000, message = "VARCHAR(2000)长度必须介于 1 和 2000 之间")
    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getParentId() {
        return parent != null && parent.getId() != null ? parent.getId() : "0";
    }
}