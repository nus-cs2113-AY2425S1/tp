# Project Portfolio Page

## Overview
**ExchangeCourseMapper** is a Command Line Interface (CLI) tool that helps NUS students plan their course mapping for
Student Exchange Programs (SEP). It provides features to list, compare, and manage courses between NUS and partner
universities, assisting students in efficiently planning their module mapping and keeping track of their mappings.

## Summary of Contributions

### Code Contributed
You can view my code contributions on the tP Code Dashboard [here](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=wahkia&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20).

### Enhancements Implemented

1. **ListCommandsCommand**
   - Implemented the `ListCommandsCommand` class to provide users with an accessible list of all available commands.
     This command enhances usability, particularly for new users who want an overview of possible actions within the application.
   - Integrated it with the `Parser` to trigger the command upon inputting "commands."

2. **ListPersonalTrackerCommand**
   - Developed the `ListPersonalTrackerCommand` to help users view their personal list of mapped courses.
     This command lists all courses that the user has added to their personal tracker, indexed for easy reference.
   - Enhanced user experience by displaying each course in a readable format, along with descriptive headers and line separators.

3. **CompareMappedCommand**
   - Created the `CompareMappedCommand` to allow users to compare course mappings between two selected universities.
     This command filters modules by university, identifies common and unique course codes, and displays comparison results.
   - Implementing this involved handling complex filtering and set operations to ensure accurate, user-friendly outputs.

4. **Storage Class**
   - Developed the `Storage` class to handle the storage and retrieval of mapped courses.
   - Implemented methods to save courses in JSON format and retrieve data from the `myList.json` file for persistent
     tracking. This class interacts with commands like the `DeleteCoursesCommand`, `AddCoursesCommand`, `FindCoursesCommand`,
     `ListPersonalTrackerCommand`, `CompareMappedCommand` to retrieve and store course information, ensuring data consistency across commands.

5. **DataIntegrityChecker**
   - Designed the DataIntegrityChecker class to validate the integrity of data in myList.json, ensuring that only valid course data is stored.
   - Implemented methods to detect duplicate course entries, preventing users from adding duplicate mappings. This feature
     helps maintain accurate records for users and provides clear output about duplicates.

### Contributions to the User Guide (UG)
- Authored sections explaining the use of `ListCommandsCommand`, `ListPersonalTrackerCommand`, and `CompareMappedCommand`.
- Provided detailed command descriptions and examples for each command, including expected outputs and potential use cases.

### Contributions to the Developer Guide (DG)
- Documented the `ListCommandsCommand`, `ListPersonalTrackerCommand`, and `CompareMappedCommand` in the DG.
- Added UML diagrams to illustrate the flow and structure of the `Storage` class and its interactions with commands,
  including Sequence Diagrams for each command to detail their interactions with the `Parser`, `Storage`
  and user input/output handling.

### Contributions to Team-Based Tasks
- **Code Reviews**: Reviewed several PRs to ensure code quality and adherence to coding standards.
- **Code Quality Improvements**: Provided feedback on code readability, consistent method structure, and practices like
  avoiding long methods, reducing deep nesting, and commenting minimally but clearly.
- **Collaboration**: Actively supported team members by providing feedback on command implementations and
  troubleshooting issues with integration of classes. Including GitHub code reviews and comments across multiple PRs.
- Managed the release of v2.0
- Maintained the issue tracker

### Contributions Beyond the Project Team
- **Group PR review**: Provided insights and review other group projects, particularly regarding their diagrams for DG. [here](https://github.com/nus-cs2113-AY2425S1/tp/pull/22)
- **Bug Reporting**: Identified and reported bugs in similar CLI projects by other teams to enhance the overall quality of peer projects during PE Dry Run.

---

