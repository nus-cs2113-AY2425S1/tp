# Shaun Goh's Project Portfolio Page

## Project: JavaNinja

JavaNinja is a desktop learning tool application designed for teaching programming concepts. Users interact with the tool via a Command-Line Interface (CLI).

Below are my key contributions to the project:

Here is also a link to the code I have contributed:

[Link to Contribution Dashboard for Shaun Goh](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=shaunngoh&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=shaunngoh&tabRepo=AY2425S1-CS2113-W12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

---

### Main Classes and Structure

- **Primary Class Structure and Relationships**:
    - **Primary Class Structure and Relationships**:
    - Created foundational classes: `Parser`, `Cli`, `JavaNinja`, `QuizGenerator`, `QuizManager`, `TopicManager`, `Topic`, `Question`, and `Quiz`.
    - Each class was designed to adhere to the **Single Responsibility Principle (SRP)**, ensuring that each class has a well-defined purpose and focuses on a single aspect of the application’s functionality. This makes the codebase modular, maintainable, and extensible for future features.
    - Designed an object-oriented structure where each quiz session takes in a topic containing various question types, supporting modularity and extensibility.
        - Eventually allows for different types of generated quizzes, such as quizzes of multiple topics, timed quizzes, or untimed quizzes.

---

### New Features Implemented

- **CLI and Parser Functionality**:
    - **Command-Line Interface (CLI)**:
        - **Enhanced User Interactions**: Implemented detailed, user-friendly messaging within CLI to guide users through commands and inputs.
        - **Dynamic Command Handling**: Developed CLI functionality to respond dynamically to user inputs, providing options and prompts based on the current context.
    - **Parser**:
        - **Flexible Command Parsing**: Implemented flexible parsing that supports multiple command formats (e.g., `select /d timed /t TOPIC | random`). The parser adapts to various user inputs and interprets both simple and complex commands.
        - **Robust Error Handling**: Enhanced error detection and handling within the parser to provide specific feedback on command issues and guide users toward correct usage.


- **Randomized Topic Selection in a Quiz Session**:
    - **Description**: Enabled users to take a quiz that randomly selects questions from multiple topics. Users can specify the number of topics and questions for a varied session.
    - **Justification**: Diversifies the learning experience by reinforcing concepts across subjects in one quiz session.

- **MCQ and Question Type Selection**:
    - **Description**: Added multiple question types, including Multiple-Choice Questions (MCQ) and `Fill in the Blank (FITB)`, with options for timed and untimed sessions.
    - **Justification**: Provides different formats and timed options, adding challenge and flexibility to the quizzes.

- **Codebase Structure Improvements**:
    - **Description**: Redesigned and improved the code structure to follow object-oriented principles, enhancing modularity and cohesion.
    - **Justification**: Simplifies future development, enabling new features with minimal refactoring.

---

### Contributions to the User Guide

- **Enhanced Documentation for Randomized Topics**:
    - Explained usage of the randomized topic selection feature and provided clear example outputs.

- **Consistent Formatting**:
    - Edited the guide to ensure that example commands, outputs, and descriptions were clear and consistent.

- **Sections Contributed**:
    - Added detailed instructions for commands such as:
        - `select` for choosing topics or taking random quizzes
        - `view` and `review` for scores and history
        - `exit` and `quit` for ending sessions

---

### JUnit Tests

- **Extensive Test Coverage**:
    - Developed comprehensive JUnit tests for key components: `QuizGenerator`, `Parser`, `QuizManager`, `JavaNinja` and `TopicManager`.
    - **Justification**: Tests covered typical and edge cases, ensuring robustness and reliability.
    - **Approach**: Focused on high code coverage by testing individual methods and workflows, designing tests to catch bugs in both existing and new features.

---

### Contributions to the Developer Guide

- **Documentation and Formatting**:
    - Documented the `QuizGenerator`, `QuizManager`, `Parser`, `JavaNinja` and `TopicManager` classes with explanations of their structure and functions.
    - Reformatted the Developer Guide for readability, making it accessible for new developers.

- **Diagrams Created**:
    - **Class Diagrams**:
        - Created diagrams for `QuizGenerator`, `QuizManager`, `Parser`, `JavaNinja` and `TopicManager`, illustrating their structures and relationships.
    - **Sequence Diagrams**:
        - Developed sequence diagrams for each of the classes stated above and it's complex flows, such as quiz selection and random topic generation, to demonstrate internal interactions.

---

### Project Management and Team Contributions

- **Feature and Code Review**:
    - Conducted detailed code reviews, ensuring consistency, optimization, and adherence to coding standards.
    - Resolved Checkstyle issues across the codebase for uniform formatting and readability.

- **Collaborative Development**:
    - Actively engaged in team discussions, sharing insights on troubleshooting, debugging, and strategising new features.

---

### Summary

Through my contributions, I enhanced JavaNinja by implementing new features, improving the code structure, enhancing the User and Developer Guides, and providing comprehensive testing. My work aimed to make the application intuitive for users and maintainable for developers, establishing a foundation for future growth.