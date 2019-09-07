package duke.storage;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Class that handles the saving and loading of the task list on the system.
 */
public class Storage {
    private static final String FILE_PATH = "data.txt";

    /**
     * Loads a task list from the system.
     *
     * @param ui The user interface of duke.
     * @return The the loaded task list, if any, from the system.
     */
    public TaskList load(Ui ui){
        File dataFile = new File(FILE_PATH);
        TaskList taskList = new TaskList();
        if(dataFile.exists()) {
            try {
                FileInputStream fis = new FileInputStream(FILE_PATH);
                ObjectInputStream ois = new ObjectInputStream(fis);
                taskList = ((TaskList) ois.readObject());
                //taskList.refreshTotalTask();
                ois.close();
                fis.close();
                return taskList;
            } catch (IOException ioe) {
                ui.printMessage("\u2639 OOPS!!! We are unable to load your task list :-(");
            } catch (ClassNotFoundException c) {
                //ignore
            }
        }
        ui.printMessage("No saved list found. Creating a new list...");
        return taskList;
    }

    /**
     * Saves the current task list onto the system.
     *
     * @param taskList The task list, containing all the tasks, to be saved on the system.
     * @param ui The user interface of duke.
     */
    public void save(TaskList taskList, Ui ui){
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskList);
            oos.close();
            fos.close();
        } catch (IOException ioe){
            ui.printMessage("\u2639 OOPS!!! We are unable to save your task list :-(");
        }
    }
}
