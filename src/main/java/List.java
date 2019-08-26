import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class List {
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private static final String FILE_PATH = "data.txt";

    private int parseTaskNumber(String command) throws DukeExceptions{
        command = command.substring(4);
        command = command.trim();
        if(command.length() < 1){
            throw new DukeExceptions("The task number cannot be blank");
        }
        try{
            int correctTaskNumber = Integer.parseInt(command);
            if(taskList.size() == 0){
                throw new DukeExceptions("There are 0 tasks in the list.");
            }
            else if((correctTaskNumber <= taskList.size()) && (correctTaskNumber > 0)){
                correctTaskNumber--;
                if(taskList.get(correctTaskNumber).getStatusIcon().equals("Y")){
                    throw new DukeExceptions("Task has already been completed.");
                }
                return correctTaskNumber;
            } else{
                throw new DukeExceptions("Incorrect task number. Please enter one task number from within the list.");
            }
        } catch(NumberFormatException e){
            throw new DukeExceptions("Incorrect task number. Please enter one task number from within the list.");
        }
    }

    private void successfulAddTask(){
        System.out.println("Got it. I've added this task:\n"
                + taskList.get(taskList.size() - 1).getDetails());
        if(taskList.size() == 1){
            System.out.println("Now you have " + taskList.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
    }

    public void addNewToDo(String details) throws DukeExceptions{
        if(details.length() == 0){
            throw new DukeExceptions("The description of a todo cannot be empty.");
        }
        Task newTodo = new ToDo(details);
        taskList.add(newTodo);
        successfulAddTask();
    }

    public void addNewDeadline(String details) throws DukeExceptions{
        if(details.length() == 0 || details.equals("/by")){
            throw new DukeExceptions("The description and deadline of a task cannot be empty.");
        }
        if(details.length() < 3 || details.substring(details.length()-4).equals(" /at")){
            throw new DukeExceptions("The task must have a deadline.");
        }
        if(!details.contains(" /by ")){
            if(details.contains("/by ") && details.split("/by ").length == 2
                    && details.split("/by ")[0].length() < 1){
                throw new DukeExceptions("The task must have a description.");
            }
            throw new DukeExceptions("The task must have a deadline." +
                    " Command format:\nevent <task description> /by <deadline>");
        }
        String[] deadlineDetails = details.split(" /by ");
        Task newDeadline = new Deadline(
                details.substring(0,details.length()-deadlineDetails[deadlineDetails.length-1].length()-5)
                ,deadlineDetails[deadlineDetails.length-1]);
        taskList.add(newDeadline);
        successfulAddTask();
    }

    public void addNewEvent(String details) throws DukeExceptions{
        if(details.length() == 0 || details.equals("/at")){
            throw new DukeExceptions("The description and time of an event cannot be empty.");
        }
        if(details.length() < 3 || details.substring(details.length()-4).equals(" /at")){
            throw new DukeExceptions("The event must have a date and time.");
        }
        if(!details.contains(" /at ")){
            if(details.contains("/at ") && details.split("/at ").length == 2
                    && details.split("/at ")[0].length() < 1){
                throw new DukeExceptions("The event must have a description.");
            }
            throw new DukeExceptions("The event must have a date and time." +
                    " Command format:\nevent <event description> /at <date and time>");
        }
        String[] eventDetails = details.split(" /at ");
        Task newEvent = new Event(
                details.substring(0,details.length()-eventDetails[eventDetails.length-1].length()-5)
                ,eventDetails[eventDetails.length-1]);
        taskList.add(newEvent);
        successfulAddTask();
    }

    public void addTask(String newTask){
        String[] addTaskDetails = newTask.split(" ");
        String taskDescription;
        String[] splitTaskDescription;
        switch(addTaskDetails[0]) {
        case "deadline":
            taskDescription = newTask.substring(9);
            splitTaskDescription = taskDescription.split(" /by ");
            Task newDeadline = new Deadline(splitTaskDescription[0], splitTaskDescription[1]);
            taskList.add(newDeadline);
            break;
        case "event":
            taskDescription = newTask.substring(6);
            splitTaskDescription = taskDescription.split(" /at ");
            Task newEvent = new Event(splitTaskDescription[0], splitTaskDescription[1]);
            taskList.add(newEvent);
            break;
        case "todo":
            taskDescription = newTask.substring(5);
            taskDescription = taskDescription.trim();
            if(taskDescription.length() > 0) {
                Task newToDo = new ToDo(taskDescription);
                taskList.add(newToDo);
            } else {
                System.out.println("\u2639 OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        default:
            return;
        }
    }

    public void viewTasks(String[] commandAction) throws DukeExceptions{
        if(commandAction.length != 1){
            throw new DukeExceptions("The \"list\" command cannot be used with other arguments.");
        }
        if(taskList.size() < 1){
            System.out.println("There are 0 tasks in the list.");
            return;
        }
        for(int i = 0; i < taskList.size();i++)
        {
            System.out.println((i+1) + ". " + (taskList.get(i)).getDetails());
        }
    }
    public void finishTask(String command){
        try{
            int taskNumber = parseTaskNumber(command);
            taskList.get(taskNumber).markAsDone();
            System.out.println("Nice! I've marked this task as done: ");
        }
        catch (DukeExceptions errorMessage){
            System.out.println(errorMessage.toString());
        }
    }

    public void saveList(){
        try {
            File dataFile = new File(FILE_PATH);
            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskList);
            oos.close();
            fos.close();
        } catch (IOException ioe){
            System.out.println("\u2639 OOPS!!! We are unable to save your task list :-(");
        }
    }

    public void initializeList(){
        File dataFile = new File(FILE_PATH);
        if(dataFile.exists()) {
            try {
                FileInputStream fis = new FileInputStream(FILE_PATH);
                ObjectInputStream ois = new ObjectInputStream(fis);
                taskList = (ArrayList<Task>) ois.readObject();
                ois.close();
                fis.close();
                Task.totalTasks = taskList.size();
            } catch (IOException ioe) {
                System.out.println("\u2639 OOPS!!! We are unable to load your task list :-(");
            } catch (ClassNotFoundException c) {
                //ignore
            }
        }
    }
}
