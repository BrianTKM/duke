package duke.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class representing a deadline task.
 */
public class Deadline extends Task {
    private Date deadline;

    /**
     * Creates a new deadline with the given description and deadline.
     *
     * @param taskDescription Description of the task.
     * @param deadline Deadline of when the task should be completed by.
     */
    public Deadline(String taskDescription, Date deadline){
        this.taskDescription = taskDescription;
        this.deadline = deadline;
    }

    /**
     * Returns the deadline description and due date.
     *
     * @return Deadline description and deadline.
     */
    public String getTaskDetails(){
        DateFormat printDateTime = new SimpleDateFormat("dd MMMM yyyy HH:mma");
        return ("[D][" + this.getStatusIcon() + "] " + this.taskDescription
                + " (by: " + printDateTime.format(this.deadline) + ")");
    }
}
