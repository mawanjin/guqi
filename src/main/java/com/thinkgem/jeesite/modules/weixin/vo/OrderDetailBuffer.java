package com.thinkgem.jeesite.modules.weixin.vo;

/**
 * 缓冲类的详情
 * Created by lala on 16/11/18.
 */
public class OrderDetailBuffer {
    private String subType;//二级分类
    private String subSubType;//三级分类
    //尺寸
    private int length;//长
    private int width;//宽
    private int height;//高
    private int cubage;//材料体积
    private int material;//材料
    private int materialBrand;//材料名牌
    private int density;//密度
    private int color;//颜色
    private int antiFranklinism;//抗静电要求
    private int packageRequirements;//包装要求

    public String getSubSubType() {
        return subSubType;
    }

    public void setSubSubType(String subSubType) {
        this.subSubType = subSubType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCubage() {
        return cubage;
    }

    public void setCubage(int cubage) {
        this.cubage = cubage;
    }

    public int getMaterial() {
        return material;
    }

    public void setMaterial(int material) {
        this.material = material;
    }

    public int getMaterialBrand() {
        return materialBrand;
    }

    public void setMaterialBrand(int materialBrand) {
        this.materialBrand = materialBrand;
    }

    public int getDensity() {
        return density;
    }

    public void setDensity(int density) {
        this.density = density;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getAntiFranklinism() {
        return antiFranklinism;
    }

    public void setAntiFranklinism(int antiFranklinism) {
        this.antiFranklinism = antiFranklinism;
    }

    public int getPackageRequirements() {
        return packageRequirements;
    }

    public void setPackageRequirements(int packageRequirements) {
        this.packageRequirements = packageRequirements;
    }
}
