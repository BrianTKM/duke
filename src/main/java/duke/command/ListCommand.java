package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command{
    public void execute(Ui ui, TaskList tasklist){
        tasklist.viewTask(ui);
    }
}