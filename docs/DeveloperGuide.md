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

#### Architecture 

Main components of the architecture:

`Main` (consisting of class `JavaNinja`) is in charge of the app launch and shut down. 
1. At app launch, it initialises the components in the correct order and connects them with each other
2. At shut down, it shuts down the other components and invokes cleanup methods 

The bulk of the app's work is done by the respective components: 
1. `Cli`: It handles the user interface of the app 
2. `Parser`: The command executor 
3. `QuizManager`: Holds the data of the App in memory
4. `Storage`: Reads data from, and writes data to, the hard disk.

`Commons` represent a collection of classes used by other components.

How the architecture components interact with each other: 

The Sequence Diagram below shows how the components interact with each other for the scenario where the user issues the command `add Flashcard /q what's 2+3 /a 5`

![image](https://github.com/user-attachments/assets/1dd443de-b538-4fb8-b4d1-3bff4643b3dd)


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