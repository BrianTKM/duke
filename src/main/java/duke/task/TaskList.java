package duke.task;

import duke.exception.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<Task>();

    public void refreshTotalTask(){
        Task.totalTask = taskList.size();
    }

    public ArrayList<Task> getTaskList(){
        return taskList;
    }

    public void setTaskList(ArrayList<Task> loadedList){
        this.taskList = loadedList;
    }

    public void addTask(Task newTask){
        taskList.add(newTask);
        Task.totalTask++;
    }

    public void viewTask(Ui ui){
        if(Task.totalTask == 0){
            ui.printMessage("There are 0 tasks in the task list.");
        }
        for(int i = 0; i < Task.totalTask; i++){
            ui.printMessage(i+1 + ". " + taskList.get(i).getTaskDetails());
        }
    }

    public void finishTask(Ui ui, int taskNumber) throws DukeException {
        if(Task.totalTask == 0){
            ui.printMessage("There are 0 tasks in the list.");
            return;
        }
        if(taskNumber > Task.totalTask || taskNumber <= 0){
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
        if(Task.totalTask == 0){
            ui.printMessage("There are 0 tasks in the task list.");
            return;
        }
        ui.printMessage("Finding all tasks with \"" + keyword + "\" in task description:");
        for(int i = 0; i < Task.totalTask; i++){
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
        if(Task.totalTask == 0){
            ui.printMessage("There are 0 tasks in the list.");
        }
        if(taskNumber > Task.totalTask || taskNumber <= 0){
            throw new DukeException("Task number entered is out of range.");
        }
        taskNumber--;
        Task temp = taskList.get(taskNumber);
        taskList.remove(taskNumber);
        ui.printMessage("Noted. I've removed this task: ");
        ui.printMessage(temp.getTaskDetails());
        temp = null;
        Task.totalTask--;
        if(Task.totalTask == 1){
            ui.printMessage("Now you have " + Task.totalTask + " task in the list.");
        } else {
            ui.printMessage("Now you have " + Task.totalTask + " tasks in the list.");
        }
    }
}
