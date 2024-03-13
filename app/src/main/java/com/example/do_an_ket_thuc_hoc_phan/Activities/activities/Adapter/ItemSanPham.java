package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter;

public class ItemSanPham {
    public static final int TYPEList = 1;
    public static final int TYPEGrid = 2;
    public static final int TYPEStagge = 3;
    private int typedisplay;
    private byte[] hinh;
    private String txt_phong;

    public ItemSanPham(byte[] hinh, String txt_phong) {
        this.hinh = hinh;
        this.txt_phong = txt_phong;
    }
    public int getTypedisplay() {
        return typedisplay;
    }

    public void setTypedisplay(int typedisplay) {
        this.typedisplay = typedisplay;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public String getTxt_phong() {
        return txt_phong;
    }

    public void setTxt_phong(String txt_phong) {
        this.txt_phong = txt_phong;
    }
}
