# Developer Guide

## Acknowledgements
We used these third party libraries to develop our application:
- Gson
- Mockito

## Design & implementation

### Edit Programme 

#### Feature Implementation
Edit Programme encompasses all functionality related to editing programme details. It is facilitated by the various
insert, delete and update functionality that is present in Programme, Exercise and Day respectively.

For reference, the following models how ProgrammeList and its various contained classes are structured.

![](images/programmeModel.png)

Due to the nested nature of this data, all edit commands will traverse from ProgrammeList > Programme > Day > Exercise until
it reaches the necessary depth to accomplish the relevant operation.

These operations include:
- Adding or removing Days to the Programme
- Adding or removing Exercises to Days in the Programme
- Updating the details of Exercises in Days in the Programme

Given below is an example usage scenario for 'delete exercise' and how the edit programme functions at each step.

Step 1. The user creates a programme with a given number of Days with their respective Exercises. ProgrammeList will contain a reference to this programme after its creation.

![](images/editCommandStepOne.png)

Step 2. The user executes `programme edit /p 1 /d 1 /x 1` to delete the first exercise in the first day of the first programme. The programme first retrieves the given day with `ProgrammeList#getDay()`.

![](images/editCommandStepTwo.png)

Step 3. With the Day object, it performs the `Day#deleteExercise()` with the given exercise ID

![](images/editCommandStepThree.png)

Step 4. The deleted Exercise object is then returned to the `DeleteExerciseCommand` to display as part of the CommandResult.

![](images/editCommandStepFour.png)

The overall design that enables this functionality is described generically by the following sequence diagram.

![Edit Command generic sequence](images/editCommand.png)

The 'Model' class in the above diagram is a generalization of the various data models that are being interacted with
to perform each specific edit command. For each edit command, the following sequence diagrams 
further break down how this interaction works.

##### Add/Remove day
![Add/Remove Day](images/addDayCommand.png)

##### Add/Remove exercise
![Add/Remove Exercise](images/addExerciseCommand.png)

##### Update exercise
![Edit Exercise](images/editExerciseCommand.png)

To summarize, the following activity diagram describes how the overall operation occurs.

![](images/editCommandActivityDiagram.png)

#### Design Considerations

##### Chosen Approach: Hierarchical Command Pattern
The current implementation uses a hierarchical command pattern with factories where:
1. Commands traverse through ProgrammeList > Programme > Day > Exercise
2. Each level handles its own specific edit operations
3. Changes are propagated upwards through the hierarchy

**Key Benefits**
- **Encapsulation**: Each layer manages its own data and operations
- **Single Responsibility**: Each class handles only its specific level of edits
- **Extensibility**: Easy to add new edit operations at any level
- **Maintainability**: Changes to one level don't affect others

##### Alternative Approaches

**1. Direct Access Pattern**

Instead of traversing the hierarchy, directly access and modify the target object.

```java
class ProgrammeList {
    public Exercise getExercise(int progId, int dayId, int exerciseId) {
        return programmes.get(progId)
                        .getDays().get(dayId)
                        .getExercises().get(exerciseId);
    }
    
    public void editExercise(int progId, int dayId, int exerciseId, ExerciseDetails details) {
        Exercise exercise = getExercise(progId, dayId, exerciseId);
        exercise.update(details);
    }
}
```
Pros: 
- Simpler to implement
- Command calls will be "flattened" to only ProgrammeList class
- Fewer custom methods needed for edit operations

Cons:
- Violates encapsulation by exposing internal structure
- Complicates validation and error handling
- Reduces flexibility for future changes

**2. Visitor Pattern Approach**

Using a visitor pattern to traverse the hierarchy and perform edits.

```java
interface ProgrammeVisitor {
    void visitProgramme(Programme prog);
    void visitDay(Day day);
    void visitExercise(Exercise exercise);
}

class EditVisitor implements ProgrammeVisitor {
    private final EditDetails details;
    
    @Override
    public void visitExercise(Exercise exercise) {
        // Perform edit operation
    }
    // Other visit methods...
}
```
Pros:
- Reduces coupling between ProgrammeList components
- Easy to extend and maintain edit functionality
- Respects Separation of Concern by abstracting editing functionality to a separate file

Cons:
- Considered overkill for the fixed scope of the feature (unlikely to add any more types of edit operations)
- Complicates testing by requiring Mocks or Reflection
- Difficult to track operation flow 

## Product scope
BuffBuddy is a fitness tracking app that help you track workout, meals, water to aid you in achieving your body goals.
### Target user profile

Gym goers who need a quick way to create, manage and track their progress

### Value proposition

* Users will be able to quickly create, update and view their workout programmes
* Users will be able to track their progress as they progress on their fitness journey
* Users will be able to track water and calorie intake to better track their nutrition

## User Stories

| Version | As a ...               | I want to ...                                           | So that I can ...                                      |
|---------|------------------------|---------------------------------------------------------|--------------------------------------------------------|
| v1.0    | user                   | create a new workout plan/routine                       | tailor my workout to fit my needs                      |
| v1.0    | user                   | create a workout entry (input sets, weights, rep, time) | keep track of my progress                              |
| v1.0    | user                   | view my routine when I begin my workout                 | follow my plan more effectively                        |
| v1.0    | user                   | view their logged workout entry for a specific day      | see what they have done previously                     |
| v1.0    | user                   | delete a workout entry                                  | remove mistakenly created logs                         |
| v1.0    | user                   | delete a fitness routine if I no longer use it          | ensure my routines remain relevant and organized       |
| v1.0    | user                   | edit my existing fitness routine                        | further customize my routines after making them        |
| v2.0    | user                   | view a summary of my weekly workout activity            | measure my overall progress                            |
| v2.0    | user                   | track my personal bests for each exercise               | see improvements over time                             |
| v2.0    | user                   | log my body measurements                                | track calories burned/intake/gains more accurately     |
| v2.0    | nutrition-focused user | track calories burned during my workout                 | align my fitness routine with my dietary goals         |
| v2.0    | user                   | View body measurements over time                        | see the progress of their body                         |
| v2.0    | user                   | add a meal I just ate                                   | track my meals over time                               |
| v2.0    | user                   | delete a meal I ate                                     | delete a wrongly inputted meal                         |
| v2.0    | user                   | view my meals I ate on a certain date                   | see how much calories I have eaten                     |
| v2.0    | user                   | add my water intake                                     | track my water intake for each day                     |
| v2.0    | user                   | view my water intake                                    | see how much water I have consumed across days/week    |
| v2.0    | user                   | delete a water intake                                   | remove any mistakes made when inputting water intake   |
| v2.0    | user                   | set weekly/monthly exercise goals                       | to be accountable and motivate myself to stay on track |


## Non-Functional Requirements

* Java 11 or above installed
* Program built to support only single user

## Glossary

* *exercise* - Any exercise that can defined by a name, rep, set, weight and calories burned.
* *day* - A day is a collection of exercises meant to be done in one day
* *programme* - A programme is a weekly plan of workout days meant to be performed in rotation
 
## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
