# Lucas Spencer Chan - A0254523J - (lucas-sc0) - Project Portfolio Page (PPP)

## Project Overview: Inventra  
Inventra is a CLI-based inventory management system designed for small and medium-sized businesses.
It provides users with tools to manage custom inventory fields, track records and generate insights.
This product was build with the contribution of T11 G4 team members, as part of CS2113 module.

### Summary of Contributions
### Code Contributions:
Please view all my contributions to the project codes [here](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=lucas-sc0&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other).

### Enhancements Implemented:
1. Implemented Core Functionalities:
* `add -h` : Enable user to add custom headers(fields).
* `AddCommand.java`: Implemented the input validation and input processing for add -h command.
* `Command.java`: Implemented abstract Command class which all other Command classes inherit from.
* `Inventory.java`: Created the data structures and defined the operations used for our inventory.
* `UI.java`: Created the visual table format which all fields and records are displayed in.
* `CommandParser.java`: Implemented CommandParser class in adherence to SRP.
* `CSV.java`: Fixed errors with metadata handling after deletion, ensured CSV metadata is updated correctly after delete -e 
and delete -a. Fixed inverted values being displayed in CSV due to hashmap implementation for Inventory data structure.


2. Exception Handling and Assertions:
* Custom exception were build on-top of existing code to handle invalid inputs
    - InventraInvalidRecordCountException
* Created assertions for defensive programming
* Configured non verbose logging for add command to ensure decluttering of UI
* Addressed bugs highlighted in PE-D 

3. JUnit Test Cases
* 44 AddCommand test cases which achieved:
    * Method Coverage: 100% (7/7)
    * Line Coverage: 85% (86/101)
    * Branch Coverage: 80% (67/83)

### Contributions to User Guide (UG)
1. Contributed and Updated the following `Command` Sections: `help`, `update`, `delete`.
2. Reformatted `delete` command section to adhere to the standards of UG documentation.
3. Created table of contents and ensured workable links.
4. Contributed to FAQ and Known Issues sections of the UG.
5. Periodic updates to UG
    * Minor bug fixes to inconsistencies in UG such as missing links and incorrect naming.
    * Update of Command Summary after addition of new commands.

### Contributions to Developer Guide (DG)
* Created all diagrams in the DG consisting of all the Architecture Diagram, Class Diagrams and Sequence Diagrams
* Provided detailed implementation descriptions for `UpdateCommand`:
  - Included method breakdown and descriptions of class, key methods and flags involved.
  - Documented rationale of method-used to implement `UpdateCommand`, and alternative considerations.
* Created table of contents and ensured workable links.
* Overall formatting to correspond to the diagrams.
* Added to the Acknowledgements section.

### Contributions to Team-Based Tasks
1. Milestone Management:
* Updated master/README.md to reflect `Inventra's` product description.
* Release management of Inventra 1.0, 2.0 and 2.1.
* Ensure github pages are up accessible by public.
* Resolved and integrated merge conflicts.

2. Project Management:
* Suggested idea of either a business analytics system or inventory management system in Zoom meetings. Eventually decided on the inventory management system.
* Brainstormed ideas of possible implementations of commands and command flags in Zoom meetings.
* Suggested approach for data persistence using CSV while staying within project constraints in Zoom meetings.
* Suggested user session/account system but was scrapped due to project constraints in Zoom meetings.
* Proposed delegation of tasks.

### Summary
Through my contributions to the development of Inventra, I played a key role in implementing core functionalities, 
Additionally, I contributed significantly to the project’s documentation, especially the User Guide and 
Developer Guide, by updating command sections, creating diagrams, and ensuring consistency and clarity. 
I also contributed to the team’s project management efforts, helping define the product vision, managing milestones, 
and resolving technical challenges, which ensured the successful delivery of multiple product versions on schedule.



