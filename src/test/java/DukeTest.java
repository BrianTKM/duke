import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.parse.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void parserTest(){
        Parser parserTest = new Parser();
        Command expectedCommand = new ListCommand();
        Command actualCommand = null;
        try {
            actualCommand = parserTest.getCommand("list");
        } catch (DukeException errorMessage){
            System.out.println("Error...");
        }
        assertEquals(actualCommand,expectedCommand);
    }
}