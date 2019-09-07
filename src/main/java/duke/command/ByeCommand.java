package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class to represent a "bye" command.
 */
public class ByeCommand extends Command {

    /**
     * Creates a new "bye" command which will end duke after execution.
     */
    public ByeCommand(){
        this.exit = true;
    }

    /**
     * Executes the "bye" command to save the task list and exit duke.
     *
     * @param ui The user interface of duke.
     * @param taskList The task list to be saved on the system.
     */
    public void execute(Ui ui, TaskList taskList) {
        Storage storage = new Storage();
        storage.save(taskList, ui);
        ui.printMessage("Bye. Hope to see you again soon!");
    }
}
