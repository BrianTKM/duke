package duke.duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parse.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * The Duke class to run all duke commands.
 */
public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Parser parser;
    private Storage storage;

    /**
     * Starts running duke.
     * If hasExit is set to true, duke will end.
     */
    public void run(){
        storage = new Storage();
        parser = new Parser();
        ui = new Ui();
        tasks = new TaskList();
        tasks = storage.load(ui);
        boolean hasExit = false;
        ui.greetUser();

        while(!hasExit && ui.hasNextLine()){
            try {
                String command = ui.readCommand();
                Command c = parser.getCommand(command);
                c.execute(ui, tasks);
                hasExit = c.getExit();
            } catch (DukeException errorMessage){
                ui.printMessage(errorMessage.toString());
            }
            ui.printMessage("");
        }
    }
}