# Frederick Amal Emerson's Project Portfolio Page

## Project: Flashbang
Flashbang - is a desktop application for creating flashcards and learning in an effective way. The user interacts with it using a CLI with predefied set of commands. It is written in Java, and has about 1000LoC.

# Summary of Contributions
Given below are my contributions to the project.

- ## Features
- Added ability to load and save flashcards to the user's system
- Added Edit command so users can edit saved flashcards and provided various flexibility options to make the process
  smoother
- Added Help Command for easy viewing of available Commands
- Set up basic types Class,FlashCardSet,FlashCard

- ## Enhancement
- Developed the core command for the Flashbang session (#189) and (#190), enabling users to engage in a flashcard-based Q&A session
  with options to reveal answers upon request. Enhanced the command with a timer component to allow users to track the
  time spent on each flashcard and the entire flashcard set.
- Added UI fixes :
- - Added Code Indication Pointer so users are aware of the typing zone to refine user input handling
- - Removed the repetitive spam of available commands for every invalid command with a simple direction to
- - Refactored the Card's ToString component so its more compact, informative and fits in with other command's invokation
- - Conducted refactoring of the `Parser` class (#83) to streamline command parsing using regular expressions by mainly
    fixing regex expressions and logic issues
- - Resolved issues and enhanced the `view` command (#194) and (#196) to ensure accurate display of flashcards.

- ## User Guide
- Wrote feature sections: `view`, `edit`, `search`
    - **Edit**: Detailed instructions on how users can edit flashcards.
    - **View**: Explained the process for viewing flashcards.
    - **search**: Provided a comprehensive guide on using the search feature.
  
- ## Developer Guide
- Wrote ‘Storage component’ section:
    - Explained the role and functionality of the storage in loading and storing flashcards.
- Made Storage Class Diagram:
    - Created a visual representation of the storage class structure and the types of object involved.
- Made Storage Sequence Diagram:
    - Illustrated the sequence of operations involved in both writing and reading flashcards.
  
- ## Testing
- Wrote testcases for ViewAll Command (#206)

- ## Links
- **Code contributed:** [link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=frederickemerson&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&since=2024-09-20&tabOpen=true&tabType=authorship&checkedFileTypes=docs~functional-code~test-code~other&tabAuthor=frederickemerson&tabRepo=AY2425S1-CS2113-T11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
- **PRs:** [list of PRs from GitHub](https://github.com/AY2425S1-CS2113-T11-2/tp/pulls?q=+is%3Apr+author%3Afrederickemerson+)
