package com.example.android.cryptotrade.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.cryptotrade.pojo.Alert;

/**
 * Created by DITHA on 16/07/2017.
 */

public class DataHelper{
    private static final String DB_NAME = "alarm.db";
    private static final int DB_VER = 1;

    public static final String DB_TABLE = "tb_alert";
    public static final String COL_ID = "_id";
    public static final String COL_CRYPTO_NAME = "_cryptoname";
    public static final String COL_PRICE = "_price";
    public static final String COL_STATUS = "_status";

    private static final String TAG = "AlertDBAdapter";
    private DatabaseHelper db_helper;
    private SQLiteDatabase db;

    private static final String DB_CREATE = "create table tb_alert (_id integer primary key, _cryptoname text not null, _price text not null, _status text not null);";

    private final Context context;

    private static class DatabaseHelper extends SQLiteOpenHelper{
        public DatabaseHelper(Context context){
            super(context, DB_NAME, null, DB_VER);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            Log.d(TAG, "Upgrade DB");
            db.execSQL("DROP TABLE IF EXIST" + DB_TABLE);
            onCreate(db);
        }
    }

    public DataHelper(Context context){
        this.context = context;
    }

    public DataHelper open() throws SQLiteException{
        db_helper = new DatabaseHelper(context);
        db = db_helper.getWritableDatabase();
        return this;
    }

    public void Close(){
        db_helper.close();
    }

    public void CreateAlert(Alert alert){
        ContentValues val = new ContentValues();
        val.put(COL_CRYPTO_NAME, alert.getCryptoName());
        val.put(COL_PRICE, alert.getPrice());
        val.put(COL_STATUS, alert.getStatus());
        db.insert(DB_TABLE, null, val);
    }

    public boolean DeleteAlert(int id){
        return db.delete(DB_TABLE, COL_ID + "=" +id, null) > 0;
    }

    public Cursor getAllData(){
        return db.query(DB_TABLE, new String[]{
                COL_ID, COL_CRYPTO_NAME, COL_PRICE, COL_STATUS
        }, null, null, null, null, null);
    }
}
/*
public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "alert.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE alert(id integer primary key autoincrement, cryptoname text, price text, status text)";
        Log.d("Data", "onCreate" + sql);
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
} */
