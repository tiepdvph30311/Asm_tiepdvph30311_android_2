//package tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Dao;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Database.DatabaseHelper;
//import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.model.HoatDongTinhThan;
//
//public class HoatDongTinhThanDao {
//    private SQLiteDatabase database;
//    private DatabaseHelper dbHelper;
//
//    public HoatDongTinhThanDao(Context context) {
//        dbHelper = new DatabaseHelper(context);
//        database = dbHelper.getWritableDatabase();
//    }
//
//    public void addHoatDongTinhThan(HoatDongTinhThan hoatDongTinhThan) {
//        ContentValues values = new ContentValues();
//        values.put("maNguoiDung", hoatDongTinhThan.getMaNguoiDung());
//        values.put("ngayThucHien", hoatDongTinhThan.getNgayThucHien());
//        values.put("noiDung", hoatDongTinhThan.getNoiDung());
//
//        database.insert("HoatDongTinhThan", null, values);
//    }
//    public HoatDongTinhThan getHoatDongTinhThan(int id) {
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.query("HoatDongTinhThan", new String[]{"MaHoatDongTinhThan", "MaNguoiDung", "NgayThucHien", "NoiDung"},
//                "MaHoatDongTinhThan=?", new String[]{String.valueOf(id)}, null, null, null, null);
//        if (cursor != null) {
//            cursor.moveToFirst();
//        }
//        HoatDongTinhThan hoatDongTinhThan = new HoatDongTinhThan(
//                cursor.getInt(0),
//                cursor.getInt(1),
//                cursor.getString(2),
//                cursor.getString(3)
//        );
//        cursor.close();
//        return hoatDongTinhThan;
//    }
//
//    public List<HoatDongTinhThan> getAllHoatDongTinhThan() {
//        List<HoatDongTinhThan> hoatDongTinhThanList = new ArrayList<>();
//        String selectQuery = "SELECT * FROM HoatDongTinhThan";
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                HoatDongTinhThan hoatDongTinhThan = new HoatDongTinhThan();
//                hoatDongTinhThan.setMaHoatDong(cursor.getInt(0));
//                hoatDongTinhThan.setMaNguoiDung(cursor.getInt(1));
//                hoatDongTinhThan.setNgayThucHien(cursor.getString(2));
//                hoatDongTinhThan.setNoiDung(cursor.getString(3));
//                hoatDongTinhThanList.add(hoatDongTinhThan);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return hoatDongTinhThanList;
//    }
//
//    public int updateHoatDongTinhThan(HoatDongTinhThan hoatDongTinhThan) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("MaNguoiDung", hoatDongTinhThan.getMaNguoiDung());
//        values.put("NgayThucHien", hoatDongTinhThan.getNgayThucHien());
//        values.put("NoiDung", hoatDongTinhThan.getNoiDung());
//        return db.update("HoatDongTinhThan", values, "MaHoatDongTinhThan = ?", new String[]{String.valueOf(hoatDongTinhThan.getMaHoatDong())});
//    }
//
//    public void deleteHoatDongTinhThan(int id) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        db.delete("HoatDongTinhThan", "MaHoatDongTinhThan = ?", new String[]{String.valueOf(id)});
//        db.close();
//    }
//}
//

package tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Database.DatabaseHelper;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.model.HoatDongTinhThan;

public class HoatDongTinhThanDao {

    private DatabaseHelper dbHelper;

    public HoatDongTinhThanDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void addHoatDongTinhThan(HoatDongTinhThan hoatDongTinhThan) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maNguoiDung", hoatDongTinhThan.getMaNguoiDung());
        values.put("ngayThucHien", hoatDongTinhThan.getNgayThucHien());
        values.put("noiDung", hoatDongTinhThan.getNoiDung());
        db.insert("HoatDongTinhThan", null, values);
    }

    public List<HoatDongTinhThan> getAllHoatDongTinhThan(int userId) {
        List<HoatDongTinhThan> hoatDongTinhThanList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = "maNguoiDung = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        Cursor cursor = db.query("HoatDongTinhThan", null, selection, selectionArgs, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                HoatDongTinhThan hoatDongTinhThan = new HoatDongTinhThan();
                hoatDongTinhThan.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                hoatDongTinhThan.setMaNguoiDung(cursor.getInt(cursor.getColumnIndexOrThrow("maNguoiDung")));
                hoatDongTinhThan.setNgayThucHien(cursor.getString(cursor.getColumnIndexOrThrow("ngayThucHien")));
                hoatDongTinhThan.setNoiDung(cursor.getString(cursor.getColumnIndexOrThrow("noiDung")));
                hoatDongTinhThanList.add(hoatDongTinhThan);
            }
            cursor.close();
        }

        return hoatDongTinhThanList;
    }
}
