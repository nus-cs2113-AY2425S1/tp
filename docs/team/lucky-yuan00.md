# Jinlin's Project Portfolio Page

## Project: JavaNinja

JavaNinja is a desktop learning tool application designed to help users improve their programming knowledge through quizzes. The application uses a CLI interface, is implemented in Java, and comprises around 10 kLoC.

Below are my key contributions to the project.

### New Features Implemented:

- **Display Time Left Feature**:
    - **Description**: Added a feature to display time remaining in the quiz, with notifications at critical intervals: 3 minute, 1 minute, 30 seconds, and 10 seconds.
    - **Justification**: This feature enhances user experience by keeping users aware of their time constraints, encouraging efficient completion of quizzes.

- **JUnit Testing and Documentation for Developer Guide**:
    - **Description**: Expanded the JUnit testing coverage for core classes such as `Parser` and `QuizSession`, including detailed documentation of tests in the Developer Guide.
    - **Justification**: The added tests ensured reliable functionality and supported error handling within the application, increasing robustness.
    - **Credits**: Designed and implemented with extensive JUnit assertions to improve overall testing reliability.

### Diagrams and Documentation:

- **Class and Sequence Diagrams for `QuizSession`**:
    - **Description**: Created and refined the class and sequence diagrams for the `QuizSession` component in the Developer Guide, providing clear visual representations of quiz functionalities and user interactions.
    - **Justification**: These diagrams clarified the flow and structure of `QuizSession` for developers, supporting accurate implementation and future updates.

### Enhancements to Existing Features:

- **QuizManager Updates**:
    - Enhanced the `QuizManager` class with new methods and added assertions and logging to support tracking quiz states and user inputs.

- **True/False Functionality**:
    - Completed the implementation for handling True/False questions, including input validation and error handling to prevent invalid answers.

- **Storage Update**:
    - Developed `saveQuestionToFile` in the `Storage` class to address missing method errors in `QuizManager`, ensuring correct data management.

### Code Contributions:

- **Detailed Contributions**: [View RepoSense Report](#)

### Project Management and Documentation:

- **Developer Guide**:
  - **Class Diagrams**: Enhanced the Developer Guide with detailed class diagrams for multiple key modules:
    - Added a complete class diagram for the `QuizSession` class.
    - Created a diagram illustrating relationships involving the `True/False` class.
    - Developed an initial overall class diagram for the entire project.

    These diagrams thoroughly depict each class, their responsibilities, hierarchy, and interactions, providing a cohesive framework for understanding the structure and promoting consistency and scalability across the team.

  - **Sequence Diagrams**:
    - Created an initial sequence diagram for processes related to the `True/False` class, detailing logical interactions and state changes when handling True/False questions.
    - Developed a comprehensive sequence diagram for the `QuizSession` module, mapping out the entire quiz lifecycle, from initiation and time management to user interactions and completion.

    These visual representations serve as an intuitive guide for subsequent development and testing, helping team members design new features aligned with the current project structure.

- **User Guide**:
    - Added feature explanations, including display time left functionality and sample use cases for user understanding.

### Community and Teamwork:

- Reviewed PRs and participated in discussions, providing feedback on error handling and optimization.
- Fixed Checkstyle issues to ensure consistent code formatting and readability.
