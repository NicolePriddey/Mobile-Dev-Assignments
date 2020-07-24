package priddey.nicole.assignmentone;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SummaryActivity extends AppCompatActivity {

    //WorkDay workDay;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //workDay = (WorkDay) getIntent().getParcelableExtra("parcel_data");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        db = new Database(this);


        ArrayList<HashMap<String,String>> workDayList = db.totals();

        for (HashMap hm : workDayList ){
            Log.i("day", hm.get("totalWork").toString());
            Log.i("break", hm.get("totalBreak").toString());
            Log.i("e", "-1" );
        }


        ListView lv = (ListView) findViewById(R.id.workDayList);
        ListAdapter adapter = new SimpleAdapter(SummaryActivity.this,
                workDayList, R.layout.list_row,
                new String[] {"totalWork", "totalBreak",},
                new int[] {R.id.totalDay,R.id.totalBreak});
        lv.setAdapter(adapter);

//        ArrayList<HashMap<String,String>> workDayList = db.getDays();
//
//        Log.i("e", "0" );
//        ListView lv = (ListView) findViewById(R.id.workDayList);
//        Log.i("e", "1" );
//        ListAdapter adapter = new SimpleAdapter(SummaryActivity.this,
//            workDayList, R.layout.list_row,
//            new String[] {"totalWork", "totalBreak", "projectID", "date"},
//            new int[] {R.id.totalDay,R.id.totalBreak, R.id.projectId,R.id.date});
//        Log.i("e", "2" );
//        lv.setAdapter(adapter);
//        Log.i("e", "3" );

        //ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.list_row, workDayList);

        //updateList();
    }

    public void deleteData(View view) {
        db.delete();
        finish();
        startActivity(getIntent());
    }

//    public void updateList(){
//        List<String> stringWorkDays = new ArrayList<>();
//        stringWorkDays.add("Hours worked: ");
//        stringWorkDays.add(workDay.getTotalDay());
//        stringWorkDays.add("Break duration: ");
//        stringWorkDays.add(workDay.getTotalBreak());
//        stringWorkDays.add("Project number: ");
//        stringWorkDays.add(String.valueOf(workDay.projectId));
//
//        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.activity_summary, stringWorkDays);
//        ListView listView = findViewById(R.id.workDaylist);
//        listView.setAdapter(adapter);
//    }
}
