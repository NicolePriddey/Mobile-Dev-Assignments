package priddey.nicole.workdayfinal2;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class WorkingFragment extends Fragment {

    WorkDay workDay;
    TextView totalWorkingTime;
    TextView totalBreakTime;
    Button breakBtn;
    Button endDayBtn;
    Long totalMillsPassed;

    //create the timer to update the working total
    CountUpTimer timer = new CountUpTimer(60000) {
        public void onTick(long millisPassed) {
            totalMillsPassed = millisPassed;
            if (workDay.getTotalDay()/1000/60 < 60 ) totalWorkingTime.setText((workDay.getTotalDay() + millisPassed)/1000/60 + " mins");
            else if (workDay.getTotalDay()/1000/60 > 60) totalWorkingTime.setText((workDay.getTotalDay() + millisPassed)/1000/60/60 + " hour(s)");
        }

        public void onFinish() {
            workDay.addToDay(totalMillsPassed);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //check if there is information passed in the bundle
        Bundle bundle = this.getArguments();
        if ( bundle != null ) {
            //get the information out of the bundle
            long day = bundle.getLong("day");
            long breakTime = bundle.getLong("break");
            int projectId = bundle.getInt("projectId");
            workDay = new WorkDay(day, breakTime, projectId);
        }
        else workDay = new WorkDay();


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_working, container, false);

        //set the text views
        totalWorkingTime = view.findViewById(R.id.workingTotal);
        totalBreakTime = view.findViewById(R.id.breakTotal);

        //create the break button on click listener
        breakBtn = (Button) view.findViewById(R.id.breakBtn);
        breakBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                takeBreak();
            }
        });

        //create end of day button on click listener
        endDayBtn = view.findViewById(R.id.eodBtn);
        endDayBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                endDay();
            }
        });

        setBreakTime();



        //start the timer
        timer.start();

        return view;
    }

    public void setBreakTime(){
        totalBreakTime.setText(workDay.getTotalBreak()/1000/60 + " mins");
    }

    public void takeBreak() {
        timer.onFinish();
        timer.stop();

        //Add workday information to bundle
        Bundle bundle = new Bundle();
        bundle.putLong("day", workDay.getTotalDay());
        bundle.putLong("break", workDay.getTotalBreak());
        bundle.putInt("projectId", workDay.getProjectId());


        BreakFragment fragment = new BreakFragment();
        //pass the current workday information
        fragment.setArguments(bundle);
        //Open the break fragment
        getFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }

    public void endDay() {
        timer.onFinish();
        timer.stop();

        //add the workday to the database
        Database database = new Database(getActivity());
        database.insertWorkDay(String.valueOf(workDay.getTotalDay()/1000/60), String.valueOf(workDay.getTotalBreak()/1000/60), workDay.getProjectId());

        //open summary activity
        Intent endDay = new Intent(getActivity(), SummaryActivity.class);
        startActivity(endDay);
    }
}