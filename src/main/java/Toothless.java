import java.util.Scanner;

public class Toothless {
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

    //checking the CLI command
    public static void checkOperation(String reply){
        int listIndex;
        String[] tempArray = reply.split(" ");
        String mark = tempArray[0];

        if(mark.equals("mark")){
            listIndex = Integer.parseInt(tempArray[1]);
            Task.markStatus(mark,listIndex);
        }else if(mark.equals("unmark")){
            listIndex = Integer.parseInt(tempArray[1]);
            Task.markStatus(mark,listIndex);
        }else if(mark.equals("list")){
            Task.getList();
        }else if(mark.equals("todo")){
            ToDo.toDoString(reply);
        }else if(!mark.isEmpty()){
            Task.addList(reply);
        }else{
            System.out.println("You have entered a wrong entry or command \n");
        }
    }
}
