package tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Database;

//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class DatabaseHelper extends SQLiteOpenHelper {
//    private static final String DATABASE_NAME = "app.db";
//    private static final int DATABASE_VERSION = 1;
//
//    public static final String TABLE_USER = "NguoiDung";
//    public static final String TABLE_CHUYEN_GIA = "ChuyenGia";
//    public static final String TABLE_TU_VAN_TAM_LY = "TuVanTamLy";
//    public static final String TABLE_BMI = "BMI";
//    public static final String TABLE_HOAT_DONG_VAN_DONG = "HoatDongVanDong";
//    public static final String TABLE_HOAT_DONG_TINH_THAN = "HoatDongTinhThan";
//
//    // Columns
//    public static final String COLUMN_ID = "MaNguoiDung";
//    public static final String COLUMN_USERNAME = "TenDangNhap";
//    public static final String COLUMN_EMAIL = "Email";
//    public static final String COLUMN_PASSWORD = "MatKhau";
//    public static final String COLUMN_GENDER = "GioiTinh";
//    public static final String COLUMN_HEIGHT = "ChieuCao";
//    public static final String COLUMN_WEIGHT = "CanNang";
//
//    // SQL for creating tables
//    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + " (" +
//            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            COLUMN_USERNAME + " TEXT, " +
//            COLUMN_EMAIL + " TEXT, " +
//            COLUMN_PASSWORD + " TEXT, " +
//            COLUMN_GENDER + " TEXT, " +
//            COLUMN_HEIGHT + " REAL, " +
//            COLUMN_WEIGHT + " REAL" +
//            ");";
//
//    private static final String CREATE_CHUYEN_GIA_TABLE = "CREATE TABLE " + TABLE_CHUYEN_GIA + " (" +
//            "MaChuyenGia INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            "TenChuyenGia TEXT, " +
//            "LinhVucChuyenMon TEXT, " +
//            "Email TEXT" +
//            ");";
//
//    private static final String CREATE_TU_VAN_TAM_LY_TABLE = "CREATE TABLE " + TABLE_TU_VAN_TAM_LY + " (" +
//            "MaTuVan INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            "MaNguoiDung INTEGER, " +
//            "MaChuyenGia INTEGER, " +
//            "LinhVucTuVan TEXT, " +
//            "NoiDungTuVan TEXT, " +
//            "FOREIGN KEY(MaNguoiDung) REFERENCES " + TABLE_USER + "(" + COLUMN_ID + ")," +
//            "FOREIGN KEY(MaChuyenGia) REFERENCES " + TABLE_CHUYEN_GIA + "(MaChuyenGia)" +
//            ");";
//
//    private static final String CREATE_BMI_TABLE = "CREATE TABLE " + TABLE_BMI + " (" +
//            "MaBMI INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            "MaNguoiDung INTEGER, " +
//            "ChieuCao REAL, " +
//            "CanNang REAL, " +
//            "GiaTriBMI REAL, " +
//            "NgayTinh TEXT, " +
//            "FOREIGN KEY(MaNguoiDung) REFERENCES " + TABLE_USER + "(" + COLUMN_ID + ")" +
//            ");";
//
//    private static final String CREATE_HOAT_DONG_VAN_DONG_TABLE = "CREATE TABLE " + TABLE_HOAT_DONG_VAN_DONG + " (" +
//            "MaHoatDongVanDong INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            "MaNguoiDung INTEGER, " +
//            "NgayThucHien TEXT, " +
//            "SoBuocChan INTEGER, " +
//            "MucTieu TEXT, " +
//            "DaDatDuoc TEXT, " +
//            "FOREIGN KEY(MaNguoiDung) REFERENCES " + TABLE_USER + "(" + COLUMN_ID + ")" +
//            ");";
//
//    private static final String CREATE_HOAT_DONG_TINH_THAN_TABLE = "CREATE TABLE " + TABLE_HOAT_DONG_TINH_THAN + " (" +
//            "MaHoatDongTinhThan INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            "MaNguoiDung INTEGER, " +
//            "NgayThucHien TEXT, " +
//            "NoiDung TEXT, " +
//            "FOREIGN KEY(MaNguoiDung) REFERENCES " + TABLE_USER + "(" + COLUMN_ID + ")" +
//            ");";
//
//    public DatabaseHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_USER_TABLE);
//        db.execSQL(CREATE_CHUYEN_GIA_TABLE);
//        db.execSQL(CREATE_TU_VAN_TAM_LY_TABLE);
//        db.execSQL(CREATE_BMI_TABLE);
//        db.execSQL(CREATE_HOAT_DONG_VAN_DONG_TABLE);
//        db.execSQL(CREATE_HOAT_DONG_TINH_THAN_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHUYEN_GIA);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TU_VAN_TAM_LY);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BMI);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOAT_DONG_VAN_DONG);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOAT_DONG_TINH_THAN);
//        onCreate(db);
//    }
//}

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user_database";
    private static final int DATABASE_VERSION = 2; // Tăng version của cơ sở dữ liệu

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng users
        String createUsersTable = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, " +
                "email TEXT, " +
                "password TEXT, " +
                "gender TEXT, " +
                "dob TEXT, " +
                "address TEXT, " +
                "height REAL, " +
                "weight REAL)";
        db.execSQL(createUsersTable);

        // Tạo bảng Hoạt Động Vận Động
        String createHoatDongVanDongTable = "CREATE TABLE HoatDongVanDong (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maNguoiDung INTEGER, " +
                "ngayThucHien TEXT, " +
                "soBuocChan INTEGER, " +
                "mucTieu TEXT, " +
                "daDatDuoc TEXT, " +
                "FOREIGN KEY(maNguoiDung) REFERENCES users(id))";
        db.execSQL(createHoatDongVanDongTable);

        // Tạo bảng Hoạt Động Tinh Thần
        String createHoatDongTinhThanTable = "CREATE TABLE HoatDongTinhThan (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maNguoiDung TEXT, " +
                "ngayThucHien TEXT, " +
                "noiDung TEXT)";
        db.execSQL(createHoatDongTinhThanTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE users ADD COLUMN address TEXT");
            db.execSQL("ALTER TABLE HoatDongVanDong ADD COLUMN address TEXT");
            db.execSQL("ALTER TABLE HoatDongTinhThan ADD COLUMN address TEXT");
        }
    }
}
