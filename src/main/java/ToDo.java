public class ToDo extends Task{
    public ToDo(String taskDetail){
        super(taskDetail);
    }

    @Override
    public String getDetails(){
        return "[T]" + super.getDetails();
    }
}
