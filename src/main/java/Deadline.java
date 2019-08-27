import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task{
    private Date dueDate;

    public Deadline(String taskDetail, Date dueDate){
        super(taskDetail);
        this.dueDate = dueDate;
    }

    @Override
    public String getDetails(){
        DateFormat printDateTime = new SimpleDateFormat("dd MMMM yyyy HH:mma");
        return "[D]" + super.getDetails() + " (by: " + printDateTime.format(this.dueDate) + ")";
    }
}
