package com.google.android.gms.location.sample.locationupdates;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Arvind on 19-10-2015.
 */
public class DBHelper extends SQLiteOpenHelper {


    private final String TAG = getClass().getSimpleName();

    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, Friend.DATABASE_NAME, null, Friend.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FRIEND_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY  AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                Friend.TABLE,
                Friend.Column.ID,
                Friend.Column.LOC_NAME,
                Friend.Column.LATITUDE,
                Friend.Column.LONGITUDE,
                Friend.Column.TIME

        );
        Log.i(TAG, CREATE_FRIEND_TABLE);
        // create friend table
        db.execSQL(CREATE_FRIEND_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_FRIEND_TABLE = "DROP TABLE IF EXISTS " + Friend.TABLE;
        db.execSQL(DROP_FRIEND_TABLE);
        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);
        onCreate(db);
    }

    public void addFriend(Friend friend) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Friend.Column.ID, friend.getId());
        values.put(Friend.Column.LOC_NAME, friend.getLocationName());
        values.put(Friend.Column.LATITUDE, friend.getLatitude());
        values.put(Friend.Column.LONGITUDE, friend.getLongitude());
        values.put(Friend.Column.TIME, friend.getTime());

        sqLiteDatabase.insert(Friend.TABLE, null, values);

        sqLiteDatabase.close();
    }


    public Friend getFriend(String time) {


        String selectQuery = "SELECT  * FROM " + Friend.TABLE + " WHERE " + Friend.Column.TIME + " = '" + time+"'";
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        //String[] data = null;
        Friend friend = new Friend();
        if (cursor != null &&cursor.moveToFirst()) {
            //  cursor.moveToFirst();

            while (cursor.moveToNext()) {
                friend.setId((int) cursor.getLong(0));
                friend.setLocationName(cursor.getString(1));
                friend.setLatitude(cursor.getString(2));
                friend.setLongitute(cursor.getString(3));
                friend.setTime(cursor.getString(4));
            }


            // db.close();
        }
//
        return friend;
    }

}
