package com.paic.dto;

/**
 * @Author: Admin
 * @Date: 2021/8/29 12:13
 * @Description:
 */
public class BloodTypeDto {
    private String bloodType;
    private Integer proportion;

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Integer getProportion() {
        return proportion;
    }

    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }

    public BloodTypeDto(String bloodType, Integer proportion) {
        this.bloodType = bloodType;
        this.proportion = proportion;
    }

    public BloodTypeDto() {

    }

    @Override
    public String toString() {
        return "BloodTypeDto{" +
                "bloodType='" + bloodType + '\'' +
                ", proportion=" + proportion +
                '}';
    }
}
