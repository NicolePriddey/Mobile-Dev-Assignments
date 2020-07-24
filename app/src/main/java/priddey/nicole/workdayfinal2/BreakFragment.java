package priddey.nicole.workdayfinal2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class BreakFragment extends Fragment {
    WorkDay workDay;
    TextView totalBreakTime;
    Button backToWorkBtn;
    Long totalMillsPassed;
    //create the timer to update the break total
    CountUpTimer timer = new CountUpTimer(60000) {
        public void onTick(long millisPassed) {
            totalMillsPassed = millisPassed;
            totalBreakTime.setText((workDay.getTotalBreak() + millisPassed)/1000/60 + " mins");
        }

        public void onFinish() {
            workDay.addToBreak(totalMillsPassed);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //get the information out of the bundle
        Bundle bundle = this.getArguments();
        long day = bundle.getLong("day");
        long breakTime = bundle.getLong("break");
        int projectId = bundle.getInt("projectId");
        workDay = new WorkDay(day, breakTime, projectId);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_break, container, false);

        //set the text view
        totalBreakTime = view.findViewById(R.id.breakLen);

        //create the back to work button on click listener
        backToWorkBtn = (Button) view.findViewById(R.id.endBreakBtn);
        backToWorkBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                backToWork();
            }
        });


        //start the timer
        timer.start();

        return view;
    }

    public void backToWork() {
        timer.onFinish();
        timer.stop();
        //Add workday information to bundle
        Bundle bundle = new Bundle();
        bundle.putLong("day", workDay.getTotalDay());
        bundle.putLong("break", workDay.getTotalBreak());
        bundle.putInt("projectId", workDay.getProjectId());


        WorkingFragment fragment = new WorkingFragment();
        //pass the current workday information
        fragment.setArguments(bundle);
        //Open the working fragment
        getFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }

}