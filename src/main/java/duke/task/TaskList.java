package duke.task;

import duke.exception.DukeException;
import duke.ui.Ui;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class representing the task list in duke.
 */
public class TaskList implements Serializable {
    private ArrayList<Task> taskList = new ArrayList<Task>();

    /**
     * Adds a new task into the task list.
     *
     * @param newTask New task to be added into the task list.
     */
    public void addTask(Task newTask){
        taskList.add(newTask);
    }

    /**
     * Displays a list of all the tasks in the task list.
     *
     * @param ui The user interface of duke.
     */
    public void viewTask(Ui ui){
        if(taskList.size() == 0){
            ui.printMessage("There are 0 tasks in the task list.");
        }
        for(int i = 0; i < taskList.size(); i++){
            ui.printMessage(i+1 + ". " + taskList.get(i).getTaskDetails());
        }
    }

    /**
     * Marks the selected task as done.
     *
     * @param ui The user interface of duke.
     * @param taskNumber The selected task number in the task list.
     * @throws DukeException If task number is out of range or if task is already completed.
     */
    public void finishTask(Ui ui, int taskNumber) throws DukeException {
        if(taskList.size() == 0){
            ui.printMessage("There are 0 tasks in the list.");
            return;
        }
        if(taskNumber > taskList.size() || taskNumber <= 0){
            throw new DukeException("Task number entered is out of range.");
        }
        taskNumber--;
        if(taskList.get(taskNumber).getStatusIcon().equals("\u2713")){
            throw new DukeException("Task has already been completed.");
        }
        taskList.get(taskNumber).markAsDone();
        ui.printMessage("Nice! I've marked this task as done: ");
        ui.printMessage(taskList.get(taskNumber).getTaskDetails());
    }

    /**
     * Finds all tasks contining the selected keyword.
     *
     * @param ui The user interface of duke.
     * @param keyword The keyword to be used for searching.
     */
    public void findTask(Ui ui, String keyword){
        int counter = 0;
        if(taskList.size() == 0){
            ui.printMessage("There are 0 tasks in the task list.");
            return;
        }
        ui.printMessage("Finding all tasks with \"" + keyword + "\" in task description:");
        for(int i = 0; i < taskList.size(); i++){
            if(taskList.get(i).getTaskDescription().toLowerCase().contains(keyword.toLowerCase())) {
                if (counter == 0) {
                    ui.printMessage("Here are the matching tasks in your list:");
                }
                ui.printMessage((counter+1) + taskList.get(i).getTaskDetails());
                counter++;
            }
        }
        if(counter == 0){
            ui.printMessage("There are 0 tasks that matches your keyword");
        }
    }

    /**
     * Deletes the selected task from the task list.
     *
     * @param ui The user interface of duke.
     * @param taskNumber The task number in the task list.
     * @throws DukeException If the task number is out of range.
     */
    public void deleteTask(Ui ui, int taskNumber) throws DukeException{
        if(taskList.size() == 0){
            ui.printMessage("There are 0 tasks in the list.");
        }
        if(taskNumber > taskList.size() || taskNumber <= 0){
            throw new DukeException("Task number entered is out of range.");
        }
        taskNumber--;
        Task temp = taskList.get(taskNumber);
        taskList.remove(taskNumber);
        ui.printMessage("Noted. I've removed this task: ");
        ui.printMessage(temp.getTaskDetails());
        temp = null;
        if(taskList.size() == 1){
            ui.printMessage("Now you have " + taskList.size() + " task in the list.");
        } else {
            ui.printMessage("Now you have " + taskList.size() + " tasks in the list.");
        }
    }
}
