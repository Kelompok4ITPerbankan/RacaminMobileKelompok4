package com.example.rakaminmobilekelompok4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ProfileDB";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PROFILE = "profile";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_ALAMAT = "alamat";
    public static final String COLUMN_NOMOR_TELEPON = "nomor_telepon";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_PROFILE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAMA + " TEXT, " +
                    COLUMN_STATUS + " TEXT, " +
                    COLUMN_ALAMAT + " TEXT, " +
                    COLUMN_NOMOR_TELEPON + " TEXT" +
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        onCreate(db);
    }
}
