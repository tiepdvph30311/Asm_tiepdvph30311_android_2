package tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Database.DatabaseHelper;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.model.User;

public class UserDao {

    private DatabaseHelper dbHelper;

    public UserDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long insert(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        values.put("gender", user.getGender());
        values.put("dob", user.getDob());
        values.put("address", user.getAddress());
        values.put("height", user.getHeight());
        values.put("weight", user.getWeight());
        return db.insert("users", null, values);
    }

    public User getUser(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {"id", "username", "email", "password", "gender", "dob", "address", "height", "weight"};
        String selection = "username=? AND password=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow("username")));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
            user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow("password")));
            user.setGender(cursor.getString(cursor.getColumnIndexOrThrow("gender")));
            user.setDob(cursor.getString(cursor.getColumnIndexOrThrow("dob")));
            user.setAddress(cursor.getString(cursor.getColumnIndexOrThrow("address")));
            user.setHeight(cursor.getDouble(cursor.getColumnIndexOrThrow("height")));
            user.setWeight(cursor.getDouble(cursor.getColumnIndexOrThrow("weight")));
            cursor.close();
            return user;
        }
        return null;
    }

    public User getUserById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {"id", "username", "email", "password", "gender", "dob", "address", "height", "weight"};
        String selection = "id=?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow("username")));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
            user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow("password")));
            user.setGender(cursor.getString(cursor.getColumnIndexOrThrow("gender")));
            user.setDob(cursor.getString(cursor.getColumnIndexOrThrow("dob")));
            user.setAddress(cursor.getString(cursor.getColumnIndexOrThrow("address")));
            user.setHeight(cursor.getDouble(cursor.getColumnIndexOrThrow("height")));
            user.setWeight(cursor.getDouble(cursor.getColumnIndexOrThrow("weight")));
            cursor.close();
            return user;
        }
        return null;
    }
}
