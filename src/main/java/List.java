import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
            throw new DukeExceptions("The task must have a deadline."
                    + " Command format:\nevent <task description> /by <deadline>");
        }
        String[] deadlineDetails = details.split(" /by ");
        String[] eventTime = deadlineDetails[deadlineDetails.length-1].split(" ");
        if(eventTime.length < 2){
            throw new DukeExceptions("Incorrect date/time format."
                    + " Format should be dd/MM/yyyy HHmm (24h format).");
        }
        String dateAndTime = eventTime[0] + " "
                + deadlineDetails[deadlineDetails.length-1].substring(eventTime[0].length()).trim();
        DateFormat temp = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date dateTime;
        temp.setLenient(false);
        try{
            dateTime = temp.parse(dateAndTime);
            if(dateTime.compareTo(new Date()) <= 0){
                throw new DukeExceptions("Deadline entered is before now.");
            }
        } catch (ParseException e){
            throw new DukeExceptions("Incorrect time format. Please enter a valid dd/MM/yyyy HHmm(24h) format.");
        }
        Task newDeadline = new Deadline(
                details.substring(0,details.length()-deadlineDetails[deadlineDetails.length-1].length()-5),dateTime);
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
        if(!details.contains(" /at ")) {
            if (details.contains("/at ") && details.split("/at ").length == 2
                    && details.split("/at ")[0].length() < 1) {
                throw new DukeExceptions("The event must have a description.");
            }
            throw new DukeExceptions("The event must have a date and time."
                    + " Command format:\nevent <event description> /at <date and time>");
        }
        String[] eventDetails = details.split(" /at ");
        String[] eventDates = eventDetails[eventDetails.length-1].split("-");
        if(eventDates.length != 2){
            throw new DukeExceptions("Incorrect event period."
                    + " Event period format is dd/mm/yyyy HHmm - dd/mm/yyyy HHmm");
        }
        String[] eventStartTime = eventDates[0].split(" ");
        String[] eventEndTime = eventDates[1].split(" ");
        if(eventStartTime.length < 2 || eventEndTime.length < 2){
            throw new DukeExceptions("Incorrect date/time format."
                    + " Format should be dd/MM/yyyy HHmm (24h format).");
        }
        String startDateAndTime = eventStartTime[0] + " "
                + eventDates[0].substring(eventStartTime[0].length()).trim();
        String endDateAndTime = eventEndTime[0] + " "
                + eventDates[1].substring(eventEndTime[0].length()).trim();
        DateFormat temp = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date startDateTime;
        Date endDateTime;
        temp.setLenient(false);
        try{
            startDateTime = temp.parse(startDateAndTime);
            endDateTime = temp.parse(endDateAndTime);
            if(startDateTime.compareTo(new Date()) <= 0){
                throw new DukeExceptions("Event entered is before now.");
            }
            if(endDateTime.compareTo(startDateTime) <= 0){
                throw new DukeExceptions("Event end date is before start date");
            }
        } catch (ParseException e){
            throw new DukeExceptions("Incorrect time format."
                    + " Please enter a valid dd/MM/yyyy HHmm(24h) format.");
        }
        Task newEvent = new Event(
                details.substring(0,details.length()-eventDetails[eventDetails.length-1].length()-5),startDateTime
                ,endDateTime);
        taskList.add(newEvent);
        successfulAddTask();
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
