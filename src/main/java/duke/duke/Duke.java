package duke.duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parse.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Parser parser;
    private Storage storage;

    public void run(){
        storage = new Storage();
        parser = new Parser();
        ui = new Ui();
        tasks = new TaskList();
        tasks = storage.load(ui);
        boolean hasExit = false;
        ui.greetUser();

        while(!hasExit){
            try {
                String command = ui.readCommand();
                Command c = parser.getCommand(command);
                c.execute(ui, tasks);
                hasExit = c.getExit();
            } catch (DukeException errorMessage){
                ui.printMessage(errorMessage.toString());
            }
            ui.printMessage("");
        }
    }
    /*
    private static final String FILE_PATH = "data.txt";
    List taskList = new List();

    public Duke() {
        greetUser();
        taskList.initializeList();
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
        switch (commandAction[0]) {
            case "todo":
                try {
                    taskList.addNewToDo((command.substring(4)).trim());
                } catch (DukeExceptions errorMessage){
                    System.out.println(errorMessage.toString());
                }
                break;
            case "deadline":
                try {
                    taskList.addNewDeadline((command.substring(8)).trim());
                } catch (DukeExceptions errorMessage){
                    System.out.println(errorMessage.toString());
                }
                break;
            case "event":
                try {
                    taskList.addNewEvent((command.substring(5)).trim());
                } catch (DukeExceptions errorMessage){
                    System.out.println(errorMessage.toString());
                }
                break;
            case "done":
                taskList.finishTask(command);
                break;
            case "delete":
                taskList.deleteTask(command);
            case "find":
                taskList.findTask(command);
                break;
            case "list":
                try {
                    taskList.viewTasks(commandAction);
                } catch (DukeExceptions errorMessage){
                    System.out.println(errorMessage.toString());
                }
                break;
            case "bye":
                try{
                    this.bye(commandAction);
                    return 1;
                }
                catch (DukeExceptions errorMessage){
                    System.out.println(errorMessage.toString());
                }
                break;
            default:
                System.out.println("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        System.out.print("\n");
        return 0;
    }

    public void bye(String[] commandAction) throws DukeExceptions{
        if(commandAction.length != 1) {
            throw new DukeExceptions("The \"bye\" command cannot be used with other arguments.");
        }
        taskList.saveList();
        System.out.println("Bye. Hope to see you again soon!");
    }*/
}