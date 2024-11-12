# Avjay Bhar - Project Portfolio Page

## Overview
FitTrackCLI is command-line-based chatbot to help users manage their NAPFA related exercises and goals.
It lets users record and track training sessions, calculate their NAPFA scores and awards and set fitness goals.

## Summary of Contributions

### Code contributed: [(link to contribution dashboard)](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=Yvorm&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=Yvorm&tabRepo=AY2425S1-CS2113-W13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements implemented:
I implemented the following classes, exceptions and functions which handle backend data storage, reminder functionality,
execution of commands, and JUnit testing.

I was responsible for integrating SaveFile functionality, exception checking, and data parsing, to **all functions** 
of the program, including those implemented by other members.

I was also responsible for establishing and enforcing data storage paradigms across my team members' work, 
such as parsing methods associated to the `Saveable `abstract class (`toSaveString` / `fromSaveString`) and program initialisation.

I partially reimplemented Ayushi Yadav's original work pertaining to dietary management (`FoodEntry`, `WaterEntry`, 
`FoodEaterIntake`, and associated functionalities) to increase disk-storage functionality. 

I wrote for all JUnit tests applicable to the methods I added within these classes.

#### Class-implementations

| Class                                                            | Functionality                                                                                                |
|------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| `Saveable` (abstract)                                            | Entry point of the application, manages application initialization by coordinating other classes             |
| `Reminder`                                                       | Manages logging for the application, ensuring errors and important events are properly recorded              |
| `FoodEntry` (reimplemented)                                      | Records the user’s information, such as age and gender, and provides methods to modify or retrieve this data |
| `WaterEntry` (reimplemented)                                     | Handles parsing of user input, converting it into commands and actions                                       |
| `FoodWaterIntake` (reimplemented)                                | High-level and modular data class for user dietary information,                                              |
| `Storage`                                                        | Handles high-level data initialisation/saving from/to a SaveFile on disk                                     |
| `TrainingSession` (Implemented by other members, extended by me) | Added saveFile functionality and data parsing                                                                |
| `Goal` (Implemented by other members, extended by me)            | Added saveFile functionality and data parsing                                                                |

#### Class-implementations

| Functionality                 | Purpose                                                                                                                         |
|-------------------------------|---------------------------------------------------------------------------------------------------------------------------------|
| Data Saving to disk           | Facilitates all updating methods across the program to write to the disk using a standardised format                            |
| Data initialisation           | Allows the program to initialise data from a correctly formatted Save File, establishing data permanence between user sessions. |
| Add a Reminder                | Adds a Reminder with a specified description and due date, to allow the user to keep track of upcoming events/deadlines.        |
| List all Reminders            | Displays all Reminders the user has added.                                                                                      |
| List all Upcoming Reminders   | Displays all Reminders  the user has added due in the next week.                                                                |
| Delete a Reminder             | Removes a Reminder from the list.                                                                                               |
| Add a Food / Water Item       | Logs food/water consumed, allowing users to track their calorie intake/hydration to better assess their diet.                   |
| Delete a Food / Water Item    | Removes a food/Water entry.                                                                                                     |
| List all Food / Water entries | Displays all Food/Water intake the user has had in the current day, to provide a high-level overview of dietary intake.         | 
| Exit the program              | Ends FitTrack CLI task and exits.                                                                                               |

## Documentation:
### User Guide:

Documented the following features: Data initialisation, Add Reminder, List Reminders, List Upcoming Reminders, Delete Reminder.

For each feature, I detailed its purpose, format, constraints, and included examples of its expected output.

### Developer Guide:


- Documented the Reminder class, designing sequence diagrams for Add Reminder, List Reminders, List Upcoming Reminders, 
and Delete Reminder functionality.
- Designed the Class diagram used to support the explanation of Storage functions.

## Project Management:
### Contributions to team-based tasks

- Led group discussions and final-stage (v2.0-) project planning - allocating tasks and assignments during meetings.
- Reviewed and (re)implemented significant portions of object classes (i.e `TrainingSession`, `Goal`, `User`) and `Parser` logic contributed by other team members on a rolling basis.   
- Led conflict-resolution to assuage early miscommunications, and establish clear lines of communication that were used heavily from the midpoint of the project thereafter.

### Review/mentoring contributions:
Jointly responsible for reviewing codebase quality across team member contributions, and enforcing code-quality and documentation standards.

