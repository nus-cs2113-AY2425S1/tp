package ui;

public enum Messages {
    WELCOME("Hi! Welcome to FindOurSEP! Enter help for the list of commands."),
    EXIT("Goodbye! Have a great day!"),
    ALLOCATE_COMPLETE("Allocation algorithm complete! Run generate command to view."),
    REVERT_COMPLETE("Revert complete! Run the allocate command whenever you are ready."),
    HELP("""
        Here is the list of possible commands:
            add         Adds a student with the specified ID, GPA, and preference rankings.
                        Example: add id/A1234567I gpa/5.0 p/{13,61,43}
                        Note: PREFERENCE_RANKINGS should be enclosed in curly braces

            delete      Deletes the student with the specified ID from the list.
                        Example: delete A1234567I

            help        Displays this help message.

            list        Lists all students currently in the system.

            find        Finds the student with the keyword, returning either a list or report.
                        Example: find list A1234567I

            minimum     Sets the minimum GPA to be considered for exchange.
        
            filter      Filters student data with a keyword, returning either a list or report.
                        User can choose between ascending/descending id/gpa and allocation status.
                        The format is: 'filter <list/report> <allocated/unallocated>',
                                       or 'filter <list> <gpa/id> <ascending/descending>'
                        Example: filter list gpa ascending

            stats       Displays GPA stats for students who have been allocated a specific uni.
                        Usage:
                        stats -avggpa <UNI_INDEX>  Displays the average GPA for the specified uni.
                        stats -mingpa <UNI_INDEX>  Displays the minimum GPA for the specified uni.

            viewQuota   Displays the index, name, and remaining quota for the specified uni.
                        Usage: viewQuota <UNI_INDEX>

            allocate    Allocates students to available slots based on their preferences.
        
            revert      Reverts the student list to original, pre-allocation state.

            generate    Generates a report of allocations and student data.

            exit        Exits the application.
        """),
    ERROR("An error occurred. Please try again.");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
