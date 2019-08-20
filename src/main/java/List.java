import java.util.ArrayList;

public class List {
    private ArrayList<Task> TaskList = new ArrayList<Task>();

    public void AddTask(String NewTask){
        Task NewAddedTask = new Task(NewTask);
        TaskList.add(NewAddedTask);
    }
    public void ViewTasks(){
        String indentation = "| ";
        if(TaskList.size() < 1){
            System.out.println(indentation + "No task has been added yet");
            return;
        }
        for(int i = 0; i < TaskList.size();i++)
        {
            System.out.println(indentation + (i+1) + ". " + (TaskList.get(i)).getTask());
        }
    }
}
