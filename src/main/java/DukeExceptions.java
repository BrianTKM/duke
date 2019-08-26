public class DukeExceptions extends Exception{
    private String errorMessage;
    public DukeExceptions(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String toString(){
        return "\u2639 OOPS!!! " + this.errorMessage;
    }
}
