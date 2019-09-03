package duke.task;

import duke.exception.DukeException;
import duke.ui.Ui;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> taskList = new ArrayList<Task>();

    public void addTask(Task newTask){
        taskList.add(newTask);
    }

    public void viewTask(Ui ui){
        if(taskList.size() == 0){
            ui.printMessage("There are 0 tasks in the task list.");
        }
        for(int i = 0; i < taskList.size(); i++){
            ui.printMessage(i+1 + ". " + taskList.get(i).getTaskDetails());
        }
    }

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
