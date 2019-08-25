import java.util.Scanner;

public class Duke {
    List taskList = new List();
    private boolean tryParseInt(String value){
        //used to check if task number entered by user is an int
        try{
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
    public Duke() {
        greetUser();
        //startup
    }
    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    public int executeCommand(String command) {
        String[] commandAction = command.split(" ", 2);
        if(commandAction.length == 0){
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            return 0;
        }
        switch (commandAction[0]) {
            case "todo":
            case "deadline":
            case "event":
                taskList.addTask(command);
                break;
            case "done":
                taskList.finishTask(Integer.parseInt(commandAction[1]));
                break;
            case "list":
                taskList.viewTasks();
                break;
            case "bye":
                if(commandAction.length == 1) {
                    this.bye();
                    return 1;
                }
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        System.out.print("\n");
        return 0;
    }

    public void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}

