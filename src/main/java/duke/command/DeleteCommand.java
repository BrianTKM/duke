package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class to represent a "delete" task.
 */
public class DeleteCommand extends Command{
    private final int taskNumber;

    /**
     * Creates a new "delete" command with the selected task.
     *
     * @param taskNumber The task number in the task list.
     */
    public DeleteCommand(int taskNumber){
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the "delete" command to delete the selected task from the task list.
     *
     * @param ui The user interface of duke.
     * @param tasklist The task list containing all the tasks.
     */
    public void execute(Ui ui, TaskList tasklist){
        try {
            tasklist.deleteTask(ui, taskNumber);
        } catch (DukeException errorMessage){
            ui.printMessage(errorMessage.toString());
        }
    }
}
