package com.example.resort;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper1 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "services.db";
    private static final int DATABASE_VERSION = 3;

    // Table names
    public static final String TABLE_SERVICE_RESERVATIONS = "service_reservations";

    // Service reservations table columns
    public static final String COLUMN_SERVICE_RESERVATION_ID = "id";
    public static final String COLUMN_SERVICE_RESERVATION_NAME = "name";
    public static final String COLUMN_SERVICE_TYPE = "service_type";

    public DatabaseHelper1(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create service reservations table
        String createServiceReservationsTable = "CREATE TABLE " + TABLE_SERVICE_RESERVATIONS + "(" +
                COLUMN_SERVICE_RESERVATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_SERVICE_RESERVATION_NAME + " TEXT NOT NULL," +
                COLUMN_SERVICE_TYPE + " TEXT NOT NULL)";
        db.execSQL(createServiceReservationsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if necessary
        if (oldVersion < 3) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_RESERVATIONS);
            onCreate(db);
        }
    }

    // Service reservations management
    public long addServiceReservation(String name, String serviceType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SERVICE_RESERVATION_NAME, name);
        values.put(COLUMN_SERVICE_TYPE, serviceType);
        long result = db.insert(TABLE_SERVICE_RESERVATIONS, null, values);
        db.close();
        return result;
    }

    public Cursor getAllServiceReservations() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_SERVICE_RESERVATIONS, null);
    }
}