import command.Deadlines;
import command.Events;
import command.Task;
import command.ToDo;
import exceptions.*;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Toothless {
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static final String logo = "--------------------------------\n" +
            "Hello I'm Toothless\n" + "What can I do for you?\n" +
            "--------------------------------\n";

    private static final String commands =  "Commands List\n" + "--------------------------------\n" +
            "list - to list all tasks added\n" +
            "bye - to exit CLI\n" +
            "mark [num] eg. mark 1\n" +
            "unmark [num] eg.unmark 2\n" +
            "todo [task name] eg. todo go run\n" +
            "deadline [task name] /by [deadline date/time] eg. deadline pay loan /by 01/01/25\n" +
            "event [task name] /from [start date/time] /to [end date/time] eg. attend conference /from Monday 1pm /to Friday 9pm\n" +
            "--------------------------------";

    private static final String bye = "--------------------------------\n" +
                    "Bye. Hope to see you again soon!\n" +
                    "--------------------------------" ;

    //checking the CLI command
    private static void checkOperation(String reply){
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
        }else if(operation.equals("delete")){
            listIndex = Integer.parseInt(tempArray[1]);
            checkOutOfBounds(listIndex);
            deleteTask(listIndex);
        }
        else if(operation.equals("list")){
            getList();
        }else if(operation.equals("todo")){
            checkArrayLengthException(tempArray);
            taskName = extractTaskName(reply,operation);
            addToDo(taskName);
        }else if(operation.equals("deadline")){
            checkArrayLengthException(tempArray);
            checkContainByFromTo(reply,operation);
            taskName = extractTaskName(reply,operation);
            String by = extractBy(reply);
            addDeadline(taskName,by);
        }else if(operation.equals("event")){
            checkArrayLengthException(tempArray);
            checkContainByFromTo(reply,operation);
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
    private static void addToDo(String taskName){
        ToDo td = new ToDo(taskName);
        tasks.add(td);
        printTopMessage("todo");
        System.out.println("[" + td.getToDoStatus() + "]" + "[" + td.getMarkStatus() + "] " + td.getTaskName() + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.");
        printBorder();
    }

    //add deadline to list
    private static void addDeadline(String taskName, String by){
        Deadlines d = new Deadlines(taskName, by);
        tasks.add(d);
        printTopMessage("deadline");
        System.out.println("[" + d.getDeadlineStatus() + "]" + "[" + d.getMarkStatus() + "] " + d.getTaskName() + " (by: " + d.getBy() + ")\n" +
                "Now you have " + tasks.size() + " tasks in the list.");
        printBorder();
    }

    //add event to list
    private static void addEvent(String taskName, String from, String to){
        Events e = new Events(taskName, from, to);
        tasks.add(e);
        printTopMessage("event");
        System.out.println("[" + e.getEventStatus() + "]" + "[" + e.getMarkStatus() + "] " + e.getTaskName() + " (from: " + e.getFrom() + " to: " + e.getTo()  + ")\n" +
                "Now you have " + tasks.size() + " tasks in the list.");
        printBorder();
    }

    //check array length for exception
    private static void checkArrayLengthException(String[] array){
        if(array.length == 1){
            throw new Empty();
        }
    }

    //check whether input is accessing index out of bounds
    private static void checkOutOfBounds(int id){
        if(id > tasks.size()){
            throw new OutOfBounds();
        }
    }

    //checks whether it contains by,from,to
    private static void checkContainByFromTo(String reply, String operation){
        if(operation.equals("deadline") && !reply.contains("/by")){
            throw new WrongFormat();
        }

        if(operation.equals("event") && (!reply.contains("/from") || !reply.contains("/to"))){
            throw new WrongFormat();
        }
    }

    private static void checkEmptyByFromTo(String reply){
        if(reply.isEmpty()){
            throw new IncompleteFormat();
        }
    }

    //list all task
    private static void getList(){
        printTopMessage("list");

        int count = 1;

        for(Task t : tasks){
            System.out.println(count + ". " + t.getTask());
            count++;
        }

        printBorder();
    }

    private static void deleteTask(int index){
        index -= 1;
        printTopMessage("delete");

        System.out.println(tasks.get(index).getTask() + "\n" + "Now you have " + (tasks.size() - 1) + " tasks in the list." );

        printBorder();

        tasks.remove(index);
    }

    //marking or unmarking the task
    private static void markStatus(String mark, int listIndex){
        if(mark.equals("mark")){
            tasks.get(listIndex-1).setMarkStatus(true);
            printTopMessage("mark");
            System.out.println("[" + tasks.get(listIndex-1).getMarkStatus() + "] " + tasks.get(listIndex-1).getTaskName());
        }else{
            tasks.get(listIndex-1).setMarkStatus(false);
            printTopMessage("unmark");
            System.out.println("[" + tasks.get(listIndex-1).getMarkStatus() + "] " + tasks.get(listIndex-1).getTaskName());
        }

        printBorder();
    }

    //print header message
    private static void printTopMessage(String command){
        printBorder();

        if(command.equals("list")){
            System.out.println("Here are the tasks in your list:");
        }else if(command.equals("mark")){
            System.out.println("Nice! I've marked this task as done:");
        }else if(command.equals("unmark")){
            System.out.println("OK, I've marked this task as not done yet:");
        }else if(command.equals("delete")){
            System.out.println("Noted I've removed this task:");
        }
        else{
            System.out.println("Got it. I've added this task:");
        }
    }

    //print line border
    private static void printBorder(){
        System.out.println("--------------------------------");
    }

    //get task name
    private static String extractTaskName(String reply, String command){
        String taskName;

        if(command.equals("todo")){
            reply = reply.substring(4);
            taskName = reply.trim();
        }else if(command.equals("deadline")){
            int byIndex = reply.indexOf("/by");
            reply = reply.substring(8,byIndex);
            taskName = reply.trim();
        }else if(command.equals("event")){
            int byIndex = reply.indexOf("/from");
            reply = reply.substring(6,byIndex);
            taskName = reply.trim();
        }else{
            throw new InvalidCommand();
        }

        return taskName;
    }

    //get by
    private static String extractBy(String reply){
        int byIndex = reply.indexOf("/by");
        reply = reply.substring(byIndex + 3);
        checkEmptyByFromTo(reply);
        return reply.trim();
    }

    //get from to
    private static String extractFromTo(String reply, String command){
        int fromIndex = reply.indexOf("/from");
        int toIndex = reply.indexOf("/to");

        if(command.equals("from")){
            reply = reply.substring(fromIndex + 5,toIndex);
        }else{
            reply = reply.substring(toIndex + 3);
        }
        checkEmptyByFromTo(reply);
        return reply.trim();
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }

    private static void fillFile(String filePath){
        for(Task t : tasks){
            try{
                appendToFile(filePath,t.getTask());
            }catch(IOException e){
                System.out.println("Unable to append");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(logo);
        System.out.println(commands);

        String filePath = "data/data.txt";

        File f = new File(filePath);

        Scanner input = new Scanner(System.in);

        String reply = input.nextLine();

        while(!reply.equals("bye")){
            try{
                checkOperation(reply);
            }
            catch(Empty e) {
                System.out.println("Please fill in a task");
            }catch(InvalidCommand e){
                System.out.println("Please enter a valid entry or command");
            }catch(OutOfBounds e) {
                System.out.println("The task does not exist");
            }
            catch(NumberFormatException e) {
                System.out.println("Please enter a numeric value");
            }catch(WrongFormat e) {
                System.out.println("Please double check the format of the command again");
            }catch(IncompleteFormat e) {
                System.out.println("Your command is uncomplete, check again");
            }

            reply = input.nextLine();
        }

        fillFile(filePath);

        System.out.println(bye);
    }
}
