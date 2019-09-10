package duke.parser;

import duke.exception.DukeException;
import duke.parse.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void deadlineNoDescription(){
        Parser parserTest = new Parser();
        DukeException errorMessage = assertThrows(DukeException.class,()
                -> parserTest.getCommand("deadline"));
        assertTrue(errorMessage.toString().contains("The description and deadline of a task cannot be empty."));
    }

    @Test
    public void deadlineNoDeadline(){
        Parser parserTest = new Parser();
        DukeException errorMessage = assertThrows(DukeException.class,()
                -> parserTest.getCommand("deadline hah"));
        assertTrue(errorMessage.toString().contains("The task must have a deadline."
                + " Command format:\n deadline <task description> /by <deadline>"));
        errorMessage = assertThrows(DukeException.class,()
                -> parserTest.getCommand("deadline ff /by"));
        assertTrue(errorMessage.toString().contains("The task must have a deadline."
                + " Command format:\n deadline <task description> /by <deadline>"));
    }

    @Test
    public void deadlineOnlyDate(){
        Parser parserTest = new Parser();
        DukeException errorMessage = assertThrows(DukeException.class,()
                -> parserTest.getCommand("deadline haha /by 12/12/2019"));
        assertTrue(errorMessage.toString().contains("Incorrect date/time format."
                + " Format should be \"dd/MM/yyyy HHmm\" in 24h format."));
    }

    @Test
    public void deadlinePastDate(){
        Parser parserTest = new Parser();
        DukeException errorMessage = assertThrows(DukeException.class,()
                -> parserTest.getCommand("deadline haha /by 12/12/1996 1200"));
        assertTrue(errorMessage.toString().contains("Deadline entered is before now."));
    }

    @Test
    public void deadlineWrongDateFormat(){
        Parser parserTest = new Parser();
        DukeException errorMessage = assertThrows(DukeException.class,()
                -> parserTest.getCommand("deadline haha /by abc ggg"));
        assertTrue(errorMessage.toString().contains("Incorrect time format."
                + " Please enter a valid dd/MM/yyyy HHmm (24h) format."));
    }

    @Test
    public void extraArgument(){
        Parser parserTest = new Parser();
        DukeException errorMessage = assertThrows(DukeException.class,() -> parserTest.getCommand("bye g"));
        assertTrue(errorMessage.toString().contains("The \"bye\" command cannot be used with other arguments."));
        errorMessage = assertThrows(DukeException.class,() -> parserTest.getCommand("list g"));
        assertTrue(errorMessage.toString().contains("The \"list\" command cannot be used with other arguments."));
    }

    @Test
    public void emptyCommand(){
        Parser parserTest = new Parser();
        DukeException errorMessage = assertThrows(DukeException.class,() -> parserTest.getCommand(""));
        assertTrue(errorMessage.toString().contains("Command cannot be empty"));
    }

    @Test
    public void randomCommand(){
        Parser parserTest = new Parser();
        DukeException errorMessage = assertThrows(DukeException.class,() -> parserTest.getCommand("blah"));
        assertTrue(errorMessage.toString().contains("I'm sorry, but I don't know what that means :-("));
    }

    @Test
    public void toDoNoDescription(){
        Parser parserTest = new Parser();
        DukeException errorMessage = assertThrows(DukeException.class,() -> parserTest.getCommand("todo"));
        assertTrue(errorMessage.toString().contains("The description of a todo cannot be empty."));
    }

    @Test
    public void eventNoDetails(){
        Parser parserTest = new Parser();
        DukeException errorMessage = assertThrows(DukeException.class,() -> parserTest.getCommand("event"));
        assertTrue(errorMessage.toString().contains("The description and time of an event cannot be empty."));
    }

    @Test
    public void eventNoDate(){
        Parser parserTest = new Parser();
        DukeException errorMessage = assertThrows(DukeException.class,()
                -> parserTest.getCommand("event haha /at"));
        assertTrue(errorMessage.toString().contains("The event must have a date and time."));
        errorMessage = assertThrows(DukeException.class,() -> parserTest.getCommand("event ha"));
        assertTrue(errorMessage.toString().contains("The event must have a date and time."));
    }

    @Test
    public void eventNoDescription(){
        Parser parserTest = new Parser();
        DukeException errorMessage = assertThrows(DukeException.class,()
                -> parserTest.getCommand("event /at 12/12/2019 1200 - 12/12/2019 1300"));
        assertTrue(errorMessage.toString().contains("The event must have a description."));
    }

    @Test
    public void eventWrongDateFormat(){
        Parser parserTest = new Parser();
        DukeException errorMessage = assertThrows(DukeException.class,()
                -> parserTest.getCommand("event ha /at 12/12/2019 1200 12/12/2019 1300"));
        assertTrue(errorMessage.toString().contains("Incorrect event date format."
                + " Event period format is dd/mm/yyyy HHmm - dd/mm/yyyy HHmm"));
    }

    @Test
    public void eventNoAt(){
        Parser parserTest = new Parser();
        DukeException errorMessage = assertThrows(DukeException.class,()
                -> parserTest.getCommand("event ha 12/12/2019 1200 - 12/12/2019 1300"));
        assertTrue(errorMessage.toString().contains("The event must have a date and time."
                + " Command format:\nevent <event description> /at <date and time>"));
    }

    @Test
    public void eventNoDash(){
        Parser parserTest = new Parser();
        DukeException errorMessage = assertThrows(DukeException.class,()
                -> parserTest.getCommand("event ha /at 12/12/2019 1200 12/12/2019 1300"));
        assertTrue(errorMessage.toString().contains("Incorrect event date format."
                + " Event period format is dd/mm/yyyy HHmm - dd/mm/yyyy HHmm"));
    }

    @Test
    public void eventPastStartDate(){
        Parser parserTest = new Parser();
        DukeException errorMessage = assertThrows(DukeException.class,()
                -> parserTest.getCommand("event ha /at 12/12/2018 1200 - 12/12/2019 1300"));
        assertTrue(errorMessage.toString().contains("Event start date is before now."));
    }

    @Test
    public void eventEndBeforeStart(){
        Parser parserTest = new Parser();
        DukeException errorMessage = assertThrows(DukeException.class,()
                -> parserTest.getCommand("event ha /at 12/12/2019 1200 - 12/12/2019 1100"));
        assertTrue(errorMessage.toString().contains("Event end date is before start date."));
    }

    @Test
    public void eventIncorrectDateFormat(){
        Parser parserTest = new Parser();
        DukeException errorMessage = assertThrows(DukeException.class,()
                -> parserTest.getCommand("event ha /at blah blah - 12/12/2019 1100"));
        assertTrue(errorMessage.toString().contains("Incorrect time format."
                + " Please enter a valid \"dd/MM//yyyy HHmm\" in 24h format."));
        errorMessage = assertThrows(DukeException.class,()
                -> parserTest.getCommand("event ha /at 12/12/2019 - 13/12/2019"));
        assertTrue(errorMessage.toString().contains("Incorrect date/time format."
                + " Format should be dd/MM/yyyy HHmm (24h format)."));
    }
}
