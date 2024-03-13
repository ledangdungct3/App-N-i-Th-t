package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter;

public class ItemXemThem {
    private byte[] xt_img;
    private String txt_price;
    private String txt_loai;

    public ItemXemThem(byte[] xt_img, String txt_price, String txt_loai) {
        this.xt_img = xt_img;
        this.txt_price = txt_price;
        this.txt_loai = txt_loai;
    }

    public byte[] getXt_img() {
        return xt_img;
    }

    public void setXt_img(byte[] xt_img) {
        this.xt_img = xt_img;
    }

    public String getTxt_price() {
        return txt_price;
    }

    public void setTxt_price(String txt_price) {
        this.txt_price = txt_price;
    }

    public String getTxt_loai() {
        return txt_loai;
    }

    public void setTxt_loai(String txt_loai) {
        this.txt_loai = txt_loai;
    }
}
