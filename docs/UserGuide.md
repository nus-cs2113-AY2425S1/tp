# Java Ninja User Guide

---

## Introduction

Java Ninja is a command-line interface (CLI) learning tool focused on helping beginner programmers enhance their understanding of fundamental programming concepts. This tool provides different modes of assessments with various difficulty levels as you progress.

- [Quick Start](#quick-start)
- [Features](#features)
- [Command Summary](#command-summary)
- [Troubleshooting](#troubleshooting)

---

## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `JavaNinja` from [here](http://link.to/duke).
3. Copy the file to the folder you want to use as the home folder for JavaNinja.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar javaninja.jar command to run the application.
   ```shell
   java -jar javaninja.jar
   ```
5. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.
   Some example commands you can try:
   - `help`: Lists all basic commands.
   - `view`: Displays available topics.
   - `select /d timed|untimed /t [topic]`: Selects a specified topic (e.g., `select /d timed /t loops`) to start the quiz.
   - `review`: Displays a summary of quiz results.
   - `exit`: Exits the quiz, activate only while doing the quiz.
   - `quit`: Exits the program.
   - `add Flashcards` : Adds a flashcard for your own quick revision
6. Refer to the features below for each command.

---

## Features

### 1. View Topics: `view`
Displays a list of all available quiz topics that you can attempt, such as loops, conditionals, and data types.

**Format**: `view`

**Example**:
   ```shell
   view
   ```

### 2. Select Topic and Start Quiz: `select`
This command allows you to select a specific topic or a random selection of topics to initiate a quiz. Before starting, you will be prompted to enter:
- A time limit for the quiz (in seconds or minutes) if the quiz is timed.
- The number of questions you wish to attempt.
- /d: Specifies whether the quiz should be timed or untimed
- /t: Specifies the topic for the quiz. You can provide a specific topic name or use random to select a mix of questions from multiple topics.
  - If random is chosen, you will be prompted to specify how many topics you'd like to be tested on, and how many questions per topic.

**Format**: `select /d timed|untimed /t TOPIC|Random`

**Example**: 
   ```shell
    select /d timed /t loops
    OR
    select /d untimed /t random
   ```

### 3. Taking a Quiz
Once a topic is selected, the quiz begins with a series of questions in multiple-choice, True/False, or fill-in-the-blank formats.

> Note: The question types are randomised, so you may not be able to see all the question formats displayed in a single quiz session!

* **Exit Quiz**: Type `exit` anytime during the quiz to end it early.
* **Automatic Termination**: The quiz ends automatically if the time limit expires or the specified number of questions is completed.

This countdown feature ensures that users are aware of time constraints, enhancing the user experience and preventing unexpected quiz terminations.

**Example**:
   ```shell
   Set a time limit for the quiz.
   Enter the number of questions you want to attempt (Max 15): 10
   Enter the number of minutes for the quiz (or 0 for seconds): 
   Enter the number of seconds: 10
   ```

### 4. Adding a Flashcard: `add Flashcards`
Allows users to add their own quiz questions. Useful for your own revision in the future!

> Note: You're not allowed to add in any other custom topics other than flashcards!

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

| Command                                      | Description                                                        |
|----------------------------------------------|--------------------------------------------------------------------|
| `view`                                       | View all available quiz topics.                                    |
| `select /d timed OR untimed /t [TOPIC_NAME]` | Select a timed or untimed topic and start the quiz for that topic. |
| `add Flashcards`                             | Add a custom flashcard question.                                   |
| `review`                                     | View results of quizzes taken during this session.                 |
| `help`                                       | List all commands with descriptions.                               |
| `exit`                                       | Exit the quiz.                                                     |
| `quit`                                       | Exit the program.                                                  |


## Troubleshooting

- **Issue**: The application does not start when running the `java -jar javaninja.jar` command.
  - **Solution**: Make sure Java 17 or above is installed and properly configured in your system's PATH. You can check your Java version with `java -version`.

- **Issue**: Commands are not recognized or produce unexpected results.
  - **Solution**: Ensure correct command syntax. Use the `help` command to verify command formats.
