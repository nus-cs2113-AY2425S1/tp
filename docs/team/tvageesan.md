# Project Portfolio: Thiru Vageesan

## Project: BuffBuddy

BuffBuddy is a workout and meal tracker that tracks your programmes, workouts, meals and water intake alongside tracking your calories and personal bests. The user interacts with it using a CLI.

## Summary of Contributions

### Code Contributed

- **Code Link**: [Click here to view my code on the tP Code Dashboard](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=tvageesan&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=TVageesan&tabRepo=AY2425S1-CS2113-W10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements Implemented

- Implemented the `BuffBuddy` class which serves as the main entry point for the application. This class initializes the UI, parser, storage, programme list, and history, and contains the main application loop.
- Implemented the `Edit Programme` epic feature as well as its associated features `Edit Exercise`, `Create Exercise`, `Delete Exercise`, `Create Day`, `Delete Day` which allows the user to edit the details of a programme.
- Designed and implemented initial version of `ProgrammeCommand` classes, which allows the user to add, delete and view programmes.
- Implemented the `Delete Workout Log` feature, which allows the user to delete a logged workout from `History`.

### Contributions to the User Guide (UG)

- Added/edited the following sections:
  - **Added documentation for edit programme related features**: Description of the section or feature.
  - **Standardized format for all commands**: Made sure that all command descriptions were kept consistent and followed the same format.
  - **Added important notes on command format**: Added notes on the format of the arguments for each command to avoid user confusion.

### Contributions to the Developer Guide (DG)

- **Sections Contributed**: Command component, feature documentation for `Edit Programme`
- **UML Diagrams**:
  - **Command Class** as well as all subclasses
  - **Edit Command** sequence diagram, activity diagram as well as sequence diagrams for all five of its associated subcommands.

### Contributions to Team-Based Tasks

- Participated in team meetings.
- Assisted with planning of milestone features and adjusting issues according to time constraints.
- Helped design overall class structure and maintain a consistent vision for the project as the project developed

### Review/Mentoring Contributions

- **Pull Request Reviews**:

  - [PR #71 - HistoryTest](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/71)
  - [PR #150 - Added MealCommandFactory and ViewMealCommand classes](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/150)
  - [PR #23 - Update Storage Class, toJson/fromJson Methods](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/23)
  - [PR #139 - Weeklysummary and personalbests features](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/139)
  - [PR #159 - Fix History and Logging Issue](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/159)
  - [PR #25 - Add parsing functionalities on user input ](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/25)
  - [PR #28 - Add parsing functionalities on user input, Fix bugs related to checkstyle](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/28)
  - [PR #29 - Complete History class](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/29)
  - [PR #50 - Improve History class](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/50)
  - [PR #143 - Add Meal, MealList, skeleton Record, mealcommand and addmealcommand classes](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/143)
  - [PR #144 - Refactor Storage and DataAdapter classes](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/144)
  - [PR #179 - Update Developer Guide](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/179)
  - [PR #199 - Updated javaDocs for the meals features ](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/199)
  - [PR #204 - Edits in Parser Package](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/204)
  - [PR #219 - Enhance Test Coverage for Programme Commands](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/219)
  - [PR #286 - V2.0 bug fix](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/286)
  - [PR #231 - Fix the string in print PB commands](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/231)
  - [PR #292 - Fix DG Order ](https://github.com/AY2425S1-CS2113-W10-3/tp/pull/292)

- Mentored team members by providing code feedback and debugging assistance.
- Reviewed code consistently such that the team adhered to good design principles and coding standards.
- Suggested & helped plan larger refactors such as the creation of `FlagParser` or `FileManager` classes

### Contributions Beyond the Project Team

Reported 11 bugs for the WheresMyMoney team during PE Dry run: https://github.com/TVageesan/ped/issues
