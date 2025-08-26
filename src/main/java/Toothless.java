import java.util.Arrays;
import java.util.Scanner;

public class Toothless {
    private static String[] list = new String[100];
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

            if(reply.equals("list")){
                getList(Arrays.copyOf(list, listCount));
            }else{
                addList(reply);
            }
            reply = input.nextLine();
        }

        System.out.println("--------------------------------\n" +
                "Bye.Hope to see you again soon! \n" +
                "--------------------------------\n");
    }

    public static void addList(String reply){
        System.out.println("--------------------------------\n" +
                "added: " + reply + "\n" +
                "--------------------------------\n");
        list[listCount] = reply;
        listCount++;
    }

    public static void getList(String[] list){
        System.out.println("--------------------------------");
        for(int i = 0; i < list.length; i++){
            System.out.println(i+1 + ". " + list[i]);
        }
        System.out.println("--------------------------------\n");
    }
}
