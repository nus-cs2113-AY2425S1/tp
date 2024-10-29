# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}
We used these third party library
- Gson
- Mockito

## Design


### DailyRecord component
API: `DailyRecord.java`
![Diagram for DailyRecord Component](./images/DailyRecord_API_UML.jpg)
The `DailyRecord` component,
- **Tracks daily workout, meals, and water intake:** The `DailyRecord` class maintains a log of the day’s activities, meals consumed, 
and water intake. It provides methods to add, update, and retrieve each of these records.
- **Encapsulates multiple data types:** `DailyRecord` works with various objects such as `Day` (for recording the workout programme), 
`MealList` (for managing a list of meals consumed), and `Water` (for tracking daily water intake). These components are stored and managed 
together within a single daily record.
- **Enables modification and deletion:** The class provides methods for adding meals and water to the record, updating the workout for the 
day, and removing items such as meals or water entries. Each modification is logged for traceability.
- **Calculates key daily statistics:** `DailyRecord` is capable of calculating the total calories burned from the recorded `Day` and the 
calories gained from the `MealList`. It can also sum the total water intake for the day.
- **Provides a comprehensive summary:** The class’s `toString()` method generates a detailed summary of the day’s activities, including 
calories burned, meals eaten, water consumed, and the caloric balance, making it easy to retrieve and display all relevant information in a readable format.


### Storage component
API: `Storage.java`
![Diagram for Storage Component](./images/Storage_API_UML.jpg)
The `Storage` component, 
- **Handles the saving and loading of both `ProgrammeList` and `History` data in JSON format:** The `Storage` component is responsible 
for serializing `ProgrammeList` and `History` objects into JSON format and passing them on to `FileManager`, as well as getting the 
data in Json format from `FileManager` and deserializing it into the appropriate objects when needed.
- **Serves as an adapter between `FileManager` and `BuffBuddy` classes:** `Storage` acts as an intermediary, translating between the JSON 
data handled by `FileManager` and the objects in the `BuffBuddy` application, ensuring seamless conversion between formats.
- **Relies on `ProgrammeList` and `History` from the Model component:** Since the `Storage` component is tasked with saving and retrieving 
the `ProgrammeList` and `History` objects, it ensures the data is accurately represented and stored.
- **Utilizes custom serializers:** To properly handle date formats and other specific needs, Storage makes use of custom serializers for 
objects like LocalDate from the `DateSerilazer` class, ensuring that these objects are correctly serialized to and deserialized from JSON.


### FileManager
API: `FileManager.java`
![Diagram for FileManager Component](./images/FileManager_API_UML.jpg)
The `FileManager` component, 
- **Manages the saving and loading of data:** The `FileManager` class is responsible for reading data from and writing data to the file 
specified by the user. It ensures that both the `ProgrammeList` and `History` data are stored in JSON format, and retrieves them when needed.
- **Handles file creation and directory management:** Before saving data, `FileManager` checks whether the necessary directories and 
files exist. If they do not, it creates them to ensure data can be stored correctly.
- **Leverages JSON for data structure:** `FileManager` uses `Gson` to serialize and deserialize JSON data, making it easy to work with 
structured data. It also ensures the data is formatted in a readable way using pretty printing for clarity.
- **Performs error handling and logging:** `FileManager` employs detailed logging to track the progress of saving and loading operations. 
If any issues arise during file operations (e.g., missing files, failed directory creation), they are logged, and exceptions are thrown to handle errors gracefully.

---

## Implementation
This section describes some noteworthy details on how certain features are implemented.

### Save/Load Feature

The save/load mechanism is handled by three main components: `Storage`, `FileManager`, and `DateSerializer`. `FileManager` manages file interactions, including reading from and writing to JSON data files, while `Storage` handles the conversion between JSON objects and `ProgrammeList`/`History` objects. The `DateSerializer` is used for converting `LocalDate` to/from JSON format.

#### FileManager implements the following key operations:
- **`FileManager#load()`**: Reads the data file into a `JsonObject`.
- **`FileManager#save(JsonObject data)`**: Writes the `JsonObject` containing all data back into the file.
- **`FileManager#createDirIfNotExist()`** and **`FileManager#createFileIfNotExist()`**: Ensure that the necessary directory and file exist before saving.

#### Storage converts JSON data into Java objects:
- **`Storage#loadProgrammeList()`**: Converts the `ProgrammeList` JSON data into a `ProgrammeList` object using `programmeListFromJson()`.
- **`Storage#loadHistory()`**: Converts the `History` JSON data into a `History` object using `historyFromJson()`.
- **`Storage#saveData()`**: Converts `ProgrammeList` and `History` into JSON using `createJSON()`, and passes it to `FileManager#save()`.

#### DateSerializer is responsible for:
- **`DateSerializer#serialize()`**: Converts `LocalDate` into `JsonElement`.
- **`DateSerializer#deserialize()`**: Converts `JsonElement` back into `LocalDate`.

These operations are exposed in the `Model` interface, allowing seamless saving and loading of `ProgrammeList` and `History` data.


### Flow of Operations
Given below is an example usage scenario and how the save/load mechanism behaves at each step.

**Step 1.** The user launches the application for the first time. A `Storage` object is initialized by `BuffyBuddy`, and it attempts to load data from 
the file using `FileManager`. If no data file exists, `Storage` initializes an empty `ProgrammeList` and `History`.

**Step 2.** The user interacts with the application by adding programmes or logging workout activities and meals, modifying both the 
`ProgrammeList` and `History`. These changes are stored temporarily in memory, but no data is saved to the file at this point.

**Step 3.** When the user chooses to exit the application, `Model#saveData()` is triggered, which in turn calls `Storage#saveData()`. 
At this point, `Storage` converts the current `ProgrammeList` and `History` into JSON format using the `createJSON()` method and passes 
the `JsonObject` to `FileManager#save()`.

**Step 4.** The `FileManager` saves the updated `JsonObject` to the data file, ensuring that the user's changes are preserved for the 
next session. If necessary, `FileManager#createDirIfNotExist()` and `FileManager#createFileIfNotExist()` ensure that the correct directories
and files are in place before saving.

**Step 5.** The next time the user launches the application, `Storage#loadProgrammeList()` and `Storage#loadHistory()` are called, which 
load the data from the file via `FileManager#load()`. The loaded data is then converted from JSON back into `ProgrammeList` and `History` 
objects, restoring the user's previous session.

The following sequence diagram shows how a load operation for ProgrammeList goes through the Storage component:
![Sequence Diagram for Load operation](./images/LoadProgrammeList_Seq_Dia.jpg)

The following sequence diagram shows how a load operation for ProgrammeList goes through the Storage component:
![Sequence Diagram for Load operation](./images/LoadHistory_Seq_Dia.jpg)

The following sequence diagram shows how a save operation goes through the Storage component:
![Sequence Diagram for Save operation](./images/Save_Sequence_Diagram.jpg)

--- 

# Documentation, logging, testing, configuration, dev-ops
- Documentation guide (add link for these)
- Testing guide
- Logging guide

--- 

# Appendix: Requirements

## Product scope
BuffBuddy is a fitness tracking app that help you track workout, meals, water to aid you in achieving your body goals.
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

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


## Use cases

## Non-Functional Requirements
{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
