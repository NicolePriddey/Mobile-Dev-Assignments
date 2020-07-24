package priddey.nicole.workdayfinal2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class SummaryActivity extends AppCompatActivity {

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Summary");

        //The Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        Intent home = new Intent(SummaryActivity.this, MainActivity.class);
                        startActivity(home);
                        return true;
                    case R.id.nav_workDay:
                        Intent working = new Intent(SummaryActivity.this, WorkingActivity.class);
                        startActivity(working);
                        return true;
                    case R.id.nav_summary:
                        return true;
                }
                return false;
            }
        });

        //Database stuff
        db = new Database(this);
        ArrayList<HashMap<String,String>> workDayList = db.totals();

        ListView lv = findViewById(R.id.workDayList);
        ListAdapter adapter = new SimpleAdapter(SummaryActivity.this,
                workDayList, R.layout.list_row,
                new String[] {"totalWork", "totalBreak",},
                new int[] {R.id.totalDay,R.id.totalBreak});
        lv.setAdapter(adapter);
    }

    public void deleteData(View view) {
        db.delete();
        finish();
        startActivity(getIntent());
    }
}