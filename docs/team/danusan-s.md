# Danusan Sugumar (danusan-s) Personal Portfolio page:

## Project overview (Inventra):

Inventra is a CLI-based inventory management system designed for small and medium-sized businesses.
It provides users with a flexible way to manage custom inventory fields, track records and manage inventory.
This product was build with the contribution of T11 G4 team members, as part of CS2113 module.

## Summary of Contributions:

### Code Contributions:
My code contributions can be found [here](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=danusan-s&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other).

### Enhancements implemented:
1. Implemented core functionalities:
    * Implemented the delete command and its enhancements.
    * Core functionality:
        - `delete` Ability to delete a record using an index.
        - `delete -h` Ability to delete a header using name.
    * Enhancements:
        - `delete -r` Ability to delete a range of records.
        - `delete -a` Ability to delete all records.
        - `delete -e` Ability to delete entire table including headers.
    
    * Implemented the help command and its enhancements.
    * Core functionality:
        - `help` Gives the user a short guide on all commands
    * Enhancements:
        - `help <command>` Gives the user information on a specific command.

2. Exception handling:
    * Implemented various exception classes to be used across the codebase:
        - InventraOutOfBoundsException
        - InventraRangeOutOfBoundsException
        - InventraExcessArgsException
        - InventraInvalidCommandException
        - InventraInvalidFlagException
        - InventraMissingArgsException
        - InventraInvalidNumberException
        - InventraInvalidTypeException
        - InventraMissingFieldsException
    * Modified existing code to utilise new exception classes.

3. Testing:
    * Wrote extensive test code for the delete command
    * Additionally, performed regular Gradle test and CI Pipelines, which includes:
        - performed checks to ensure program's sanity
        - ensured program is free from any checkstyle errors: improper naming convention, line length violation, and unused imports
        - addressed bugs highlighted by peers in PE-Dry run 


### Contributions to the User Guide:

1. Main Contributions:
   * Delete Command:
       - Added extensive user guide relating to the delete command and its derivatives.
   * Help Command:
       - Added information on the help command.

2. Additional contributions:
    * Reworded certain parts of the UG based on feedback on the dry run.
    * Added more specific details to the different parts of the guide.


### Contributions to the Developer Guide:

1. Main Contributions:
    * Delete Command:
        - Added explanation for the implementation of the delete command
        - Added sequence diagram relating to the delete command.

### Contributions to team based tasks:

1. Project Discussions:
    * Met up with the team regularly to discuss features, issues and enhancements for the project.

2. Code cleanup:
    * Performed extensive code cleanup and refactoring for the project.
    * Helped tackle bugs outside my assigned part of the project.
    * Created different classes to make the code more object-oriented and less cluttered.

3. Mentoring:
    * Helped teammates with issues relating to the CI actions and GitHub.


