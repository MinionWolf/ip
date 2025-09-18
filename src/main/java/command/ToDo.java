package command;

public class ToDo extends Task{

    private boolean toDoStatus;

    public ToDo(String taskName) {
        super(taskName);
        this.toDoStatus = true;
    }

    public String getType() {
        return (toDoStatus ? "T" : " ");
    }

    public void setToDoStatus(boolean toDoStatus) {
        this.toDoStatus = toDoStatus;
    }

    @Override
    public String getTask(){
        return "[" + getType() + "] " +  super.getTask();
    }

    @Override
    public String getFrom(){
        return "";
    };

    @Override
    public String getTo(){
        return "";
    };

    @Override
    public String getBy(){
        return "";
    };

}
