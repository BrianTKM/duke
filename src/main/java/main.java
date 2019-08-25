import java.util.Scanner;

public class main{
    public static void main(String[] args){
        Duke duke = new Duke();
        Scanner input = new Scanner(System.in);
        int endProg = 0;
        while(endProg == 0 && input.hasNextLine()){
            String command = input.nextLine();
            command = command.trim();
            endProg = duke.executeCommand(command);
        }
    }
}