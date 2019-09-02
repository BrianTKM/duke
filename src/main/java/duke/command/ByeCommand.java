package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand(){
        this.exit = true;
    }

    public void execute(Ui ui, TaskList taskList) {
        Storage storage = new Storage();
        storage.save(taskList, ui);
        ui.printMessage("Bye. Hope to see you again soon!");
    }
}
