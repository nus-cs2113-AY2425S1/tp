# Java Ninja User Guide

---

## Introduction

Java Ninja is a command-line interface (CLI) learning tool focused on helping beginner programmers enhance their understanding of fundamental programming concepts. This tool provides different modes of assessments with various difficulty levels as you progress.

- [Quickstart](#QuickStart)
- [Features](#Features)

---

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 17 or above installed.
2. Down the latest version of `JavaNinja` from [here](http://link.to/duke).
3. Copy the file to the folder you want to use as the home folder for JavaNinja.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar javaninja.jar command to run the application.
   ```shell
   java -jar javaninja.jar
   ```
5. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.
   Some example commands you can try:
   - `help`: Lists all basic commands.
   - `view`: Displays available topics.
   - `select [topic]`: Selects a specified topic (e.g., `select loops`) to start the quiz.
   - `review`: Displays a summary of quiz results.
   - `exit`: Exits the quiz, activate only while doing the quiz.
   - `quit`: Exits the program.
   - `add Flashcards` : Adds a flashcard for your own quick revision
6. Refer to the features below for each command.

---

## Features 

{Give detailed description of each feature}
### 1. View Topics: `view`
Displays a list of all available quiz topics that you can attempt, such as loops, conditionals, and data types.

**Format**: `view`

**Example**:
   ```shell
   view
   ```

### 2. Select Topic and Start Quiz: `select`
Selects a specific topic and initiates a quiz. Before the quiz starts, you will be prompted to enter:
- A time limit for the quiz (in seconds or minutes).
- The number of questions you wish to attempt.

**Format**: `select [TOPIC_NAME]`

**Example**: 
   ```shell
   select loops
   ```

### 3. Taking a Quiz
Upon selecting a topic, the quiz begins with a series of questions. Depending on the type of question, you will see prompts for multiple-choice, True/False, or fill-in-the-blank responses.

Once a topic is selected, the quiz starts with questions in multiple-choice, True/False, or fill-in-the-blank formats.

* **Exit Quiz**: Type `exit` anytime during the quiz to end it early.
* **Automatic Termination**: The quiz ends automatically if the time limit expires or the specified number of questions is completed.

**Example**:
   ```shell
   Set a time limit for the quiz.
   Enter the number of minutes (or 0 if you want to set seconds): 0
   Enter the number of seconds: 10
   Enter the number of questions you want to attempt: 10
   ```

### 4. Adding a Custom Question: `add Flashcards`
Allows users to add their own quiz questions.

Format: `add Flashcards /q [QUESTION] /a [ANSWER]`

* The `QUESTION` can be in a natural language format.
* The `ANSWER` cannot contain punctuation.  

**Example**:
```shell
add Flashcards /q The keyword used to define a class is `____`. /a class
```

**Output Expected**:
```shell
Added flashcard: Q: The keyword used to define a class is `____`. A: class
```

### 5. Reviewing Past Results: `review`
Displays a summary of all quiz results taken during the current session, including scores and comments on performance.

Format: `review`

**Example**:
```shell
review
```

**Expected Output**:
```shell
Reviewing your past results:
{
 Topic: Loops,
 Score: 0%,
 Time Limit: 60 seconds,
 Questions Attempted: 1,
 Comment: Better luck next time!
}
```


### 6. Exit Quiz: `exit`
Exit the current quiz session if you want to stop answering questions without finishing the quiz.

**Format**: `exit`

**Example**:
```shell
exit
```

### 7. Exit Programme: `quit`
Exits the programme

**Format**: `quit`

**Example**:
```shell
quit
```
---

## FAQ

**Q**: What should I do if I encounter an error saying "Command not recognized"?  
**A**: Ensure you have typed the command correctly without any typos. Use the `help` command to view all available commands and check the correct format.

---

## Command Summary

{Give a 'cheat sheet' of commands here}

| Command               | Description                                        |
|-----------------------|----------------------------------------------------|
| `view`                | View all available quiz topics.                    |
| `select [TOPIC_NAME]` | Select a topic and start the quiz for that topic.  |
| `add Flashcard`       | Add a custom flashcard question.                   |
| `review`              | View results of quizzes taken during this session. |
| `help`                | List all commands with descriptions.               |
| `exit`                | Exit the quiz.                                     |
| `quit`                | Exit the program.                                  |


## Troubleshooting

- **Issue**: The application does not start when running the `java -jar javaninja.jar` command.
  - **Solution**: Make sure Java 17 or above is installed and properly configured in your system's PATH. You can check your Java version with `java -version`.

- **Issue**: Commands are not recognized or produce unexpected results.
  - **Solution**: Ensure correct command syntax. Use the `help` command to verify command formats.
