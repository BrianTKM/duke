import java.io.Serializable;

public class Task implements Serializable {
    static int totalTasks = 0;
    private String taskDetail;
    private boolean isDone;

    public Task(String taskDetail){
        this.taskDetail = taskDetail;
        this.isDone = false;
        totalTasks += 1;
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
