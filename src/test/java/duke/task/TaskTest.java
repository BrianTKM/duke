package duke.task;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void newToDoTest(){
        ToDo toDoTest = new ToDo("hello");
        assertEquals("[T][N] hello",toDoTest.getTaskDetails());
    }


    @Test
    public void newDeadlineTest(){
        DateFormat temp = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date dateTime;
        temp.setLenient(false);
        Deadline deadlineTest = null;
        try{
            dateTime = temp.parse("12/12/9999 1200");
            deadlineTest = new Deadline("deadline test", dateTime);
        } catch(ParseException e){
            System.out.println("Error...");
        }
        assertEquals("[D][N] deadline test (by: 12 December 9999 12:00PM)",deadlineTest.getTaskDetails());
    }


    @Test
    public void newEventTest(){
        DateFormat temp = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date startDateTime;
        Date endDateTime;
        temp.setLenient(false);
        Event eventTest = null;
        try{
            startDateTime = temp.parse("12/12/2019 1200");
            endDateTime = temp.parse("12/12/2019 1300");
            eventTest = new Event("event test", startDateTime, endDateTime);
        } catch(ParseException e){
            System.out.println("Error...");
        }
        assertEquals("[E][N] event test (at: 12 December 2019 12:00PM"
                + " - 12 December 2019 01:00PM)",eventTest.getTaskDetails());
    }
}
