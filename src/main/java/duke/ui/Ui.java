package duke.ui;

import java.util.Scanner;

public class Ui {
    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    public void printMessage(String msg){
        System.out.println(msg);
    }

    public String readCommand(){
        Scanner input = new Scanner(System.in);
        String fullCommand = input.nextLine();
        return fullCommand;
    }
}
