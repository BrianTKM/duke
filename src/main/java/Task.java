public class Task {
    protected String taskDetail;
    protected boolean taskStatus;

    public Task(String TaskDetail){
        this.taskDetail = TaskDetail;
        this.taskStatus = false;
    }
    public String getTask(){
        return this.taskDetail;
    }
    public String getStatusIcon() {
        return (taskStatus ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public void MarkAsDone(){
        this.taskStatus = true;
    }
}
