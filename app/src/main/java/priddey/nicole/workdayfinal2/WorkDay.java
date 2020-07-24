package priddey.nicole.workdayfinal2;

public class WorkDay{
    private long totalDay = 0;
    private long totalBreak = 0;
    private int projectId = 0;


    public WorkDay(long totalDay, long totalBreak, int projectId) {
        this.totalDay = totalDay;
        this.totalBreak = totalBreak;
        this.projectId = projectId;
    }

    public WorkDay() {

    }

    public long getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(int totalDay) {
        this.totalDay = totalDay;
    }

    public long getTotalBreak() {
        return totalBreak;
    }

    public void setTotalBreak(int totalBreak) {
        this.totalBreak = totalBreak;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void addToBreak(long mins){
        totalBreak += mins;
    }

    public void addToDay(long milliseconds ){
        totalDay += milliseconds;
    }
}
