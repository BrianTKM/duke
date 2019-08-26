public class Event extends Task{
    private String eventDate;

    public Event(String taskDetail, String eventDate){
        super(taskDetail);
        this.eventDate = eventDate;
    }

    @Override
    public String getDetails(){
        return "[E]" + super.getDetails() + " (at: " + this.eventDate + ")";
    }
}
