package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.DTO;

public class LayItemSP {
    private byte[] hinh;
    private String mota,thongtin,uudai,hienthi;
    private double gia;

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getThongtin() {
        return thongtin;
    }

    public void setThongtin(String thongtin) {
        this.thongtin = thongtin;
    }

    public String getUudai() {
        return uudai;
    }

    public void setUudai(String uudai) {
        this.uudai = uudai;
    }

    public String getHienthi() {
        return hienthi;
    }

    public void setHienthi(String hienthi) {
        this.hienthi = hienthi;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
}
