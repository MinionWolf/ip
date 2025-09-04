public class ToDo extends Task{

    private boolean toDoStatus;

    public ToDo(String taskName) {
        super(taskName);
        this.toDoStatus = true;
    }

    public String getToDoStatus() {
        return (toDoStatus ? "T" : " ");
    }

    public void setToDoStatus(boolean toDoStatus) {
        this.toDoStatus = toDoStatus;
    }

    @Override
    public String getTask(){
        return "[" + getToDoStatus() + "] " +  super.getTask();
    }


}
