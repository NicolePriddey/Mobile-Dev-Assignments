package priddey.nicole.assignmentone;

import android.os.Parcel;
import android.os.Parcelable;


public class WorkDay implements Parcelable {

    private static int totalDay = 0;
    private static int totalBreak = 0;
    static int projectId = 0;

    protected WorkDay(Parcel in) {
        totalDay = in.readInt();
        totalBreak = in.readInt();
    }

    public WorkDay() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(totalDay);
        dest.writeInt(totalBreak);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WorkDay> CREATOR = new Creator<WorkDay>() {
        @Override
        public WorkDay createFromParcel(Parcel in) {
            return new WorkDay(in);
        }

        @Override
        public WorkDay[] newArray(int size) {
            return new WorkDay[size];
        }
    };

    public static String getTotalDay() {
        return String.valueOf(totalDay);
    }

    public void setTotalDay(int totalDay) {
        this.totalDay = totalDay;
    }

    public static String getTotalBreak() {
        return String.valueOf(totalBreak);
    }

    public void setTotalBreak(int totalBreak) {
        this.totalBreak = totalBreak;
    }

    public static void addToBreak(long mins){
        totalBreak += mins;
    }

    public static void addToDay(long mins){
        totalDay += mins;
    }
}
