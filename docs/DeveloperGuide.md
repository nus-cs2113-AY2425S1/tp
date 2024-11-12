# Developer Guide

## Acknowledgements
<!-- @@author Bev-low -->

BuffBuddy uses the following libraries
1. [Gson](https://google.github.io/gson/) - For saving and loading user data to a JSON file
2. [JUnit](https://junit.org/junit5/) - For automated testing
3. [Mockito](https://site.mockito.org/) - Supplements JUnit testing by creating mocks
4. [Gradle](https://gradle.org/) - For build automation

## Design

<!-- @@author nirala-ts -->
### UI Component

![Class_Diagram_for_Ui](images/uiComponent.png)

The `UI` component manages the input and output interface between the user and the system, allowing interaction through command input and message displays. It enables seamless communication of user requests and system feedback in an organized and formatted manner.

- **Handles user inputs and outputs**: The `UI` component relies on `Scanner` for capturing user input and `PrintStream` for outputting messages to the console. The `readCommand()` method reads a line of text, typically representing a user command, and returns it for processing.
- **Displays feedback messages**: The component provides `showMessage(String msg)`, `showMessage(Exception e)`, and `showMessage(CommandResult result)` methods to present different types of feedback to users, including general messages, error messages, and results of command executions. These methods ensure messages are formatted and include consistent visual separators.
- **Shows program start and end messages**: The component features `showWelcome()` and `showFarewell()` methods to display welcome and farewell messages, respectively, creating a friendly user experience from start to finish.
- **Keeps input and output streams flexible for testing**: The `UI` component is constructed with a `Scanner` and `PrintStream`, which can be replaced or redirected as needed, allowing easy adaptability for testing and debugging purposes.

<!-- @@author Bev-low -->
<div style="page-break-after: always;"></div>
### Programme Component

![Programme Component Classes Diagram](./images/programmeComponentClassDiagram.png)

The `ProgrammeList` component,

- **Manages a collection of programmes**: The `ProgrammeList` class is designed to manage a list of `Programme` objects, supporting easy addition, retrieval, and deletion of programmes. It also allows tracking and setting an active programme.
- **Provides collection-based functionality**: The class includes essential methods for operations such as retrieving the current list size, adding a new programme, and deleting or retrieving a programme by index. All key actions are logged for better traceability and debugging.
- **Supports programme activation and management**: The `startProgramme()` method sets a specific `Programme` as the active programme, enabling users to track which programme is currently in use. This ensures a seamless way to handle active programme operations.
- **Handles edge cases and maintains data integrity**: The `ProgrammeList` class checks for out-of-bounds access and throws appropriate exceptions when invalid indexes are provided. This helps maintain data consistency and prevents runtime errors.
- **Detailed representation**: The `toString()` method returns a comprehensive representation of the programme list, indicating the active programme for better user interface display and reporting.

The `Programme` component,

- **Manages a collection of days**: The `Programme` class consists of multiple day objects, supporting easy addition, retrieval and deletion of days.
- **Ensures data consistency and error handling**: The `Programme` class includes validations to handle cases such as null programme names or invalid indexes when accessing days. These validations help maintain data integrity and prevent unexpected runtime issues. Methods that attempt to access invalid indexes throw appropriate exceptions, maintaining robust error handling.
- **Detailed representation**: The `toString()` method returns a formatted string that includes the programme name and all the days in the programme, making it suitable for displaying programme information in user interfaces or summaries.
- **Maintains programme organization**: The class structure, with methods for inserting, retrieving, and deleting days, supports seamless organization and updates within a `Programme`, ensuring that users can easily manage the content and structure of their training schedules.

The `Day` component,

- **Represents a single day of exercises**: The `Day` class models a day that can contain multiple exercises. It serves as a building block for a structured workout or training programme.
- **Manages exercises within a day**: The class supports adding, retrieving, and deleting exercises through methods like `insertExercise()`, `getExercise()`, and `deleteExercise()`. This makes it easy to modify the list of exercises for a particular day.
- **Ensures data consistency and error handling**: The `Day` class checks for edge cases, such as invalid indexes when accessing or deleting exercises, and throws appropriate exceptions. Assertions are used to enforce that the day name and exercises are not null or empty upon initialization.
- **Calculates total calories burnt**: The `getTotalCaloriesBurnt()` method sums up the calories from all exercises in the day's list, providing a quick overview of the total effort for that day.
- **Detailed representation**: The `toString()` method returns a formatted string listing the day's name and each exercise, making it convenient for displaying in user interfaces or summaries.
- **Enables object comparison and usage in collections**: The `equals()` and `hashCode()` methods are overridden to facilitate comparisons between `Day` objects and to support their use in collections, ensuring that days can be managed accurately.

The `Exercise` component,

- **Models an individual exercise**: The `Exercise` class represents a specific exercise, detailing its sets, reps, weight, calories burned, and name. It provides a structured way to encapsulate exercise data within a day.
- **Allows for detailed updates**: The `updateExercise()` method accepts an `ExerciseUpdate` object and selectively updates fields of the `Exercise` based on the non-null values in the `ExerciseUpdate`. This ensures flexibility in modifying only the required fields without affecting the others.
- **Includes validation**: Each update method validates inputs, ensuring that null values do not update existing fields.
- **Facilitates data retrieval**: The class provides getters such as `getCalories()`, `getWeight()`, and `getName()` for accessing specific details of the exercise.
- **Detailed representation**: The `toString()` method returns a formatted string summarizing the exercise, including its name, number of sets, reps, weight used, and calories burned. This makes it easy to display exercise details in user interfaces or reports.
- **Enables object comparison and consistent storage**: The `equals()` and `hashCode()` methods are overridden to allow for the comparison of `Exercise` objects and ensure consistency when storing them in collections. This helps in managing and tracking unique exercises within larger structures like days or programmes.

The `ExerciseUpdate` component,

- **Facilitates partial updates to Exercise objects**: The `ExerciseUpdate` class is designed to enable the modification of specific fields in an `Exercise` object. Each field in the `ExerciseUpdate` can be null, indicating that the corresponding attribute in the target `Exercise` should not be updated.
- **Holds update data for exercises**: The class includes fields such as `sets`, `reps`, `weight`, `calories`, and `name`, which can be used to selectively update an `Exercise`. This enables targeted updates without altering other unchanged fields.
- **Ensures flexibility in exercise management**: By accepting nulls for unmodified fields, the `ExerciseUpdate` class provides a flexible way to update only the required attributes of an `Exercise`, streamlining the process of making changes to specific exercise details.
- **Supports integration with update methods**: The `ExerciseUpdate` class can be passed as a parameter to methods in the `Exercise` class (e.g., `updateExercise()`), facilitating a seamless process for applying partial updates based on provided non-null values.
- **Simplifies exercise modification logic**: With this class, the logic for updating exercises is consolidated, simplifying the code and ensuring consistency when modifying `Exercise` objects in various contexts.

<!-- @@author Atulteja -->
<div style="page-break-after: always;"></div>
### Meal Component

![Meal and MealList Class Diagram](./images/mealAndMealListClassDiagram.png)

The `Meal` component,

- **Represents individual meals with nutritional information:** The `Meal` class encapsulates details about a meal, specifically its name and calorie count. This allows easy tracking of individual meals within a day.
- **Attributes:** Each `Meal` object has two main attributes: `name` (the name of the meal) and `calories` (the calorie content of the meal).
- **Validation:** The class enforces constraints during instantiation, ensuring that the `name` cannot be null or empty and that the calorie count is non-negative, reducing errors in meal tracking.
- **Supports equality checks and hashing:** The `Meal` class overrides `equals()` and `hashCode()` methods to ensure that meals with identical names and calorie counts are considered equal, which is useful for meal comparison and for storing in collections like sets.
- **Detailed representation:** The `toString()` method of `Meal` provides a concise, readable summary of the meal’s details, including the name and calorie count in the format `"[Meal Name] | [Calories] kcal"`. This makes it easy to display meal information in logs, summaries, or user interfaces.

The `MealList` component,

- **Manages a collection of meals:** The `MealList` class provides functionality for managing a list of `Meal` objects, allowing for easy addition, deletion, and retrieval of meals throughout the day.
- **Provides collection-based functionality:** The class includes methods for common operations, such as checking if the list is empty, getting the total number of meals, and adding or deleting meals from the list. Each action is logged for traceability.
- **Supports detailed retrieval and representation:** The `getMeals()` method returns the list of all meals, and the `toString()` method generates a formatted string that lists each meal with its index, making it convenient for displaying meal information in a user interface or summary.
- **Ensures data consistency:** The `MealList` class handles edge cases, such as attempts to delete meals at invalid indexes, by throwing appropriate exceptions. This helps maintain data integrity within the list.
- **Facilitates efficient comparisons and storage:** The class overrides `equals()` and `hashCode()` methods, which enables comparison of two `MealList` objects and allows it to be used in collections, ensuring that meal tracking remains accurate and consistent.

### Water Component

![Water Class Diagram](./images/waterClassDiagram.png)

The `Water` component,

- **Tracks daily water intake:** The `Water` class allows for recording individual water consumption entries throughout the day, stored in liters. Each entry is logged, providing a detailed trace of daily water consumption.
- **Attributes:** The main attribute of the `Water` class is waterList, a list of Float values representing individual water intake entries in liters.
- **Validation and error handling:** When adding water entries, the `Water` class enforces that the water amount is positive. Deletion attempts with invalid indexes are handled with exceptions, ensuring safe and predictable usage.
- **Supports collection-based functionality:** The `Water` class includes methods for adding and deleting water entries, checking if the list is empty, and retrieving the entire list of entries. Each action is logged, allowing developers to track and troubleshoot any changes to the water intake log.
- **User-friendly representation:** The toString() method formats and returns a string representation of all water entries. Each entry is listed with an index, making it easy to display in user interfaces and summaries.
- **Efficient storage and retrieval:** The getWaterList() method returns the full list of water intake entries, while the class’s clear and consistent data structure facilitates straightforward water consumption tracking and data retrieval.

### History Component

![Class diagram of History Component](./images/historyComponent.png)

- **Chronologically stores workout records**: The `History` class uses a `LinkedHashMap<LocalDate, DailyRecord>` to store workout records, mapping each `LocalDate` to a `DailyRecord` for that day. This data structure preserves insertion order, enabling sequential, date-based record management.

- **Key Attributes**:
    - **History Map**: The main attribute `history` is a `LinkedHashMap<LocalDate, DailyRecord>` storing each workout log, meal, and water intake record by date.
    - **Logging**: A `Logger` instance for tracking actions, such as record creation, deletion, and retrieval, ensuring traceability within the class.

- **Efficient data retrieval and management**:
    - **Accessing Records by Date**: The `History` class allows retrieval of `DailyRecord` entries by specific dates, supporting users who wish to review or edit their fitness data for a particular day.
    - **Adding and Deleting Records**: Users can log new workout records or delete existing entries, providing flexibility in managing their fitness history.

- **Detailed representation for user interaction**:
    - **Formatted Summaries**: Methods such as `getFormattedPersonalBests()` and `getWeeklyWorkoutSummary()` provide clear, formatted outputs, making it easy for users to read and interpret their progress.
    - **String Representation**: The `toString()` method generates a readable output of all records, enhancing user experience in viewing historical data within the application interface.

- **Ensures data integrity and consistency**:
    - **Validation**: Before operations like deletion or retrieval, checks ensure records exist for specified dates, preventing errors in data handling.
    - **Safe Updates and Comparisons**: By providing methods like `isBetter()` for comparing exercises, the `History` component ensures users’ records accurately reflect their achievements without duplication or inconsistencies.

This structured and user-focused design of the `History` component empowers BuffBuddy users to track, manage, and assess their fitness journey effectively.


<!-- @@author Bev-low -->
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

In summary, the `History` component manages a comprehensive log of workout records, enabling users to view, update, and delete daily entries, track personal bests, and generate weekly summaries. Its methods and attributes work together to provide a structured, accessible history of the user's fitness activities.

### Storage Component

![Diagram for Storage. FileManager Component](./images/storageAndFileManager.png)

The `Storage` component,

- **Handles the saving and loading of both `ProgrammeList` and `History` data in JSON format:** The `Storage` component is responsible
  for serializing `ProgrammeList` and `History` objects into JSON format and passing them on to `FileManager`, as well as getting the
  data in Json format from `FileManager` and deserializing it into the appropriate objects when needed.
- **Serves as an adapter between `FileManager` and `BuffBuddy` classes:** `Storage` acts as an intermediary, translating between the JSON
  data handled by `FileManager` and the objects in the `BuffBuddy` application, ensuring seamless conversion between formats.
- **Relies on `ProgrammeList` and `History` from the Model component:** Since the `Storage` component is tasked with saving and retrieving
  the `ProgrammeList` and `History` objects, it ensures the data is accurately represented and stored.
- **Utilizes custom serializers:** To properly handle date formats and other specific needs, Storage makes use of custom serializers for
  objects like LocalDate from the `DateSerializer` class, ensuring that these objects are correctly serialized to and deserialized from JSON.

The `FileManager` component,

- **Manages the saving and loading of data:** The `FileManager` class is responsible for reading data from and writing data to the file
  specified by the user. It ensures that both the `ProgrammeList` and `History` data are stored in JSON format, and retrieves them when needed.
- **Handles file creation and directory management:** Before saving data, `FileManager` checks whether the necessary directories and
  files exist. If they do not, it creates them to ensure data can be stored correctly.
- **Leverages JSON for data structure:** `FileManager` uses `Gson` to serialize and deserialize JSON data, making it easy to work with
  structured data. It also ensures the data is formatted in a readable way using pretty printing for clarity.
- **Performs error handling and logging:** `FileManager` employs detailed logging to track the progress of saving and loading operations.
  If any issues arise during file operations (e.g., missing files, failed directory creation), they are logged, and exceptions are thrown to handle errors gracefully.

The `DateSerializer` component, 

- **Custom serialization and deserialization for `LocalDate`**: The `DateSerializer` class provides a way to serialize and deserialize `LocalDate` objects to and from JSON strings formatted as `dd-MM-yyyy`. This ensures that date data in JSON format remains consistent and human-readable.
- **Implements `JsonSerializer` and `JsonDeserializer` interfaces**: The class implements both `JsonSerializer<LocalDate>` and `JsonDeserializer<LocalDate>` from the Gson library, allowing it to handle JSON conversion for `LocalDate` objects.
- **Uses a standardized date format**: The `DateTimeFormatter` is configured with the pattern `dd-MM-yyyy`, which ensures that all serialized and deserialized dates conform to this format.

<!-- @@author nirala-ts -->
### Parser Component

![Class_Diagram_for_Factory_Component](images/parserComponent.png)

The `Parser` component,

- **Acts as the main entry point for interpreting user input**: The `Parser` class breaks down the command string into a 
  main command and arguments, identifies the relevant factory, and delegates command creation to the appropriate subcomponent.
- **Delegates command creation to `CommandFactory`**: By leveraging `CommandFactory`, `Parser` hands off the creation of `Command` 
  objects based on parsed command types and arguments, supporting extensibility for different command types.


The `CommandFactory` component,

- **Centralizes command production**: The `CommandFactory` class is responsible for creating `Command` objects based on the command type in user input, providing a single access point for command creation.
- **Manages subcommand factories**: It delegates specific command creation tasks to sub-factories, including `ProgrammeCommandFactory`, `MealCommandFactory`, `WaterCommandFactory`, and `HistoryCommandFactory`, based on the parsed command. If the command is unsupported, it returns an `InvalidCommand`.


The `ProgrammeCommandFactory` component,

![Class_Diagram_for_ProgrammeCommandFactory_Component](images/progFactoryComponent.png)

- **Processes program-related commands**: This factory handles commands related to creating, viewing, editing, starting, deleting, 
  and logging programs within the application.
- **Parses and prepares complex program structures**: It includes helper methods to interpret hierarchical program structures,
  allowing users to create and modify workout programs with days and exercises. It also supports commands with complex flags, ensuring flexibility in program management.
- _Note:_ Since `ProgrammeCommandFactory` is responsible for creating a wide variety of commands, the class diagram has been simplified by using the superclass `Command` class to 
  represent all sub-command classes that are actually created.



The `MealCommandFactory` component,

![Class_Diagram_for_MealCommandFactory_Component](images/mealFactoryComponent.png)


- **Parses meal-related commands**: This factory handles commands for adding, deleting, and viewing meals, providing a structured 
  way to manage dietary information within the application.
- **Validates and processes flagged arguments**: It uses `FlagParser` to interpret and validate command flags for meal-related attributes, 
  such as name, calories, and date. This ensures that inputs are correctly structured and validated before creating meal commands.


The `WaterCommandFactory` component,

![Class_Diagram_for_WaterCommandFactory_Component](images/waterFactoryComponent.png)

- **Handles water tracking commands**: This factory parses commands related to adding, deleting, and viewing water entries, allowing 
  users to track their daily water intake.
- **Ensures valid water-related input**: It uses `FlagParser` to validate command flags, ensuring that water volume and date inputs are
  correctly provided.


The `HistoryCommandFactory` component,

![Class_Diagram_for_HistoryCommandFactory_Component](images/historyFactoryComponent.png)

- **Generates history-related commands**: This factory handles commands for viewing, listing, deleting history entries, and managing 
  personal bests and weekly summaries.
- **Interprets user commands and arguments for history management**: It uses helper methods (e.g., `prepareViewHistoryCommand`) to 
  parse user commands and arguments, constructing the corresponding `Command` objects for various history-related operations.


The `FlagParser` component,

![Class_Diagram_for_FlagParser_Component](images/flagParserComponent.png)

- **Interprets flagged arguments in command strings**: This class provides advanced parsing of flagged arguments, supporting flexible 
  parsing and retrieval of values by flags, aliases, and data types (integer, float, date).
- **Validates flags for correct command structure**: `FlagParser` ensures that required and unique flags are present, allowing flexible 
  command input through aliases while enforcing structure.


The `ParserUtils` component,

![Class_Diagram_for_ParserUtils_Component](images/parserUtilsComponent.png)

- **Provides utility methods for parsing tasks**: This class offers helper methods for argument splitting, number parsing, index validation, 
  and date formatting, simplifying common parsing tasks.
- **Handles date and number validation**: It includes specialized methods for parsing dates and numbers, ensuring valid input for commands 
  requiring these data types.


The `FlagDefinitions` component,

![Class_Diagram_for_FlagDefinitions_Component](images/flagDefinitionsComponent.png)

- **Defines standard command flags**: This class contains constants representing command flags, establishing a standard set of flags used 
  across the application.
- **Validates flags quickly**: By storing valid flags in a set (`VALID_FLAGS`), `FlagDefinitions` allows for efficient validation during 
  command parsing.



<!-- @@author TVageesan -->
### Command Component

#### Overview

To interact with BuffBuddy, the user's input commands are parsed into discrete `Command` objects that have the sole responsibility of accomplishing that task.

As BuffBuddy contains many commands and thus many types of `Command` subclasses, the following diagram presents a simplified representation of the various `Command` classes:

![Summary of Command classes](images/commandSummary.png)

Each abstract subclass of `Command` represents a generalization of the various commands available to BuffBuddy. In the following sections, each abstract class and their respective purposes will be elaborated on.

#### Programme Commands

`ProgrammeCommand` is an abstract class for all `Command` classes that interact with `ProgrammeList` and its encapsulated data.
The following diagram documents all `ProgrammeCommand` subclasses.

![Summary of Programme classes](images/programmeCommandSummary.png)

`EditProgrammeCommand` classes are a subset of `ProgrammeCommand` classes that focus specifically on editing the internal `ProgrammeList` data. As this data is concerned only with `ProgrammeList`, `EditCommand#execute()` has been narrowed through method overloading to only take in `ProgrammeList` as a parameter.

![Summary of Edit classes](images/editCommandSummary.png)

#### Meal Commands

`MealCommand` is an abstract class for all `Command` classes that interact with meal-related data within the application. These commands allow users to log, edit, and manage their meal entries, ensuring that their dietary information is accurately tracked and updated. The following diagram documents all `MealCommand` subclasses.

![Summary of Meal classes](images/mealCommandSummary.png)

#### Water Commands

`WaterCommand` is an abstract class for all `Command` classes that interact with water-related data within the application. These commands allow users to log, edit, and manage their water intake entries, ensuring that their hydration information is accurately tracked and updated. The following diagram documents all `WaterCommand` subclasses.

![Summary of Water classes](images/waterCommandSummary.png)

#### History Commands

`HistoryCommand` is an abstract class for all `Command` classes that interact with `History` data within the application. These involve viewing weekly summaries, viewing their recorded data and getting their personal bests for each exercise. The following diagram documents all `HistoryCommand` subclasses.

![Summary of History classes](images/historyCommandSummary.png)

### Common Component

![Class_Diagram_for_Common_Component](images/commonUtilsComponent.png)

`common` package contains `Utils` class that is used across the multiple packages for validation and formatting.

---

<!-- @@author nirala-ts -->
## Implementation

### Create Programme

#### Overview

#### Overview
The **Create Programme** feature allows users to create a new workout programme. Users can either create a simple programme
with just a name or design a multi-day schedule containing various exercises with details such as sets, reps, weight, and calories. 
This feature enables users to personalize their workout plans according to their fitness goals.

These operations include:
- Parsing the programme name and optional day/exercise details.
- Creating and organizing Day and Exercise objects within the programme.
- Adding the completed programme to ProgrammeList.

#### Example Usage
Given below is an example usage scenario for 'create programme' and how the create programme command functions at each step.

**Step 1**: The user executes the command `programme create Starter /d 1 /e Push-Ups /e Squats` to create a new programme named "Starter" with one day containing two exercises: "Push-Ups" and "Squats".

**Step 2**: After parsing this input, a `CreateProgrammeCommand` is created.

**Step 3**: The command then calls `ProgrammeCommandFactory#prepareCreateCommand()` to parse the details of the programme.

**Step 4**: Inside `prepareCreateCommand`, the programme name and day details are parsed. For each day specified:
- `ProgrammeCommandFactory#parseDay()` is called to create a new `Day` object.
- For each exercise in the day, `ProgrammeCommandFactory#parseExercise()` is called to create an `Exercise` object with the specified details.
- Each created `Exercise` is added to the `Day` object.

**Step 5**: The `CreateProgrammeCommand` then calls `ProgrammeList#insertProgramme()` with the parsed programme name and list of days to add the new programme to `ProgrammeList`.

**Step 6**: The created `Programme` object is returned to `CreateProgrammeCommand`.

**Step 7**: The `CreateProgrammeCommand` formats a message indicating successful creation of the programme.

**Step 8**: The formatted message is included in a `CommandResult`, which is returned to the user interface.

**Step 9**: The user interface displays the result message to the user, confirming the successful creation of the programme.

#### Sequence Diagram

![Sequence Diagram for createProgramme feature](./images/createProgramme.png)


_Note_: Happy path is assumed in the sequences diagram. Error handling has been simplified to keep the diagram brief.
Generally, if a conditional check fails (i.e. if the Programme Name is missing), a ProgrammeException will be thrown and 
interrupt the command execution. BuffBuddy will print the appropriate error message based on the Exception and then wait for the next command.


<!-- @@author TVageesan -->
### Start Programme

#### Overview

The **Start Programme** feature allows users to start a specific workout programme. This sets the programme as the active programme, which other commands will default to if no programme is explicitly specified.

#### Example Usage

Given below is an example usage scenario for 'start programme' and how the start programme command functions at each step.

**Step 1:** The user has a list of workout programmes stored in `ProgrammeList`. Each programme may contain multiple days and exercises.

**Step 2:** The user executes the command `programme start 1` to start the first programme in the list.

**Step 3:** After parsing this input, a `StartProgrammeCommand` is created and executed.

**Step 4:** The command then calls `ProgrammeList#startProgramme()` with the given programme index to set the programme as active.

**Step 5:** The `Programme` object that was started is returned to the `StartProgrammeCommand`.

**Step 6:** The `StartProgrammeCommand` formats the details of the started programme into a message.

**Step 7:** The formatted message is included in a `CommandResult`, which is returned to the user interface.

**Step 8:** The user interface displays the result message to the user, confirming the successful activation of the programme.

#### Sequence diagram

![](images/startProgramme.png)

### View Programme

#### Overview

The **View Programme** feature allows users to view the details of a specific programme.

#### Example Usage

Given below is an example usage scenario for 'view programme' and how the view programme command functions at each step.

**Step 1:** The user has a list of workout programmes stored in `ProgrammeList`. Each programme may contain multiple days and exercises.

**Step 2:** The user executes the command `programme view 1` to view the first programme in the list.

**Step 3:** After parsing this input, a `ViewProgrammeCommand` is created and executed.

**Step 4:** The command then calls `ProgrammeList#getProgramme()` with the given programme index to retrieve the programme from the list.

**Step 5:** The retrieved `Programme` object is returned to the `ViewProgrammeCommand`.

**Step 6:** The `ViewProgrammeCommand` formats the details of the retrieved programme into a message.

**Step 7:** The formatted message is included in a `CommandResult`, which is returned to the user interface.

**Step 8:** The user interface displays the result message to the user, showing the details of the selected programme.

#### Sequence Diagram

![](images/viewProgramme.png)

### Delete Programme

#### Overview

The **Delete Programme** feature allows users to delete created programmes from the programme list.

#### Example Usage

Given below is an example usage scenario for 'delete programme' and how the delete programme command functions at each step.

**Step 1:** The user has a list of workout programmes stored in `ProgrammeList`. Each programme may contain multiple days and exercises.

**Step 2:** The user executes the command `programme delete 1` to delete the first programme in the list.

**Step 3:** After parsing this input, a `DeleteProgrammeCommand` is created and executed.

**Step 4:** The command then calls `ProgrammeList#deleteProgram()` with the given programme index to remove the programme from the list.

**Step 5:** The deleted `Programme` object is returned to the `DeleteProgrammeCommand`.

**Step 6:** The `DeleteProgrammeCommand` formats the details of the deleted programme into a message.

**Step 7:** The formatted message is included in a `CommandResult`, which is returned to the user interface.

**Step 8:** The user interface displays the result message to the user, confirming the successful deletion of the programme.

#### Sequence Diagram

![Delete Programme Sequence Diagram](images/deleteProgramme.png)

### Edit Programme

#### Overview

The **Edit Programme** feature allows for in-depth management of programme structures, supporting operations to add, remove, and update days and exercises within each programme.

To perform an edit to any aspect of this data, the EditCommand will traverse the ProgrammeList and its nested data structures until it reaches the necessary depth to perform its edit operation.

These operations include:

- Adding or removing Days to the Programme
- Adding or removing Exercises to Days in the Programme
- Updating the details of Exercises in Days in the Programme

##### Example Usage

Given below is an example usage scenario for 'delete exercise' and how the edit programme functions at each step.

Step 1. The user creates a programme with a given number of Days with their respective Exercises. ProgrammeList will contain a reference to this programme after its creation.

Step 2. The user executes `programme edit /p 1 /d 1 /xe 1` to delete the first exercise in the first day of the first programme.

Step 3. After parsing this input, a `DeleteExerciseCommand` (inheriting from the generic `EditProgrammeCommand`) is created and executed.

Step 4. The command first retrieves the chosen Programme with `ProgrammeList#getProgramme()`.

Step 5. The command then retrieves the chosen Day with `Programme#getDay()`.

Step 6. With the Day object, it performs the `Day#deleteExercise()` with the given exercise ID

Step 7. The deleted Exercise object is then returned to the `DeleteExerciseCommand` to display as part of the returned `CommandResult`.

#### Sequence Diagram

The overall design that enables this functionality is described generically by the following sequence diagram.

![Edit Command generic sequence](images/editCommand.png)

The `Model` class in the above diagram is a generalization of the various data models that are being interacted with
to perform each specific edit command. For each edit command, the following sequence diagrams
further break down how this interaction works.

In each diagram, error handling has been simplified to keep the diagram brief.
Generally, if a conditional check fails (i.e. if the selected `Programme` does not exist), a `ProgrammeException` will be thrown and interrupt the command execution. `BuffBuddy` will print the appropriate error message based on the Exception and then wait for the next command.

##### Add day

![Add/Remove Day](images/addDayCommand.png)

##### Add exercise

![Add/Remove Exercise](images/addExerciseCommand.png)

##### Update exercise

![Edit Exercise](images/editExerciseCommand.png)

#### Activity Diagram

To summarize, the following activity diagram describes how the overall operation occurs.

![Edit Command Diagram](images/editCommandActivityDiagram.png)


<!-- @@author Atulteja -->

### Add Meal

#### Overview

The **Add Meal** feature manages the functionality related to adding meals to a daily record. It interacts with various components such as `History`, `DailyRecord`, and `MealList` to ensure meals are added correctly.

The **Add Meal** command navigates through the following hierarchy:

- **History** → **DailyRecord** → **MealList**
- If a `DailyRecord` does not exist for a given date, it is created before adding the meal.
- Similarly, a new `MealList` object is created and added to the `DailyRecord` if it doesn't already exist. The meal is then added to the `MealList` object.

These operations include:

- Adding meals to a `MealList` in the `DailyRecord` of a particular date in the `History`.

Given below is an example usage scenario for adding a meal and how the add meal command functions at each step.

#### Example Usage

**Step 1**: The user starts by adding a meal using the command:

meal add /n [mealName] /c [calories]

- The command is parsed and translated into an `AddMealCommand` object, which contains the meal object that is created as a wrapper for the name and calories.

**Step 2**: The command retrieves the `DailyRecord` for the specified date from the `History` using `getRecordByDate()`. If no record exists, a new one is created.

**Step 3**: The `AddMealCommand` adds the meal to the `MealList` of the `DailyRecord`. The `MealList` is then updated with the new list.

**Step 4**: The newly added `Meal` object is displayed as part of the `CommandResult`.

The overall design that enables this functionality is described generically by the following sequence diagram.

#### Sequence Diagram for "Add Meal" Command

![Add Meal Sequence Diagram](images/addMealSequenceDiagram.png)

The diagram shows the interactions among different classes and objects during the execution of the "Add Meal" command.

The following sequence diagrams shows the interactions between the necessary classes during the execution of the "Delete Meal" and "View meal" commands. All the parser classes interactions has been encapsulated into the 'parser' class for readability and simplification. The parser interactions remain the same for all the 3 features. 

#### Sequence Diagram for "Delete Meal" Command

![Delete Meal Sequence Diagram](images/deleteMealSequenceDiagram.png)

#### Sequence Diagram for "View Meal" Command

![View Meal Sequence Diagram](images/viewMealSequenceDiagram.png)

#### Activity Diagram for "Add Meal" Feature

![Add Meal Activity Diagram](images/addMealActivityDiagram.png)

#### Summary of Feature

The **Add Meal** feature uses a **hierarchical command pattern** to manage meal additions while maintaining good encapsulation and separation of concerns. The chosen design allows easy extensibility and maintainability.


<!-- @@author Bev-low -->
### Add Water

The **Add Water** feature manages the functionality related to adding water to a daily record. It interacts with various components such as `History`, `DailyRecord`, and `Water` to ensure water are added correctly.

The Add Water command navigates through the following hierarchy:
- **History** → **DailyRecord** → **Water**
- If a `DailyRecord` does not exist for a given date, it is created before adding the water.

These operations include:
- Adding a water log to `Water` in the `DailyRecord` of a particular date in `History`.
- 
Given below is an example usage scenario for adding a water log and how to add water command functions at each step. 

#### Example Usage

**Step 1**: The user starts by adding a water log using the command:

water add /v WATER_VOLUME [/t Date] 

- The command is parsed and translated into an `AddWaterCommand` object. Water contains an arrayList of floats, representing ml of water.

**Step 2**: The command retrieves the `DailyRecord` for the specified date from the `History` using `getRecordByDate()`. If no record exists, a new one is created.

**Step 3**: The `AddWaterCommand` adds the water log to the `Water` of the `DailyRecord`. The `Water` is then updated with the new water log.

**Step 4**: The newly added water log object is displayed as part of the `CommandResult`.

The overall design that enables this functionality is described generically by the following sequence diagram.

![Add Water Sequence Diagram](images/addWaterSequenceDiagram.png)

The diagram shows the interactions among different classes and objects during the execution of the "Add Water" command.


#### Sequence Diagram for "Delete Water" Command 

The following sequence diagrams (Delete Water, View Water) follow the same structure as the Add Water sequence diagram. In these diagrams, the section where `addWaterToRecord(waterToAdd)` is called is replaced with the respective method for each action.

#### Sequence Diagram for "Delete Water" Command


![Delete Water Sequence Diagram](images/deleteWaterSequenceDiagram.png)

#### Sequence Diagram for "View Water" Command

![View Water Sequence Diagram](images/viewWaterSequenceDiagram.png)

#### Activity Diagram for "Add Water" Feature

![Add Water Activity Diagram](images/addWaterActivityDiagram.png)

#### Summary of Feature

The **Add Water** feature uses a **hierarchical command pattern** to manage water additions while maintaining good encapsulation and separation of concerns. The chosen design allows easy extensibility and maintainability.

<!-- @@author andreusxcarvalho -->

### WeeklySummary Feature

The Weekly Summary feature allows users to view a summary of their workouts for the current week. This functionality is achieved through a combination of several interconnected components, including `WeeklySummaryCommand`, `Parser`, `HistoryCommandFactory`, and `History`. Users can access this feature through the `history wk` command in the UI. The implementation follows a command pattern, combined with the factory pattern for command creation.

### Overview

The following components are crucial to the Weekly Summary feature:

1. **Parser Component**  
   The `Parser` interprets the initial command and directs the flow as follows:

   - **`Parser#parse(String)`**: Accepts the raw input string, splits it into the main command and arguments.
   - **`CommandFactory`**: Generates the appropriate command object based on the parsed input.
   - **`HistoryCommandFactory`**: Handles the creation of history-related commands, including `WeeklySummaryCommand`.

2. **WeeklySummaryCommand Component**  
   The `WeeklySummaryCommand` implements the `Command` interface and performs the following:

   - Extends the abstract `Command` class.
   - Uses the command word `"wk"`.
   - Executes by retrieving the weekly summary from the `History` object.
   - Returns a `CommandResult` that contains the formatted summary for display.

3. **History Component**  
   The `History` class manages workout data and provides:

   - **`getWeeklyWorkoutSummary()`**: Retrieves and formats the workout data for the current week.

### Example Usage

The following example illustrates the usage scenario and behavior of the Weekly Summary feature:

1. **Step 1**: The user enters the `"history wk"` command in the UI. The UI reads this command and passes it to the `Parser`.
2. **Step 2**: The `Parser` breaks down the command `"history wk"` into:
   - Main command: `"history"`
   - Subcommand: `"wk"`
3. **Step 3**: The `Parser` uses `CommandFactory`, which recognizes this as a history command and delegates to `HistoryCommandFactory`.
4. **Step 4**: `HistoryCommandFactory` identifies `"wk"` as the `WeeklySummaryCommand` trigger and creates a new `WeeklySummaryCommand` instance.
5. **Step 5**: The `WeeklySummaryCommand` is passed back through the chain to the UI, which then calls its `execute` method.
6. **Step 6**: During execution:
   - `WeeklySummaryCommand` calls `History`'s `getWeeklyWorkoutSummary()`.
   - The summary is formatted and wrapped in a `CommandResult`.
   - The UI displays the result to the user.

### Sequence Diagram

![Sequence Diagram for WeeklySummary feature](./images/weeklySummarySequenceDiagram.png)

<!-- @@author andreusxcarvalho -->

### Log Programme Feature

The Log Programme feature allows users to log a specific day of a workout programme into the history on a specified date. This feature is implemented using components like `LogProgrammeCommand`, `Parser`, `ProgrammeCommandFactory`, and `History`. Users can activate this feature by entering the `prog log` command in the UI. The implementation follows the command pattern, alongside a factory pattern for creating commands.

### Overview

The following component is crucial to the Log Programme feature:

1. **LogProgrammeCommand Component**  
   The `LogProgrammeCommand` is responsible for logging a workout day from a programme into the history and provides:

    - Extends the abstract `Command` class.
    - Uses the command word `"log"`.
    - Executes by retrieving the specified day from the `ProgrammeList` and logging it to the `History`.
    - Returns a `CommandResult` containing a success message or relevant feedback.

### Example Usage

The following example illustrates the usage scenario and behavior of the Log Programme feature:

1. **Step 1**: The user enters the `prog log /p [PROG_INDEX] /d [DAY_INDEX] /t [DATE]` command in the UI. The UI reads this command and passes it to the `Parser`.
2. **Step 2**: The `Parser` breaks down the command `prog log` into:
    - Main command: `prog`
    - Subcommand: `log`
3. **Step 3**: The `Parser` uses `CommandFactory`, which recognizes this as a programme command and delegates to `ProgrammeCommandFactory`.
4. **Step 4**: `ProgrammeCommandFactory` identifies `log` as the `LogProgrammeCommand` trigger and creates a new `LogProgrammeCommand` instance with the specified parameters.
5. **Step 5**: The `LogProgrammeCommand` is passed back through the chain to the UI, which then calls its `execute` method.
6. **Step 6**: During execution:
    - `LogProgrammeCommand` retrieves the programme and day specified by the user from `ProgrammeList`.
    - It then logs the day to the `History` object using `History`'s `getRecordByDate()` and `logRecord()` methods.
    - The result is formatted in a `CommandResult`.
    - The UI displays the result to the user.

### Sequence Diagram

![Sequence Diagram for Log Programme feature](./images/logProgrammeSequenceDiagram.png)


<!-- @@author Bev-low -->
### Save/Load Feature

The save/load mechanism is handled by three main components: `Storage`, `FileManager`, and `DateSerializer`. `FileManager` manages file interactions, including reading from and writing to JSON data files, while `Storage` handles the conversion between JSON objects and `ProgrammeList`/`History` objects. The `DateSerializer` is used for converting `LocalDate` to/from JSON format.

### Example Usage

Given below is an example usage scenario and how the save/load mechanism behaves at each step.

**Step 1.** The user launches the application for the first time. A `Storage` object is initialized by `BuffyBuddy`, and it attempts to load data from
the file using `FileManager`. If no data file exists, `Storage` initializes an empty `ProgrammeList` and `History`.

**Step 2.** The user interacts with the application by adding programmes or logging workout activities and meals, modifying both the
`ProgrammeList` and `History`. After each command is carried out and when the user chooses to exit the application, `Storage#saveData()` is called.

**Step 3.** At this point, `Storage` converts the current `ProgrammeList` and `History` into JSON format using the `createJSON()` method and passes
the `JsonObject` to `FileManager#save()`.

**Step 4.** The `FileManager` saves the updated `JsonObject` to the data file, ensuring that the user's changes are preserved for the
next command or session. If necessary, `FileManager#createDirIfNotExist()` and `FileManager#createFileIfNotExist()` ensure that the correct directories
and files are in place before saving.

**Step 5.** The next time the user launches the application, `Storage#loadProgrammeList()` and `Storage#loadHistory()` are called, which
load the data from the file via `FileManager#load()`. The loaded data is then converted from JSON back into `ProgrammeList` and `History`
objects, restoring the user's previous session.

The following sequence diagram shows how a load operation for ProgrammeList goes through the Storage component:

![Sequence Diagram for Load operation](./images/loadProgrammeListSequenceDiagram.png)

The following sequence diagram shows how a save operation goes through the Storage component:

![Sequence Diagram for Save operation](./images/saveSequenceDiagram.png)

---
<!-- @@author -->
## Documentation, logging, testing, configuration, dev-ops

* [Logging Guide](LoggingGuide.md)
* [Testing Guide](TestingGuide.md)

## Appendix

### Product scope

BuffBuddy is a fitness tracking app that help you track workout, meals, water to aid you in achieving your body goals.

### Target user profile

Gym goers who need a quick way to create, manage and track their workout plans and progress.

### Value proposition

- Users will be able to quickly create, update and view their workout programmes
- Users will be able to track their progress as they progress on their fitness journey
- Users will be able to track water and calorie intake to better track their nutrition


<!-- @@author nirala-ts-->
## User Stories

| Version | As a ...               | I want to ...                                      | So that I can ...                                     |
|---------|------------------------|----------------------------------------------------|-------------------------------------------------------|
| v1.0    | fitness enthusiast     | create a new workout programme/routine             | tailor my workout to fit my needs                     |
| v1.0    | fitness enthusiast     | set a programme as active                          | default to this programme when logging workouts       |
| v1.0    | fitness enthusiast     | add a workout day to my programme                  | structure my programme with specific workout days     |
| v1.0    | fitness enthusiast     | add exercises to a workout day                     | define the exercises and goals for that day           |
| v1.0    | fitness enthusiast     | edit my existing fitness routine                   | further customize my routines after making them       |
| v2.0    | fitness enthusiast     | update exercise details like weight, sets, or reps | adjust my routine based on progress or goals          |
| v1.0    | fitness enthusiast     | delete a workout entry                             | remove mistakenly created logs                        |
| v1.0    | fitness enthusiast     | delete a fitness routine if I no longer use it     | ensure my routines remain relevant and organized      |
| v2.0    | fitness enthusiast     | delete a workout day or exercise from a programme  | keep my programme up to date with relevant exercises  |
| v1.0    | fitness enthusiast     | log my workout for a specific day                  | keep track of my progress and activities              |
| v1.0    | fitness enthusiast     | view my routine when I begin my workout            | follow my plan more effectively                       |
| v2.0    | fitness enthusiast     | view a specific workout record                     | review my activities and progress on a particular day |
| v2.0    | fitness enthusiast     | view all my workout programmes                     | have a quick overview of all available programmes     |
| v2.0    | progress tracking user | view a summary of my weekly workout activity       | measure my overall progress                           |
| v2.0    | progress tracking user | track my personal bests for each exercise          | see improvements over time                            |
| v2.0    | nutrition-focused user | track calories burned during my workout            | align my fitness routine with my dietary goals        |
| v2.0    | nutrition-focused user | add a meal I ate                                   | track my meals and caloric intake                     |
| v2.0    | nutrition-focused user | delete a meal I logged                             | remove incorrect meal entries                         |
| v2.0    | nutrition-focused user | view my meals on a certain date                    | see how many calories I consumed                      |
| v2.0    | nutrition-focused user | view a caloric balance in the history view         | understand my net calorie intake and expenditure      |
| v2.0    | hydration-focused user | add my water intake                                | track my water intake for each day                    |
| v2.0    | hydration-focused user | view my water intake                               | see how much water I have consumed across days/week   |
| v2.0    | hydration-focused user | delete a water intake                              | remove any mistakes made when inputting water intake  |
| v2.0    | user                   | exit BuffBuddy                                     | close the program after completing my activities      |


## Non-Functional Requirements

- Ensure that you have Java 17 or above installed.
- Program is built to support single user only

## Glossary

- **Exercise**: An exercise defined by a name, number of reps and sets, weight and average calories burned.
- **Day**: A ‘workout day’ is a collection of exercises to be done together.
- **Programme**: A programme is a collection of workout days.
- **Daily Record**: A daily record contains a user's workout activity, food intake and water intake for any given day.


<!-- @@author TVageesan -->
## Instructions for manual testing

Here’s a structured manual testing guide for BuffBuddy based on the app's user guide and aligned with the reference format you provided.

---

### **Manual Testing Guide for BuffBuddy**



#### **Initial Launch**

- Download the BuffBuddy JAR file and place it in an empty folder.
- Launch the application by using `java -jar BuffBuddy.jar` in the terminal.

#### **Adding and Managing Programmes**

1. **Adding a New Programme**
    - **Command**: `prog create PROG_NAME`
    - **Example**: `prog create Starter`
    - **Expected Outcome**:
        - A confirmation message with the programme name appears, e.g., "New programme created: Starter".

2. **Setting an Active Programme**
    - **Command**: `prog start [PROG_INDEX]`
    - **Example**: `prog start 1`
    - **Expected Outcome**:
        - The specified programme is marked as "Active".

3. **Listing All Programmes**
    - **Command**: `prog list`
    - **Expected Outcome**:
        - A list of all programmes is shown with an indication of which is currently active.

4. **Deleting a Programme**
    - **Command**: `prog delete [PROG_INDEX]`
    - **Example**: `prog delete 1`
    - **Expected Outcome**:
        - The specified programme is deleted, and if it was the active one, another becomes active.

---

#### **Adding and Managing Workout Days**

1. **Adding a New Day to a Programme**
    - **Command**: `prog edit /p PROG_INDEX /ad DAY_NAME`
    - **Example**: `prog edit /p 1 /ad Cardio`
    - **Expected Outcome**:
        - A new day, e.g., "Cardio," is added to the specified programme.

2. **Deleting a Day from a Programme**
    - **Command**: `prog edit /p PROG_INDEX /xd DAY_INDEX`
    - **Example**: `prog edit /p 1 /xd 1`
    - **Expected Outcome**:
        - The specified day is removed from the programme.

---

#### **Adding, Updating, and Deleting Exercises**

1. **Adding an Exercise to a Day**
    - **Command**: `prog edit /p PROG_INDEX /d DAY_INDEX /ae /n EXERCISE_NAME /s SETS /r REPS /w WEIGHT /c CALORIES`
    - **Example**: `prog edit /p 1 /d 1 /ae /n Push Up /s 3 /r 15 /w 0 /c 50`
    - **Expected Outcome**:
        - The exercise details are added to the specified day.

2. **Updating an Exercise**
    - **Command**: `prog edit /p PROG_INDEX /d DAY_INDEX /ue EXERCISE_INDEX [args]`
    - **Example**: `prog edit /p 1 /d 1 /ue 1 /r 12`
    - **Expected Outcome**:
        - The updated exercise details are shown.

3. **Deleting an Exercise**
    - **Command**: `prog edit /p PROG_INDEX /d DAY_INDEX /xe EXERCISE_INDEX`
    - **Example**: `prog edit /p 1 /d 1 /xe 1`
    - **Expected Outcome**:
        - The specified exercise is removed from the list for that day.

---

#### **Recording and Viewing Workouts**

1. **Logging a Workout**
    - **Command**: `prog log /p PROG_INDEX /d DAY_INDEX [/t DATE]`
    - **Example**: `prog log /p 1 /d 1 /t 01-01-2024`
    - **Expected Outcome**:
        - A confirmation message displays, showing the exercises completed and calories burned.

2. **Viewing Workout History**
    - **Command**: `history list`
    - **Expected Outcome**:
        - A list of all recorded workout sessions, with dates and summaries, is displayed.

---

#### **Tracking and Viewing Meals**

1. **Adding a New Meal**
    - **Command**: `meal add /n MEAL_NAME /c CALORIES [/t DATE]`
    - **Example**: `meal add /n Chicken Breast /c 300 /t 01-01-2024`
    - **Expected Outcome**:
        - Confirmation that the meal has been added, with calories shown.

2. **Viewing Meals**
    - **Command**: `meal view [DATE]`
    - **Example**: `meal view 01-01-2024`
    - **Expected Outcome**:
        - List of meals for the specified date, showing names and calories.

---

#### **Managing Water Logs**

1. **Adding a Water Log**
    - **Command**: `water add /v WATER_VOLUME [/t DATE]`
    - **Example**: `water add /v 500 /t 01-01-2024`
    - **Expected Outcome**:
        - Confirmation that the water log has been added, showing volume.

2. **Viewing Water Logs**
    - **Command**: `water view [DATE]`
    - **Expected Outcome**:
        - List of water logs for the date, showing volumes in liters.

---

#### **Personal Best and Summary Views**

1. **View Personal Best for an Exercise**
    - **Command**: `history pb EXERCISE_NAME`
    - **Example**: `history pb Bench Press`
    - **Expected Outcome**:
        - Display of the user's best record for the specified exercise.

2. **View Weekly Summary**
    - **Command**: `history wk`
    - **Expected Outcome**:
        - A summary of workouts, meals, and water logs for the past week.

---

#### **Data Management and Error Handling**

1. **Corrupted Data File Simulation**
    - **Steps**:
        - Edit or corrupt the data file (e.g., remove keys).
        - Re-launch BuffBuddy.
    - **Expected Outcome**:
        - BuffBuddy should initialize with an empty data file, treating the user as a new entry.

2. **Exiting the Application**
    - **Command**: `bye`
    - **Expected Outcome**:
        - BuffBuddy exits gracefully with a confirmation message.

