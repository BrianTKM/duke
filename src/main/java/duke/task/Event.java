package duke.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class representing an event task.
 */
public class Event extends Task {
    private Date startDate;
    private Date endDate;

    /**
     * Creates a new event task with the given description and date-time.
     *
     * @param taskDescription Description of the event.
     * @param startDate The starting date and time of event.
     * @param endDate The ending date and time of event.
     */
    public Event(String taskDescription, Date startDate, Date endDate){
        this.taskDescription = taskDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the event details.
     *
     * @return Event description, start date-time and end date-time.
     */
    public String getTaskDetails(){
        DateFormat printDateTime = new SimpleDateFormat("dd MMMM yyyy hh:mma");
        return ("[E][" + this.getStatusIcon() + "] " + this.taskDescription
                + " (at: " + printDateTime.format(this.startDate) + " - " + printDateTime.format(this.endDate) + ")");
    }
}