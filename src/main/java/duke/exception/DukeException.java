package duke.exception;

/**
 * Class that handles exceptions thrown while running duke.
 */
public class DukeException extends Exception{
    private String errorMessage;
    public DukeException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Converts the thrown exception into a string format.
     *
     * @return The error message based on the exception caught.
     */
    public String toString(){
        return "\u2639 OOPS!!! " + this.errorMessage;
    }
}
