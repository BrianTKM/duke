package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword){
        this.keyword = keyword;
    }

    public void execute(Ui ui, TaskList tasklist){
        tasklist.findTask(ui, keyword);
    }
}
