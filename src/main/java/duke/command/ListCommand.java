package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class to represent a "list" command.
 */
public class ListCommand extends Command{
    /**
     * Executes the command on the given list to view all tasks.
     *
     * @param ui The user interface displaying duke to the user.
     * @param tasklist The task list containing all tasks.
     */
    public void execute(Ui ui, TaskList tasklist){
        tasklist.viewTask(ui);
    }
}