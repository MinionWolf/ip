public class Task {
    protected String taskName;
    protected boolean markStatus;

    public Task(String taskName) {
        this.taskName = taskName;
        this.markStatus = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getMarkStatus(){
        return (markStatus ? "X" : " ");
    }

    public void setMarkStatus(boolean markStatus) {
        this.markStatus = markStatus;
    }

    public String getTask(){
        return "[" + getMarkStatus() + "] " +  getTaskName();
    }
}
