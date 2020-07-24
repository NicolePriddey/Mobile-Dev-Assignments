package priddey.nicole.workdayfinal2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String projectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");

        //bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        Menu menu = bottomNavigationView.getMenu();

        //highlight this page
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        //item listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    return true;
                case R.id.nav_workDay:
                    Intent working = new Intent(MainActivity.this, WorkingActivity.class);
                    startActivity(working);
                    return true;
                case R.id.nav_summary:
                    Intent summary = new Intent(MainActivity.this, SummaryActivity.class);
                    startActivity(summary);
            }
            return false;
            }
        });

        //project Spinner
        Spinner spinner = findViewById(R.id.spinner);
        ArrayList<String> arrayList = new ArrayList<>();
        //TODO: get these from the database
        arrayList.add("Project 1");
        arrayList.add("Assignment 2");
        //TODO: add ability to add more
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                projectName = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
    }

    public void startWorking(View view) {
        Intent working = new Intent(this, WorkingActivity.class);

        int projectId = 1;
        if (projectName.equals("Project 1")) projectId = 1;
        else if (projectName.equals("Assignment 2")) projectId = 2;

        working.putExtra("projectId", projectId);
        startActivity(working);
    }
}