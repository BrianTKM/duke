package duke.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    private Date startDate;
    private Date endDate;

    public Event(String taskDescription, Date startDate, Date endDate){
        this.taskDescription = taskDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public String getTaskDetails(){
        DateFormat printDateTime = new SimpleDateFormat("dd MMMM yyyy HH:mma");
        return ("[E][" + this.getStatusIcon() + "] " + this.taskDescription
                + " (at: " + printDateTime.format(this.startDate) + " - " + printDateTime.format(this.endDate) + ")");
    }
}