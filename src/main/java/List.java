import java.util.ArrayList;

public class List {
    private ArrayList<Task> taskList = new ArrayList<Task>();

    public void addTask(String newTask){
        String[] addTaskDetails = newTask.split(" ");
        if(addTaskDetails[0].equals("deadline")){
            String taskDescription = newTask.substring(9);
            String[] splitTaskDescription = taskDescription.split(" /by ");
            Task newDeadline = new Deadline(splitTaskDescription[0],splitTaskDescription[1]);
            taskList.add(newDeadline);
        } else if(addTaskDetails[0].equals("event"))
        {
            String taskDescription = newTask.substring(6);
            String[] splitTaskDescription = taskDescription.split(" /at ");
            Task newEvent = new Event(splitTaskDescription[0],splitTaskDescription[1]);
            taskList.add(newEvent);
        } else if(addTaskDetails[0].equals("todo")){
            String taskDescription = newTask.substring(5);
            Task newToDo = new ToDo(taskDescription);
            taskList.add(newToDo);
        } else{
            return;
        }
        System.out.println("Got it. I've added this task:\n"
                + taskList.get(taskList.size() - 1).getDetails());
        if(Task.totalTasks == 1){
            System.out.println("Now you have " + Task.totalTasks + " task in the list.");
        } else {
            System.out.println("Now you have " + Task.totalTasks + " tasks in the list.");
        }
    }
    public void viewTasks(){
        if(taskList.size() < 1){
            //if task list empty
            System.out.println("No task has been added yet");
            return;
        }
        for(int i = 0; i < taskList.size();i++)
        {
            System.out.println((i + 1) + ". " + (taskList.get(i)).getDetails());
        }
    }
    public void finishTask(int taskNumber){
        taskNumber--;
        if(Task.totalTasks == 0){
            System.out.println("There are no tasks in the list");
            return;
        }
        if(taskNumber >= 0 && taskNumber < taskList.size()){
            if(((taskList.get(taskNumber)).getStatusIcon()).equals("Y")){
                System.out.println("The task has already been completed");
            }
            else{
                (taskList.get(taskNumber)).markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println((taskList.get(taskNumber)).getDetails());
            }
        }
        else
        {
            System.out.println("Invalid task number");
        }
    }
}
