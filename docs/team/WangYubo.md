# Wang Yubo's Project Portfolio Page

## Project: JavaNinja

JavaNinja is a desktop learning tool application used for teaching programming concepts. The user interacts with it using a CLI. It is written in Java, and has about 10 kLoC.

Below are my key contributions to the project.

### New Features Implemented:

- **Question Type: Fill in the Blank (FITB):**
  - **Description:** Developed a new question type, "Fill in the Blank," allowing users to input answers directly for validation. This feature includes answer normalization to ensure case-insensitivity and trimmed whitespace for accurate comparison.
  - **Justification:** This new question type enriches the variety of quiz questions available, enhancing the learning experience by adding diverse question formats.
- **Review Function:**
  - **Description:** Implemented a review function to allow users to view their past quiz results, which helps them track their progress and areas of improvement.
  - **Justification:** This feature adds value by providing feedback and historical context, enabling users to reflect on their learning journey.
- **Timer Setup for Quizzes:**
  - **Description:** Introduced a feature where users can set a timer before starting a quiz. The timer counts down and notifies users when their time is up, ending the quiz automatically.
  - **Justification:** This feature encourages time management and simulates real test conditions, which can improve users' test-taking skills.
- **Number of Questions Selection:**
  - **Description:** Enabled users to specify the number of questions they wish to attempt in a quiz, providing flexibility in how they interact with the application.
  - **Justification:** This addition makes the quiz experience customizable, accommodating users who want short or extensive practice sessions.

### Contributions to the User Guide:

- **Formatting and Structure**: Enhanced the layout and organization of the guide for better readability.
- **Sections Contributed**:
    - **Quickstart**: Created an easy-to-follow guide for first-time users.
    - **Features**: Documented detailed usage for commands such as:
        - `view`
        - `select`
        - taking a quiz
        - adding questions
        - `review`
        - `exit`
        - `quit`
    - **Command Summary**: Provided a comprehensive command summary for quick reference.
- **Justification**: These sections ensure that users understand how to use the application effectively, providing a comprehensive guide for various commands.

### JUnit Tests:

- **Comprehensive Test Coverage**:
    - Developed JUnit tests for critical components including:
        - `Cli`
        - `FillInTheBlank`
        - `Parser`
        - `TopicManager`
- **Justification**: Extensive testing ensured reliability and robustness, allowing early identification of issues and validation of new and existing features.
- **Approach**:
    - Tests included both typical and edge cases to verify the application's behavior across a range of scenarios.
    - Focused on maintaining high code coverage and catching potential bugs or errors during development.

### Contributions to the Developer Guide:

- **Detailed Documentation for FITB**:
    - Authored the documentation for the "Fill in the Blank" question type, explaining its implementation and usage.

- **Diagrams Created**:
    - **Class Diagrams**:
        - Developed diagrams for the `Cli` class to illustrate its structure and interactions.
    - **Sequence Diagrams**:
        - Created detailed sequence diagrams for the `Cli` class, showcasing the command handling flow and interactions with other components.

- **Justification**:
    - These contributions helped provide clear visual representations and explanations of the codebase, aiding both current and future developers in understanding and maintaining the project.

### Project Management and Team Contributions:

- **Participation**:
    - Engaged in team discussions and provided constructive feedback during PR reviews to enhance code quality and maintain project standards.
- **Review and Feedback**:
    - Reviewed multiple PRs to ensure adherence to project requirements and suggested improvements for better implementation and optimization.
- **Checkstyle and Code Standards**:
    - Assisted in resolving Checkstyle issues to maintain consistent code formatting and readability across the codebase.
- **Collaboration**:
    - Supported team members by sharing knowledge, troubleshooting problems, and ensuring smooth integration of new features and bug fixes.
