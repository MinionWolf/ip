package command;

public class Deadlines extends Task{
    private String by;
    private boolean deadlineStatus;

    public Deadlines(String taskName, String by) {
        super(taskName);
        this.by = by;
        this.deadlineStatus = true;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getDeadlineStatus() {
        return (deadlineStatus ? "D" : " ");
    }

    public void setDeadlineStatus(boolean deadlineStatus) {
        this.deadlineStatus = deadlineStatus;
    }

    @Override
    public String getTask(){
        return "[" + getDeadlineStatus() + "] " +  super.getTask();
    }
}
