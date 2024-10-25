# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

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

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
