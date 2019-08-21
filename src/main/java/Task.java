public class Task {
    protected String taskDetail;
    protected boolean isDone;

    public Task(String TaskDetail){
        this.taskDetail = TaskDetail;
        this.isDone = false;
    }
    public String getTask(){
        return this.taskDetail;
    }
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public void markAsDone(){
        this.isDone = true;
    }
}
