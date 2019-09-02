package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command{
    private final Task task;

    public AddCommand(Task task){
        this.task = task;
    }
    public void execute(Ui ui, TaskList tasklist){
        tasklist.addTask(task);
        ui.printMessage("Got it. I've added this task: ");
        ui.printMessage(task.getTaskDetails());
    }
}
