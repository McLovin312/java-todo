import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.PriorityQueue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class Todo{
    public static void main(String args[]){
        List<Task> todo = new ArrayList<>();

        Scanner scnr = new Scanner(System.in);

        while(true){
            System.out.println("\n\033[36m+----------------------------+\033[0m");
            System.out.println("\033[36m|\033[0m         \033[1mTO-DO LIST\033[0m         \033[36m|\033[0m");
            System.out.println("\033[36m+----------------------------+\033[0m");
            System.out.println("\033[36m|\033[0m  \033[33m1.\033[0m Create Task            \033[36m|\033[0m");
            System.out.println("\033[36m|\033[0m  \033[33m2.\033[0m View Tasks             \033[36m|\033[0m");
            System.out.println("\033[36m|\033[0m  \033[33m3.\033[0m Complete Task          \033[36m|\033[0m");
            System.out.println("\033[36m|\033[0m  \033[33m4.\033[0m Delete Task            \033[36m|\033[0m");
            System.out.println("\033[36m|\033[0m  \033[33m5.\033[0m Save Tasks             \033[36m|\033[0m");
            System.out.println("\033[36m|\033[0m  \033[31m6.\033[0m Exit                   \033[36m|\033[0m");
            System.out.println("\033[36m+----------------------------+\033[0m");

            System.out.print("\033[1mSelect an option:\033[0m \033[32m");
            int num = scnr.nextInt();
            System.out.print("\033[0m");

            if (num == 1){
                scnr.nextLine();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDate selectedDate = null; 
                LocalTime selectedTime = null; 
                
                System.out.print("\n\033[33m> When will this task be due? (Format: YYYY-MM-DD):\033[0m ");
                while(selectedDate == null){
                    String input = scnr.nextLine();
                    try{
                        selectedDate = LocalDate.parse(input, dateFormatter);
                        System.out.println("\033[32m  * Selected date: " + selectedDate + "\033[0m");
                    }
                    catch (DateTimeParseException e){
                        System.out.println("\033[31m  X Invalid format. Please try again (Format: YYYY-MM-DD)\033[0m");
                        System.out.print("\033[33m> Try again:\033[0m "); 
                    }
                }
                
                System.out.print("\033[33m> What time? (Format: HH:mm:ss):\033[0m ");
                while(selectedTime == null){
                    String timeInput = scnr.nextLine();
                    try{
                        selectedTime = LocalTime.parse(timeInput, timeFormatter);
                        System.out.println("\033[32m  * Selected time: " + selectedTime + "\033[0m");
                    }
                    catch (DateTimeParseException e){
                        System.out.println("\033[31m  X Invalid format. Please try again (Format: HH:mm:ss)\033[0m");
                        System.out.print("\033[33m> Try again:\033[0m ");
                    }
                }

                scnr.nextLine(); 

                System.out.print("\033[33m> What priority? (1 - 5):\033[0m ");
                int priorityLevel = scnr.nextInt();
                scnr.nextLine();

                System.out.print("\033[33m> What task would you like to add?:\033[0m ");
                String taskDesc = scnr.nextLine();
                Task myTask = new Task(taskDesc, priorityLevel);

                myTask.selectedDate = selectedDate;
                myTask.selectedTime = selectedTime;

                todo.add(myTask);
                
                System.out.println("\n\033[1;32mTask added!\033[0m \033[90mDue on " + selectedDate + " at " + selectedTime + "\033[0m\n");
            }

            else if (num == 2) {
                System.out.println("\n\033[1;36m--- Tasks ---\033[0m");
                System.out.println("\033[90mTotal Tasks: " + todo.size() + "\033[0m\n");

                todo.sort(Comparator.comparing(Task::getPriority).thenComparing(Task::getSelectedDate).thenComparing(Task::getSelectedTime));

                for (int i = 0; i < todo.size(); i++){
                    System.out.println("  " + todo.get(i).toString());
                    System.out.println();
                }
            }

            else if (num == 3){
                System.out.println("\n\033[1;36m--- Complete a Task ---\033[0m");
                System.out.println("\033[33mWhich task would you like to complete? (Input number):\033[0m ");
                
                for (int i = 0; i < todo.size(); i++){
                    System.out.println("  \033[1;36m" + (i + 1) + ".\033[0m " + todo.get(i).toString());
                }
                
                System.out.print("\033[33m> \033[0m");
                int selectedTask = scnr.nextInt();
                selectedTask -= 1;
                todo.get(selectedTask).markComplete();
                
                System.out.println("\n\033[1;32m* Task marked as complete!\033[0m\n"); 
            }

            else if (num == 4){
                System.out.println("\n\033[1;31m--- Delete a Task ---\033[0m");
                System.out.println("\033[33mSelect a task to delete:\033[0m ");
                
                for (int i = 0; i < todo.size(); i++){
                    System.out.println("  \033[1;31m" + (i + 1) + ".\033[0m " + todo.get(i).toString());
                }
                
                System.out.print("\033[33m> \033[0m");
                int deleteTask = scnr.nextInt();
                
                System.out.println("\n\033[1;32m* Deleted task: " + deleteTask + "\033[0m\n");
                
                deleteTask -= 1;
                todo.remove(deleteTask);
            }

            else if (num == 5){
                System.out.println("\n\033[1;36m--- Saving Tasks ---\033[0m");

                java.nio.file.Path path = Paths.get("C:\\todo\\todo.csv");

                try(java.io.BufferedWriter writer = Files.newBufferedWriter(path)) {
                    
                    for (Task task : todo) {
                        String fileLine = task.toString();

                        writer.write(fileLine);
                        writer.newLine();

                    System.out.println("\033[1;32m* Tasks successfully saved!\033[0m\n");

                }
                } 
                catch(IOException e) {
                    System.out.println("\033[1;31m  X Error saving file: " + e.getMessage() + "\033[0m\n");
                }
            }

            else{
                System.out.println("\n\033[1;32mGoodbye!\033[0m\n");
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
        return (isCompleted ? "\033[1;32m[X]\033[0m " : "\033[1;31m[ ]\033[0m ") 
             + "\033[1;35m[Priority " + priority + "]\033[0m " 
             + "\033[1m" + description + "\033[0m " 
             + "\033[90mon " + selectedDate + " at " + selectedTime + "\033[0m";
    }
}
}