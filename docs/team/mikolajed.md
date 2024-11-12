# Mikolaj Jedrzejewski's Project Portfolio Page

## Project: Flashbang
Flashbang - is a desktop application for creating flashcards and and learning in an effective way. The user interacts with it using a CLI with predefied set of commands. It is written in Java, and has about 1000LoC.

Given below are my contributions to the project.

- **New feature** Added ability to perform a quiz for a set of flashcards as it is called - Flashbang. 
    - What is does: the part implemented by me goes through all flashcards and displays a question and asks user about the answer.
    - Justification: it is an essential part of the app to learn from flashcards - getting a question and not revealing the answer.
    - Highlights: It is the most complex command that is used - it uses UI component for both input and output.
- **New feature/Enhancement** Did major refactoring of Parser class.
    - It uses regular expression for extracting command type and the parameters.
    - It uses a factory design pattern to create commands. 
    - Command creation separate from execution.
- **New feature** Added ability to display formatted outputs in the UI class and getting inputs.
- **New feature** Implemented command for viewing all flashcards in the app. 
- **Enhancement** Removed dependency of command execution from Storage component.
    - Was it hard: No, only one command was using it and that responsibility was moved elsewhere. 
- **Testing** Added JUnit tests for the Parser class.
- **Code contributed:** [link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=mikolajed&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=mikolajed&tabRepo=AY2425S1-CS2113-T11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
- **Documentation**
    - Added sequence diagram for Parser and described its workings. 
    - Updated UG with a few LoC   
    - Unified style for PlantUML diagrams.
- **Community**
    - PRs: [list of PRs from GitHub](https://github.com/AY2425S1-CS2113-T11-2/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me)
