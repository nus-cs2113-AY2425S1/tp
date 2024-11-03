# Developer Guide

## Acknowledgements
- The `Parser` is adapted from [Dan Linh's iP](https://github.com/DanLinhHuynh-Niwashi/ip/tree/master/src/main/java/niwa/parser) code, with changes to get on well with the current project 

## Design & implementation
### Command Parser
The `Parser` class is responsible for interpreting user commands and extracting the associated arguments. It facilitates interaction between the user and the underlying command execution logic.
#### Class responsibilities
1. **Command registration**: Maintain a mapping of command words to their corresponding `Command` objects.
2. **Command parsing**: Convert a command string entered by the user into a `Command` object.
3. **Argument extraction**: Extract and organize the arguments associated with a given command.
#### Class attributes
1. **commands: LinkedHashMap<String, Command>**
  - Description: Associates command words (as keys) with their corresponding Command objects (as values).
#### Class main methods
1. **registerCommands(Command command): void**
  - **Parameters**:
    - `command`: The `Command` object to be registered.
  - **Process**:
    - Retrieves the `COMMAND_WORD` field from the `Command` object
    - Adds the word and the command to the `commands` map.
    
    ![register_command](./diagrams/parser/register-command-sequence.png)
    
2. **parseCommand(String commandPart): Command**
  - **Parameters**: 
    - `commandPart`: A string representing the command word entered by the user.
  - **Returns**: The corresponding `Command` object or `null` if the command is not found.
  - **Process**: 
    - Retrieves the associated `Command` object from the `commands` map, using the provided commandPart.

  ![parse_command](./diagrams/parser/parse-command-sequence.png)
  
3. **extractArguments(Command command, String argumentString): Map<String, String>**
  - **Parameters**: 
    - `command`: The `Command` object for which arguments are to be extracted.
    - `argumentString`: The string containing the arguments to be parsed.
  - **Returns**: A map of argument keys and their corresponding values.
  - **Process**: 
    - Initializes an empty map for arguments
    - Retrieves the expected argument keys from the command
    - Invokes `splitCommandRecursively` to populate the arguments map.
  
  ![extract_arguments](./diagrams/parser/extract-arguments-sequence.png)
  
4. **splitCommandRecursively(String argumentString, String[] keywords, Map<String, String> arguments, String prevKeyword): void**
  - **Parameters**: 
    - `argumentString`: The string containing the arguments to be split.
    - `keywords`: An array of expected keywords for argument extraction.
    - `arguments`: The map where extracted arguments will be stored.
    - `prevKeyword`: The keyword found in the previous recursive call.
  - **Description**: Extracts values associated with keywords and updates the arguments map accordingly.
  - **Process**:
    - Base case: No argument left to split: `argumentString.isEmpty()`
    - Find the first keyword in the list that appears in the argumentString
    - If found:
      - Attach the part before the keyword with the previously found keyword and put in to `arguments`.
      - Delete the keyword from the `keywords` list (to not be considered in the next call)
      - Pass the remaining `argumentString` after the keyword to the next recursive call
    - If not found (mean that the last keyword reached):
      - Attach the remaining part with the previously found keyword and put in to `arguments`.

## Product scope
### Target user profile
#### Demographics:
- Age: 18-25 years old
- Education: College or university students
- Income: Limited or variable income (part-time jobs, allowances, or scholarships)
#### Psychographics:
- Tech-Savvy: Comfortable using command-line interfaces and basic programming concepts.
- Motivated to create good spending habit: Aware of personal finance status and developing better money management habits.

### Value proposition
uNivUSaver offers a practical solution for students who want to take control of their finances, avoid over-spending and manage their saving goal.
- Customized budgeting tools: Helps users set up personalized budgets based on their income and expenses, allowing them to see where their money goes and how to optimize it.
- Habit formation: Encourages regular check-ins and tracking of spending habits, helps students develop a consistent routine for managing their finances.
- Limit tracking: Helps users to set specific monthly limit and monitor their progress to avoid over-spending.

## User Stories

| Version | As a...                              | I want to...                                           | So that I can...                                                |
|---------|--------------------------------------|--------------------------------------------------------|-----------------------------------------------------------------|
| v1.0   | new user                              | see usage instructions                                 | refer to them when I forget how to use the application          |
| v1.0   | student                               | input my expenses                                      | keep track of how much I spend daily                            |
| v1.0   | student with part-time job            | input my income into the budget                        | my budget reflects my earnings                                  |
| v1.0   | student                               | create categories for expenses                         | separate my expenses into the respective categories             |
| v1.0   | user                                  | delete any expense                                     | remove any spending that was input wrongly                      |
| v1.0   | user                                  | delete any income                                      | remove any income that was input wrongly                        |
| v1.0   | student with changing spending purposes| delete any category                                    | clean up unecessary categories                                  |
| v1.0   | student who wishes to manage my budget| view my transaction history                            | figure out my spending habits                                   |
| v1.0   | student                               | view my budget amount                                  | know the current state of my budget                             |
| v1.0   | student who wishes to manage spendings| view summary of my spending                            | have an idea of where my money goes in a period of time         |
| v1.0   | student who wishes to manage incomes  | view summary of my incomes                             | have an idea of where my sources of income in a period of time  |
| v1.0   | user                                  | view current category list                             | know the existing categories in my budget                       |
| v1.0   | student with various spending purposes| keep track of my spending in each category             | see how much I spend for each category                          |
| v2.0   | time-constrained student              | see fixed reminders to input daily expenses            | improve accuracy in recording and avoid missing any transactions|
| v2.0   | student that easily overspending      | see fixed reminders of today expenses                  | stop myself from overspending in the rest of the day            |
| v2.0   | student                               | find my expenses and incomes by keywords               | search for old transactions                                     |
| v2.0   | who often changes my mind             | categorize my expenses after creating                  | easily change my expense category                               |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
