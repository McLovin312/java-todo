import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.PriorityQueue;

class Todo{
    public static void main(String args[]){
        List<Task> todo = new ArrayList<>();

        Scanner scnr = new Scanner(System.in);

        while(true){
            System.out.println("---- To Do List ----");
            System.out.println("1. Create Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");

            System.out.println("Select an option: ");
            int num = scnr.nextInt();

            if (num == 1){
                scnr.nextLine();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDate selectedDate = null; 
                LocalTime selectedTime = null; 
                System.out.print("When will this task be due? (Format: YYYY-MM-DD): ");
                while(selectedDate == null){
                    String input = scnr.nextLine();
                    try{
                        selectedDate = LocalDate.parse(input, dateFormatter);
                        System.out.println("Selected date: " + selectedDate);
                    }
                    catch (DateTimeParseException e){
                        System.out.println("Invalid format. Please try again (Format: YYYY-MM-DD)");
                    }
                }
                System.out.print("What time? (Format: HH:mm:ss): ");
                while(selectedTime == null){
                    String timeInput = scnr.nextLine();
                    try{
                        selectedTime = LocalTime.parse(timeInput, timeFormatter);
                        System.out.println("Selected time: " + selectedTime);
                    }
                    catch (DateTimeParseException e){
                        System.out.println("Invalid format. Please try again (Format: HH:mm:ss)");
                    }
                }

                scnr.nextLine();

                System.out.print("What priority? (1 - 5): ");
                int priorityLevel = scnr.nextInt();
                scnr.nextLine();

                System.out.print("What task would you like to add?: ");
                String taskDesc = scnr.nextLine();
                Task myTask = new Task(taskDesc, priorityLevel);

                myTask.selectedDate = selectedDate;
                myTask.selectedTime = selectedTime;

                todo.add(myTask);
                
                System.out.println("Task added! Due on " + selectedDate + " at " + selectedTime);
                System.out.println();
            }

            else if (num == 2) {
                System.out.println();
                System.out.println("---Tasks---");
                System.out.println("Total Tasks: " + todo.size());

                todo.sort(Comparator.comparing(Task::getPriority).thenComparing(Task::getSelectedDate).thenComparing(Task::getSelectedTime));


                for (int i = 0; i < todo.size(); i++){
                    System.out.println(todo.get(i).toString());

                System.out.println();

                }
            }

            else if (num == 3){
                System.out.println("Which task would you like to complete? (Input number): ");
                for (int i = 0; i < todo.size(); i++){
                    System.out.println(i + 1 + " " + todo.get(i).toString());
                }
                int selectedTask = scnr.nextInt();
                selectedTask -= 1;
                todo.get(selectedTask).markComplete();
                
            }

            else if (num == 4){
                System.out.println("Select a task to delete: ");
                for (int i = 0; i < todo.size(); i++){
                    System.out.println(i + 1 + " " + todo.get(i));
                }
                int deleteTask = scnr.nextInt();
                System.out.println("Deleted task: " + deleteTask);
                System.out.println();
                deleteTask -= 1;
                todo.remove(deleteTask);
            }
            else{
                break;
            }
        }


    }

    static class Task {
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

    @Override
    public String toString() {
        return (isCompleted ? "[X] " : "[ ] ") + "[Priority " + priority + "] " + description + " on " + selectedDate + " at " + selectedTime;
    }
}

}