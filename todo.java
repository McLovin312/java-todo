import java.util.*;

class Todo{
    public static void main(String args[]){
        List<Task> todo = new ArrayList<>();

        Scanner scnr = new Scanner(System.in);
        
        while(true){
            System.out.println("---To Do List----");
            System.out.println("1. Create Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");

        
            System.out.println("Select an option: ");
            int num = scnr.nextInt();

            if (num == 1){
                scnr.nextLine();
                System.out.print("What task would you like to add?: ");
                Task myTask = new Task(scnr.nextLine());

                todo.add(myTask);
                
                System.out.println("Task added!");
                System.out.println();
            }

            else if (num ==2) {
                System.out.println();
                System.out.println("---Tasks---");
                System.out.println("Total Tasks: " + todo.size());
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

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
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

    @Override
    public String toString() {
        return (isCompleted ? "[X] " : "[ ] ") + description;
    }
}

}