package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.DTO;

public class LayItemXT {
    private byte[] hinh;
    private String mota,thongtin,hienthi;
    private Double gia;


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

    public String getHienthi() {
        return hienthi;
    }

    public void setHienthi(String hienthi) {
        this.hienthi = hienthi;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }
}
