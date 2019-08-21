import java.util.Scanner;

public class Duke {
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

        executeDuke();
        //start performing tasks
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
    public void executeDuke() {
        List taskList = new List();
        String indentation = "| ";
        Scanner input = new Scanner(System.in);
        while(true) {
            String command = input.nextLine();
            //get next user input

            String[] splitCommand = command.split(" ");
            //split user input by space

            if(command.isEmpty() || command.isBlank()) {
                //if user press enter without typing
                System.out.println(indentation + "Please enter a command");
                //tell user enter a command
            }
            else if (splitCommand[0].equals("bye") && splitCommand.length == 1) {
                //if user only sees "bye"
                System.out.println(indentation + "Bye. Hope to see you again soon!");
                //display goodbye message

                System.out.println("|___");
                break;
                //end loop to exit duke
            } else if(splitCommand[0].equals("list") && splitCommand.length == 1){
                //if user wants to list
                taskList.viewTasks();
            } else if(splitCommand[0].equals("done")){
                //when user want to finish task
                int taskNumlocator = 1;
                if(splitCommand.length > 2){
                    //if "done 1 2 3" is entered
                    while(taskNumlocator < splitCommand.length && (splitCommand[taskNumlocator].isBlank()
                            || splitCommand[taskNumlocator].isEmpty())){
                        taskNumlocator++;
                    }
                    System.out.println(indentation + "Only the first input after \"done\" (Task number: "
                            + splitCommand[taskNumlocator] + ") will be performed");
                }
                else if(splitCommand.length == 1){
                    //if only "done" is entered
                    System.out.println(indentation + "Please specify a task number");
                    System.out.println("|___\n");
                    continue;
                }
                if(tryParseInt(splitCommand[taskNumlocator])) {  //ensure is valid task
                    int TaskNum = Integer.parseInt(splitCommand[taskNumlocator]);
                    taskList.finishTask(TaskNum);
                } else
                {
                    System.out.println(indentation + "Invalid task number");
                }
            } else {
                //add task to task list
                taskList.AddTask(command);
                System.out.println(indentation + "added: " + command);
            }
            System.out.println("|___\n");
        }
    }
}

