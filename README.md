# Java CLI To-Do List

A lightweight, command-line based task management application written in Java. This application provides a color-coded terminal interface allowing users to easily manage their daily tasks, prioritize them, set deadlines, and save their progress to a local file.

## Features

*   **Task Creation:** Add tasks with a description, priority level (1-5), due date (YYYY-MM-DD), and time (HH:mm:ss).
*   **Intelligent Sorting:** When viewing tasks, they are automatically sorted first by priority, then by due date, and finally by time.
*   **Status Tracking:** Mark tasks as complete or incomplete.
*   **Task Management:** Delete tasks that are no longer needed.
*   **Data Persistence:** Save your current list of tasks to a comma-separated values (CSV) file (`todo.csv`).
*   **Color-Coded UI:** Uses ANSI escape codes to provide a visually organized and readable terminal interface.

## Prerequisites

*   **Java Development Kit (JDK):** Version 8 or higher is required (due to the use of the `java.time` package).
*   **Terminal:** A terminal emulator that supports ANSI color codes (most modern terminals on Linux, macOS, and Windows do).

## How to Run

1. Clone or download the repository to your local machine.
2. Open your terminal and navigate to the directory containing the `Todo.java` file.
3. Compile the Java file using the following command:
   ```bash
   javac Todo.java
   ```
4. Run the compiled application:
   ```bash
   java Todo
   ```

## Usage

Upon running the application, you will be presented with a main menu. Type the number corresponding to the action you want to take and press Enter.

1. **Create Task:** Prompts you for the due date, time, priority level, and description. Date and time must follow the strict formatting provided in the prompts.
2. **View Tasks:** Displays all currently loaded tasks, sorted automatically by urgency and priority. Completed tasks will be marked with an `[X]`.
3. **Complete Task:** Prompts you to select a task number from the list to mark as finished.
4. **Delete Task:** Prompts you to select a task number to permanently remove from your list.
5. **Save Tasks:** Writes your current task list to `todo.csv` in the same directory as the program.
6. **Exit:** Closes the application. 

## Data Storage

Tasks are saved locally in a file named `todo.csv`. The application formats the data cleanly, escaping commas and quotes within task descriptions to ensure the CSV structure remains intact. The format follows: `isCompleted,priority,"description",YYYY-MM-DD,HH:mm:ss`.
