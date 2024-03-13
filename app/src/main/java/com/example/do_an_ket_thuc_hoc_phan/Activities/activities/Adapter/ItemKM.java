package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter;

import java.io.Serializable;

public class ItemKM implements Serializable{
    private byte[] img;
    private String name_title,name_cate;

    public ItemKM(byte[] img, String name_title, String name_cate) {
        this.img = img;
        this.name_title = name_title;
        this.name_cate = name_cate;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getName_title() {
        return name_title;
    }

    public void setName_title(String name_title) {
        this.name_title = name_title;
    }

    public String getName_cate() {
        return name_cate;
    }

    public void setName_cate(String name_cate) {
        this.name_cate = name_cate;
    }
}
