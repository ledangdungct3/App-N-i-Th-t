package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.ItemDonHang;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.ItemKM;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.ItemSanPham;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.ItemUuDai;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.ItemXemThem;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.ItemYT;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.DTO.LayItemSP;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.DTO.LayItemXT;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.DTO.ThongTinDN;

import java.util.ArrayList;
import java.util.List;

public class CreateDatabase extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "quanly.sqlite";
    public static final String TB_KhuyenMai = "khuyenmai";
    public static final String TB_xemnh ="catexemnh";
    public static final String TB_xemsp ="sanpham";
    public static final String TB_uudai= "cateuudai";
    public static final String TB_damua = "damua";
    public static final String TB_DN = "dangnhap";
    public static final String TB_YT = "yeuthich";
    //KM

    public CreateDatabase(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String catekm = "CREATE TABLE "+TB_KhuyenMai + " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hinh BOLB," +
                "mota NTEXT," +
                "gia DOUBLE,"+
                "thongtin NTEXT,"+
                "hienthi NTEXT)";
        String catext = "CREATE TABLE "+TB_xemnh + " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hinh BOLB," +
                "mota NTEXT," +
                "gia DOUBLE,"+
                "thongtin NTEXT,"+
                "hienthi NTEXT)";
        String sanpham = "CREATE TABLE "+TB_xemsp + " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hinh BOLB," +
                "mota NTEXT," +
                "gia DOUBLE,"+
                "uudai NTEXT,"+
                "thongtin NTEXT,"+
                "hienthi NTEXT)";
        String uudai = "CREATE TABLE "+TB_uudai + " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hinh BOLB," +
                "sale NTEXT,"+
                "mota NTEXT)";
        String damua = "CREATE TABLE "+TB_damua + " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hinh BOLB," +
                "sale NTEXT,"+
                "mota NTEXT)";
        String dangnhap = "CREATE TABLE "+TB_DN + " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fullname NTEXT,"+
                "username NTEXT,"+
                "email NTEXT,"+
                "password NTEXT,"+
                "number NTEXT)";
        String yeuthich = "CREATE TABLE "+TB_YT + " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hinh BOLB," +
                "mota NTEXT)";
        db.execSQL(catekm);
        db.execSQL(catext);
        db.execSQL(sanpham);
        db.execSQL(uudai);
        db.execSQL(damua);
        db.execSQL(dangnhap);
        db.execSQL(yeuthich);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void addItemkm(byte[] hinh,String mota,Double gia,String thongtin,String hienthi){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "INSERT INTO "+CreateDatabase.TB_KhuyenMai+ " VALUES (null,?,?,?,?,?)";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1,hinh);
        statement.bindString(2,mota);
        statement.bindDouble(3,gia);
        statement.bindString(4,thongtin);
        statement.bindString(5,hienthi);
        statement.executeInsert();
    }
    public void addItemYT(byte[] hinh,String ten){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "INSERT INTO "+CreateDatabase.TB_YT+ " VALUES (null,?,?)";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1,hinh);
        statement.bindString(2,ten);
        statement.executeInsert();
    }
    public List<ItemYT> getallYT(){
        List<ItemYT> list= new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " +CreateDatabase.TB_YT;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        while (cursor != null && cursor.moveToNext()){
            list.add(new ItemYT(cursor.getBlob(1),cursor.getString(2)));
        }
        return list;
    }
    public List<ItemKM> getallkm(){
        List<ItemKM> list= new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " +CreateDatabase.TB_KhuyenMai;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        while (cursor != null && cursor.moveToNext()){
            list.add(new ItemKM(cursor.getBlob(1),cursor.getString(2),cursor.getString(4)));
        }
        return list;
    }
    public LayItemXT laytheoid(int id){
        LayItemXT layItemXT = new LayItemXT();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM "+CreateDatabase.TB_KhuyenMai+" WHERE id = "+ id;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        while (cursor != null && cursor.moveToNext()){
            layItemXT.setHinh(cursor.getBlob(1));
            layItemXT.setMota(cursor.getString(2));
            layItemXT.setGia(cursor.getDouble(3));
            layItemXT.setThongtin(cursor.getString(4));
            layItemXT.setHienthi(cursor.getString(5));
        }
        return layItemXT;
    }
    public void addItemxt(byte[] hinh,String mota,Double gia,String thongtin,String hienthi){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "INSERT INTO "+TB_xemnh+ " VALUES (null,?,?,?,?,?)";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1,hinh);
        statement.bindString(2,mota);
        statement.bindDouble(3,gia);
        statement.bindString(4,thongtin);
        statement.bindString(5,hienthi);
        statement.executeInsert();
    }
    public List<ItemXemThem> getallxt(){
        List<ItemXemThem> list= new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " +CreateDatabase.TB_xemnh;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        while (cursor != null && cursor.moveToNext()){
            list.add(new ItemXemThem(cursor.getBlob(1),cursor.getString(3),cursor.getString(2)));
        }
        return list;
    }
    public void addItemsp(byte[] hinh,String mota,Double gia,String uudai,String thongtin,String hienthi){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "INSERT INTO "+TB_xemsp+ " VALUES (null,?,?,?,?,?,?)";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1,hinh);
        statement.bindString(2,mota);
        statement.bindDouble(3,gia);
        statement.bindString(4,uudai);
        statement.bindString(5,thongtin);
        statement.bindString(6,hienthi);
        statement.executeInsert();
    }
    public List<ItemSanPham> getallsp(){
        List<ItemSanPham> list= new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " +CreateDatabase.TB_xemsp;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        while (cursor != null && cursor.moveToNext()){
            list.add(new ItemSanPham(cursor.getBlob(1),cursor.getString(2)));
        }
        return list;
    }
    public void addItemud(byte[] hinh,String sale,String mota){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "INSERT INTO "+TB_uudai+ " VALUES (null,?,?,?)";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1,hinh);
        statement.bindString(2,sale);
        statement.bindString(3,mota);
        statement.executeInsert();
    }
    public List<ItemUuDai> getallud(){
        List<ItemUuDai> list= new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " +CreateDatabase.TB_uudai;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        while (cursor != null && cursor.moveToNext()){
            list.add(new ItemUuDai(cursor.getBlob(1),cursor.getString(2),cursor.getString(3)));
        }
        return list;
    }
    // Lay thei id KM

    public LayItemXT laytheoidxt(int id){
        LayItemXT layItemXT = new LayItemXT();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM "+CreateDatabase.TB_xemnh+" WHERE id = "+ id;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        while (cursor != null && cursor.moveToNext()){
            layItemXT.setHinh(cursor.getBlob(1));
            layItemXT.setMota(cursor.getString(2));
            layItemXT.setGia(cursor.getDouble(3));
            layItemXT.setThongtin(cursor.getString(4));
            layItemXT.setHienthi(cursor.getString(5));
        }
        return layItemXT;
    }
    public LayItemSP laytheoidsp(int id){
        LayItemSP layItemXT = new LayItemSP();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM "+CreateDatabase.TB_xemsp+" WHERE id = "+ id;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        while (cursor != null && cursor.moveToNext()){
            layItemXT.setHinh(cursor.getBlob(1));
            layItemXT.setMota(cursor.getString(2));
            layItemXT.setGia(cursor.getDouble(3));
            layItemXT.setUudai(cursor.getString(4));
            layItemXT.setThongtin(cursor.getString(5));
            layItemXT.setHienthi(cursor.getString(6));
        }
        return layItemXT;
    }
    //hoadon
    public void addItemhoadon(byte[] hinh,String mota,String gia){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "INSERT INTO "+TB_damua+ " VALUES (null,?,?,?)";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1,hinh);
        statement.bindString(2,mota);
        statement.bindString(3,gia);
        statement.executeInsert();
    }
    public List<ItemDonHang> getallhoadon(){
        List<ItemDonHang> list= new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " +CreateDatabase.TB_damua;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        while (cursor != null && cursor.moveToNext()){
            list.add(new ItemDonHang(cursor.getBlob(1),cursor.getString(2),cursor.getString(3)));
        }
        return list;
    }
    public int xoaHDtheoid(int id){
        SQLiteDatabase database = getWritableDatabase();
       String WhereClause = "id=?";
       String[] whereArgs = {Integer.toString(id)};
       return database.delete(TB_damua,WhereClause,whereArgs);

    }
    public boolean xoaXTtheoid(int id){
        SQLiteDatabase database = getWritableDatabase();
        long ktra = database.delete(TB_xemnh,"id"+ " = " +id
                ,null);
        if(ktra !=0 ){
            return true;
        }else {
            return false;
        }
    }
    public boolean xoaKMtheoid(int id){
        SQLiteDatabase database = getWritableDatabase();
        long ktra = database.delete(TB_KhuyenMai,"id"+ " = " +id
                ,null);
        if(ktra !=0 ){
            return true;
        }else {
            return false;
        }
    }
    public boolean xoaSPtheoid(int id){
        SQLiteDatabase database = getWritableDatabase();
        long ktra = database.delete(TB_xemsp,"id"+ " = " +id
                ,null);
        if(ktra !=0 ){
            return true;
        }else {
            return false;
        }
    }
    public boolean xoaUDtheoid(int id){
        SQLiteDatabase database = getWritableDatabase();
        long ktra = database.delete(TB_uudai,"id"+ " = " +id
                ,null);
        if(ktra !=0 ){
            return true;
        }else {
            return false;
        }
    }
    public List<ItemSanPham> getallsptheoten(String s){
        List<ItemSanPham> list= new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String whereClause = "mota like ?";
        String[] whereArgs = {s.trim()};
        Cursor cursor = sqLiteDatabase.query(CreateDatabase.TB_xemsp,null,whereClause,whereArgs,null,null,null);
        while (cursor != null && cursor.moveToNext()){
            list.add(new ItemSanPham(cursor.getBlob(1),cursor.getString(2)));
        }
        return list;
    }

    public boolean updateKMid(LayItemXT layItemXT,int id) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase database = getWritableDatabase();
        contentValues.put("hinh",layItemXT.getHinh());
        contentValues.put("mota",layItemXT.getMota());
        contentValues.put("gia",layItemXT.getGia());
        contentValues.put("thongtin",layItemXT.getThongtin());
        contentValues.put("hienthi",layItemXT.getHienthi());
        long ktra = database.update(CreateDatabase.TB_KhuyenMai,contentValues
                ,"id"+" = "+id,null);
        if(ktra != 0){
            return true;
        }else {
            return false;
        }
    }
    public boolean updateXTid(LayItemXT layItemXT,int id) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase database = getWritableDatabase();
        contentValues.put("hinh",layItemXT.getHinh());
        contentValues.put("mota",layItemXT.getMota());
        contentValues.put("gia",layItemXT.getGia());
        contentValues.put("thongtin",layItemXT.getThongtin());
        contentValues.put("hienthi",layItemXT.getHienthi());
        long ktra = database.update(CreateDatabase.TB_xemnh,contentValues
                ,"id"+" = "+id,null);
        if(ktra != 0){
            return true;
        }else {
            return false;
        }
    }
    public boolean updateSPid(LayItemSP layItemSP,int id) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase database = getWritableDatabase();
        contentValues.put("hinh",layItemSP.getHinh());
        contentValues.put("mota",layItemSP.getMota());
        contentValues.put("gia",layItemSP.getGia());
        contentValues.put("uudai",layItemSP.getUudai());
        contentValues.put("thongtin",layItemSP.getThongtin());
        contentValues.put("hienthi",layItemSP.getHienthi());
        long ktra = database.update(CreateDatabase.TB_xemsp,contentValues
                ,"id"+" = "+id,null);
        if(ktra != 0){
            return true;
        }else {
            return false;
        }
    }

    public long addND(ThongTinDN thongTinDN){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase database = getWritableDatabase();
        contentValues.put("fullname",thongTinDN.getFull_name());
        contentValues.put("username",thongTinDN.getUer_name());
        contentValues.put("email",thongTinDN.getEmail());
        contentValues.put("password",thongTinDN.getPass_word());
        contentValues.put("number",thongTinDN.getNumber());

        long ktra = database.insert(CreateDatabase.TB_DN,null,contentValues);
        return ktra;
    }
    @SuppressLint("Range")
    public int KiemTraDN(String tenDN, String matKhau){
        SQLiteDatabase database = getWritableDatabase();
        String query = "SELECT * FROM " +CreateDatabase.TB_DN+ " WHERE "
                + "username = '"+ tenDN+"' AND password = '"+matKhau +"'";
        int manv = 0;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            manv = cursor.getInt(cursor.getColumnIndex("id")) ;
            cursor.moveToNext();
        }
        return manv;
    }


}
