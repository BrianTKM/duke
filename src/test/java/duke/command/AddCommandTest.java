package duke.command;

import duke.exception.DukeException;
import duke.parse.Parser;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    @Test
    public void addToDoTest(){
        Command commandTester = null;
        Ui uiTest = new Ui();
        TaskList taskListTest = new TaskList();
        Parser parserTester = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try{
            commandTester = parserTester.getCommand("todo hello");
        } catch(DukeException errorMessage){
            System.out.println("Error...");
        }
        commandTester.execute(uiTest,taskListTest);
        assertEquals("Got it. I've added this task: \r\n[T][N] hello\r\n",outContent.toString());
    }

    @Test
    public void addDeadlineTest(){
        Command commandTester = null;
        Ui uiTest = new Ui();
        TaskList taskListTest = new TaskList();
        Parser parserTester = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try{
            commandTester = parserTester.getCommand("deadline haha /by 12/12/3000 1300");
        } catch(DukeException errorMessage){
            System.out.println("Error...");
        }
        commandTester.execute(uiTest,taskListTest);
        assertEquals("Got it. I've added this task: \r\n[D][N] haha (by: 12 December 3000 01:00PM)\r\n"
                ,outContent.toString());
    }

    @Test
    public void addEventTest(){
        Command commandTester = null;
        Ui uiTest = new Ui();
        TaskList taskListTest = new TaskList();
        Parser parserTester = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try{
            commandTester = parserTester.getCommand("event event test /at 12/12/3000 1300 - 12/12/3000 1400");
        } catch(DukeException errorMessage){
            System.out.println("Error...");
        }
        commandTester.execute(uiTest,taskListTest);
        assertEquals("Got it. I've added this task: \r\n"
                        + "[E][N] event test (at: 12 December 3000 01:00PM - 12 December 3000 02:00PM)\r\n"
                ,outContent.toString());
    }
}
