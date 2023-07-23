package com.example.sneaky;

public class SneakerModel {

    String img,name,company;

    public SneakerModel() {
    }

    public SneakerModel(String img, String name, String company) {
        this.img = img;
        this.name = name;
        this.company = company;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }
}
