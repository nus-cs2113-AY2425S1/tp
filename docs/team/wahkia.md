# Project Portfolio Page

## Overview
**ExchangeCourseMapper** is a Command Line Interface (CLI) tool that helps NUS students plan their course mapping for Student Exchange Programs (SEP). It provides features to list, compare, and manage courses between NUS and partner universities, assisting students in efficiently planning their module mapping and keeping track of their mappings.

## Summary of Contributions

### Code Contributed
You can view my code contributions on the tP Code Dashboard [here].

### Enhancements Implemented

1. **ListCommandsCommand**
    - Implemented the `ListCommandsCommand` class to provide users with an accessible list of all available commands. This command enhances usability, particularly for new users who want an overview of possible actions within the application.
    - Integrated it with the `Parser` to trigger the command upon inputting "commands."

2. **ListPersonalTrackerCommand**
    - Developed the `ListPersonalTrackerCommand` to help users view their personal list of mapped courses. This command lists all courses that the user has added to their personal tracker, indexed for easy reference.
    - Enhanced user experience by displaying each course in a readable format, along with descriptive headers and line separators.

3. **CompareMappedCommand**
    - Created the `CompareMappedCommand` to allow users to compare course mappings between two selected universities.
    - This command filters modules by university, identifies common and unique course codes, and displays comparison results. Implementing this involved handling complex filtering and set operations to ensure accurate, user-friendly outputs.

4. **Storage Class**
    - Developed the `Storage` class to handle the storage and retrieval of mapped courses.
    - Implemented methods to save courses in JSON format and retrieve data from the `myList.json` file for persistent tracking. This class interacts with both the `ListPersonalTrackerCommand` and `CompareMappedCommand` to retrieve and store course information, ensuring data consistency across commands.

### Contributions to the User Guide (UG)
- Authored sections explaining the use of `ListCommandsCommand`, `ListPersonalTrackerCommand`, and `CompareMappedCommand`.
- Provided detailed command descriptions and examples for each command, including expected outputs and potential use cases.

### Contributions to the Developer Guide (DG)
- Documented the `ListCommandsCommand`, `ListPersonalTrackerCommand`, and `CompareMappedCommand` in the DG.
- Added UML diagrams to illustrate the flow and structure of the `Storage` class and its interactions with commands, including Sequence and Class Diagrams for each command to detail their interactions with the `Parser`, `Storage`, and user input/output handling.

### Contributions to Team-Based Tasks
- **Code Reviews**: Reviewed several PRs to ensure code quality and adherence to coding standards.
- **Mentoring and Collaboration**: Actively supported team members by providing feedback on command implementations and troubleshooting issues with the `Parser` and `Storage` integration.

### Contributions Beyond the Project Team
- **Forum Contributions**: Provided insights and solutions in forums, particularly regarding CLI tool development and JSON storage handling.
- **Bug Reporting**: Identified and reported bugs in similar CLI projects by other teams to enhance the overall quality of peer projects.

---

### Optional: Developer Guide Contributions (Extracts)
Below are the diagrams I contributed to the Developer Guide:


### Optional: User Guide Contributions (Extracts)
Below are the sections I contributed to the User Guide:

