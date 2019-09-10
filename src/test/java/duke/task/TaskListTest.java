package duke.task;

import duke.exception.DukeException;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void emptyListTest(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        TaskList testTaskList = new TaskList();
        try {
            testTaskList.viewTask(ui);
            testTaskList.finishTask(ui, 1);
            testTaskList.deleteTask(ui,1);
            testTaskList.findTask(ui,"test");
            assertEquals("There are 0 tasks in the task list.\r\n"
                    + "There are 0 tasks in the list.\r\nThere are 0 tasks in the list.\r\n"
                    + "There are 0 tasks in the task list.\r\n",outContent.toString());
        } catch(DukeException errorMessage){
            System.out.println("Error...");
        }
    }

    @Test
    public void populatedListTest(){
        TaskList testTaskList = new TaskList();
        Ui ui = new Ui();
        ToDo newTodo = new ToDo("hello");
        testTaskList.addTask(newTodo);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            testTaskList.viewTask(ui);
            testTaskList.finishTask(ui, 1);
            testTaskList.findTask(ui,"hel");
            testTaskList.findTask(ui,"gg");
            testTaskList.deleteTask(ui,1);
            assertEquals("1. [T][N] hello\r\n"
                    + "Nice! I've marked this task as done: \r\n[T][Y] hello\r\n"
                    + "Finding all tasks with \"hel\" in task description:\r\n"
                    + "Here are the matching tasks in your list:\r\n1. [T][Y] hello\r\n"
                    + "Finding all tasks with \"gg\" in task description:\r\n"
                    + "There are 0 tasks that matches your keyword\r\n"
                    + "Noted. I've removed this task: \r\n[T][Y] hello\r\n"
                    + "Now you have 0 tasks in the list.\r\n",outContent.toString());
        } catch(DukeException errorMessage){
            System.out.println("Error...");
        }
    }
}
