package duke.task;

import java.io.Serializable;

/**
 * Class to represent each task.
 */
public abstract class Task implements Serializable {
    String taskDescription;
    private boolean isDone = false;

    /**
     * Checks if the task is completed.
     *
     * @return Tick if task is done; Cross if it is not done.
     */
    String getStatusIcon(){
        return (isDone ? "Y" : "N");
    }

    /**
     * Returns the given task description.
     *
     * @return Task description.
     */
    String getTaskDescription(){
        return taskDescription;
    }

    /**
     * Marks the current task as "done"
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Abstract method that Returns the selected task details.
     *
     * @return Task details.
     */
    public abstract String getTaskDetails();
}
