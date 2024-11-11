# Developer Guide

* [Acknowledgements](#acknowledgements)
* [Design](#design)
  * [Main components](#main-components)
  * [Interactions between components](#interactions-between-components)
  * [UI component](#ui-component)
  * [Storage component](#storage-component)
  * [Parser component](#parser-component)
  * [Command component](#command-component)
  * [Event component](#event-component)
* [Implementation](#implementation)
  * [Command parsing](#command-parsing-)
  * [List feature](#list-feature)
  * [Add feature](#add-feature)
  * [Remove feature](#remove-feature)
  * [View feature](#view-feature)
  * [Edit feature](#edit-feature)
  * [Mark/unmark feature](#markunmark-feature)
  * [Copy feature](#copy-feature)
  * [Sort feature](#sort-feature)
  * [Filter feature](#filter-feature)
  * [Find feature](#find-feature)
  * [Saving and loading of data](#saving-and-loading-of-data)
* [Appendix A: Product scope](#appendix-a-product-scope)
  * [Target user profile](#target-user-profile)
  * [Value proposition](#value-proposition)
* [Appendix B: User stories](#appendix-b-user-stories)
* [Appendix C: Non-functional requirements](#appendix-c-non-functional-requirements)
* [Appendix D: Glossary](#appendix-d-glossary)

<div style="page-break-after: always;"></div>

* [Appendix E: Instructions for manual testing](#appendix-e-instructions-for-manual-testing)
  * [Launch and shutdown](#launch-and-shutdown)
  * [Adding an event](#adding-an-event)
  * [Adding a participant](#adding-a-participant)
  * [Adding an item](#adding-an-item)
  * [Removing an event](#removing-an-event)
  * [Removing a participant](#removing-a-participant)
  * [Removing an item](#removing-an-item)
  * [Editing an event](#editing-an-event)
  * [Editing a participant](#editing-a-participant)
  * [Editing an item](#editing-an-item)
  * [Viewing an event](#viewing-an-event)
  * [Marking an event as done](#marking-an-event-as-done)
  * [Marking a participant as present](#marking-a-participant-as-present)
  * [Marking an item as accounted for](#marking-an-item-as-accounted-for)
  * [Copying the participant list](#copying-the-participant-list)
  * [Sorting the event list](#sorting-the-event-list)
  * [Filtering the event list](#filtering-the-event-list)
  * [Finding a participant](#finding-a-participant)
  * [Saving and loading of data](#saving-and-loading-of-data)

## Acknowledgements

This application uses the following dependencies:

* [OpenCSV 5.9](https://mvnrepository.com/artifact/com.opencsv/opencsv/5.9) to read and write to `.txt` files.
* [JUnit Jupiter API 5.10.0](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/5.10.0) for unit testing.
* [JUnit Jupiter Engine 5.10.0](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine/5.10.0) for unit testing.
<div style="page-break-after: always;"></div>

## Design

<img src = "images/ArchitectureDiagram.png">

The above **Architecture Diagram** provides the high-level design of the application.

### Main components

The application comprises the following components:

* `Main`, which handles program startup and shutdown, and also interactions between other components.
* `UI`, which handles user input and showing messages to the user.
* `Storage`, which handles the loading and saving of data from and to a file upon program startup and shutdown.
* `Parser`, which converts user input into commands.
* `Command`, which are executed to modify the data stored in the program.
* `Event`, which stores the program's data.
<div style="page-break-after: always;"></div>

### Interactions between components

The overall program execution is as follows:

1. Upon program startup, the `Ui` shows the user a welcome message.
2. `Storage` loads the event data from the saved file into `Event`
3. The program enters the command loop upon invocation of the `runCommandLoop()` method.
4. In the command loop, the program gets, parses and executes commands entered by the user.
5. `Storage` saves the event data in `Event` after the execution of each user command.
6. The program exits the command loop once the `exit` command is entered by the user.

<img src = "images/ArchitectureSequenceDiagram.png">

The above **Sequence Diagram** shows how the different components of the system interact in the above operation. The components are represented by classes, as follows:

* `Main` refers to the `Main` class in `Main`.
* `Ui` refers to the `Ui` class in `Ui`.
* `Storage` refers to the `Storage` class in `Storage`.
* `EventList` refers to the list of events in `Event` which the program's event data is stored (see the [Event component](#event-component) section for more details).

The **Sequence Diagrams** within the reference frames in the above diagram can be found in the [Command component](#command-component) and [Saving and loading of data](#saving-and-loading-of-data) sections.
<div style="page-break-after: always;"></div>

### UI component

<img src = "images/UiClassDiagram.png">

The `UI` component comprises an Ui class, as shown in the above **Class Diagram**.

The `UI` does the following:

* Show the user a welcome message upon program startup.
* Take in command input from the user and pass the input to `Main` (through the `getCommand()` method).
* Show output messages from `Command` to the user after command execution (through the `showOutputMessage()` method).
* Show error messages from any caught exceptions (through the `showErrorMessageToUser()` method).
<div style="page-break-after: always;"></div>

### Storage component

<img src = "images/StorageClassDiagram.png">

The `Storage` component's component classes are shown in the above **Class Diagram**.

These are:

* A `Storage` class that handles the loading and saving of event data into files.
* A `FileParser` that parses through the file contents when loading event data.

The `Storage` component does the following:
* Load event data from `data.txt` file into `EventList` upon program startup.
* Save events data from `EventList` into the aforementioned `data.txt` file each time a command is executed.

Additional details on the implementation of the above operations can be found in the [Saving and loading of data](#saving-and-loading-of-data) section.
<div style="page-break-after: always;"></div>

### Parser component

<img src = "images/ParserClassDiagram.png">

The `Parser` component comprises a `Parser` class, as shown in the above **Class Diagram**.

The `Parser` class takes in a user input string, and constructs an `XYZCommand` object with fields parsed from the input.

The logic of the `Parser` component is illustrated in the following use case:

1. Upon receiving a user command input, `Main` constructs a new `Parser`. This state is represented in the following **Object Diagram**: 

    <img src = "images/ParserObjectDiagram0.png">
    <div style="page-break-after: always;"></div>

2. `Main` passes the user command input to `Parser`, which parses the input and constructs an `XYZCommand` object, which is returned to `Main`. 
The `Parser` instance is no longer referenced. The current state is as shown in the **Object Diagram** below:

    <img src = "images/ParserObjectDiagram1.png">

The interactions between `Parser` and the other components in the above procedure is shown in the sequence diagram in the [Command component](#command-component) section.

Further details regarding command parsing can be found in the [Command parsing](#command-parsing-) section.

### Command component

The `Command` component comprises multiple `XYZCommand` classes, which inherit from the abstract `Command` parent class.

In each `XYZCommand` class, command execution is done through the `execute()` method, which is implemented from an abstract method in `Command`.
After the command execution, `XYZCommand`'s output message is set depending on whether the execution was a success or a failure.

The `Command` component and its component classes are shown in the below **Component Diagram**:

<img src = "images/CommandClassDiagram.png">
<div style="page-break-after: always;"></div>

The logic of the command parsing and execution in `Command` is as follows:

1. The `Ui` takes in a command input from the user, and passes the user command input to `Main`.
2. The `Parser` gets the user command input from `Main` and creates an `XYZCommand` instance.
The parameters of the `XYZCommand` instance are parsed from fields given in the user input.
3. The program's event data (in the form of `EventList`) is passed to `XYZCommand` by `Main`.
4. `XYZCommand` is executed with the invocation of the `execute()` method. 
5. The `Ui` gets `XYZCommand`'s output message, and shows it to the user. 

The interactions between `Command` and other components in the system for the above set of operations is shown in the following _Sequence Diagram_:

<img src = "images/CommandSequenceDiagram.png">
<div style="page-break-after: always;"></div>

### Event component

The `Event` component comprises an `EventList` class that is composed of multiple `Event`s. Each `Event` contains event participant data, represented by multiple `Participant` objects.

The `Event` component and its component classes are shown in the below **Class Diagram**:

Each `Event` is composed of the following: 

* Data on event details (the event name, date and venue), stored as separate variables.
* Data on the event priority, stored as a `Priority` enumeration value (which can be `HIGH`, `MEDIUM`, or `LOW`).
* A list of `Participant`s, each representing a participant at the event.
* A list of `Items`s, each representing an item for the event.

The component and its dependencies are shown in the below **Component Diagram**:

<img src = "images/EventComponentDiagram.png">
<div style="page-break-after: always;"></div>

## Implementation

This section describes some noteworthy details on how certain features are implemented.

### Command parsing ###

The user command input for the program is in the following format:

* `COMMAND_WORD FLAG PARAMETER FLAG_2 PARAMETER_2 ...`

where `COMMAND_WORD` determines the command type, `FLAG` is a command flag demarcating a parameter, and `PARAMETER` is a parameter value.

The above input is parsed into `Command`s by the `Parser` by the `Parser#parseCommand` operation, which, based upon the value of `COMMAND_WORD`, does the following:  

* If the command does not take in any parameters, the `Parser` constructs the corresponding `XYZCommandObject`.
* Otherwise, the `Parser` invokes a `Parser#parseXYZCommand` operation.

The `Parser#parseXYZCommand` operation then does the following:
* If `XYZCommand` has multiple possible operations, the `Parser`, based on the value of the first command flag, constructs the
`XYZCommand` object for one of these operations. An example from `parseAddCommand` (where `commandFlag` is the first flag) is shown in the code snippet below:

```
switch (commandFlag) {
    case EVENT_FLAG:
        return getAddEventCommand(input);
    case PARTICIPANT_FLAG:
        return getAddParticipantCommand(input);
    case ITEM_FLAG:
        return getAddItemCommand(input);
    default:
        logger.log(WARNING, "Invalid command format");
        throw new InvalidCommandException(INVALID_ADD_MESSAGE);
    }
```

* Otherwise, the `Parser` parses the user command input based on the values of the `FLAG`s in the input.
* If any of the `FLAG`s in the user command input are not present, invalid or in the wrong order, the `Parser` throws an `InvalidCommandException`.
<div style="page-break-after: always;"></div>

The interactions between classes for the parsing of a command with parameters is shown in the following **Sequence Diagram**:

<img src="images/CommandParsingSequenceDiagram.png">

### List feature

The `list` feature allows users to view all scheduled events in the system. 
It is implemented in the `ListCommand` class, which extends the base `Command` class and formats the output to display all events. 
Internally, the `list` operation does not modify any data but simply retrieves and displays the information from `EventList`.

The `ListCommand` class performs the following key operations:

* ListCommand#execute() — Generates a formatted message displaying all events in the list.

These operations are accessible through the `Command` and can be invoked when the list command is entered by the user.

#### Feature implementation

Given below is an example usage scenario and the behavior of the list feature at each step:

1. User Command Input:
The user enters the command `list` to view all scheduled events.

2. Command Recognition:
The `COMMAND_WORD` is set to "list", enabling the system to recognize the command input and invoke `ListCommand`.

3. Execution of ListCommand#execute():
The `execute()` method retrieves each event from `eventList` and appends it to a formatted output message.
It uses the `String.format` method with `LIST_MESSAGE` to include the total number of events in the message header.
Events are appended to `outputMessage` with numbered formatting for readability.
    <div style="page-break-after: always;"></div>

4. Output Generation:
The method stores the generated `outputMessage` in `this.message`, ready for display.

The interactions between components during the execution of the `list` command are show in the **Sequence Diagram** below:

<img src = "images/ListCommandSequenceDiagram.png">

### Add feature

The `add` feature allows users to remove `Event`s from the `EventList`, `Participant`s or `Item`s from an `Event`.
It is implemented in the `AddCommand` class which extends the base `Command` class, and in the `EventList`.

The feature has three operations, namely:

1. `EventList#addParticipantToEvent()`, which adds a `Participant` to an `Event` in the `EventList`.
2. `EventList#addItemFromEvent()`, which adds an `Item` to an `Event` in the `EventList`.
3. `EventList#addEvent()`, which adds an `Event` to the `EventList`.

These three operations are invoked from `AddCommand` through `AddCommand#execute()`. This overrides the `Command#execute()` operation in `Command`,
and is invoked when the latter operation is called.

In `AddCommand#execute()`, one operation is selected based on the values stored in several members of the `AddCommand` instance, namely:

* `participantName`, the name of the `Participant` to be added to the specified `Event`,
* `itemName`, the name of the `Item` to be added to the specified `Event`,
* `eventName`, the name of the specified `Event`.
<div style="page-break-after: always;"></div>

The operation selection logic is as follows:

1. If `participantName` is not `null`, `EventList#addParticipantToEvent()` will be invoked.
2. Otherwise, if `itemName` is not `null`, `EventList#addItemToEvent()` will be invoked.
3. Otherwise, `EventList#addEvent()` will be invoked.

This operation selection logic is executed upon the invocation of `AddCommand#execute()`.

The interactions between components during the operation selection in `AddCommand#execute()` are show in the **Sequence Diagram** below:

<img src = "images/AddCommandSequenceDiagram.png">

The `EventList#addParticipantToEvent()` operation works as follows:

1. `EventList` gets the `Event` with the event name `eventName` from the list of `Event`s stored within it.
2. In the selected `Event`, `Event` checks if there is a `Participant` with the name in `participantName` in the list of `Participant`s. If there is one, it adds an indexed suffix to `participantName`.
3. Otherwise, `Event` creates a new `Participant` object with the parameters passed to it, and adds it to the `Participant` list.

The indexed suffix added for duplicate `Participant` names takes the form `NAME (INDEX)`. For each duplicate added, the index is increased.
For example, three participants with the name `John Tan` will be stored as `John Tan`, `John Tan (1)` and `John Tan (2)`.

If an `Event` with a name matching `eventName` is not found, the operation returns am empty string to indicate that the operation was unsuccessful. Otherwise, the operation returns the added `Participant` name.
<div style="page-break-after: always;"></div>

The interactions between components during the execution of the `EventList#addParticipantToEvent()` operation are show in the **Sequence Diagram** below:

<img src = "images/AddParticipantSequenceDiagram.png">
<img src = "images/AddParticipantEventSequenceDiagram.png">

The operation logic for `EventList#addItemToEvent()` is similar to that for `EventList#addParticipantToEvent()`, and will not be elaborated upon.

The interactions between components during the execution of the `EventList#addEvent()` operation are show in the **Sequence Diagram** below:

1. `EventList` checks if there is a `Event` with the name in `eventName` in its list of `Events`s. If there is one, it adds an indexed suffix to `eventName`.
2. Otherwise, `EventList` creates a new `Event` object with the parameters passed to it, and adds it to the `Event` list.

<img src = "images/AddEventSequenceDiagram.png">

Upon the execution of the above operations, the output message is set based on the operation's return value, to indicate if the removal was successful.
If the removal was successful, the details of the added `Event`, `Participant`, or `Item` are shown to the user.

The `Parser` assigns the values of the parameters directly to their respective members, depending on the first command flag in the user input, as follows:

* If the first command flag is the event flag (`-e`), the `Parser` only assigns values to `eventName`.
* If the first command flag is the participant flag (`-p`), the `Parser` assigns values to `eventName` and `participantName`.
* If the first command flag is the item flag (`-m`), the `Parser` assigns values to `eventName` and `itemName`.

### Remove feature

The `remove` feature allows users to remove `Event`s from the `EventList`, `Participant`s or `Item`s from an `Event`.
It is implemented in the `RemoveCommand` class which extends the base `Command` class, and in the `EventList`.

The feature has three operations, namely:

1. `EventList#removeParticipantFromEvent()`, which removes a `Participant` from an `Event` in the `EventList`.
2. `EventList#removeItemFromEvent()`, which removes an `Item` from an `Event` in the `EventList`.
3. `EventList#removeEvent()`, which removes an `Event` from the `EventList`.

These three operations are invoked from `RemoveCommand` through `RemoveCommand#execute()`. This overrides the `Command#execute()` operation in `Command`,
and is invoked when the latter operation is called.

In `RemoveCommand#execute()`, one operation is selected using a logic similar to that for `AddCommand#execute()`. For more details, refer to _Add feature_.

The interactions between components during the operation selection in `RemoveCommand#execute()` are show in the **Sequence Diagram** below:

<img src = "images/RemoveCommandSequenceDiagram.png">

The `EventList#removeParticipantFromEvent()` operation works as follows:

1. `EventList` gets the `Event` with the event name `eventName` from the list of `Event`s stored within it.
2. The selected `Event` compares the names of the `Participant`s in its list of `Participant`s with `participantName`.
3. If a `Participant` with a matching name is found, the `Participant` is removed from the `Participant` list of the `Event`.

If an `Event` with a name matching `eventName` or a `Participant` with name matching `participantName` is not found, the operation returns `false`
to indicate that the operation was unsuccessful. Otherwise, the operation returns `true`.
<div style="page-break-after: always;"></div>

The interactions between components during the above operation are shown in the **Sequence Diagram** below:

<img src = "images/RemoveParticipantSequenceDiagram.png">
<img src = "images/RemoveParticipantFromEvent.png">

The operation logic for `EventList#removeItemFromEvent()` is similar to that for `EventList#removeParticipantFromEvent()`.

The `EventList#removeEvent()` operation works as follows:

1. `EventList` compares the names of the `Event`s in its list of `Event`s with `eventName`.
2. If an `Event` with a matching name is found, the `Event` is removed from the `Event` list of the `EventList`.

If an `Event` with a name matching `eventName` is not found, the operation returns `false` to indicate that the operation was unsuccessful. Otherwise, the operation returns `true`.

The interactions between components during the above operation are shown in the **Sequence Diagram** below:

<img src = "images/RemoveEventSequenceDiagram.png">

Upon the execution of the above operations, the output message is set based on the operation's return value, to indicate if the removal was successful.

The members in `RemoveCommand`  are similar to those in `AddCommand`, and are set from parameters in the `remove` command by the `Parser` in a similar way.

### View feature

The `view` feature allows users to view the participants or items for a selected event.
The feature comprises `ViewCommand`, which extends `Command`, and has one operation, which shows the user a list of all participants or items for an event.

The above operation is implemented as `ViewCommand#execute()`. This overrides the `Command#execute()` operation in `Command`,  
and is invoked when the latter operation is called.

The output of the operation depends on two variables: 
* `eventName`, the name of the selected event.
* `isViewingParticipants`, which is true if a list of participants is to be shown, and false otherwise.
<div style="page-break-after: always;"></div>

The `ViewCommand#execute()` operation works as follows:

1. `ViewCommand` gets the `Event` with name `eventName` from `EventList`. If the event is not found, the command's output message will be set to an error message.
2. Depending on the value of `isViewingParticipants`, `ViewCommand` either gets a list of `Participant`s or `Item`s from `Event`.
3. `ViewCommand` constructs an output message of formatted strings that it gets from the `Participant`s or `Item`s in the list.

The interactions between components during the execution of the `view` command are show in the **Sequence Diagrams** below:

<img src = "images/ViewEventSequenceDiagram.png">
<img src = "images/ViewParticipantSequenceDiagram.png">
<img src = "images/ViewItemSequenceDiagram.png">

The values of `eventName` and `isViewingParticipants` are set by the user through the event and type parameters in the `view` command respectively.

The `Parser` assigns the event parameter directly to `eventName`. Conversely, it sets `isViewingParticipants` to true if the type parameter value is `participant`, 
to false if the type parameter value is `item`, and treats any other value entered as invalid.

### Edit feature

The `edit` feature allows users to edit the information of an event, or the information of a participant/item in an event.
This feature is implemented in the `EditEventCommand` `EditParticipantCommand` `EditItemCommand` classes, which extends the `Command` base class and utilises the flag to determine the edit content.

The feature comprises three operations, namely:
* `EditEventCommand#execute()`, which edits the information of an event.
* `EditParticipantCommand#execute()`, which edits the information of a participant in an event.
* `EditItemCommand#execute()`, which edits the information of an item in an event.

The above three operations override the `Command#execute()` operation in `Command`,
and is invoked when the latter operation is called.
<div style="page-break-after: always;"></div>

#### Feature implementation

Given below is an example usage scenario and the behaviour of the `edit` feature at each step:
1. The user enters the command edit followed by a flag (-e/-p/-m) to edit the information of event/participant/item.
2. If the flag is `-e`, `EditEventCommand` calls `EditEventCommand#execute()`, which calls `EventList#editEvent()` to edit the event. 
   It looks for the event, modifies the information and returns true if the event exits. Otherwise, it returns false.
3. If the flag is `-p`, `EditParticipantCommand` calls `EditParticipantCommand#execute()`, which calls `EventList#editParticipant()` to edit the participant.
   It looks for the event and the specified participant, and then modifies the information and returns true if the participant is found. Otherwise, it returns false.
4. If the flag is `-m`, `EditItemCommand` calls `EditItemCommand#execute()`, which calls `EventList#editItem()` to edit the item.
   It looks for the event and the specified item, modifies the item and returns true if the item is found. Otherwise, it returns false.
5. After editing, a message `outputMessage` will be shown to the user. If the edit operation was successful, this message will contain the updated details of the event/participant/item updated.

If the new name of the `Event`, `Participant`, or `Item` is a duplicate of that of an existing `Event`, `Participant`, or `Item`, an indexed suffix will be added to the name.
This is done in the same way as described in [Add feature](#add-feature);

The interactions between components of `EditEventCommand#execute()` are shown in the **Sequence Diagram** below:

<img src="images/EditEventCommandSequenceDiagram.png">
<div style="page-break-after: always;"></div>

The interactions between components of `EditParticipantCommand#execute()` are shown in the **Sequence Diagram** below:

<img src="images/EditParticipantCommandSequenceDiagram.png">

The interactions between components of `EditItemCommand#execute()` are shown in the **Sequence Diagram** below:

<img src="images/EditItemCommandSequenceDiagram.png">
<div style="page-break-after: always;"></div>

### Mark/unmark feature

The `mark/unmark` feature allows users to mark and unmark `Event`s in the `EventList`, or `Participant`s or `Item`s stored in an `Event`. The feature comprises the abstract `MarkCommand` class,
which extends `Command`, and three child classes, `MarkEventCommand`, `MarkParticipantCommand`, and `MarkItemCommand`.

The feature comprises three operations, namely:
* `MarkEventCommand#execute()`, which marks an event as done or not done.
* `MarkParticipantCommand#execute()`, which marks a participant as present or absent.
* `MarkItemCommand#execute()`, which marks an item as accounted or unaccounted.

The above three operations override the `Command#execute()` operation in `Command`,
and is invoked when the latter operation is called.

#### Feature implementation

Given below is an example usage scenario for `MarkEventCommand#execute()`, and how it behaves at each step.

1. The user adds an event `Event 1` to the event list. The mark status for `Event 1` is initially `false` or not done, as shown in the **Object Diagram** below:

    <img src = "images/MarkEventObjectDiagram1.png">

2. The user enters the command `mark -e Event 1 -s done` to mark `Event 1` as done. `MarkEventCommand` calls `MarkEventCommand#execute`,
in which it gets the event `Event 1` from the event list, and sets its mark status to `true` or done, as shown in the **Object Diagram** below.

    <img src = "images/MarkEventObjectDiagram2.png">

3. The user then enters the command `mark -e Event 1 -s undone` to mark `Event 1` as not done. The `MarkEventCommand` again calls `MarkEventCommand#execute`,
in which it gets the event `Event 1` from the event list, and sets its mark status to `false` or not done.
    <div style="page-break-after: always;"></div>

The interactions between components during the execution of `MarkEventCommand#execute` are shown in the **Sequence Diagram** below:

<img src = "images/MarkEventSequenceDiagram.png">

Upon execution of the command, the output message of `MarkEventCommand` is set to inform the user if the event has been marked done or not done,
or if the operation was unsuccessful (e.g. if the event specified is not present in the event list).

The `MarkParticipantCommand#execute` operation is executed as follows:

1. `EventList` gets the `Event` with the specified event name from its list of `Event`s.
2. The selected `Event` then gets the `Participant` with the specified participant name from its list of `Participant`s.
3. The selected `Participant` is marked present or absent.

The operation would be unsuccessful if the specified `Event` in `EventList`, or the specified `Participant` in `Event` is not found.

The interactions between components during the execution of `MarkParticipantCommand#execute` are shown in the **Sequence Diagram** below:

<img src = "images/MarkParticipantSequenceDiagram.png">
<div style="page-break-after: always;"></div>

The output message of `MarkParticipantCommand` is set in a similar way as `MarkEventCommand`.

The operation logic for `MarkItemCommand#execute()` is similar to that for `MarkParticipantCommand#execute()`.

The user determines if the `MarkCommand` is to mark or to unmark through the status parameter (indicated by the `-s` flag) in the `mark` command.
The `Parser` then checks this parameter for two possible values and constructs the `MarkCommand` object accordingly.

These two values are as follows:
* For `MarkEventCommand`, `done` to mark, `undone` to unmark,
* For `MarkParticipantCommand`, `present` to mark, `absent` to unmark,
* For `MarkItemCommand`, `accounted` to mark, `unaccounted` to unmark.

Any other values entered for the status parameter will be treated as invalid.

### Copy feature

The copy feature allows users to copy the list of participants from one event to another. This feature is implemented in the `CopyCommand` class,  which extends `Command`,
The `CopyCommand` copies participants from a source event to a destination event if both events exist in the event list.

The main operations for `copy` feature include:
* Checking if both source and destination events exists in `EventList`
* Verifying that the source event contains a non-empty participant list
* Copying the participant list from the source event to the destination event
* Displaying an appropriate message based on the outcome of the operation

The above operation is implemented as `CopyCommand#execute()`. This overrides the `Command#execute()` operation in `Command`,
and is invoked when the latter operation is called.

#### Feature implementation

Given below is an example usage scenario and the behaviour of the `copy` feature at each step:
1. The user enters the command `copy EventA > EventB` to copy participants from EventA to EventB. 
2. `CopyCommand` calls `CopyCommand#execute`, where it attempts to get the participant list from EventA, and copy the participant list over to EventB if there are existing participants.
    <div style="page-break-after: always;"></div>

The interactions between components of `CopyCommand#execute` are shown in the **Sequence Diagram** below:

<img src="images/CopyCommandSequenceDiagram.png">

Upon execution of the command, the output message of `CopyCommand` is set to inform the user if the participants list has been copied,
or if the operation was unsuccessful (e.g. if the participant list that is meant to be copied is empty).

### Sort feature

The `sort` feature allows users to organize events in a chosen order based on different attributes, such as name, time, or priority.
This feature is implemented in the `SortCommand` class, which extends the `Command` base class and utilises a keyword to determine the sorting criterion.

The `SortCommand` supports the following sorting options:
* **By Name:** Alphabetically sorts the events by name
* **By Time:** Orders events bases on scheduled time
* **By Priority:** Organises events by priority level, with the highest priority appearing first

The above operation is implemented as `SortCommand#execute()`. This overrides the `Command#execute()` operation in `Command`,
and is invoked when the latter operation is called.
<div style="page-break-after: always;"></div>

#### Feature implementation

The `SortCommand` class is constructed with a specified sorting keyword and performs sorting operations based on this keyword.
Given below is an example usage scenario and the behaviour of the `sort` feature at each step:
1. The user enters the command sort followed by a keyword (name, time, or priority) e.g. `sort -by name` to specify the sorting criterion
2. `SortCommand` calls `SortCommand#execute`, which based on the keyword invokes one of the following 3 methods
    * `sortByName()` - Sort events alphabetically by name
    * `sortByTime()` - Sort events chronologically by time
    * `sortByPriority` - Sort events by priority level
    
    After sorting, a success message is appended to `outputMessage` which indicates the sorting criterion used
3. The final sorted list is then formatted and appended to `outputMessage`,
    which is subsequently stored in `this.message` and displayed to the user.

The interactions between components of `SortCommand#execute` are shown in the **Sequence Diagram** below:

<img src="images/SortCommandSequenceDiagram.png">
<div style="page-break-after: always;"></div>

### Filter feature

The `filter` feature allows users to filter events from the event list based on specified criteria. 
This feature is implemented in the `FilterCommand` class, which extends the `Command` base class and uses flags to determine the filtering criteria.

The `FilterCommand` supports the following filter options:
* **By Name:** Finds any events containing the specified name
* **By Time:** Finds all events scheduled at the specified time
* **By Priority:** Finds all events with the specified priority level

The above operation is implemented as `FilterCommand#execute()`. This overrides the `Command#execute()` operation in `Command`,
and is invoked when the latter operation is called.

#### Feature implementation

The `FilterCommand` class is constructed with a specified filter flag and keywords. It then performs filter operations based on both the flag and keywords.
Given below is an example usage scenario and the behaviour of the `filter` feature at each step:
1. The user enters the command filter followed by a flag (`-e: name, -d : date, -t: time, -x date-time,  or -u: priority`) and their search keyword e.g. `filter -e work` to specify the filtering criterion
2. `FilterCommand` calls `FilterCommand#execute`, which based on the flag invokes one of the following 3 methods:

   * `filterEventsByName()` - Finds events containing given name (keyword)
   * `filterEventsByDate()` - Finds events occurring during given date (keyword)
   * `filterEventsByTime()` - Finds events occurring during given time (keyword)
   * `filterEventsByDateTime()` - Finds events occurring during given date-time (keyword)
   * `filterEventsByPriority()` - Finds events with given priority (keyword)
   
   After filtering, a success message is appended to `outputMessage` which indicates the filtering criterion used
3. The final filtered list is then formatted and appended to `outputMessage`,
   which is subsequently stored in `this.message` and displayed to the user.
<div style="page-break-after: always;"></div>
The interactions between components of `FilterCommand#execute` are shown in the **Sequence Diagram** below:

<img src="images/FilterCommandSequenceDiagram.png" width="550">
<div style="page-break-after: always;"></div>

### Find feature

The `find` feature allows users to locate participants within a specified event by their name.
This feature is implemented in the `FindCommand` class, which extends the `Command` base class. 
The feature provides detailed feedback, informing the user whether the event or participant was found.

The above operation is implemented as `FindCommand#execute()`. This overrides the `Command#execute()` operation in `Command`,
and is invoked when the latter operation is called.

#### Feature implementation

The `FindCommand` class performs a finding operation within an event of a specified participant. 
Given below is an example usage scenario and the behaviour of the `find` feature at each step:

1. The user enters the command `find -e EVENT -p PARTICIPANT` to find participants in the specified event given a specified name
2. The `FindCommand` searches for the specified event within the eventList by calling `getEventByName(eventName)`, which returns an `Optional<Event>`
3. If the event exists,  the method `findParticipants(personName)` is invoked on the retrieved event to get a list of participants matching `personName`
    * If participants are found, `outputMessage` is appended with a success message followed by a formatted list of found participants
    * Otherwise, if either the event is not found or participants is not found, a corresponding failure message is appended to `outputMessage`
4. The final `outputMessage` is subsequently stored in `this.message` and displayed to the user.

The interactions between components of `FindCommand#execute` are shown in the **Sequence Diagram** below:

<img src="images/FindCommandSequenceDiagram.png">
<div style="page-break-after: always;"></div>

### Saving and loading of data

As mentioned in the _Storage component_ section, the program automatically saves any stored data in `EventList` into `data.txt` file, and loads
the data from this file when the program runs.

In `data.txt`, each line represents an object (`Event`, `Participant`, or `Item`), organised in the following format:

```
EVENT,FIELD,FIELD,...
PARTCIPANT,FIELD,FIELD,...
ITEM,FIELD,FIELD,...
```

where `FIELD` represents a value corresponding to a property of the object (e.g., `Event` name or `Participant` email).

This functionality is implemented by the `Storage` and `FileParser` classes, encompassing two main operations:
* `Main#loadData()`, which loads data from the `data.txt` file into `EventList`.
* `Main#saveData()`, which saves all data stored in `EventList` (including its `Events`, `Participants`, and `Items`) into `data.txt`.

#### The `Main#loadData()` operation works as follows:

1. `Storage` initializes `FileParser` to read data from `data.txt` into `EventList`.
2. `FileParser` processes each line, identifying whether it represents an `Event`, `Participant`, or `Item`, and appropriately adds each object to the relevant `Event` in `EventList`.
3. Lines with insufficient or invalid fields are skipped, while lines with extra fields have the additional fields ignored.

#### Loading Events, Participants and Items

For `Event` loading:
* `Storage` creates an instance of `FileParser` and provides the file path to `data.txt`.
* `FileParser` reads each line, and for `Event` lines, it adds a new `Event` to `EventList` using the fields from the line.

For `Participant` and `Item` loading:
* The logic mirrors the loading of `Events`, where each `Participant` or `Item` is associated with the correct `Event` based on its specified fields.
<div style="page-break-after: always;"></div>

The **Sequence Diagram** below demonstrates the interactions during loading.

<img src = "images/StorageLoadingSequenceDiagram.png">

The logic for the loading of `Item`s is similar to that for `Participant`s.
<div style="page-break-after: always;"></div>

#### The `Main#saveData()` operation saves data in the same order as `Main#loadData()` and works as follows.

1. `Storage` retrieves `Events` from `EventList` and writes each `Event` and its associated `Participants` and `Items` to `data.txt`.
2. Each line is formatted based on the object type, either as an `Event`, `Participant`, or `Item`.

#### Saving Events, Participants, and Items

For saving `Events`:
* Storage obtains the list of Events from EventList and writes each Event line by line to data.txt.

For `Participant` and `Item` saving:
* For each Event, Storage retrieves the list of Participants and Items, writing each line in the respective format.
<div style="page-break-after: always;"></div>

The class interactions during saving are displayed in the **Sequence Diagram** below.

<img src= "images/StorageSavingSequenceDiagram.png" width="550">

The logic for the saving of `Item`s is similar to that for `Participant`s.

<div style="page-break-after: always;"></div>

Reading and writing from and to `data.txt` is done through operations from the **OpenCSV** library, namely:

* `CSVReader#readAll()`, which is invoked by `FileParser` when loading data, to convert the file into a list of arrays of `String` to be parsed.
* `CSVWriter#writeNext()`, which is invoked when saving data, to save the fields for an `Event`, `Participant`, or `Item` into the file.

<div style="page-break-after: always;"></div>

## Appendix A: Product scope

### Target user profile
The target user:

* has a need to organise a large number of events
* organises small-scale events, such that he is able to handle all matters on his own
* prefers typing to mouse interactions
* is comfortable using a command-line interface

### Value proposition

The user is able to organise and manage his events more quickly and efficiently than with a mouse/GUI app

## Appendix B: User Stories

| Version | As a ... | I want to ...                                                   | So that I can ...                                                                         |
|---------|----------|-----------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| v1.0    | new user | see the list of commands                                        | know how to format my input                                                               |
| v1.0    | user     | add new events                                                  | manage future events                                                                      |
| v1.0    | user     | remove events from the events list                              | maintain the event list with events that are relevant and current                         |
| v1.0    | user     | list all current events                                         | know which events are currently ongoing                                                   |
| v1.0    | user     | add participants to a specific event                            | ensure all relevant individuals are included in that event's participant list efficiently |
| v1.0    | user     | remove participants who are no longer coming to specific events | efficiently keep the participant list for that event up-to-date and relevant              |
| v1.0    | user     | view the participant list of an event                           | know who is involved                                                                      |
| v2.0    | user     | mark events as completed                                        | easily track all past events                                                              |
| v2.0    | user     | mark participants present                                       | exactly know who signed up but did not attend the event                                   |
| v2.0    | user     | save events info                                                | still access the information if the program terminates                                    |
| v2.0    | user     | filter events by keywords                                       | find relevant information efficiently                                                     |
| v2.0    | user     | edit event details                                              | update latest changes to events                                                           |
| v2.0    | user     | copy participant details across events                          | update events with the same participants efficiently                                      |    
| v2.0    | user     | sort events by certain order (e.g. Priority)                    | visually view events in a certain order                                                   |
| v2.0    | user     | find if a person is in a certain event                          | quickly confirm a participant’s involvement in an event                                   |
| v2.0    | user     | add items to a specific event                                   | keep track of what I need for that event                                                  |
| v2.0    | user     | mark items as accounted for                                     | make sure I do not prepare excess items for an event                                      |

<div style="page-break-after: always;"></div>

## Appendix C: Non-Functional Requirements

* Should work for any **mainstream OS** (Windows, macOS, Linux) as long as Java 17 is installed.
* Should be able to store data for a large number of `Event`s without any loss in performance.
* Should be able to execute any command within 1 second of receiving user input.
* Should be able to load a corrupted save file without crashing.
* Should be able to work in any directory that the `JAR` is copied to.

## Appendix D: Glossary

* _Command_ - an action that is carried out in the program as a result of user input.
* _List_ - a container class that stores multiple instances of an object. 
* _Parameter_ - a value in the user command input that is used for the parsing of a command.

## Appendix E: Instructions for manual testing

### Launch and shutdown

1. Initial launch

   1. Download the `.jar` file and copy it to an empty folder.  
   
   2. Open a new terminal window in the folder the `.jar` file is in, and enter the command `java -jar manager.jar`.
      Expected: The terminal will print a welcome message and prompt for user input.

### Adding an event

1. Adding an `Event` to the `Event` list

   1. Prerequisite: An event with the name `Event 1` is not present in the `Event` list.
      List all `Event`s with `list` after each test case.
   
   2. Test case: `add -e Event 1 -t 2024-10-10 18:00 -v Venue 1 -u high`
      Expected: An `Event` with name `Event 1` is added to the `Event` list. A success message is shown.

   3. Test case: `add -e Event 1 -t 2024-10-10 -v Venue 1 -u HIGH`  
      Expected: No `Event` is added. A date-time format error message is shown.

   4. Test case: `add -e Event 1 -t 2024-10-10 18:00 -v Venue 1 -u top`  
      Expected: No `Event` is added. An error message is shown.
   <div style="page-break-after: always;"></div>

2. Adding a duplicate `Event` to the `Event` list

   1. Prerequisite: An event with the name `Event 1` is present in the list.  
      List all `Event`s with `list` after each test case.
   
   2. Test case: `add -e Event 1 -t 2024-10-10 18:00 -v Venue 1 -u HIGH`  
      Expected: A `Event` with name `Event 1(1)` is added. A success message is shown.

### Adding a participant

1. Adding a duplicate `Participant` to an `Event`
   
   1. Prerequisite: An event with the name `Event 1` is present in the list.
      A `Participant` with the name `Participant 1` is present in `Event 1`'s `Participant` list.
      List all `Participant`s with `view -e Event 1 -y participant` after each test case.
   
   2. Test case: `add -p Participant 1 -email part@gmail.com -e Event 1`.   
      Expected: A `Participant` with name `Participant 1(1)` is added. A success message is shown.

### Adding an item

1. Adding a duplicate `Item` to an `Event`

   1. Prerequisite: An event with the name `Event 1` is present in the list.
      A `Item` with the name `Item 1` is present in `Event 1`'s `Item` list.
      List all `Item`s with `view -e Event 1 -y item` after each test case.

   2. Test case: `add -p Item -e Event 1`.  
      Expected: A `Item` with name `Item(1)` is added. A success message is shown.

### Removing an event

1. Removing an `Event` from the `Event` list

   1. Prerequisite: An event with the name `Event 1` is present in the list.
      An event with the name `Event 2` is not present in the list.
      List all `Event`s with `list` after each test case.
   
   2. Test case: `remove -e Event 1`  
      Expected: The `Event` with name `Event 1` is removed. A success message is shown.

   3. Test case: `remove -e Event 2`  
      Expected: No `Event` is removed. An error message is shown.
<div style="page-break-after: always;"></div>

### Removing a participant

1. Removing a `Participant` from an `Event`

   1. Prerequisite: An event with the name `Event 1` is present in the list.
      A `Participant` with the name `Participant 1` is present in `Event 1`'s `Participant` list.
      A `Participant` with the name `Participant 2` is not present in `Event 1`'s `Participant` list.
      List all `Participant`s with `view -e Event 1 -y participant` after each test case.

   2. Test case: `remove -p Participant 1 -e Event 1`  
      Expected: The `Participant` with name `Participant 1` is removed from `Event 1`'s `Participant` list. A success message is shown.

   3. Test case: `remove -p Participant 2 -e Event 1`  
      Expected: No `Participant` is removed. An error message is shown.

2. Removing a `Participant` from an invalid `Event`

   1. Prerequisite: An event with the name `Event 1` is not present in the list.
      List all `Participant`s with `view -e Event 1 -y participant` after each test case.
   
   2. Test case: `remove -p Participant 1 -e Event 1`  
      Expected: No `Participant` is removed. An error message is shown.

### Removing an item

1. Removing a `Item` from an `Event`

   1. Prerequisite: An event with the name `Event 1` is present in the list.
      A `Item` with the name `Item 1` is present in `Event 1`'s `Item` list.
      A `Item` with the name `Item 2` is not present in `Event 1`'s `Item` list.
      List all `Item`s with `view -e Event 1 -y item` after each test case.

   2. Test case: `remove -m Item 1 -e Event 1`  
      Expected: The `Participant` with name `Participant 1` is removed from `Event 1`'s `Participant` list. A success message is shown.

   3. Test case: `remove -m Item 2 -e Event 1`  
      Expected: No `Item` is removed. An error message is shown.

2. Removing a `Item` from an invalid `Event`

   1. Prerequisite: An event with the name `Event 1` is not present in the list.
      List all `Item`s with `view -e Event 1 -y item` after each test case.

   2. Test case: `remove -m Item 1 -e Event 1`  
      Expected: No `Item` is removed. An error message is shown.

### Editing an event

1. Editing an `Event` in the `Event` list.

   1. Prerequisite: An event with name `Event 1` and venue `Function Room` is present in the list.  
      List all `Event`s with `list` after each test case.   
   
   2. Test case: `edit -e Event 1 -name Event 1 -t 2024-10-25 16:00 -v Billiards Room -u HIGH`  
      Expected: The venue for `Event 1` is changed to `Billiards Room`. A success message is shown.

### Editing a participant

1. Editing a `Participant` in an `Event`

   1. Prerequisite: An event with the name `Event 1` is present in the list.
      A `Participant` with the name `Jonathan` and email `than@gmail.com` is present in `Event 1`'s `Participant` list.
      List all `Participant`s with `view -e Event 1 -y participant` after each test case.
   
   2. Test case: `edit -p Jonathan -name Jonathan -n 91823213 -email jona@gmail.com -e Event 1`  
      Expected: The email for `Jonathan` is changed to `jona@gmail.com`. A success message is shown.

### Editing an item

1. Editing an `Item` in an `Event`

   1. Prerequisite: An event with the name `Event 1` is present in the list.
      A `Item` with the name `Toilet roll` is present in `Event 1`'s `Item` list.
      List all `Item`s with `view -e Event 1 -y item` after each test case.

   2. Test case: `edit Toilet roll > Kitchen towel -e Event 1`  
      Expected: `Toilet roll` is no longer present, and `Kitchen towel` is present, in the `Item`s list. A success message is shown.

### Viewing an event

1. Viewing the `Participant`/`Item` list of an `Event`

   1. Prerequisite: An event with the name `Event 1` is present in the list.
   
   2. Test case: `view -e Event 1 -y person` 
      Expected: An error message is shown.
<div style="page-break-after: always;"></div>

### Marking an event as done

1. Marking an invalid `Event` as done

   1. Prerequisite: An event with the name `Event 1` is not present in the list.
      List all `Event`s with `list` after each test case.

   2. Test case: `mark -e Event 1 -s done`  
      Expected: No `Event` is marked. An error message is shown.

### Marking a participant as present

1. Marking an `Participant` in an `Event`

   1. Prerequisite: An event with the name `Event 1` is present in the list.
      A `Participant` with the name `Participant 1` is present in `Event 1`'s `Participant` list.
      A `Participant` with the name `Participant 2` is not present in `Event 1`'s `Participant` list.
      List all `Participant`s with `view -e Event 1 -y participant` after each test case.

   2. Test case: `mark -p Participant 1 -e Event 1 -s present`  
      Expected: The `Participant` with name `Participant 1` is marked as present. A success message is shown.

   3. Test case: `mark -p Participant 1 -e Event 1 -s done`  
      Expected: No `Participant` is marked. An invalid status error message is shown.
   
   4. Test case: `mark -p Participant 2 -e Event 1 -s present`  
      Expected: No `Participant` is marked. An error message is shown.

2. Marking a `Participant` in an invalid `Event`

   1. Prerequisite: An event with the name `Event 1` is not present in the list.
      List all `Participant`s with `view -e Event 1 -y participant` after each test case.

   2. Test case: `mark -p Participant 1 -e Event 1 -s present`  
      Expected: No `Participant` is marked. An error message is shown.
<div style="page-break-after: always;"></div>

### Marking an item as accounted for

1. Marking an `Item` in an `Event`

   1. Prerequisite: An event with the name `Event 1` is present in the list.
      A `Item` with the name `Item 1` is present in `Event 1`'s `Item` list.
      A `Item` with the name `Item 2` is not present in `Event 1`'s `Item` list.
      List all `Item`s with `view -e Event 1 -y item` after each test case.

   2. Test case: `mark -m Item 1 -e Event 1 -s accounted`  
      Expected: The `Item` with name `Item 1` is marked as accounted for. A success message is shown.

   3. Test case: `mark -m Item 1 -e Event 1 -s done`  
      Expected: No `Item` is marked. An invalid status error message is shown.

   4. Test case: `mark -m Item 2 -e Event 1 -s accounted`  
      Expected: No `Item` is marked. An error message is shown.

2. Marking an `Item` in an invalid `Event`

   1. Prerequisite: An event with the name `Event 1` is not present in the list.
      List all `Item`s with `view -e Event 1 -y item` after each test case.

   2. Test case: `mark -m Item 1 -e Event 1 -s accounted`  
      Expected: No `Item` is marked. An error message is shown.

### Copying the participant list

1. Copying a `Participant` list of size 0

   1. Prerequisite: Events with the names `Event 1` and `Event 2` are present in the list.
      `Event 1` has no `Participant`s in its `Participant`s list.
   
   2. Test case: `copy Event 1 > Event 2`  
      Expected: The `Participant` list in `Event 1` is not copied over to `Event 2`. An error message is shown.

2. Copying a `Participant` list to an event with an existing `Participant` list.

    1. Prerequisite: Events with the names `Event 1` and `Event 2` are present in the list.
       Both `Event 1` and `Event 2` have `Participant`s in their `Participant` lists.

    2. Test case: `copy Event 1 > Event 2`  
       Expected: The `Participant` list in `Event 1` is copied over to `Event 2`, overwriting `Event 2`'s `Participant` list. A success message is shown.
<div style="page-break-after: always;"></div>

### Sorting the event list

1. Sorting the `Event` list by name

   1. Prerequisite: Events with the names `Doughnut making`, `Chocolate making` and `Bread making` are present in the `Event` list.
   
   2. Test case: `sort -by name`  
      Expected: A list of `Event`s is shown, with `Bread making` coming before `Chocolate making`, and `Chocolate making` coming before `Doughnut making`.

### Filtering the event list

1. Filtering out `Event`s by name

   1. Prerequisite: Events with the names `Knitting class` and `Crochet class` are present in the `Event` list.
   
   2. Test case: `filter -e class`  
      Expected: A list of `Event`s, including `Knitting class` and `Crochet class`, is shown. 

### Finding a participant

1. Finding `Participant`s in an invalid `Event`

   1. Prerequisite: An event with the name `Event 1` is not present in the `Event` list.
   
   2. Test case: `find -e Event 1 -p John`  
      Expected: No `Participant`s are listed. An error message is shown.

### Saving and loading of data

1. Loading from a corrupted data file

   1. Prerequisite: Multiple `Event`s are present in the `Event` list.

   2. Run the program by opening a new terminal window and entering `java -jar manager.jar`.
      The program would give a warning that a line cannot be loaded, and the `Event` represented by the line would not be present in the `Event`s list.
      
   3. Essentially all corrupted rows are ignored and file parsing will still work.
