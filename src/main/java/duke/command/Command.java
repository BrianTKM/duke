package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Abstract class representing each command.
 */
public abstract class Command {
    protected boolean exit = false;

    /**
     * Determines if duke will exit.
     *
     * @return If duke will exit.
     */
    public boolean getExit(){
        return exit;
    }

    /**
     * Abstract method that executes the command on the given user interface and task list.
     *
     * @param ui The user interface of duke.
     * @param taskList The task list which the command will execute on.
     */
    public abstract void execute(Ui ui, TaskList taskList);
}
