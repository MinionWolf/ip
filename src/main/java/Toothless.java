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
                    "--------------------------------\n" ;

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
        String manipulatedString;
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
            manipulatedString = manipulateString(reply,operation);
            addList(manipulatedString,operation);
        }else if(operation.equals("deadline")){

        }
        else{
            System.out.println("You have entered a wrong entry or command \n");
        }
    }

    //add task to list
    public static void addList(String reply, String command){
        if(command.equals("todo")){
            ToDo td = new ToDo(reply);
            list[listCount] = td;
            listCount++;
            printTopMessage(command);
            System.out.println("[" + td.getToDoStatus() + "]" + "[" + td.getMarkStatus() + "] " + td.getTaskName() + "\n" +
                    "Now you have " + listCount + " tasks in the list. \n");
        }else{
            System.out.println("Unable to add to list");
        }

        printBorder();
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
            System.out.println("[" + list[listIndex-1].getMarkStatus() + "] " + list[listIndex-1].getTaskName() + "\n");
        }else{
            list[listIndex-1].setMarkStatus(false);
            printTopMessage("unmark");
            System.out.println("[" + list[listIndex-1].getMarkStatus() + "] " + list[listIndex-1].getTaskName() + "\n");
        }

        printBorder();
    }

    public static void printTopMessage(String command){
        printBorder();

        if(command.equals("add")){
            System.out.println("Got it. I've added this task:");
        }else if(command.equals("mark")){
            System.out.println("Nice! I've marked this task as done:");
        }else if(command.equals("unmark")){
            System.out.println("OK, I've marked this task as not done yet:");
        }else{
            System.out.println("Here are the tasks in your list:");
        }
    }

    public static void printBorder(){
        System.out.println("--------------------------------");
    }

    //manipulate strings
    public static String manipulateString(String reply,String command){
        String manipulatedString = "";

        if(command.equals("todo")){
            int spaceIndex = reply.indexOf(" ");
            manipulatedString = reply.substring(spaceIndex + 1);
        }

        return manipulatedString;
    }
}
