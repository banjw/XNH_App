package com.fhxk.cooperative.xnh_app;

/**
 * Created by xk on 2018/12/5.
 */

public class Family {
    private String name;
    private String heyiNum;

    public Family(String name, String heyiNum) {
        this.name = name;
        this.heyiNum = heyiNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeyiNum() {
        return heyiNum;
    }

    public void setHeyiNum(String heyiNum) {
        this.heyiNum = heyiNum;
    }

    @Override
    public String toString() {
        return "Family{" +
                "name='" + name + '\'' +
                ", heyiNum='" + heyiNum + '\'' +
                '}';
    }
}
