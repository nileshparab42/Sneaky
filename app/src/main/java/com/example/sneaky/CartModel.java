package com.example.sneaky;

public class CartModel {
    String email,img,price,date,sname,style;

    public CartModel(){}

    public CartModel(String email, String img, String price, String date, String sname, String style) {
        this.email = email;
        this.img = img;
        this.price = price;
        this.date = date;
        this.sname = sname;
        this.style = style;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
