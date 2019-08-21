import java.util.ArrayList;

public class List {
    private ArrayList<Task> taskList = new ArrayList<Task>();

    String indentation = "| ";
    public void AddTask(String NewTask){
        Task NewAddedTask = new Task(NewTask);
        taskList.add(NewAddedTask);
    }
    public void viewTasks(){
        if(taskList.size() < 1){    //if task list empty
            System.out.println(indentation + "No task has been added yet");
            return;
        }
        for(int i = 0; i < taskList.size();i++)
        {
            System.out.println(indentation + (i+1) + ". [" + (taskList.get(i)).getStatusIcon() + "] " + (taskList.get(i)).getTask());
        }
    }
    public void finishTask(int taskNumber){
        taskNumber--;
        if(taskNumber >= 0 && taskNumber < taskList.size()){
            if(((taskList.get(taskNumber)).getStatusIcon()).equals("\u2713")){
                System.out.println(indentation + "The task has already been completed");
            }
            else{
                (taskList.get(taskNumber)).MarkAsDone();
                System.out.println(indentation + "Nice! I've marked this task as done: ");
                System.out.println(indentation + "[" + (taskList.get(taskNumber)).getStatusIcon() + "] " + (taskList.get(taskNumber)).getTask());
            }
        }
        else
        {
            System.out.println(indentation + "Invalid task number");
        }
    }
}
