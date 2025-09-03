public class ToDo extends Task{

    private static boolean toDoStatus;

    public ToDo(String taskName, boolean toDoStatus) {
        super(taskName);
        this.toDoStatus = toDoStatus;
    }

    public static String getToDoStatus() {
        return (toDoStatus ? "T" : " ");
    }

    public void setToDoStatus(boolean toDoStatus) {
        this.toDoStatus = toDoStatus;
    }

    public static void toDoString(String reply){
        int spaceIndex = reply.indexOf(" ");
        String toDoTask = reply.substring(spaceIndex + 1);
        addList(toDoTask,"todo");
    }
}
