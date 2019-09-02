package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command{
    private final int taskNumber;

    public DeleteCommand(int taskNumber){
        this.taskNumber = taskNumber;
    }

    public void execute(Ui ui, TaskList tasklist){
        try {
            tasklist.deleteTask(ui, taskNumber);
        } catch (DukeException errorMessage){
            ui.printMessage(errorMessage.toString());
        }
    }
}
