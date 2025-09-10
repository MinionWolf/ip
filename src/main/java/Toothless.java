import exceptions.Empty;
import exceptions.InvalidCommand;
import exceptions.OutOfBounds;
import java.util.Arrays;
import java.util.Scanner;

public class Toothless {
    private static int listCount = 0;
    private static Task[] list = new Task[100];

    private static final String logo = "--------------------------------\n" +
            "Hello I'm Toothless \n" + "What can I do for you? \n" +
            "-------------------------------- \n";

    private static final String commands =  "Commands List \n" + "--------------------------------\n" +
            "list - to list all tasks added \n" +
            "bye - to exit CLI \n" +
            "mark [num] eg. mark 1 \n" +
            "unmark [num] eg.unmark 2 \n" +
            "todo [task name] eg. todo go run \n" +
            "deadline [task name] /by [deadline date/time] eg. deadline pay loan /by 01/01/25\n" +
            "event [task name] /from [start date/time] /to [end date/time] eg. attend conference /from Monday 1pm /to Friday 9pm \n" +
            "--------------------------------";

    private static final String bye = "--------------------------------\n" +
                    "Bye. Hope to see you again soon! \n" +
                    "--------------------------------" ;

    public static void main(String[] args) {
        System.out.println(logo);
        System.out.println(commands);

        Scanner input = new Scanner(System.in);

        String reply = input.nextLine();

        while(!reply.equals("bye")){
            try{
                checkOperation(reply);
            }
            catch(Empty e) {
                System.out.println("Please fill in a task \n");
            }catch(InvalidCommand e){
                System.out.println("Please enter a valid entry or command \n");
            }catch(OutOfBounds e) {
                System.out.println("The task does not exist \n");
            }
            catch(NumberFormatException e) {
                System.out.println("Please enter a numeric value \n");
            }

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
            checkArrayLengthException(tempArray);
            listIndex = Integer.parseInt(tempArray[1]);
            checkOutOfBounds(listIndex);
            markStatus(operation,listIndex);
        }else if(operation.equals("unmark")){
            listIndex = Integer.parseInt(tempArray[1]);
            checkOutOfBounds(listIndex);
            markStatus(operation,listIndex);
        }else if(operation.equals("list")){
            getList();
        }else if(operation.equals("todo")){
            checkArrayLengthException(tempArray);
            taskName = extractTaskName(reply,operation);
            addToDo(taskName);
        }else if(operation.equals("deadline")){
            checkArrayLengthException(tempArray);
            taskName = extractTaskName(reply,operation);
            String by = extractBy(reply);
            addDeadline(taskName,by);
        }else if(operation.equals("event")){
            checkArrayLengthException(tempArray);
            taskName = extractTaskName(reply,operation);
            String from = extractFromTo(reply,"from");
            String to = extractFromTo(reply,"to");
            addEvent(taskName,from,to);
        }
        else{
            throw new InvalidCommand();
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

    //add deadline to list
    public static void addDeadline(String taskName, String by){
        Deadlines d = new Deadlines(taskName, by);
        list[listCount] = d;
        listCount++;
        printTopMessage("deadline");
        System.out.println("[" + d.getDeadlineStatus() + "]" + "[" + d.getMarkStatus() + "] " + d.getTaskName() + "(by: " + d.getBy() + ") \n" +
                "Now you have " + listCount + " tasks in the list.");
        printBorder();
    }

    //add event to list
    public static void addEvent(String taskName, String from, String to){
        Events e = new Events(taskName, from, to);
        list[listCount] = e;
        listCount++;
        printTopMessage("event");
        System.out.println("[" + e.getEventStatus() + "]" + "[" + e.getMarkStatus() + "] " + e.getTaskName() + "(from: " + e.getFrom() + " to: " + e.getTo()  + ") \n" +
                "Now you have " + listCount + " tasks in the list.");
        printBorder();
    }

    //checks array length for exception
    public static void checkArrayLengthException(String[] array){
        if(array.length == 1){
            throw new Empty();
        }
    }

    //check whether accessing index out of bounds
    public static void checkOutOfBounds(int id){
        if(id > listCount){
            throw new OutOfBounds();
        }
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
        String taskName;
        int spaceIndex;

        if(command.equals("todo")){
            spaceIndex = reply.indexOf(" ");
            taskName = reply.substring(spaceIndex + 1);
        }else if(command.equals("deadline")){
            spaceIndex = reply.indexOf(" ");
            int byIndex = reply.indexOf("/by");
            taskName = reply.substring(spaceIndex + 1,byIndex);
        }else if(command.equals("event")){
            spaceIndex = reply.indexOf(" ");
            int byIndex = reply.indexOf("/from");
            taskName = reply.substring(spaceIndex + 1,byIndex);
        }else{
            throw new InvalidCommand();
        }

        return taskName;
    }

    //get by
    public static String extractBy(String reply){
        int byIndex = reply.indexOf("/by");
        return reply.substring(byIndex + 4);
    }

    //get from to
    public static String extractFromTo(String reply, String command){
        String output;

        int fromIndex = reply.indexOf("/from");
        int toIndex = reply.indexOf("/to");

        if(command.equals("from")){
            output = reply.substring(fromIndex + 6,toIndex - 1);
        }else{
            output = reply.substring(toIndex + 4);
        }

        return output;
    }
}
