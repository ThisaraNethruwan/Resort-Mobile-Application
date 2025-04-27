package com.example.resort;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "luxevista.db";
    private static final int DATABASE_VERSION = 5;

    // Table names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_BOOKINGS = "bookings";
    private static final String TABLE_SERVICE_RESERVATIONS = "service_reservations";

    // Users table columns
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_PASSWORD = "password";
    private static final String COLUMN_USER_FULL_NAME = "fullName";
    private static final String COLUMN_USER_PHONE_NUMBER = "phoneNumber";

    // Bookings table columns
    private static final String COLUMN_BOOKING_ID = "id";
    private static final String COLUMN_BOOKING_USER_ID = "user_id";
    private static final String COLUMN_BOOKING_ROOM_TYPE = "room_type";
    private static final String COLUMN_BOOKING_BOOKING_DATE = "booking_date";
    private static final String COLUMN_BOOKING_GUEST_NAME = "guest_name";

    // Service reservations table columns
    private static final String COLUMN_SERVICE_RESERVATION_ID = "id";
    private static final String COLUMN_SERVICE_RESERVATION_NAME = "name";
    private static final String COLUMN_SERVICE_TYPE = "service_type";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_EMAIL + " TEXT UNIQUE NOT NULL, " +
                COLUMN_USER_PASSWORD + " TEXT NOT NULL, " +
                COLUMN_USER_FULL_NAME + " TEXT, " +
                COLUMN_USER_PHONE_NUMBER + " TEXT)";
        db.execSQL(createUsersTable);

        String createBookingsTable = "CREATE TABLE " + TABLE_BOOKINGS + "(" +
                COLUMN_BOOKING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BOOKING_USER_ID + " INTEGER NOT NULL, " +
                COLUMN_BOOKING_ROOM_TYPE + " TEXT NOT NULL, " +
                COLUMN_BOOKING_BOOKING_DATE + " TEXT NOT NULL, " +
                COLUMN_BOOKING_GUEST_NAME + " TEXT NOT NULL, " +
                "FOREIGN KEY(" + COLUMN_BOOKING_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + "))";
        db.execSQL(createBookingsTable);

        String createServiceReservationsTable = "CREATE TABLE " + TABLE_SERVICE_RESERVATIONS + "(" +
                COLUMN_SERVICE_RESERVATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SERVICE_RESERVATION_NAME + " TEXT NOT NULL, " +
                COLUMN_SERVICE_TYPE + " TEXT NOT NULL)";
        db.execSQL(createServiceReservationsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 5) {
            db.execSQL("ALTER TABLE " + TABLE_USERS + " ADD COLUMN " + COLUMN_USER_FULL_NAME + " TEXT");
            db.execSQL("ALTER TABLE " + TABLE_USERS + " ADD COLUMN " + COLUMN_USER_PHONE_NUMBER + " TEXT");
        }
    }

    public void addUser(String email, String password, String fullName, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_PASSWORD, password);
        values.put(COLUMN_USER_FULL_NAME, fullName);
        values.put(COLUMN_USER_PHONE_NUMBER, phoneNumber);
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public Cursor getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_USERS,
                new String[]{COLUMN_USER_EMAIL, COLUMN_USER_FULL_NAME, COLUMN_USER_PHONE_NUMBER},
                COLUMN_USER_EMAIL + "=?",
                new String[]{email},
                null, null, null);
    }

    public boolean validateUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                null,
                COLUMN_USER_EMAIL + "=? AND " + COLUMN_USER_PASSWORD + "=?",
                new String[]{email, password},
                null, null, null);

        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        return isValid;
    }

    public long addBooking(int userId, String roomType, String bookingDate, String guestName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BOOKING_USER_ID, userId);
        values.put(COLUMN_BOOKING_ROOM_TYPE, roomType);
        values.put(COLUMN_BOOKING_BOOKING_DATE, bookingDate);
        values.put(COLUMN_BOOKING_GUEST_NAME, guestName);
        long result = db.insert(TABLE_BOOKINGS, null, values);
        db.close();
        return result;
    }

    public Cursor getBookingsByUserEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_BOOKINGS +
                " INNER JOIN " + TABLE_USERS +
                " ON " + TABLE_BOOKINGS + "." + COLUMN_BOOKING_USER_ID +
                "=" + TABLE_USERS + "." + COLUMN_USER_ID +
                " WHERE " + TABLE_USERS + "." + COLUMN_USER_EMAIL + "=?";
        return db.rawQuery(query, new String[]{email});
    }
}
