package com.thinkgem.jeesite.modules.weixin.vo;

/**
 * 标签类的详情
 * Created by lala on 16/11/18.
 */
public class OrderDetailLabel {
    private String subType;//二级分类
    private String subSubType;//三级分类
    //尺寸
    private int length;//长
    private int width;//宽
    private int diameter;//直径
    private int step;//跳距
    private int R;//R导角

    private int g;//克重
    private int thickness;//厚度

    private int glue;//胶水
    private int print;//印刷
    private int materialBrand;//材料品牌
    private int printingInk;//油墨
    private int labEnv;//标签环境
    private int specialRequirements;//特殊要求
    private int arrangeType;//排列方式
    private int packageRequirements;//包装要求

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getSubSubType() {
        return subSubType;
    }

    public void setSubSubType(String subSubType) {
        this.subSubType = subSubType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getR() {
        return R;
    }

    public void setR(int r) {
        R = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public int getGlue() {
        return glue;
    }

    public void setGlue(int glue) {
        this.glue = glue;
    }

    public int getPrint() {
        return print;
    }

    public void setPrint(int print) {
        this.print = print;
    }

    public int getMaterialBrand() {
        return materialBrand;
    }

    public void setMaterialBrand(int materialBrand) {
        this.materialBrand = materialBrand;
    }

    public int getPrintingInk() {
        return printingInk;
    }

    public void setPrintingInk(int printingInk) {
        this.printingInk = printingInk;
    }

    public int getLabEnv() {
        return labEnv;
    }

    public void setLabEnv(int labEnv) {
        this.labEnv = labEnv;
    }

    public int getSpecialRequirements() {
        return specialRequirements;
    }

    public void setSpecialRequirements(int specialRequirements) {
        this.specialRequirements = specialRequirements;
    }

    public int getArrangeType() {
        return arrangeType;
    }

    public void setArrangeType(int arrangeType) {
        this.arrangeType = arrangeType;
    }

    public int getPackageRequirements() {
        return packageRequirements;
    }

    public void setPackageRequirements(int packageRequirements) {
        this.packageRequirements = packageRequirements;
    }
}
