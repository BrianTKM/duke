package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command{
    private final int taskNumber;

    public DoneCommand(int taskNumber){
        this.taskNumber = taskNumber;
    }

    public void execute(Ui ui, TaskList tasklist){
        try {
            tasklist.finishTask(ui, taskNumber);
        } catch (DukeException errorMessage){
            ui.printMessage(errorMessage.toString());
        }
    }
}
