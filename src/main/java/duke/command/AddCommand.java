package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class to represent a new command to add a new task.
 */
public class AddCommand extends Command{
    private final Task task;

    /**
     * Creates a new command with the selected task.
     *
     * @param task The new task to be added into the task list.
     */
    public AddCommand(Task task){
        this.task = task;
    }

    /**
     * Executes the command to add a new task into the task list.
     *
     * @param ui The user interface of duke.
     * @param tasklist The task list which the new task will be added to.
     */
    public void execute(Ui ui, TaskList tasklist){
        tasklist.addTask(task);
        ui.printMessage("Got it. I've added this task: ");
        ui.printMessage(task.getTaskDetails());
    }
}
