import java.util.Arrays;
import java.util.Scanner;

public class Toothless {
    private static Task[] list = new Task[100];
    private static int listCount = 0;
    private static String reply;

    public static void main(String[] args) {
        String logo = "--------------------------------\n" +
                "Hello I'm Toothless \n" + "What can I do for you? \n" +
                "--------------------------------";
        System.out.println(logo);

        Scanner input = new Scanner(System.in);

        reply = input.nextLine();

        while(!reply.equals("bye")){
            checkOperation(reply);
            reply = input.nextLine();
        }

        System.out.println("--------------------------------\n" +
                "Bye. Hope to see you again soon! \n" +
                "--------------------------------\n");
    }

    public static void addList(String reply){
        System.out.println("--------------------------------\n" +
                "added: " + reply + "\n" +
                "--------------------------------\n");
        Task t = new Task(reply);
        list[listCount] = t;
        listCount++;
    }

    public static void getList(Task[] list){
        System.out.println("-------------------------------- \n" + "Here are the tasks in your list:");
        for(int i = 0; i < list.length; i++){
            System.out.println(i+1 + ". " + "[" + list[i].getStatus() + "] " + list[i].getTaskName());
        }
        System.out.println("--------------------------------\n");
    }

    public static void taskStatus(Task[] list,String reply){
        int spaceIndex = reply.indexOf(" ");
        int listIndex = Integer.parseInt(reply.substring(spaceIndex+1));
        String mark =  reply.substring(0, spaceIndex);

        if(mark.equals("mark")){
            list[listIndex-1].setStatus(true);
            System.out.println("-------------------------------- \n" +
                    "Nice! I've marked this task as done: \n" +
                    "[" + list[listIndex-1].getStatus() + "] " + list[listIndex-1].getTaskName() + "\n" +
                    "-------------------------------- \n");
        }else{
            list[listIndex-1].setStatus(false);
            System.out.println("--------------------------------\n" +
                    "OK, I've marked this task as not done yet: \n" +
                    "[" + list[listIndex-1].getStatus() + "] " + list[listIndex-1].getTaskName() + "\n" +
                    "-------------------------------- \n");
        }
    }

    public static void checkOperation(String reply){
        if(reply.contains("mark")){
            taskStatus(list,reply);
        }else if(reply.contains("unmark")){
            taskStatus(list,reply);
        }else if(reply.contains("list")){
            getList(Arrays.copyOf(list, listCount));
        }else{
            addList(reply);
        }
    }
}
