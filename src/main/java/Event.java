import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task{
    private Date eventStartDate;
    private Date eventEndDate;

    public Event(String taskDetail, Date eventStartDate, Date eventEndDate){
        super(taskDetail);
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
    }

    @Override
    public String getDetails(){
        DateFormat printDateTime = new SimpleDateFormat("dd MMMM yyyy HH:mma");
        return "[E]" + super.getDetails() + " (at: " + printDateTime.format(this.eventStartDate) + " - "
                + printDateTime.format(this.eventEndDate)+ ")";
    }
}
