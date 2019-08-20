import java.util.Scanner;

public class Duke {
    private boolean tryParseInt(String value){  //used to check if task number entered by user is an int
        try{
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
    public Duke() {
        greet();    //startup
        execution();    //start performing tasks
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
            String command = input.nextLine();  //get next user input
            String[] SplitCommand = command.split(" "); //split user input by space
            if(command.isEmpty() || command.isBlank())          //if user input is empty
                System.out.println(indentation + "Please enter a command"); //tell user enter a command
            else if (SplitCommand[0].equals("bye") && SplitCommand.length == 1) { //if user only sees "bye" despite having space behind
                System.out.println(indentation + "Bye. Hope to see you again soon!");   //display goodbye message
                System.out.println("|___");
                break;  //end loop to exit duke
            }
            else if(SplitCommand[0].equals("list") && SplitCommand.length == 1){    //if user wants to list
                TaskList.ViewTasks();
            }
            else if(SplitCommand[0].equals("done")){    //when user want to finish task
                int TaskNumlocator = 1;
                if(SplitCommand.length > 2){        //if done 1 2 3 is entered
                    while(TaskNumlocator < SplitCommand.length && (SplitCommand[TaskNumlocator].isBlank() || SplitCommand[TaskNumlocator].isEmpty())){
                        TaskNumlocator++;
                    }
                    System.out.println(indentation + "Only the first input after \"done\" (Task number: " + SplitCommand[TaskNumlocator] + ") will be performed");
                }
                else if(SplitCommand.length == 1){ //if only "done" is entered
                    System.out.println(indentation + "Please specify a task number");
                    System.out.println("|___\n");
                    continue;
                }
                if(tryParseInt(SplitCommand[TaskNumlocator])) {  //ensure is valid task
                    int TaskNum = Integer.parseInt(SplitCommand[TaskNumlocator]);
                    TaskList.FinishTask(TaskNum);
                }
                else
                {
                    System.out.println(indentation + "Invalid task number");
                }
            }
            else {  //add task to task list
                TaskList.AddTask(command);
                System.out.println(indentation + "added: " + command);
            }
            System.out.println("|___\n");
        }
    }
}

