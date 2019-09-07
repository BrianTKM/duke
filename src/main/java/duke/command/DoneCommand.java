package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class to represent a "done" task.
 */
public class DoneCommand extends Command{
    private final int taskNumber;

    /**
     * Creates a new "done" command with the selected task.
     *
     * @param taskNumber The task number in the task list.
     */
    public DoneCommand(int taskNumber){
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the "done" command to mark the selected task as done.
     *
     * @param ui The user interface of duke.
     * @param tasklist The task list containing all the tasks.
     */
    public void execute(Ui ui, TaskList tasklist){
        try {
            tasklist.finishTask(ui, taskNumber);
        } catch (DukeException errorMessage){
            ui.printMessage(errorMessage.toString());
        }
    }
}
