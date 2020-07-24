package priddey.nicole.assignmentone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BreakActivity extends AppCompatActivity {

    WorkDay workDay;
    Date startTime;
    Date endTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workDay = (WorkDay) getIntent().getParcelableExtra("parcel_data");
        setContentView(R.layout.activity_break);
        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (workDay.projectId == 1) getSupportActionBar().setTitle("Assignment 1");
        else if (workDay.projectId == 2) getSupportActionBar().setTitle("Assignment 2");
        startBreak();
        setBreakTime();
    }

    public void setBreakTime(){
        TextView totalBreakTime = findViewById(R.id.breakLen);
        totalBreakTime.setText(workDay.getTotalBreak() + " mins");
    }
    public void backToWork(View view) {
        endTime = new Date();
        long breakTime = endTime.getTime() - startTime.getTime();

        long seconds = breakTime / 1000;
        long minutes = seconds / 60;

        workDay.addToBreak(minutes);
        Intent working = new Intent(this, WorkingActivity.class);
        working.putExtra("parcel_data", workDay);
        startActivity(working);
    }


    public void startBreak(){
        startTime = new Date();
    }

}
