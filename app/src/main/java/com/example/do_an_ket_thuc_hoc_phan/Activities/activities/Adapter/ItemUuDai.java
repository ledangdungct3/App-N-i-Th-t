package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter;

public class ItemUuDai {
    private byte[] img;
    private String txt_title,txt_des;

    public ItemUuDai(byte[] img, String txt_title, String txt_des) {
        this.img = img;
        this.txt_title = txt_title;
        this.txt_des = txt_des;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getTxt_title() {
        return txt_title;
    }

    public void setTxt_title(String txt_title) {
        this.txt_title = txt_title;
    }

    public String getTxt_des() {
        return txt_des;
    }

    public void setTxt_des(String txt_des) {
        this.txt_des = txt_des;
    }

}
