package com.octavianionel.openxchangetest.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.octavianionel.openxchangetest.model.Earthquake;

import java.util.ArrayList;

/**
 * Created by Reply on 07/11/2019.
 */

public class DatabaseHandler extends SQLiteOpenHelper {


    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "EarthquakesDB";



    //**************EARTHQUAKE**************

    // Directories table name
    private static final String TABLE_EARTHQUAKE = "earthquake";

    private static final String UNIQUE_ID = "uniqueId";
    private static final String KEY_TIME = "time";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_DEPTH = "depth";
    private static final String KEY_MAG = "mag";
    private static final String KEY_MAG_TYPE = "magType";
    private static final String KEY_NST = "nst";
    private static final String KEY_GAP = "gap";
    private static final String KEY_DMIN = "dmin";
    private static final String KEY_RMS = "rms";
    private static final String KEY_NET = "net";
    private static final String KEY_ID = "id";
    private static final String KEY_UPDATED = "updated";
    private static final String KEY_PLACE = "place";
    private static final String KEY_TYPE = "type";
    private static final String KEY_HORIZONTAL_ERROR = "horizontalError";
    private static final String KEY_DEPTH_ERROR = "depthError";
    private static final String KEY_MAG_ERROR = "magError";
    private static final String KEY_MAG_NST = "magNst";
    private static final String KEY_STATUS = "status";
    private static final String KEY_LOCATION_SOURCES = "locationSource";
    private static final String KEY_MAG_SOURCE = "magSource";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_EARTHQUAKE_TABLE = "CREATE TABLE " + TABLE_EARTHQUAKE + "("
                + UNIQUE_ID + " INTEGER PRIMARY KEY, "
                + KEY_TIME + " TEXT, "
                + KEY_LATITUDE + " TEXT, "
                + KEY_LONGITUDE + " TEXT,"
                + KEY_DEPTH + " TEXT, "
                + KEY_MAG + " TEXT, "
                + KEY_MAG_TYPE + " TEXT, "
                + KEY_NST + " TEXT, "
                + KEY_GAP + " TEXT, "
                + KEY_DMIN + " TEXT, "
                + KEY_RMS + " TEXT, "
                + KEY_NET + " TEXT, "
                + KEY_ID + " TEXT, "
                + KEY_UPDATED + " TEXT, "
                + KEY_PLACE + " TEXT, "
                + KEY_TYPE + " TEXT, "
                + KEY_HORIZONTAL_ERROR + " TEXT, "
                + KEY_DEPTH_ERROR + " TEXT, "
                + KEY_MAG_ERROR + " TEXT, "
                + KEY_MAG_NST + " TEXT, "
                + KEY_STATUS + " TEXT, "
                + KEY_LOCATION_SOURCES + " TEXT, "
                + KEY_MAG_SOURCE + " TEXT"
                + ")";

        sqLiteDatabase.execSQL(CREATE_EARTHQUAKE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EARTHQUAKE);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new earthquake data
    public void addEarthquake(Earthquake earthquakeItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        if (earthquakeItem != null) {
            ContentValues values = new ContentValues();
            values.put(KEY_TIME, earthquakeItem.getTime());
            values.put(KEY_LATITUDE, earthquakeItem.getLatitude());
            values.put(KEY_LONGITUDE, earthquakeItem.getLongitude());
            values.put(KEY_DEPTH, earthquakeItem.getDepth());
            values.put(KEY_MAG, earthquakeItem.getMag());
            values.put(KEY_MAG_TYPE, earthquakeItem.getMagType());
            values.put(KEY_NST, earthquakeItem.getNst());
            values.put(KEY_GAP, earthquakeItem.getGap());
            values.put(KEY_DMIN, earthquakeItem.getDmin());
            values.put(KEY_RMS, earthquakeItem.getRms());
            values.put(KEY_NET, earthquakeItem.getNet());
            values.put(KEY_ID, earthquakeItem.getId());
            values.put(KEY_UPDATED, earthquakeItem.getUpdated());
            values.put(KEY_PLACE, earthquakeItem.getPlace());
            values.put(KEY_TYPE, earthquakeItem.getType());
            values.put(KEY_HORIZONTAL_ERROR, earthquakeItem.getHorizontalError());
            values.put(KEY_DEPTH_ERROR, earthquakeItem.getDepthError());
            values.put(KEY_MAG_ERROR, earthquakeItem.getMagError());
            values.put(KEY_MAG_NST, earthquakeItem.getMagNst());
            values.put(KEY_STATUS, earthquakeItem.getStatus());
            values.put(KEY_LOCATION_SOURCES, earthquakeItem.getLocationSources());
            values.put(KEY_MAG_SOURCE, earthquakeItem.getMagSource());


            // Inserting Row
            db.insert(TABLE_EARTHQUAKE, null, values);
        }
        db.close();
    }

    // Getting All directories
    public ArrayList<Earthquake> getAllEarthquakes() {
        ArrayList<Earthquake> itemList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EARTHQUAKE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Earthquake item = new Earthquake();
                item.setUniqueId(Integer.parseInt(cursor.getString(0))); //id
                item.setTime(cursor.getString(1));
                item.setLatitude(cursor.getString(2));
                item.setLongitude(cursor.getString(3));
                item.setDepth(cursor.getString(4));
                item.setMag(cursor.getString(5));
                item.setMagType(cursor.getString(6));
                item.setNst(cursor.getString(7));
                item.setDmin(cursor.getString(8));
                item.setRms(cursor.getString(9));
                item.setNet(cursor.getString(10));
                item.setId(cursor.getString(11));
                item.setUpdated(cursor.getString(12));
                item.setPlace(cursor.getString(13));
                item.setType(cursor.getString(14));
                item.setHorizontalError(cursor.getString(15));
                item.setDepthError(cursor.getString(16));
                item.setDepthError(cursor.getString(17));
                item.setMagError(cursor.getString(18));
                item.setMagNst(cursor.getString(19));
                item.setStatus(cursor.getString(20));
                item.setLocationSources(cursor.getString(21));
                item.setMagSource(cursor.getString(22));
                // Adding earthquake to list
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        // return earthquake list
        return itemList;
    }




}
