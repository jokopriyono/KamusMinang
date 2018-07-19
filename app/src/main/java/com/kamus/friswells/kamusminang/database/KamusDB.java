package com.kamus.friswells.kamusminang.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressLint("UseSparseArrays")
public class KamusDB {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static KamusDB instance;
    private HashMap<Integer, String> bahasaIndo;
    private HashMap<Integer, String> bahasaMinang;
    private HashMap<Integer, String> bahasaInggris;
    private HashMap<Integer, String> bahasaJenisKata;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context aplikasi
     */
    private KamusDB(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static KamusDB getInstance(Context context) {
        if (instance == null) {
            instance = new KamusDB(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
        bahasaIndo = selectBahasaIndonesia();
        bahasaMinang = selectBahasaMinang();
        bahasaInggris = selectBahasaInggris();
        bahasaJenisKata = selectJenisKata();
    }

    public HashMap<Integer, String> getBahasaIndo() {
        return bahasaIndo;
    }

    public HashMap<Integer, String> getBahasaJenisKata() {
        return bahasaJenisKata;
    }

    public HashMap<Integer, String> getBahasaMinang() {
        return bahasaMinang;
    }

    public HashMap<Integer, String> getBahasaInggris() {
        return bahasaInggris;
    }

    /**
     * Menutup koneksi database.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Membaca semua data dari database.
     *
     * @return semua data
     */
    public List<String> getQuotes() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM kamus", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    private HashMap<Integer, String> selectBahasaIndonesia() {
        HashMap<Integer, String> list = new HashMap<>();
        Cursor cursor = database.rawQuery("SELECT * FROM kamus", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.put(cursor.getInt(0), cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    private HashMap<Integer, String> selectBahasaMinang() {
        HashMap<Integer, String> list = new HashMap<>();
        Cursor cursor = database.rawQuery("SELECT * FROM kamus", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.put(cursor.getInt(0), cursor.getString(2));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    private HashMap<Integer, String> selectBahasaInggris() {
        HashMap<Integer, String> list = new HashMap<>();
        Cursor cursor = database.rawQuery("SELECT * FROM kamus", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.put(cursor.getInt(0), cursor.getString(3));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    private HashMap<Integer, String> selectJenisKata() {
        HashMap<Integer, String> list = new HashMap<>();
        Cursor cursor = database.rawQuery("SELECT * FROM kamus", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.put(cursor.getInt(0), cursor.getString(4));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}