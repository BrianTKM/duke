package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class to represent a "find" command.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a new "find" command with the given keyword.
     *
     * @param keyword Keyword to search in the task list.
     */
    public FindCommand(String keyword){
        this.keyword = keyword;
    }

    /**
     * Executes the "find" command to search the task list with the keyword.
     *
     * @param ui The user interface of duke.
     * @param tasklist The task list which will be searched.
     */
    public void execute(Ui ui, TaskList tasklist){
        tasklist.findTask(ui, keyword);
    }
}
