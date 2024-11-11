# Project Portfolio: Low Beverly

## Project: BuffBuddy
BuffBuddy is a fitness tracker that tracks your programmes, workouts, meals and water intake. The user interacts with
it using a CLI, and it has a GUI created with JavaFX. It is written in Java. 

## Summary of Contributions

### Code Contributed
- **Code Link**: [Click here to view my code on the tP Code Dashboard](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=bev-low&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Bev-low&tabRepo=AY2425S1-CS2113-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements Implemented

- **Save/Load Feature**: Developed functionality to save and load data as JSON files using the Gson library.
  - **What it does**: Enables the application to persist user data by saving it in JSON format, ensuring that data is retained across sessions. Utilizes Gson for efficient serialization and deserialization.
  - **Justification**: Essential for user experience, allowing data continuity across application usage. Without it, users risk data loss upon closing.
  - **Highlights**: Required careful integration with the data model, managing complex data structures accurately. Includes error handling through a `StorageException` and a validation method to detect and handle corrupted files, ensuring data integrity and reliability.
  - **Credits**: The Gson library was extensively used for handling JSON serialization and deserialization.

- **Water Intake Features**: Developed functions to track, view, and manage daily water consumption.
  - **What it does**: Allows users to log daily water intake, helping them monitor hydration goals over time. Users can add, view, and delete intake entries.
  - **Justification**: Aids health-focused users by providing an easy way to track hydration, a crucial part of fitness and well-being.
  - **Highlights**: Implemented methods to add, delete, and view water entries, with a data structure designed for accurate daily updates. Modular commands (add, delete, view) were created to adhere to object-oriented principles, improving code modularity and readability. Error handling prevents invalid entries (e.g., negative values), maintaining data accuracy.

- **Custom Exceptions**: Developed specific exceptions to handle distinct error scenarios.
  - **What it does**: Custom exceptions provide tailored error messages for common issues, enhancing user experience by guiding them through specific errors.
  - **Justification**: Improves usability by offering context-specific error messages, rather than generic ones, helping users understand and resolve issues more easily.
  - **Highlights**: Custom exceptions cover scenarios such as invalid inputs, out-of-bounds errors, and corrupted files. These exceptions ensure robust error handling across the application, allowing graceful failure and preventing incorrect data from being stored.

### Contributions to the User Guide (UG)
- Added/edited the following sections:
    - **Added documentation for water features**: Documented how to use the water intake features, including adding, viewing, and deleting water entries.
    - **Edited documentation for programme edit features**: Made the user guide more readable by splitting up the programme edit features into smaller commands

### Contributions to the Developer Guide (DG)
- **Sections Contributed**: Storage component, DailyRecord Class, Programme component, Add Water Feature, Save/Load Feature
- **UML Diagrams**:
    - **Storage Component**: Illustrates the structure and interactions of the Storage class within the system, including its methods and relationships with other components such as FileManager, ProgrammeList, and History. This diagram highlights how data is loaded, saved, and managed to ensure file operations are seamless and robust.
    - **DailyRecord Class**: Shows the design of the DailyRecord class, detailing its attributes (e.g., Day, MealList, Water) and methods (e.g., logging and retrieving records). This diagram showcases the relationships between DailyRecord and other classes it interacts with, demonstrating how it integrates within the system to manage daily workout, meal, and water intake data.
    - **Programme component**: Provides an overview of the Programme component structure, displaying the relationships between ProgrammeList, Programme, Day, and Exercise classes. This diagram showcases how workout programmes are organized, managed, and stored, emphasizing the hierarchical relationship and flow of data within the system.
    - **Add Water Feature**: Depicts the sequence of operations and interactions involved in adding a water entry. The diagram shows the flow from the user’s action to the adding of the water entry in DailyRecord.
    - **Save/Load Feature**: Depicts the sequence of operations involved in saving and loading data within the application. This includes interactions between the Storage, FileManager, and any serialization or deserialization logic. The diagram highlights the workflow of data persistence, ensuring data integrity and user session restoration.


### Contributions to Team-Based Tasks
- Participated in team meetings.
- Helped refactor and adapt meal-related functionality to align with the revised `History` and `DailyRecord` structure. Ensured that meal data was correctly managed and persisted within the updated framework.
- Assisted with integrating custom exceptions into the main and test code in V2.0.
- Participated in a collaborative debugging session to identify and resolve issues before the V2.0 release.

### Review/Mentoring Contributions
- **Pull Request Reviews**:
    - [PR #229 - Adjust history commands](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/229)
    - [PR #228 - Update Programme Features in UG](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/228)
    - [PR #212 - Add Water features JUnit tests](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/212)
    - [PR #208 - Polish v1 features](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/208)
    - [PR #179 - Update Developer Guide](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/179)
    - [PR #178 - Add documentation and testing for edit programme](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/178)
    - [PR #165 - Add delete log functionality](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/165)
    - [PR #164 - Add calorie information to Exercise objects](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/164)
    - [PR #162 - Refactor EditCommand](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/162)
    - [PR #159 - Fix History and Logging Issue](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/159)
    - [PR #151 - Added WaterCommandFactory and ViewWaterCommand classes](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/151)
    - [PR #90 - Add assertions and logging details for files in parser package](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/90)
    - [PR #81 - Add assertions & logging to Command classes](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/81)
    - [PR #79 - Unit Test for Core Command Classes](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/79)
    - and more..
- Mentored team members by providing code feedback and debugging assistance.

### Contributions Beyond the Project Team
- Reported bugs in other team's product during peer reviews
