import java.util.Scanner;

public class Duke {
    public Duke() {
        greet();
        execution();
    }
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
    }
    public void execution() {
        List TaskList = new List();
        String indentation = "| ";
        Scanner input = new Scanner(System.in);
        while(true) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                System.out.println(indentation + "Bye. Hope to see you again soon!");
                System.out.println("|___");
                break;
            }
            else if(command.equals("list")){
                TaskList.ViewTasks();
            }
            else {
                TaskList.AddTask(command);
                System.out.println(indentation + "added: " + command);
            }
            System.out.println("|___\n");
        }
    }
}

