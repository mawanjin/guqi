package com.thinkgem.jeesite.modules.weixin.vo;

/**
 * 塑胶类的详情
 * Created by lala on 16/11/18.
 */
public class OrderDetailPlastic {
    private String subType;//二级分类
    private String subSubType;//三级分类
    //尺寸
    private int length;//长
    private int width;//宽
    private int thickness;//厚度
    private int height;//高
    private int weight;//重量
    private int material;//材料
    private int useOldMaterial;//使用回料
    private int useDegradable;//使用可降解塑料
    private int color;//颜色
    private int sealType;//封口方式
    private int antiFranklinism;//抗静电要求
    private int print;//印刷
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

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMaterial() {
        return material;
    }

    public void setMaterial(int material) {
        this.material = material;
    }

    public int getUseOldMaterial() {
        return useOldMaterial;
    }

    public void setUseOldMaterial(int useOldMaterial) {
        this.useOldMaterial = useOldMaterial;
    }

    public int getUseDegradable() {
        return useDegradable;
    }

    public void setUseDegradable(int useDegradable) {
        this.useDegradable = useDegradable;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSealType() {
        return sealType;
    }

    public void setSealType(int sealType) {
        this.sealType = sealType;
    }

    public int getAntiFranklinism() {
        return antiFranklinism;
    }

    public void setAntiFranklinism(int antiFranklinism) {
        this.antiFranklinism = antiFranklinism;
    }

    public int getPrint() {
        return print;
    }

    public void setPrint(int print) {
        this.print = print;
    }

    public int getPackageRequirements() {
        return packageRequirements;
    }

    public void setPackageRequirements(int packageRequirements) {
        this.packageRequirements = packageRequirements;
    }
}
