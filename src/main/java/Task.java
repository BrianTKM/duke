public class Task {
    static int totalTasks = 0;
    protected String taskDetail;
    protected boolean isDone;

    public Task(String taskDetail){
        this.taskDetail = taskDetail;
        this.isDone = false;
        totalTasks += 1;
    }
    public String getTask(){
        return this.taskDetail;
    }
    public String getStatusIcon() {
        return (isDone ? "Y" : "N");
        //return Yes or No
    }
    public String getDetails(){
        return ("[" + this.getStatusIcon() + "] " + this.taskDetail);
    }
    public void markAsDone(){
        this.isDone = true;
    }
}
