package duke.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    private Date deadline;

    public Deadline(String taskDescription, Date deadline){
        this.taskDescription = taskDescription;
        this.deadline = deadline;
    }
    public String getTaskDetails(){
        DateFormat printDateTime = new SimpleDateFormat("dd MMMM yyyy HH:mma");
        return ("[D][" + this.getStatusIcon() + "] " + this.taskDescription
                + " (by: " + printDateTime.format(this.deadline) + ")");
    }
}
