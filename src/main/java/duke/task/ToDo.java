package duke.task;

/**
 * Class representing a todo task.
 */
public class ToDo extends Task {
    /**
     * Creates a new todo task with the given description.
     *
     * @param taskDescription Description of the task.
     */
    public ToDo(String taskDescription){
        this.taskDescription = taskDescription;
    }

    /**
     * Returns the todo description of the task.
     *
     * @return Todo description.
     */
    public String getTaskDetails(){
        return ("[T][" + this.getStatusIcon() + "] " + this.taskDescription);
    }
}
