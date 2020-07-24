package priddey.nicole.assignmentone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Date;

public class WorkingActivity extends AppCompatActivity {
    WorkDay workDay;

    Date workStartTime;
    Date workEndTime;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        WorkDay workDay = (WorkDay) getIntent().getParcelableExtra("parcel_data");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working);
        AndroidNetworking.initialize(getApplicationContext());

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (workDay.projectId == 1) getSupportActionBar().setTitle("Assignment 1");
        else if (workDay.projectId == 2) getSupportActionBar().setTitle("Assignment 2");

        AsyncTimeUpdate asyncTimeUpdate = new AsyncTimeUpdate();
        asyncTimeUpdate.execute();
        workStartTime = new Date();
        setTotalTime();
        setBreakTime();
    }

    public void setTotalTime(){
        TextView totalWorkingTime = findViewById(R.id.workingTotal);
        totalWorkingTime.setText(workDay.getTotalDay() + " mins");
    }

    public void setBreakTime(){
        TextView totalBreakTime = findViewById(R.id.breakTotal);
        totalBreakTime.setText(workDay.getTotalBreak() + " mins");
    }

    public void takeBreak(View view) {

        workEndTime = new Date();
        long workTime = workEndTime.getTime() - workStartTime.getTime();
        Log.i("wt", "work time: " + workTime);
//        long seconds = workTime / 1000;
//        long minutes = seconds / 60;
        workDay.addToDay(getMinutes(workTime));

        Intent takeBreak = new Intent(this, BreakActivity.class);
        takeBreak.putExtra("parcel_data", workDay);
        startActivity(takeBreak);
    }

    public long getMinutes( long workTime){
        long seconds = workTime / 1000;
        long minutes = seconds / 60;
        return minutes;

    }

    public void endDay(View view) {
        Database database = new Database(this);
        database.insertWorkDay(workDay.getTotalDay(),workDay.getTotalBreak(), workDay.projectId);
        //WorkDays.daysList.add(workDay);
        Intent endDay = new Intent(this, SummaryActivity.class);
        //endDay.putExtra("parcel_data", workDay);
        startActivity(endDay);
    }

    public void testOuter(){
        Log.i("o", "outer class called");
    }

    private class AsyncTimeUpdate extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Log.i("i", "Async started");
            testOuter();
            return null;
        }
    }
}
