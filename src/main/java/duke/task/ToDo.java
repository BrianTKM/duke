package duke.task;

public class ToDo extends Task {
    public ToDo(String taskDescription){
        this.taskDescription = taskDescription;
    }

    public String getTaskDetails(){
        return ("[T][" + this.getStatusIcon() + "] " + this.taskDescription);
    }
}
