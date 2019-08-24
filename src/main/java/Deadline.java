public class Deadline extends Task{
    protected String dueDate;

    public Deadline(String taskDetail, String dueDate){
        super(taskDetail);
        this.dueDate = dueDate;
    }

    @Override
    public String getDetails(){
        return "[D]" + super.getDetails() + " (by: " + this.dueDate + ")";
    }
}
