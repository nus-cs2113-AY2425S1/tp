# Project Portfolio: Nirala Tanishka Singh

## Project: BuffBuddy
BuffBuddy is a workout and meal tracker that tracks your programmes, workouts, meals and water intake alongside tracking your calories and personal bests. The user interacts with it using a CLI.

## Summary of Contributions

### Code Contributed
- **Code Link**: [Click here to view my code on the tP Code Dashboard](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=nirala-ts&tabRepo=AY2425S1-CS2113-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements Implemented

I implemented six key features to improve the functionality and usability of BuffBuddy:

1.**Add New Programme**: Feature to create workout programs.
- **What it does**: Allows users to add a new workout program by specifying a name or creating a more detailed program with multiple days and exercises in one command. This enables advanced users to efficiently set up complex routines.
- **Justification**: The flexibility in creating simple or detailed programs caters to both beginner and advanced users, enhancing user experience and saving time when setting up intricate workout plans.
- **Highlights**: This feature’s enhancement for advanced users—allowing multi-day, multi-exercise entries in a single command—significantly reduces setup time for comprehensive programs.

2.**Set Programme as Active**: Feature to designate an active workout program.
- **What it does**: Lets users set a workout program as "active," allowing other commands to automatically apply to the active program without needing to specify it repeatedly.
- **Justification**: This reduces redundant input, streamlining interactions and improving ease of use for users who frequently access the same workout program.
- **Highlights**: Simplifies user experience by providing a default program, making it more intuitive and efficient to execute common commands.

3.**List All Programmes**: Feature to display all created workout programs.
- **What it does**: Shows a list of all workout programs along with the active program indicator, offering users an organized view of their routines.
- **Justification**: The list feature helps users maintain an overview of available workout options, facilitating easy selection or modification of programs.
- **Highlights**: Clearly marks the active program in the list, enhancing quick identification and management of routines.

4.**View Programme**: Feature to show details of a specific workout program.
- **What it does**: Displays the breakdown of a selected program, organized by day and exercise, showing details such as sets, reps, weight, and calories burned.
- **Justification**: This feature enables users to review the structure and specifics of each workout, helping them plan effectively and track their progress.
- **Highlights**: Organizes data in an accessible way for users, providing a comprehensive snapshot of each program’s details.

5.**Delete Programme**: Feature to remove a workout program.
- **What it does**: Allows users to delete a program by specifying its index or by defaulting to the active program.
- **Justification**: This helps users keep their program list manageable by allowing the removal of unused or outdated workouts.
- **Highlights**: Provides flexibility by supporting both specified and default deletions, enhancing usability for users who frequently update their routines.

6.**Log Workout**: Feature to track workout completion.
- **What it does**: Enables users to log the completion of a workout on a specified day, including tracking the date and exercise details.
- **Justification**: Maintaining a workout log supports users’ fitness journeys by recording their progress over time, aiding in motivation and accountability.
- **Highlights**: Captures extensive workout data—such as exercises, sets, reps, and calories burned—providing a detailed record that users can reference to track improvements.


Each feature in BuffBuddy was designed with advanced input flexibility, allowing parameters to be entered in any order and making certain parameters optional to accommodate varied user preferences. 
Additionally, all flags associated with these features have aliases, providing users with alternative command syntax to access the same functionality. This design not only improves usability but also enhances the overall user experience by enabling more intuitive and flexible interactions.
Implementing this flexibility was challenging and required sophisticated parsing logic to ensure consistent and accurate processing of commands. The parsing mechanism was carefully developed to handle alias recognition, optional parameters, and various parameter sequences without compromising functionality. This level of customization demanded a structured approach to parse inputs dynamically while maintaining clarity and ease of use across all BuffBuddy commands.


### Contributions to the User Guide (UG)

In my contributions to the User Guide (UG), I aimed to provide comprehensive documentation for users to effectively utilize the various commands available to personalize and develop their workout programs. 
Here’s an overview of the features I documented:

1. **Add New Programme**: I detailed how users can create a new workout program, from simply naming it to creating complex multi-day schedules with exercises specifying sets, reps, weight, and calories. This allows users to tailor their workout structure to meet their specific fitness goals.
2. **Set Programme as Active**: I explained the functionality of setting a program as "active," which streamlines user interaction by defaulting other commands to this active program. This feature enhances the user experience by making it easier to manage workouts without constantly re-specifying program indices.
3. **List All Programmes**: I outlined the command to view a complete list of user-created workout programs, which provides an overview of all available programs and highlights the active program for easy reference.
4. **View Programme**: I included a breakdown of how to display the details of a particular program, such as exercises organized by day, with full exercise specifications, allowing users to review their progress or plan workouts accordingly.
5. **Delete Programme**: I provided guidance on deleting a program, either by specifying the program index or defaulting to the active program, ensuring users can manage and clear their list of workout programs efficiently.
6. **Log Workout**: I documented how users can log completed workouts, specifying the date and program/day index, to help track workout progress. This feature enables users to maintain a record of their fitness journey, including sets, reps, and calories burned for each exercise.
7. **Exit BuffBuddy**: I illustrated how users can safely exit the application.

Each feature description includes clear command syntax, examples, and sample outputs to illustrate expected results, helping users to navigate and personalize their fitness routines with confidence.

In addition to the features I also 
- standardized the formatting and descriptions across all features in the User Guide, ensuring consistency and clarity for a smoother user experience
- fixed bugs raised related to UG during to PE-D
- added Command Summary Table
- added Alias Table
- added FAQ section
- added To Note section

### Contributions to the Developer Guide (DG)

**Sections Contributed**: Ui Component, Parser Component, Common Component, Create Programme Implementation, User Stories Table

**UML Diagrams**:
- **UI Component**: Showcased the structure and interactions within the `Ui` component, detailing its methods and relationships with other components.
- **Common Component**: Provided UML diagram for the `Utils` class in `common` package.
- **Parser Component**: Provided a visual overview of the Parser component, which includes the following classes:
  - Parser: The main class responsible for handling the parsing of user commands. 
  - FlagParser: Manages the interpretation and validation of flags in commands, enabling flexibility and alias support. 
  - ParserUtils: A utility class that supports parsing processes, handling repetitive parsing tasks. 
  - CommandFactory and specific factories like `HistoryCommandFactory`, `MealCommandFactory`, `ProgCommandFactory`, and `WaterCommandFactory`.
    These factories create specific command objects based on the parsed input, enabling modular handling of different command types (e.g., history, meals, programs, and water intake).
- **Create Programme Feature**: Depicted the sequence of operations for the `Create Programme` feature, showing interactions between various classes for example `Ui`, `Parser`, `ProgCommandFactory`, and `ProgrammeList`. 

---

### Contributions to Team-Based Tasks
- Actively participated in team meetings.
- Assisted with collaborative tasks such as code integration and discussions on project workflow, project design and implementations.
- Assisted team members in debugging and fixing bugs for version 2.1
  - [PR #287 - standardise UG format, add missing features](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/287)
  - [PR #290 - add aliases, update flag definitions ](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/290)
  - [PR #291 - add FAQ, update tables](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/291)
  - [PR #303 - Update UG](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/303)

### Review/Mentoring Contributions
- **Pull Request Reviews**:
  - [PR #137 - Added defensive coding standards to exercise, day, programme and programmeList classes](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/137)
  - [PR #152 - Add DailyRecord Class](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/152)
  - [PR #108 - Storage assert and log](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/108)
  - [PR #151 - Added WaterCommandFactory and ViewWaterCommand classes](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/151)

### Contributions Beyond the Project Team

Reported 13 bugs for Y2425S1-CS2113-W14-3 team during PE-D: https://github.com/nirala-ts/ped/issues
