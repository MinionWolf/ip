public class Task {
    private String taskName;
    private boolean status;

    public Task(String taskName) {
        this.taskName = taskName;
        this.status = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStatus(){
        return (status ? "X" : " ");
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
