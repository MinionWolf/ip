package command;

public class Deadlines extends ToDo{
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

    @Override
    public String getType() {
        return (deadlineStatus ? "D" : " ");
    }

    public void setDeadlineStatus(boolean deadlineStatus) {
        this.deadlineStatus = deadlineStatus;
    }

    @Override
    public String getTask(){
        return super.getTask() + " (by: " + getBy() + ")";
    }
}
