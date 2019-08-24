public class Event extends Task{
    protected String eventDate;

    public Event(String taskDetail, String eventDate){
        super(taskDetail);
        this.eventDate = eventDate;
    }

    @Override
    public String getDetails(){
        return "[T]" + super.getDetails();
    }
}
