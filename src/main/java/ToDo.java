public class ToDo extends Task{

    private boolean toDoStatus;

    public ToDo(String taskName) {
        super(taskName);
    }

    public String getToDoStatus() {
        return (toDoStatus ? "T" : " ");
    }

    public void setToDoStatus(boolean toDoStatus) {
        this.toDoStatus = toDoStatus;
    }

    public static void toDoString(String reply){
        int spaceIndex = reply.indexOf(" ");
        String toDoTask = reply.substring(spaceIndex + 1);
        System.out.println(toDoTask);
        addList(toDoTask);
        System.out.println("Got it. I've added this  task: \n" +
                "[");
    }

    @Override
    public String toString(){
        return "[" + getToDoStatus() + "]" + super.toString();
    }

}
