import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    private String description;
    private boolean isCompleted;
    LocalDate selectedDate;
    LocalTime selectedTime;
    private int priority;

    public Task(String description, int priority) {
        this.description = description;
        this.isCompleted = false;
        this.priority = priority;
    }

    public void markComplete() {
        this.isCompleted = true;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public int getPriority(){
        return priority;
    }

    public LocalDate getSelectedDate(){
        return selectedDate;
    }

    public LocalTime getSelectedTime(){
        return selectedTime;
    }

    public String formatter(){
        return String.format("%b,%s,%s,%s,%s", 
                isCompleted, 
                priority, 
                escapeForCsv(description), 
                selectedDate, 
                selectedTime);
    }

    private String escapeForCsv(String value) {
        if (value == null) {
            return "";
        }
        
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            String escapedQuotes = value.replace("\"", "\"\"");
            return "\"" + escapedQuotes + "\"";
        }
        
        return value;
    }

    @Override
    public String toString() {
        return (isCompleted ? "\033[1;32m[X]\033[0m " : "\033[1;31m[ ]\033[0m ") 
             + "\033[1;35m[Priority " + priority + "]\033[0m " 
             + "\033[1m" + description + "\033[0m " 
             + "\033[90mon " + selectedDate + " at " + selectedTime + "\033[0m";
    }
}