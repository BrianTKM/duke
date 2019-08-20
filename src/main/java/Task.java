public class Task {
    protected String TaskDetail;
    protected boolean Done;

    public Task(String TaskDetail){
        this.TaskDetail = TaskDetail;
        this.Done = false;
    }
    public String getTask(){
        return this.TaskDetail;
    }
    public String getStatusIcon() {
        return (Done ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public void MarkAsDone(){
        this.Done = true;
    }
}
