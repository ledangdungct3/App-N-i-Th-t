package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter;

public class ItemDonHang {

    private byte[] hinh;
    private String mota,gia;

    public ItemDonHang(byte[] hinh, String mota, String gia) {
        this.hinh = hinh;
        this.mota = mota;
        this.gia = gia;
    }


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

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
