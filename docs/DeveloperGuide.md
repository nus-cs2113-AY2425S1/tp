# JavaNinja Developer Guide
Java Ninja is a command-line interface (CLI) learning tool focused on helping beginner programmers enhance their understanding of fundamental programming concepts.

- [Design & implementation](#design-&-implementation)
- [Product scope](#product-scope)
    - [Target user profile](#target-user-profile)
    - [Value proposition](#value-proposition)
- [User Stories](#user-stories)
- [Non-Functional Requirements](#non-Functional-Requirements)
- [Glossary](#Glossary)
- [Instructions for manual testing](#Instructions-for-manual-testing)

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}
- [Apache Commons Lang Library](https://commons.apache.org/proper/commons-lang/) - Used for string utilities.
- [JUnit 5](https://junit.org/junit5/) - Used for testing.
- Code adapted from [AddressBook-Level3](https://github.com/se-edu/addressbook-level3).

JavaNinja uses the following tools for development:
- JUnit - Used for testing.
- Gradle - Used for build automation.

## Design & implementation
  
### Design

### Java-Ninja Implementation
`JavaNinja` serves as the main entry point for the command-line-based Java application, managing the initialization, control flow, and shutdown of the application. It composes of three core components:

1. `Cli` - Handles interactions with the user, including input and output operations.
2. `Parser` - Interprets user commands and delegates tasks to the appropriate component.
3. `QuizManager` - Manages quiz-related functions such as selecting quizzes, executing quiz sessions, and saving quiz results.

Here is the class diagram highlighting the structure of the `JavaNinja` class:
![JavaNinja](https://github.com/user-attachments/assets/2cfd1337-2e2e-4a64-8fd0-60aaaf04fb14)

#### How `JavaNinja` works:
1. **Application Initialization**:
   - Upon instantiation, JavaNinja initializes the core components: Cli, Parser, and QuizManager.
   - Cli is responsible for managing user input and output, while Parser interprets user commands and directs actions to QuizManager, which handles quiz operations.
2. **Application Flow**:
   - JavaNinja uses a loop in the run() method to keep the application active until the user decides to quit.
   - Inside the loop, Cli reads the user's input, and Parser interprets the command. Based on the command, Parser delegates tasks to QuizManager.
3. **Command Execution**:
   - Parser processes commands such as "view," "select," "review," "help," and "add." 
   - Each command invokes specific methods in QuizManager or Cli, such as displaying available quizzes, starting a quiz session, reviewing past results, or displaying help.
4. **Shutdown and Cleanup**:
   - Upon receiving the "quit" command, JavaNinja terminates the loop, triggering the shutdown sequence.
   - QuizManager saves any unsaved quiz results, and Cli displays a goodbye message and closes any open resources like the input scanner.

The `JavaNinja` class acts as a controller that organizes interactions between `Cli`, `Parser`, and `QuizManager`, ensuring a modular and maintainable structure where each component has distinct responsibilities.

### `Parser` class:
The `Parser` class is responsible for interpreting and handling user commands. 
It processes input strings from the command-line interface and routes these commands to the appropriate methods within QuizManager or Cli to execute corresponding actions.

#### How `Parser` works:
- The determineCommand(String input) method is the main entry point for interpreting user commands. It first calls processCommand(input) to isolate the command keyword, then uses a switch statement to identify the corresponding action:
  - `view`: Invokes `quizManager.printTopics()` to display all available quiz topics.
  - `select`: Calls `quizManager.selectTopic(topic)` to initiate a quiz on the specified topic. If no topic is specified, prompts the user to enter one.
  - `review`: Fetches and displays past quiz results by calling `quizManager.getPastResults()`.
  - `help`: Calls `cli.printHelp()` to show a help message with available commands.
  - `add`: Uses `quizManager.addFlashcardByUser(input)` to add a new flashcard question created by the user.
  - Default case: Prints an error message for unrecognized commands, suggesting the "help" command for guidance. 
- Any IOException encountered during command execution is caught and logged


The sequence diagram below demonstrates the interactions within the `Parser` component when a user inputs the command `select Loops`:
![ParserSequenceDiagram](https://github.com/user-attachments/assets/a5186ef9-a368-46d0-9031-32de40ec5003)

### `QuizManager` class: 
The `QuizManager` class serves as the primary controller for quiz functionalities within the application. 
It manages quiz operations such as starting and tracking quizzes, handling topics, saving results, and providing feedback.

Below is the class diagram for the `QuizManager` class, illustrating its attributes and methods.

Firstly, the overarching class diagram:

![QuizManagerArchitecture](https://github.com/user-attachments/assets/28efc5ae-9258-48d0-890b-2ee61498dbc0)

Additional details about its methods and attributes:

![QuizManager](https://github.com/user-attachments/assets/acf8bd44-f2fd-4f3d-b424-af77bd1e394f)

####  How `QuizManager` Works
- Manage Topic and Questions:
  - Manages quiz topics through TopicManager, enabling easy addition, retrieval, and organization of topics and questions.
  - Loads questions and topics from a file at startup, ensuring data availability when the application begins.
- Quiz Execution:
  - Starts a quiz session on a specific topic selected by the user via QuizSession.
  - Tracks the quiz’s progress and calculates the score, providing feedback upon quiz completion through QuizResults.
- Persistence of Quiz Data:
  - Loads previously saved quiz data from files when the application starts, making past results and quiz content readily accessible.
  - Saves new quiz data, including quiz questions and results, ensuring all data is preserved across sessions.
- Review Past Results:
  - Allows users to review their scores and feedback from previous quiz attempts, offering insights into their progress and areas for improvement.

Over here is a continuation of `Select Loops` as a sequence diagram in a more detailed level:
![QuizManagerSequenceDiagram](https://github.com/user-attachments/assets/8e41e10c-527a-45f3-87fc-0de21c506075)

### `TopicManager` class:
The `TopicManager` class is responsible for managing various topics within the application. It provides methods to add, retrieve, and organize topics, as well as handle loading and saving of question data. By interacting with `TopicManager`, users can create new topics, add questions to them, and persistently save or retrieve questions from an external storage file.

#### How `TopicManager` works:
- The `getOrCreateTopic(String topicName)` method allows users to retrieve an existing topic by name or create a new one if it doesn't exist, ensuring that topics are always unique.
- The `addTopic(Topic topic)` method adds a new topic to the list only if it’s not already present, ensuring uniqueness and avoiding duplication.
- The `printTopics()` method displays all topics currently managed by the application, logging each topic name to provide feedback to the user.
- The `addFlashcardByUser(String input)` method parses user input, creates a `Flashcard` question, adds it to the designated "Flashcards" topic, and saves it to the file.
- The `loadQuestions()` method reads questions from the specified file, parses each line, and adds the corresponding question to the appropriate topic by calling `parseTopic(String line)`.
- The `parseTopic(String line)` method interprets the content of each line in the questions file and creates appropriate question objects, such as `Flashcard`, `Mcq`, `TrueFalse`, or `FillInTheBlank`. It uses the first part of the line to assign the question to the right topic, creating the topic if necessary.

#### `TopicManager` class diagram:
![TopicManager](https://github.com/user-attachments/assets/9dc6f906-28f9-4a50-9ecc-8672e55da39a)

### Topic Instantiation
When `loadQuestions()` reads each line from the storage file, `parseTopic()` is invoked to create or retrieve the correct topic. This ensures that each question in the file is organized within its corresponding topic. If the topic is already present, it is retrieved from the list of topics; if it does not exist, a new `Topic` instance is created and added to the list. This process ensures that topics and their associated questions are correctly structured, even if the file contains new topics or question types.

The following sequence diagram shows the interactions within `TopicManager` during topic initialisation from the storage file.
![TopicInstantiationSequenceDiagram](https://github.com/user-attachments/assets/562bffb0-c24e-422f-a16b-dadd988e8291)

### True/False Feature Implementation

The `TrueFalse` class is responsible for handling true/false questions within the quiz application. This section describes its design, implementation details, and the rationale behind design choices.

#### Feature Overview
The `TrueFalse` class represents a true/false question. It stores the question text and the correct answer as a boolean. Additionally, it includes methods to validate user answers and display answer options.

#### Implementation Details

1. **Attributes**:
   - `boolean correctAnswer`: Stores the correct answer for the question.

2. **Constructor**:
   - `TrueFalse(String questionText, boolean correctAnswer)`:
     Initializes the question text and the correct answer. The constructor checks that `questionText` is non-null and non-empty, ensuring a valid question is always provided.

3. **Methods**:
   - `checkAnswer(String userAnswer)`:
     This method converts the user's input (`"true"` or `"false"`) into a boolean and compares it to the correct answer. It throws an `IllegalArgumentException` for invalid inputs, ensuring only `"true"` or `"false"` strings are accepted.
   - `printOptions()`:
     Displays answer choices ("1. True" and "2. False") for the user. This makes it clear what options are available for true/false questions.
   - `toString()`:
     Returns a string representation of the question with a "(True/False)" label to indicate its type.

#### Design Rationale

- **Boolean Storage for Correct Answer**: Storing the answer as a boolean simplifies the process of validating the user’s response since the comparison is a straightforward boolean check.
- **Input Validation in `checkAnswer`**: This method ensures only `"true"` or `"false"` strings are accepted as valid answers, which prevents user input errors.
- **Assertion Usage**: Assertions check that the question text and user answer are valid. This adds an additional layer of error handling during development.

#### Alternative Considerations

An alternative approach considered was to store `correctAnswer` as a `String` (`"true"` or `"false"`), which would simplify displaying it as text but would complicate validation. Using `boolean` was chosen for its simplicity and efficiency in logic checks.

#### UML Class Diagram

![TrueFalse Class Diagram](https://github.com/Lucky-Yuan00/tp/blob/jinlin-dev/docs/UML/TrueFalseClassDiagram.png?raw=true)

The class diagram above shows the structure of the `TrueFalse` class, including its attributes and methods, and its inheritance relationship with the `Question` superclass.

### Fill-in-the-Blank Feature Implementation

The `FillInTheBlank` class handles fill-in-the-blank questions in the quiz application. This section provides an in-depth overview of its design, implementation details, and the reasoning behind key design choices.

#### Feature Overview
The `FillInTheBlank` class represents a fill-in-the-blank question, where users provide an exact text answer to complete the question. It stores the question text with a placeholder for the blank and the correct answer as a string. It includes methods to validate the user’s answer and present the question in a way that indicates where the blank is located.

#### Implementation Details

1. **Attributes**:
    - `String correctAnswer`: Stores the correct answer text for the blank in the question.
    - `String questionText`: Stores the question text with a placeholder (e.g., `The `____` keyword is used to create a new object in Java.`).

2. **Constructor**:
    - `FillInTheBlank(String questionText, String correctAnswer)`: Initializes both the question text and correct answer. The constructor ensures that `questionText` includes a blank placeholder and that `correctAnswer` is non-null and non-empty, validating that the question is properly structured.

3. **Methods**:
    - `checkAnswer(String userAnswer)`:
      Compares the user’s answer to `correctAnswer`, ignoring case sensitivity to allow for minor capitalization differences. Throws an `IllegalArgumentException` for null or empty inputs, ensuring only valid responses are considered.
    - `printQuestion()`:
      Displays the question text with the blank to the user, making it clear where they need to provide an answer.
    - `toString()`:
      Returns a formatted string representation of the question, clearly marking it as a fill-in-the-blank question for clarity within the quiz.

#### Design Rationale

- **String Storage for Correct Answer**: The answer is stored as a string to allow precise text matching, which is necessary for fill-in-the-blank questions. This simplifies checking whether the user's input matches the correct answer exactly (or approximately, if extended to support partial matching).
- **Input Validation in `checkAnswer`**: By ensuring only non-null, non-empty strings are accepted as answers, `checkAnswer` reduces the risk of invalid input and enhances robustness.
- **Placeholder in Question Text**: Using a placeholder (e.g., `"_____"`) in the question text makes it clear to users where they should mentally "fill in the blank," improving usability.

#### Alternative Considerations

1. **Implementing Case-Sensitive Comparison**: An initial approach was to use case-sensitive comparison for `checkAnswer`, but it was modified to ignore case for a more forgiving user experience.
2. **Approximate Matching for Answers**: To increase flexibility, an alternative approach could involve approximate matching, allowing answers that closely resemble the correct answer to be accepted (e.g., `"paris"` vs. `"Paris"`). This would, however, add complexity to the validation process and was ultimately deemed unnecessary for this basic implementation.

#### UML Class Diagram

![FillInTheBlank UML Class Diagram](https://github.com/YubotKwng/tp/blob/e3358e1cc869012c202864071fbb75f2f8261712/docs/UML/FITBClassDiagram.png)

The diagram illustrates the `FillInTheBlank` class with its attributes and methods, and its inheritance relationship with the `Question` superclass. This setup allows `FillInTheBlank` to share common functionality with other question types while implementing unique behavior specific to fill-in-the-blank questions.

### Topic Class Implementation
The `Topic` class is responsible for organizing questions under specific programming topics in the quiz application. It allows for modular storage of questions, making it easy for users to select topics and for developers to manage quiz content.

#### **Feature Overview**
The `Topic` class encapsulates a programming topic along with its related questions. It helps in grouping relevant questions, enabling quizzes to be topic-based, and provides essential methods to add and access questions within each topic.

#### **Implementation Details**

1. **Attributes**:
    - `String name`: Stores the name of the topic.
    - `List<Question> questions`: Holds a list of questions related to this topic.

2. **Constructor**:
    - `Topic(String name)`:  
      Initializes the topic with a specified name and creates an empty list to hold its questions. Ensures that the `name` is non-null to prevent invalid topics.

3. **Methods**:
    - `addQuestion(Question question)`:  
      Adds a `Question` object to the topic’s question list.
    - `List<Question> getQuestions()`:  
      Returns the list of questions under the topic.
    - `String getName()`:  
      Returns the name of the topic as a string.

#### **Design Rationale**

- **Encapsulation**: Storing questions inside a `Topic` object allows better separation of concerns and makes the quiz system more modular and extendable.
- **Modularity for Scalability**: Organizing questions by topics makes the quiz system scalable, as more topics and questions can be easily added.
- **Single Responsibility Principle (SRP)**: The `Topic` class focuses only on managing a single topic and its related questions, adhering to SRP.

#### **Alternative Considerations**

1. **Global List for Questions**:
    - *Drawback*: Having a global list would make it harder to categorize questions logically and efficiently.

2. **Direct Integration into QuizManager**:
    - *Drawback*: This would tightly couple topic management with quiz functionality, making the code harder to maintain and less reusable.

#### UML Class Diagram
Below is the class diagram for the `Topic` class, illustrating its attributes and methods.
![Topic Class Diagram](https://github.com/naveen42x/tp/blob/dg/docs/UML/TopicClasDiagram.png?raw=true)

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

## Product scope
### Target user profile

Beginner programmers looking to improve their proficiency in Java and command-line skills.

### Value proposition

This tool will offer an interactive learning experience through a series of tasks and challenges designed to deepen users' understanding and proficiency in both command-line operations and Java language.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

1. Technical Requirements: Any mainstream OS, i.e. Windows, macOS or Linux, with Java 17 installed.
2. Project Scope Constraints: The application should only be used for practice and reviewing score progress
3. Project Scope Constraints: Data storage is only to be performed locally.
4. Quality Requirements: The application should be able to be used effectively by a novice with little experience with CLIs.

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

### Manual Testing
View the [User Guide](https://AY2425S1-CS2113-W12-4.github.io/tp/UserGuide.html) for the full list of UI commands and their related use case and expected outcomes.

### JUnit Testing
JUnit tests are written in the subdirectory `test` and serve to test key methods part of the application.