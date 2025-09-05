import java.util.Arrays;
import java.util.Scanner;

public class Toothless {
    private static int listCount = 0;
    private static Task[] list = new Task[100];

    private static String logo = "--------------------------------\n" +
            "Hello I'm Toothless \n" + "What can I do for you? \n" +
            "--------------------------------";

    private static String bye = "--------------------------------\n" +
                    "Bye. Hope to see you again soon! \n" +
                    "--------------------------------" ;

    public static void main(String[] args) {
        System.out.println(logo);

        Scanner input = new Scanner(System.in);

        String reply = input.nextLine();

        while(!reply.equals("bye")){
            checkOperation(reply);
            reply = input.nextLine();
        }

        System.out.println(bye);
    }

    //checking the CLI command
    public static void checkOperation(String reply){
        int listIndex;
        String taskName;
        String[] tempArray = reply.split(" ");
        String operation = tempArray[0];

        if(operation.equals("mark")){
            listIndex = Integer.parseInt(tempArray[1]);
            markStatus(operation,listIndex);
        }else if(operation.equals("unmark")){
            listIndex = Integer.parseInt(tempArray[1]);
            markStatus(operation,listIndex);
        }else if(operation.equals("list")){
            getList();
        }else if(operation.equals("todo")){
            taskName = extractTaskName(reply,operation);
            addToDo(taskName);
        }else if(operation.equals("deadline")){
            taskName = extractTaskName(reply,operation);
            String by = extractBy(reply);
            addDeadline(taskName,by);
        }else if(operation.equals("event")){
            taskName = extractTaskName(reply,operation);
            addEvent();
        }
        else{
            System.out.println("You have entered a wrong entry or command \n");
        }
    }

    //add task to list
    public static void addToDo(String taskName){
        ToDo td = new ToDo(taskName);
        list[listCount] = td;
        listCount++;
        printTopMessage("todo");
        System.out.println("[" + td.getToDoStatus() + "]" + "[" + td.getMarkStatus() + "] " + td.getTaskName() + "\n" +
                "Now you have " + listCount + " tasks in the list.");
        printBorder();
    }

    public static void addDeadline(String taskName, String by){
        Deadlines d = new Deadlines(taskName, by);
        list[listCount] = d;
        listCount++;
        printTopMessage("deadline");
        System.out.println("[" + d.getDeadlineStatus() + "]" + "[" + d.getMarkStatus() + "] " + d.getTaskName() + "(by: " + d.getBy() + ") \n" +
                "Now you have " + listCount + " tasks in the list.");
        printBorder();
    }

    public static void addEvent(String taskName, String from, String to){

    }

    //list all task
    public static void getList(){
        list = Arrays.copyOf(list, listCount);

        printTopMessage("list");

        for(int i = 0; i < list.length; i++){
            System.out.println((i+1) + ". " + list[i].getTask());
        }

        printBorder();
    }

    //marking or unmarking the task
    public static void markStatus(String mark, int listIndex){
        if(mark.equals("mark")){
            list[listIndex-1].setMarkStatus(true);
            printTopMessage("mark");
            System.out.println("[" + list[listIndex-1].getMarkStatus() + "] " + list[listIndex-1].getTaskName());
        }else{
            list[listIndex-1].setMarkStatus(false);
            printTopMessage("unmark");
            System.out.println("[" + list[listIndex-1].getMarkStatus() + "] " + list[listIndex-1].getTaskName());
        }

        printBorder();
    }

    //print header message
    public static void printTopMessage(String command){
        printBorder();

        if(command.equals("list")){
            System.out.println("Here are the tasks in your list:");
        }else if(command.equals("mark")){
            System.out.println("Nice! I've marked this task as done:");
        }else if(command.equals("unmark")){
            System.out.println("OK, I've marked this task as not done yet:");
        }else{
            System.out.println("Got it. I've added this task:");
        }
    }

    //print line border
    public static void printBorder(){
        System.out.println("--------------------------------");
    }

    //get task name
    public static String extractTaskName(String reply, String command){
        String taskName = "";
        int spaceIndex;

        if(command.equals("todo")){
            spaceIndex = reply.indexOf(" ");
            taskName = reply.substring(spaceIndex + 1);
        }else if(command.equals("deadline")){
            spaceIndex = reply.indexOf(" ");
            int byIndex = reply.indexOf("/by");
            taskName = reply.substring(spaceIndex + 1,byIndex);
        }else{
            spaceIndex = reply.indexOf(" ");
            int byIndex = reply.indexOf("/from");
            taskName = reply.substring(spaceIndex + 1,byIndex);
        }

        return taskName;
    }

    //get by
    public static String extractBy(String reply){
        int byIndex = reply.indexOf("/by");
        return reply.substring(byIndex + 3);
    }

    public static String extractFromTo(String reply, String command){
        String output;

        int fromIndex = reply.indexOf("/from");
        int toIndex = reply.indexOf("/to");

        if(command.equals("from")){
            
        }else{

        }

        return "";
    }
}
