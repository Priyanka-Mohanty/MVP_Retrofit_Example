package com.example.priyankam.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.priyankam.myapplication.model.ResultObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MainDatabase {


    /**
     * Initializing the  "Table Name"  for the database
     */


    static public final String TABLE_MASTER = "Master";
    public static final String REF_ID = "refID";
    public static final String _ID = "id";
    public static final String SITE_ID = "siteID";
    public static final String SITE_NAME = "siteName";
    public static final String EQUIPMENT_ID = "equipmentID";
    public static final String EQUIPMENT_TYPE = "equipmentType";
    public static final String URL = "url";
    public static final String PORT = "port";
    public static final String API_KEY = "apiKey";
    public static final String UUID = "uuID";
    public static final String LOAD_TYPE = "loadType";
    public static final String PIN_NUMBER = "pinNumber";
    public static final String STATUS = "status";


    protected int oldVersion = 0;
    protected int newVersion = 1;
    static MyHelper mh;
    SQLiteDatabase sdbWfm;
    private static MainDatabase mainDataBase;
    static public final String DATABASE_NAME = "TMtoc.db";

    public MainDatabase(Context con) {
        try {
            mh = new MyHelper(con, DATABASE_NAME, null, newVersion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MainDatabase getInstance(Context context) {
        if (mainDataBase == null) {
            mainDataBase = new MainDatabase(context);
        }
        return mainDataBase;
    }
    /*-----------------delete database ----------------*/

    public void deleteDatabase(Context context) {
        try {
            context.deleteDatabase(DATABASE_NAME);
        } catch (SQLiteException e) {
            if (e != null)
                Log.d(TAG, "MainDatabase/deleteDatabase/SQLiteException");
        } catch (Exception e) {
            if (e != null)
                Log.d(TAG, "MainDatabase/deleteDatabase/Exception");
        }
    }

    public void open() {
        try {
            sdbWfm = mh.getWritableDatabase();
        } catch (SQLiteCantOpenDatabaseException e) {
            if (e != null)
                Log.d(TAG, "mainDataBase/open/SQLiteCantOpenDatabaseException");
        } catch (Exception e) {
            if (e != null)
                Log.d(TAG, "mainDataBase/open/Exception");
        }
    }

    public void close() {
        try {
            sdbWfm.close();
        } catch (SQLiteException e) {
            if (e != null)
                Log.d(TAG, "mainDataBase/close/SQLiteException");
        } catch (Exception e) {
            if (e != null)
                Log.d(TAG, "mainDataBase/close/Exception");
        }
    }

    public SQLiteDatabase getMyHelper() {
        return sdbWfm;
    }


    /*----------------------Master Table--------------------------------*/

    public void insertMasterTable(List<ResultObject> resultObjects) {

        Log.d("TAG", "Number of records: " + resultObjects.size());

        try {

            List<String> list = new ArrayList<String>();
            for (int i = 0; i < resultObjects.size(); i++) {

                Log.d("TAG", "Name: " + resultObjects.get(i).getSiteName());

                ContentValues cv = new ContentValues();

                cv.put(SITE_ID, resultObjects.get(i).getSiteID());
                cv.put(SITE_NAME, resultObjects.get(i).getSiteName());
                cv.put(EQUIPMENT_ID, resultObjects.get(i).getEquipmentID());
                cv.put(EQUIPMENT_TYPE, resultObjects.get(i).getEquipmentType());
                cv.put(URL, resultObjects.get(i).getUrl());
                cv.put(PORT, resultObjects.get(i).getPort());
                cv.put(API_KEY, resultObjects.get(i).getApiKey());
                cv.put(UUID, resultObjects.get(i).getUuid());
                cv.put(LOAD_TYPE, resultObjects.get(i).getLoadType());
                cv.put(PIN_NUMBER, resultObjects.get(i).getPinNumber());
                cv.put(STATUS, resultObjects.get(i).getStatus());

                sdbWfm.insert(TABLE_MASTER, null, cv);
            }


        } catch (SQLiteException e) {
            if (e != null)
                Log.d(TAG, "mainDataBase/insertMasterTable/SQLiteException");
        } catch (Exception e) {
            if (e != null)
                Log.d(TAG, "mainDataBase/insertMasterTable/Exception");
        }

    }

    public void insertMasterTable(String siteID, String siteName,
                                  String equipmentID, String equipmentType, String Url,
                                  String port, String apiKey,
                                  String uuid, String loadType,
                                  String pinNumber, String status) {
        try {
            ContentValues cv = new ContentValues();

            cv.put(SITE_ID, siteID);
            cv.put(SITE_NAME, siteName);
            cv.put(EQUIPMENT_ID, equipmentID);
            cv.put(EQUIPMENT_TYPE, equipmentType);
            cv.put(URL, Url);
            cv.put(PORT, port);
            cv.put(API_KEY, apiKey);
            cv.put(UUID, uuid);
            cv.put(LOAD_TYPE, loadType);
            cv.put(PIN_NUMBER, pinNumber);
            cv.put(STATUS, status);

            sdbWfm.insert(TABLE_MASTER, null, cv);

        } catch (SQLiteException e) {
            if (e != null)
                Log.d(TAG, "mainDataBase/insertMasterTable/SQLiteException");
        } catch (Exception e) {
            if (e != null)
                Log.d(TAG, "mainDataBase/insertMasterTable/Exception");
        }
    }


    public Cursor getMaster() {
        Cursor c = null;
        try {
            c = sdbWfm.query(TABLE_MASTER, null, null, null, null, null, null);
        } catch (SQLiteException e) {
            if (e != null) {
                Log.e(TAG, "MainDatabase/getMaster/SQLiteException" + e.getMessage());
            } else {
                Log.e(TAG, "MainDatabase/getMaster/SQLiteException");
            }
        } catch (Exception e) {
            if (e != null) {
                Log.e(TAG, "MainDatabase/getMaster/SQLiteException" + e.getMessage());
            } else {
                Log.e(TAG, "MainDatabase/getMaster/SQLiteException");
            }
        }
        return c;
    }

    public String getStatusData(String status) {
        String StatusData = "";
        try {
            Cursor c = sdbWfm.query(TABLE_MASTER, new String[]{STATUS}, STATUS + "=?", new String[]{String.valueOf(status)}, null, null, null, null);
            if (c != null) {
                c.moveToFirst();
                StatusData = c.getString(c.getColumnIndex(STATUS));
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (e != null) {
                Log.e(TAG, "mainDataBase/getStatusData/SQLiteException" + e.getMessage());
            } else {
                Log.e(TAG, "mainDataBase/getStatusData/SQLiteException");
            }
        }

        Log.d("VisitLogData", STATUS + "=" + StatusData);
        return StatusData;
    }


    public void deleteMasterTable() {
        try {
            sdbWfm.delete(TABLE_MASTER, null, null);
        } catch (SQLiteException e) {
            if (e != null)
                Log.d(TAG, "mainDataBase/deleteMasterTable/SQLiteException");
        } catch (Exception e) {
            if (e != null)
                Log.d(TAG, "mainDataBase/deleteMasterTable/Exception");
        }
    }

    public void deleteMasterTable(String siteID) {
        try {
            sdbWfm.delete(TABLE_MASTER, SITE_ID + "=?", new String[]{"" + siteID});
        } catch (Exception e) {
            if (e != null)
                Log.d(TAG, "mainDataBase/deleteMasterTable/Exception");
        }
    }


    public int updateStatusInMasterTable(String siteID, String status) {

        int isUpdated = 0;
        try {
            ContentValues cv = new ContentValues();
            cv.put(mainDataBase.STATUS, status);
            isUpdated = sdbWfm.update(mainDataBase.TABLE_MASTER, cv, mainDataBase.SITE_ID + " = '" + siteID + "'", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdated;
    }


    /*-------------------------------------------------------------------------------------------*/

    private class MyHelper extends SQLiteOpenHelper {


        public MyHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                String createMasterTable = "CREATE TABLE " + TABLE_MASTER +
                        "(" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        SITE_ID + " TEXT," + // Define a  key
                        SITE_NAME + " TEXT," + EQUIPMENT_ID + " TEXT," + EQUIPMENT_TYPE + " TEXT," +
                        URL + " TEXT," + PORT + " TEXT," + API_KEY + " TEXT," +
                        UUID + " TEXT," + LOAD_TYPE + " TEXT," +
                        PIN_NUMBER + " TEXT," +
                        STATUS + " TEXT" +
                        ");";

                db.execSQL(createMasterTable);


            } catch (SQLiteException e) {
                if (e != null)
                    Log.d(TAG, "mainDataBase/onCreate/SQLiteException");
            } catch (Exception e) {
                if (e != null)
                    Log.d(TAG, "mainDataBase/onCreate/Exception");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int _oldVersion, int _newVersion) {
            switch (_newVersion) {
                case 2:
                    break;
                default:
                    throw new IllegalStateException(
                            "onUpgrade() with unknown newVersion" + _newVersion);

            }
        }
    }
}
