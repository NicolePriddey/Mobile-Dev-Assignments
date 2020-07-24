//package priddey.nicole.assignmentone;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//
//public class DbHandler extends SQLiteOpenHelper {
//
//    private static final int DB_VERSION = 1;
//    private static final String DB_NAME = "RecordIt";
//    private static final String TABLE_NAME = "WorkDay";
//    private static final String KEY_ID = "dayID";
//    private static final String KEY_TOTAL_WORK = "totalWork";
//    private static final String KEY_TOTAL_BREAK = "totalBreak";
//    private static final String KEY_PROJECT_ID = "projectID";
//    private static final String KEY_DATE = "date";
//
//    public DbHandler(Context context) {
//        super(context, DB_NAME, null, DB_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDb) {
//        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
//                + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + KEY_TOTAL_WORK + "INTEGER"
//                + KEY_TOTAL_BREAK + "INTEGER"
//                + KEY_PROJECT_ID + "INTEGER"
//                + KEY_DATE + "DATE"
//                + ")";
//
//        sqLiteDb.execSQL(CREATE_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDb, int oldVersion, int newVersion) {
//        sqLiteDb.execSQL("DROP TABLE " + TABLE_NAME);
//        onCreate(sqLiteDb);
//    }
//
//    public void insertWorkDay(int totalWork, int totalBreak, int projectID){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues cValues = new ContentValues();
//        cValues.put(KEY_TOTAL_WORK, totalWork);
//        cValues.put(KEY_TOTAL_BREAK, totalBreak);
//        cValues.put(KEY_PROJECT_ID,projectID);
//        cValues.put(KEY_DATE, "date");
//
//        long newRowID = db.insert(TABLE_NAME, null, cValues);
//        db.close();
//    }
//
//    public ArrayList<HashMap<String,String>> getDays(){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ArrayList<HashMap<String,String>> workDayList = new ArrayList<>();
//
//        String query = "SELECT * FROM " + TABLE_NAME;
//        Cursor cursor = db.rawQuery(query, null);
//
//        while(cursor.moveToNext()){
//            HashMap<String, String> day = new HashMap<>();
//            day.put("totalWork", cursor.getString((cursor.getColumnIndex(KEY_TOTAL_WORK))));
//            day.put("totalBreak", cursor.getString((cursor.getColumnIndex(KEY_TOTAL_BREAK))));
//            day.put("projectID", cursor.getString((cursor.getColumnIndex(KEY_PROJECT_ID))));
//            day.put("date", cursor.getString((cursor.getColumnIndex(KEY_DATE))));
//
//        }
//        return workDayList;
//    }
//}