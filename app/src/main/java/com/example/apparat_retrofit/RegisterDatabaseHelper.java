package com.example.apparat_retrofit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class RegisterDatabaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_RJTR = "tbl_r";
    private static final String TAG = "RegisterDatabaseHelper";

    public RegisterDatabaseHelper(@Nullable Context context) {
        super(context, TABLE_RJTR, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // db.execSQL("CREATE TABLE " +TABLE_TASKS+" (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, completed BOOLEAN);");
        try {
            db.execSQL("CREATE TABLE " + TABLE_RJTR + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,email TEXT,password TEXT);");
        } catch (Exception e) {
            Log.e(TAG, "onCreate: " + e.toString());
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long ADDUser(UserRegister userRegister) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", userRegister.getName());
        contentValues.put("email", userRegister.getEmail());
        contentValues.put("password", userRegister.getPassword());
        long result = sqLiteDatabase.insert(TABLE_RJTR, null, contentValues);
        sqLiteDatabase.close();
        return result;

    }

    public List<UserRegister> GETUser() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_RJTR, null);


        // we get lists of tasks
        List<UserRegister> userRegister = new ArrayList<>();
        // cheked do things with table
        if (cursor.moveToFirst()) { // cursor.moveToFirst(), come back boolean

            do {
                //create new task
                UserRegister userregister = new UserRegister();
                userregister.setId(cursor.getInt(0));
                userregister.setName(cursor.getString(1));
                userregister.setEmail(cursor.getString(2));
                userregister.setPassword(cursor.getString(3));

                userRegister.add(userregister);

            } while (cursor.moveToNext());

        }
        sqLiteDatabase.close();
        return userRegister;

    }


}
