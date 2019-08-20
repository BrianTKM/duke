import java.util.ArrayList;

public class List {
    private ArrayList<Task> TaskList = new ArrayList<Task>();

    String indentation = "| ";
    public void AddTask(String NewTask){
        Task NewAddedTask = new Task(NewTask);
        TaskList.add(NewAddedTask);
    }
    public void ViewTasks(){
        if(TaskList.size() < 1){    //if task list empty
            System.out.println(indentation + "No task has been added yet");
            return;
        }
        for(int i = 0; i < TaskList.size();i++)
        {
            System.out.println(indentation + (i+1) + ". [" + (TaskList.get(i)).getStatusIcon() + "] " + (TaskList.get(i)).getTask());
        }
    }
    public void FinishTask(int TaskNumber){
        TaskNumber--;
        if(TaskNumber >= 0 && TaskNumber < TaskList.size()){
            if(((TaskList.get(TaskNumber)).getStatusIcon()).equals("\u2713")){
                System.out.println(indentation + "The task has already been completed");
            }
            else{
                (TaskList.get(TaskNumber)).MarkAsDone();
                System.out.println(indentation + "Nice! I've marked this task as done: ");
                System.out.println(indentation + "[" + (TaskList.get(TaskNumber)).getStatusIcon() + "] " + (TaskList.get(TaskNumber)).getTask());
            }
        }
        else
        {
            System.out.println(indentation + "Invalid task number");
        }
    }
}
