package priddey.nicole.assignmentone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_summary:
                Intent summary = new Intent(this, SummaryActivity.class);
                startActivity(summary);
            break;

            case R.id.action_settings:
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void startWorking(View view) {
        Intent working = new Intent(this, WorkingActivity.class);

        WorkDay workDay = new WorkDay();
        if (view.getId() == R.id.project1Btn) workDay.projectId = 1;
        else if (view.getId() == R.id.project2Btn) workDay.projectId = 2;

        working.putExtra("parcel_data", workDay);
        startActivity(working);

//        Database db = new Database(this);
//        db.execute("");
    }
}
