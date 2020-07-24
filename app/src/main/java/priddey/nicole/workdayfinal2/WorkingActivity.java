package priddey.nicole.workdayfinal2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WorkingActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        //get the projectId passed from the last activity
        int projectId = intent.getIntExtra("projectId", 1);

        if (projectId == 1) getSupportActionBar().setTitle("Assignment 1");
        else if (projectId == 2) getSupportActionBar().setTitle("Assignment 2");

        //put the projectId into new bundle
        Bundle bundle = new Bundle();
        bundle.putInt("projectId", projectId);



        WorkingFragment fragment = new WorkingFragment();
        //add the bundle to pass
        fragment.setArguments(bundle);
        //open working fragment
        getFragmentManager().beginTransaction().add(R.id.frameLayout, fragment).commit();


        //bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        Menu menu = bottomNavigationView.getMenu();

        //highlight this page
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);


        //item listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    Intent home = new Intent(WorkingActivity.this, MainActivity.class);
                    startActivity(home);
                    return true;
                case R.id.nav_workDay:
                    return true;
                case R.id.nav_summary:
                    Intent summary = new Intent(WorkingActivity.this, SummaryActivity.class);
                    startActivity(summary);
            }
            return false;
            }
        });
    }
}