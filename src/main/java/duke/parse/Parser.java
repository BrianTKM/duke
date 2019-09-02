package duke.parse;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    public Command getCommand(String fullInput) throws DukeException {
        if(fullInput.isEmpty() || fullInput.isBlank()){
            throw new DukeException("Command cannot be empty");
        }
        fullInput = fullInput.replaceFirst("^\\s+", "");
        String[] subInput = fullInput.split(" ", 2);
        Command finalCommand;
        switch (subInput[0]) {
        case "list":
            if(!fullInput.trim().equals("list")){
                throw new DukeException("The \"list\" command cannot be used with other arguments.");
            }
            finalCommand = new ListCommand();
            return finalCommand;
        case "bye":
            if(!fullInput.trim().equals("bye")){
                throw new DukeException("The \"bye\" command cannot be used with other arguments.");
            }
            finalCommand = new ByeCommand();
            return finalCommand;
        case "done":
            fullInput = fullInput.substring(4);
            fullInput = fullInput.trim();
            if(fullInput.isBlank() || fullInput.isEmpty()){
                throw new DukeException("Task number cannot be empty.");
            }
            try{
                int taskNumber = Integer.parseInt(fullInput);
                finalCommand = new DoneCommand(taskNumber);
                return finalCommand;
            } catch (NumberFormatException e){
                throw new DukeException("Invalid task number.");
            }
        case "todo":
            fullInput = fullInput.substring(4);
            fullInput = fullInput.trim();
            if(fullInput.isEmpty() || fullInput.isBlank()){
                throw new DukeException("The description of a todo cannot be empty.");
            }
            finalCommand = new AddCommand(new ToDo(fullInput));
            return finalCommand;
        case "deadline":
            fullInput = fullInput.substring(8);
            fullInput = fullInput.trim();
            if (fullInput.length() == 0 || fullInput.equals("/by")) {
                throw new DukeException("The description and deadline of a task cannot be empty.");
            }
            if(fullInput.length() < 4 || fullInput.substring(fullInput.length()-4).equals(" /by")){
                throw new DukeException("The task must have a deadline.");
            }
            if(!fullInput.contains(" /by ")){
                if(fullInput.contains("/by ") && fullInput.split("/by ").length == 2
                        && fullInput.split("/by ")[0].length() < 1){
                    throw new DukeException("The task must have a description.");
                }
                throw new DukeException("The task must have a deadline,"
                        + " Command format:\n event <task description> /by <deadline>");
            }
            String[] deadlineDetails = fullInput.split(" /by ");
            String[] eventTime = deadlineDetails[deadlineDetails.length-1].split(" ");
            if(eventTime.length < 2){
                throw new DukeException("Incorrect date/time format."
                        + " Format should be \"dd/MM/yyyy HHmm\" in 24h format.");
            }
            String dateAndTime = eventTime[0] + " "
                    + deadlineDetails[deadlineDetails.length-1].substring(eventTime[0].length()).trim();
            DateFormat temp = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date dateTime;
            temp.setLenient(false);
            try{
                dateTime = temp.parse(dateAndTime);
                if(dateTime.compareTo(new Date()) <= 0){
                    throw new DukeException("Deadline entered is before now.");
                }
            } catch(ParseException e){
                throw new DukeException("Incorrect time format. Please enter a valid dd/MM/yyyy HHmm (24h) format.");
            }
            Task deadline = new Deadline(
                    fullInput.substring(0,fullInput.length()-deadlineDetails[deadlineDetails.length-1].length()-5),dateTime);
            finalCommand = new AddCommand(deadline);
            return finalCommand;
        case "event":
            fullInput = fullInput.substring(5);
            fullInput = fullInput.trim();
            if(fullInput.length() == 0 || fullInput.equals("/at")){
                throw new DukeException("The description and time of an event cannot be empty.");
            }
            if(fullInput.length() < 4 || fullInput.substring(fullInput.length()-4).equals(" /at")){
                throw new DukeException("The event must have a date and time.");
            }
            if(!fullInput.contains(" /at ")){
                if(fullInput.contains("/at ") && fullInput.split("/at ").length == 2
                        && fullInput.split("/at ")[0].length() < 1){
                    throw new DukeException("The event must have a description.");
                }
                throw new DukeException("The event must have a date and time."
                        + " Command format:\nevent <event description> /at <date and time>");
            }
            String[] eventDetails = fullInput.split(" /at ");
            String[] eventDates = eventDetails[eventDetails.length-1].split("-");
            if(eventDates.length != 2){
                throw new DukeException("Incorrect event period."
                        + " Event period format is dd/mm/yyyy HHmm - dd/mm/yyyy HHmm");
            }
            String[] eventStartTime = eventDates[0].split(" ");
            String[] eventEndTime = eventDates[1].split(" ");
            if(eventStartTime.length < 2 || eventEndTime.length < 2){
                throw new DukeException("Incorrect date/time format."
                        + " Format should be dd/MM/yyyy HHmm (24h format).");
            }
            String startDateAndTime = eventStartTime[0] + " "
                    + eventDates[0].substring(eventStartTime[0].length()).trim();
            String endDateAndTime = eventEndTime[0] + " "
                    + eventDates[1].substring(eventEndTime[0].length()).trim();
            DateFormat temp2 = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date startDateTime;
            Date endDateTime;
            temp2.setLenient(false);
            try{
                startDateTime = temp2.parse(startDateAndTime);
                endDateTime = temp2.parse(endDateAndTime);
                if(startDateTime.compareTo(new Date()) <= 0){
                    throw new DukeException("Event start date is before now.");
                }
                if(endDateTime.compareTo(startDateTime) <= 0){
                    throw new DukeException("Event end date is before start date.");
                }
            } catch(ParseException e){
                throw new DukeException("Incorrect time format."
                        + " Please enter a valid \"dd/MM//yyyy HHmm\" in 24h format.");
            }
            Task newEvent = new Event(
                    fullInput.substring(0,fullInput.length()-eventDetails[eventDetails.length-1].length()-5),startDateTime
                    ,endDateTime);
            finalCommand = new AddCommand(newEvent);
            return finalCommand;
        case "delete":
            fullInput = fullInput.substring(6);
            fullInput = fullInput.trim();
            if(fullInput.isBlank() || fullInput.isEmpty()){
                throw new DukeException("Task number cannot be empty.");
            }
            try{
                int taskNumber = Integer.parseInt(fullInput);
                finalCommand = new DeleteCommand(taskNumber);
                return finalCommand;
            } catch (NumberFormatException e){
                throw new DukeException("Invalid task number.");
            }
        case "find":
            if(subInput.length == 1){
                throw new DukeException("Cannot find empty input.");
            }
            finalCommand = new FindCommand(subInput[1]);
            return finalCommand;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
