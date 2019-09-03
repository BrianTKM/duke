package duke.task;

import java.io.Serializable;

public abstract class Task implements Serializable {
    String taskDescription;
    private boolean isDone = false;

    String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718");
    }

    String getTaskDescription(){
        return taskDescription;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public abstract String getTaskDetails();
}
