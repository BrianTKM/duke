package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    protected boolean exit = false;
    public boolean getExit(){
        return exit;
    }
    public abstract void execute(Ui ui, TaskList taskList);
}
