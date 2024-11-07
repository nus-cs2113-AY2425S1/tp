# Project Portfolio: Nirala Tanishka Singh

## Project: BuffBuddy
BuffBuddy is a workout and meal tracker that tracks your programmes, workouts, meals and water intake alongside tracking your calories and personal bests. The user interacts with it using a CLI.

## Summary of Contributions

### Code Contributed
- **Code Link**: [Click here to view my code on the tP Code Dashboard](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=nirala-ts&tabRepo=AY2425S1-CS2113-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements Implemented

I implemented six key features to improve the functionality and usability of BuffBuddy:

1. **Add New Programme**: Allows users to create a new workout program. Users can add either a simple program with just a name or create a detailed program by specifying multiple days and exercises at once. This feature includes an enhancement that allows advanced users to add multiple exercises across multiple days in a single command, streamlining the setup process for complex workout routines.
2. **Set Programme as Active**: Enables users to set a specific program as the "active program," which simplifies user interaction by allowing other commands to default to this active program. This reduces the need to repeatedly specify the program index for frequently used commands.
3. **List All Programmes**: Provides users with a list of all workout programs they have created, displaying each program’s name and indicating the active program for quick reference. This feature gives users an organized overview of their available workout routines.
4. **View Programme**: Displays the detailed breakdown of a specific workout program, organized by day and exercise. Each entry shows the exercise name, sets, reps, weight, and estimated calories burned, allowing users to review and plan their workouts.
5. **Delete Programme**: Allows users to delete a specific workout program, either by specifying the program index or by defaulting to the active program. This feature helps users manage and organize their workout programs effectively by removing unused or outdated routines.
6. **Log Workout**: Records the completion of a workout on a specific day within a chosen program, including options to log the date and track daily workout completion. This feature enables users to maintain a detailed record of their fitness journey, including exercises, sets, reps, and calories burned.

Each of these features contributes to a more streamlined and customizable experience for users. 
The **Create Programme** feature, in particular, includes an additional enhancement that allows users to create a complex workout program in one command, specifying multiple days and exercises. 
This provides advanced users with a powerful tool to quickly set up detailed workout plans tailored to their specific goals.



### Contributions to the User Guide (UG)

In my contributions to the User Guide (UG), I aimed to provide comprehensive documentation for users to effectively utilize the various commands available to personalize and develop their workout programs. 
Here’s an overview of the features I documented:

1. **Add New Programme**: I detailed how users can create a new workout program, from simply naming it to creating complex multi-day schedules with exercises specifying sets, reps, weight, and calories. This allows users to tailor their workout structure to meet their specific fitness goals.
2. **Set Programme as Active**: I explained the functionality of setting a program as "active," which streamlines user interaction by defaulting other commands to this active program. This feature enhances the user experience by making it easier to manage workouts without constantly re-specifying program indices.
3. **List All Programmes**: I outlined the command to view a complete list of user-created workout programs, which provides an overview of all available programs and highlights the active program for easy reference.
4. **View Programme**: I included a breakdown of how to display the details of a particular program, such as exercises organized by day, with full exercise specifications, allowing users to review their progress or plan workouts accordingly.
5. **Delete Programme**: I provided guidance on deleting a program, either by specifying the program index or defaulting to the active program, ensuring users can manage and clear their list of workout programs efficiently.
6. **Log Workout**: I documented how users can log completed workouts, specifying the date and program/day index, to help track workout progress. This feature enables users to maintain a record of their fitness journey, including sets, reps, and calories burned for each exercise.

Each feature description includes clear command syntax, examples, and sample outputs to illustrate expected results, helping users to navigate and personalize their fitness routines with confidence.


### Contributions to the Developer Guide (DG)

**Sections Contributed**: UI Component, Parser Component, Create Programme Implementation
**UML Diagrams and Explanations**:
    - **UI Component**: Showcases the structure and interactions within the `Ui` component, detailing its methods and relationships with other components, such as command inputs and message displays. The diagram highlights how the `Ui` component manages input and output streams, ensuring a seamless user interface through methods like `readCommand`, `showMessage`, and `showWelcome`. I provided detailed explanations of each class and method to clarify how user inputs and outputs are handled within the system, offering developers a deeper understanding of the UI's functionality beyond the visual diagram.
    - **Parser Component**: Showcases the structure of the `Parser` component, including the `Parser`, `FlagParser`, `ParserUtils`, and `CommandFactory` classes. The class diagram provides insight into how user commands are parsed, processed, and delegated to specific command factories for program management, meals, water intake, and history-related actions. Alongside the diagram, I provided in-depth explanations for each class and method in this component to detail the roles and responsibilities involved in parsing and command creation, helping developers understand the overall structure and individual functionalities within the Parser component.
    - **Create Programme Feature**: Depicts the sequence of operations for the `Create Programme` feature, showing interactions between `Ui`, `Parser`, `ProgCommandFactory`, and `ProgrammeList`. This sequence diagram visualizes the workflow of creating a workout program from user input to data storage, enhancing understanding of how BuffBuddy processes and structures new workout programs. Additionally, I provided a step-by-step flow of operations to accompany the sequence diagram, explaining each part of the process from parsing user input to executing the command and updating the `ProgrammeList`, ensuring developers have a clear understanding of the feature’s full implementation.

---

### Contributions to Team-Based Tasks
- Participated in team meetings.
- Assisted with collaborative tasks such as code integration and discussions on project workflow, project design and implementations.

### Review/Mentoring Contributions
- **Pull Request Reviews**:
  - [PR #137 - Added defensive coding standards to exercise, day, programme and programmeList classes](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/137)
  - [PR #152 - Add DailyRecord Class](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/152)
  - [PR #108 - Storage assert and log](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/108)
  - [PR #151 - Added WaterCommandFactory and ViewWaterCommand classes](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/151)

### Contributions Beyond the Project Team
