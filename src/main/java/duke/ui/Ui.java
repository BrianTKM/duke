package duke.ui;

import java.util.Scanner;

/**
 * The user interface class.
 */
public class Ui {
    private final Scanner input = new Scanner(System.in);

    /**
     * Returns true if there is a next line.
     *
     * @return true if there is next line; false if no next line.
     */
    public boolean hasNextLine(){
        return input.hasNextLine();
    }

    /**
     * Prints the duke logo and greet the user.
     */
    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    /**
     * Prints the expected message.
     *
     * @param msg message to print.
     */
    public void printMessage(String msg){
        System.out.println(msg);
    }

    /**
     * Reads the user input.
     *
     * @return User input.
     */
    public String readCommand(){
        String fullCommand = input.nextLine();
        return fullCommand;
    }
}
