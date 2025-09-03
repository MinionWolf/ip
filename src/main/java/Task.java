import java.util.Arrays;

public class Task {
    private String taskName;
    private boolean taskStatus;
    private static int listCount = 0;
    private static Task[] list = new Task[100];

    public Task(String taskName) {
        this.taskName = taskName;
        this.taskStatus = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskStatus(){
        return (taskStatus ? "X" : " ");
    }

    public void setTaskStatus(boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getListCount() {
        return listCount;
    }

    //add task to list
    public static void addList(String reply){
        System.out.println("--------------------------------\n" +
                "added: " + reply + "\n" +
                "--------------------------------\n");
        Task t = new Task(reply);
        list[listCount] = t;
        listCount++;
    }

    //list all task
    public static void getList(){
        list = Arrays.copyOf(list, listCount);
        System.out.println("-------------------------------- \n" + "Here are the tasks in your list:");
        for(int i = 0; i < list.length; i++){
            System.out.println(i+1 + ". " + "[" + list[i].getTaskStatus() + "] " + list[i].getTaskName());
        }
        System.out.println("--------------------------------\n");
    }

    //marking/unmarking the task
    public static void markStatus(String mark, int listIndex){
        if(mark.equals("mark")){
            list[listIndex-1].setTaskStatus(true);
            System.out.println("-------------------------------- \n" +
                    "Nice! I've marked this task as done: \n" +
                    "[" + list[listIndex-1].getTaskStatus() + "] " + list[listIndex-1].getTaskName() + "\n" +
                    "-------------------------------- \n");
        }else{
            list[listIndex-1].setTaskStatus(false);
            System.out.println("--------------------------------\n" +
                    "OK, I've marked this task as not done yet: \n" +
                    "[" + list[listIndex-1].getTaskStatus() + "] " + list[listIndex-1].getTaskName() + "\n" +
                    "-------------------------------- \n");
        }
    }
}
