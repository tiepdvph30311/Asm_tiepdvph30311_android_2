package tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Database.DatabaseHelper;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.model.HoatDongVanDong;

public class HoatDongVanDongDao {
    private DatabaseHelper dbHelper;

    public HoatDongVanDongDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }
    private SQLiteDatabase db;



    public void insertStepCount(int stepCount) {
        ContentValues values = new ContentValues();
        values.put("maNguoiDung", 1); // Thay thế với id của người dùng thực tế
        values.put("ngayThucHien", getCurrentDate());
        values.put("soBuocChan", stepCount);
        values.put("mucTieu", "10000"); // Mục tiêu có thể thay đổi
        values.put("daDatDuoc", stepCount >= 10000 ? "Yes" : "No");

        db.insert("HoatDongVanDong", null, values);
    }

    private String getCurrentDate() {
        // Trả về ngày hiện tại dưới dạng chuỗi
        // Sử dụng SimpleDateFormat để định dạng ngày tháng nếu cần
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    }

    public void addHoatDongVanDong(HoatDongVanDong hoatDongVanDong) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaNguoiDung", hoatDongVanDong.getMaNguoiDung());
        values.put("NgayThucHien", hoatDongVanDong.getNgayThucHien());
        values.put("SoBuocChan", hoatDongVanDong.getSoBuocChan());
        values.put("MucTieu", hoatDongVanDong.getMucTieu());
        values.put("DaDatDuoc", hoatDongVanDong.getDaDatDuoc());
        db.insert("HoatDongVanDong", null, values);
        db.close();
    }

    public HoatDongVanDong getHoatDongVanDong(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("HoatDongVanDong", new String[]{"MaHoatDongVanDong", "MaNguoiDung", "NgayThucHien", "SoBuocChan", "MucTieu", "DaDatDuoc"},
                "MaHoatDongVanDong=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        HoatDongVanDong hoatDongVanDong = new HoatDongVanDong(
                cursor.getInt(0),
                cursor.getInt(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getInt(4),
                cursor.getInt(5)
        );
        cursor.close();
        return hoatDongVanDong;
    }

    public List<HoatDongVanDong> getAllHoatDongVanDong() {
        List<HoatDongVanDong> hoatDongVanDongList = new ArrayList<>();
        String selectQuery = "SELECT * FROM HoatDongVanDong";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HoatDongVanDong hoatDongVanDong = new HoatDongVanDong();
                hoatDongVanDong.setMaHoatDongVanDong(cursor.getInt(0));
                hoatDongVanDong.setMaNguoiDung(cursor.getInt(1));
                hoatDongVanDong.setNgayThucHien(cursor.getString(2));
                hoatDongVanDong.setSoBuocChan(cursor.getInt(3));
                hoatDongVanDong.setMucTieu(cursor.getInt(4));
                hoatDongVanDong.setDaDatDuoc(cursor.getInt(5));
                hoatDongVanDongList.add(hoatDongVanDong);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return hoatDongVanDongList;
    }

    public int updateHoatDongVanDong(HoatDongVanDong hoatDongVanDong) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaNguoiDung", hoatDongVanDong.getMaNguoiDung());
        values.put("NgayThucHien", hoatDongVanDong.getNgayThucHien());
        values.put("SoBuocChan", hoatDongVanDong.getSoBuocChan());
        values.put("MucTieu", hoatDongVanDong.getMucTieu());
        values.put("DaDatDuoc", hoatDongVanDong.getDaDatDuoc());
        return db.update("HoatDongVanDong", values, "MaHoatDongVanDong = ?", new String[]{String.valueOf(hoatDongVanDong.getMaHoatDongVanDong())});
    }

    public void deleteHoatDongVanDong(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("HoatDongVanDong", "MaHoatDongVanDong = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}

