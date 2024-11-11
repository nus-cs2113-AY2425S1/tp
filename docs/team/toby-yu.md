# Toby Yu Project Portfolio Page

## Project: EasInternship
### Overview

EasInternship is a desktop tracking application used for tracking internship applications along the various stages
of an application. The user interacts with it using a CLI and the application is written in Java.

By using this tool, the user can:

- **Store Important Internship Information**: Users can input and store key details such as the role, company, duration, required skills, application deadlines, and additional notes.
- **Track Application Status**: Users can save the current status of their application (e.g., not applied, applied, interview scheduled, offer received) and update it as the application progresses.
- **Edit and Manage Internships**: Users can edit saved internships, modify statuses, update deadlines, or remove outdated internships from the list.
- **Filter Internships by Key Criteria**: The user can filter internships by relevant skills, application status, or upcoming deadlines.
-  **Sort Internships by Key Criteria**: Users can sort tracked internships by relevant criteria such as required skills, application status, or upcoming deadlines, helping them prioritize applications.
- **Monitor Deadlines Easily**: The tool helps users stay on top of their internship deadlines with sorting features and deadline alerts.

---

## Summary of Contributions

### New Feature: SortCommand Implementation
- **What it does**: The `SortCommand` allows users to sort their list of internships by various criteria such as skills, status or role alphabetically and deadline or duration in chronological order . This improves the ease of managing and prioritizing multiple internship applications.
- **Justification**: This feature is crucial for users who want to quickly identify upcoming deadlines or sort applications by specific attributes like company or position. It enhances the organizational capabilities of the application, making it more user-friendly.
- **Highlights**: The implementation required modifying the internal data structures to allow dynamic sorting based on different fields. It also involved significant testing to ensure that the various sorting criteria interacted correctly with other commands.
- **Credits**: Some of the sorting logic was inspired by existing Java collection utilities, but the integration required custom implementations to handle the application's data model.

### New Feature: EasInternship Class
- **What it does**: `EasInternship` serves as the main class that manages the core functionality of the application, including the list of internships and their respective commands.
- **Justification**: This class is the backbone of the application and integrates various features like adding, editing, and deleting internship entries. It also handles the interaction between user commands and the underlying data structure.
- **Highlights**: Designing the `EasInternship` class required careful consideration of how to structure the main components of the system and how they would interact in a modular and maintainable way.

### Code Contributed
- My code contributions can be found in the [RepoSense Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=toby-yu&breakdown=true).

### Enhancements to Existing Features
- Enhanced the command system to integrate with the sorting functionality (Pull requests #41, #43).
- Refactored the internship data model to support filtering and sorting features (Pull request #45).
- Improved the application's performance by optimizing the sorting algorithm for large datasets (Pull request #47).

### Testing
- Wrote comprehensive JUnit tests for the `SortCommand` to ensure accuracy across different sorting criteria and edge cases
- Achieved 60% coverage for the `SortCommand` class.

### Documentation Contributions

#### User Guide (UG)
- Authored the sections for the `SortCommand` and `EasInternship` class.
- Added detailed examples for how users can sort their internship applications by various criteria

#### Developer Guide (DG)
- Wrote the implementation details for the `SortCommand`, including a UML sequence diagram illustrating the sorting process.
- Contributed to the data model section, explaining how internships are stored and managed in the `EasInternship` class 

### Project Management
- overseeing the implementation of major features
- Coordinated the team’s effort to address bugs and feature requests during the final stages of development.

### Community Contributions
- Reviewed and provided feedback, focusing on code quality and adherence to project guidelines.
- Contributed to class-wide discussions on the forum, offering suggestions for improving code quality and project workflow.

### Contributions Beyond the Project Team
- Provided feedback for other teams’ DeveloperGuide during the peer review phase (examples: [PR #25](https://github.com/nus-cs2113-AY2425S1/tp/pull/25/files/f0b67d32a197dce41b7895792c204b6902da091c)).

---

## Optional Contributions to the Developer Guide (Extracts)

### SortCommand Class
The `SortCommand` class allows users to sort the list of internships based on specific criteria such as role, or skills alphabetically and duration, deadline. Here's an excerpt of the sequence diagram for the `SortCommand`

### Value Proposition
The value proposition parts shows the problem we solve in this project

### User Stories
Mention the situation and reason why user need such functions or design.