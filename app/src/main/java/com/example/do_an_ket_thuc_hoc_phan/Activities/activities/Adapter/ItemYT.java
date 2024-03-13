package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter;

public class ItemYT {
    private byte[] hinh;
    private String ten;

    public ItemYT(byte[] hinh, String ten) {
        this.hinh = hinh;
        this.ten = ten;
    }

    public ItemYT() {
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
