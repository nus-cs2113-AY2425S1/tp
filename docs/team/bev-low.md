# Project Portfolio: Low Beverly

## Project: BuffBuddy
BuffBuddy is a fitness tracker that tracks your programmes, workouts, meals and water intake. The user interacts with
it using a CLI, and it has a GUI created with JavaFX. It is written in Java. 

## Summary of Contributions

### Code Contributed
- **Code Link**: [Click here to view my code on the tP Code Dashboard](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=bev-low&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Bev-low&tabRepo=AY2425S1-CS2113-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements Implemented

- **Save/Load Feature**: Enabled data persistence with JSON using Gson, ensuring data continuity across sessions.
  - **What it does**: Saves user data in JSON format, maintaining it across application usage.
  - **Justification**: Prevents data loss, improving user experience.
  - **Highlights**: Integrated with complex data structures and includes error handling with `StorageException` and file validation for data integrity.
  - **Credits**: Gson library used for JSON handling.

- **Water Intake Features**: Added features to log, view, and manage daily water intake.
  - **What it does**: Tracks hydration goals by allowing users to add, view, and delete intake entries.
  - **Justification**: Supports health-conscious users in monitoring hydration.
  - **Highlights**: Commands adhere to object-oriented principles, with error handling to ensure accurate entries.

- **Custom Exceptions**: Created tailored exceptions for specific errors.
  - **What it does**: Provides clear error messages for user issues.
  - **Justification**: Improves usability with context-specific guidance.
  - **Highlights**: Manages invalid input, out-of-bounds errors, and file corruption, enhancing robustness and data safety.

### Contributions to the User Guide (UG)
- Added/edited the following sections:
  - **Water Features**: Documented usage for adding, viewing, and deleting water entries.
  - **Programme Edit Features**: Enhanced readability by splitting programme edit commands into smaller sections.

### Contributions to the Developer Guide (DG)
- **Sections Contributed**: Storage component, DailyRecord Class, Programme component, Add Water Feature, Save/Load Feature
- **UML Diagrams**:
  - **Storage Component**: Shows the structure and interactions of the Storage class, highlighting its methods and connections to FileManager, ProgrammeList, and History, ensuring smooth data handling.
  - **DailyRecord Class**: Illustrates the attributes and methods of DailyRecord, emphasizing its role in managing daily workout, meal, and water data.
  - **Programme component**: Displays relationships within ProgrammeList, Programme, Day, and Exercise, emphasizing workout programme organization and data flow.
  - **Add Water Feature**: Outlines the sequence of operations for adding a water entry, from user action to data storage in DailyRecord.
  - **Save/Load Feature**: Shows the sequence for saving/loading data, detailing Storage and FileManager interactions to ensure data persistence and integrity.


### Contributions to Team-Based Tasks
- Attended team meetings.
- Set up Gson and Mockito in Gradle.
- Maintained issue tracker and milestones.
- Refactored meal-related functionality to align with `History` and `DailyRecord`, ensuring proper data management.
- Integrated custom exceptions into main and test code for V2.0.
- Collaborated on debugging session to resolve issues pre-V2.0 release.

### Review/Mentoring Contributions
- **Pull Request Reviews**:
    - [PR #208 - Polish v1 features](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/208)
    - [PR #165 - Add delete log functionality](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/165)
    - [PR #162 - Refactor EditCommand](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/162)
    - [PR #151 - Added WaterCommandFactory and ViewWaterCommand classes](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/151) 
    - [PR #90 - Add assertions and logging details for files in parser package](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/90)
    - [PR #81 - Add assertions & logging to Command classes](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/81) 
    - [PR #45 - Add delete & create day functionality to edit command](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/45)
    - [PR #31 - Add create & edit command](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/31)
- Mentored team members by providing code feedback and debugging assistance.
