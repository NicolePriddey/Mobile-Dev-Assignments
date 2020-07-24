package priddey.nicole.assignmentone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Database extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "RecordIt";
    private static final String TABLE_NAME = "WorkDay";
    private static final String KEY_ID = "dayID";
    private static final String KEY_TOTAL_WORK = "totalWork";
    private static final String KEY_TOTAL_BREAK = "totalBreak";
    private static final String KEY_PROJECT_ID = "projectID";
    private static final String KEY_DATE = "date";

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDb) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TOTAL_WORK + " INTEGER, "
                + KEY_TOTAL_BREAK + " INTEGER, "
                + KEY_PROJECT_ID + " INTEGER, "
                + KEY_DATE + " DATE "
                + ")";
        Log.i("test", "This is a test message");
        sqLiteDb.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDb, int oldVersion, int newVersion) {
        sqLiteDb.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(sqLiteDb);
    }

    public void delete(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }

    public void insertWorkDay(String totalWork, String totalBreak, int projectID){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cValues = new ContentValues();
        cValues.put(KEY_TOTAL_WORK, totalWork);
        cValues.put(KEY_TOTAL_BREAK, totalBreak);
        cValues.put(KEY_PROJECT_ID,projectID);
        cValues.put(KEY_DATE, "04/05/20");

        long newRowID = db.insert(TABLE_NAME, null, cValues);
        db.close();
    }

    public ArrayList<HashMap<String,String>> getDays(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String,String>> workDayList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Log.i("cursor", "" + cursor.getCount());

        while(cursor.moveToNext()){
            HashMap<String, String> day = new HashMap<>();
            day.put("totalWork", cursor.getString((cursor.getColumnIndex(KEY_TOTAL_WORK))));
            day.put("totalBreak", cursor.getString((cursor.getColumnIndex(KEY_TOTAL_BREAK))));
            day.put("projectID", cursor.getString((cursor.getColumnIndex(KEY_PROJECT_ID))));
            day.put("date", cursor.getString((cursor.getColumnIndex(KEY_DATE))));

            workDayList.add(day);
        }
        return workDayList;
    }
    public ArrayList<HashMap<String,String>> totals(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String,String>> workDayList = new ArrayList<>();

        String query = "SELECT totalWork, totalBreak FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Log.i("cursor", "" + cursor.getCount());

        while(cursor.moveToNext()){
            HashMap<String, String> day = new HashMap<>();
            day.put("totalWork", cursor.getString((cursor.getColumnIndex(KEY_TOTAL_WORK))));
            day.put("totalBreak", cursor.getString((cursor.getColumnIndex(KEY_TOTAL_BREAK))));
            workDayList.add(day);
        }
        return workDayList;
    }
}
