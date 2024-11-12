# FU Yixuan - Project Portfolio Page

## Overview
EventManagerCLI is a desktop CLI application designed for managing small-scale events organised by a single user. It is written in Java, and has around 7kLoC.
The app is optimised for quick, command-based interactions, ideal for users who prefer or need a text-based interface for efficient event management.

### Summary of Contributions

* **New feature**: Added the feature to list events. [#46](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/46)
  * **What it does**: Allows the user to view all scheduled events, displaying details such as event name, datetime, and venue in a comprehensive list.
  * **Justification**: This feature is essential for users to have a quick overview of all planned events, helping them manage and review their schedules effectively.
  * **Highlights**: The list feature ensures events are displayed in an organized format, making it easy for users to scan through their events.

* **New feature**: Added the feature to sort events by name. [#99](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/99)
  * **What it does**: Enables users to sort their list of events alphabetically by event name, helping them locate events more easily.
  * **Justification**: Sorting functionality enhances usability by providing an organized view of events, especially when the user has multiple events to manage.
  * **Highlights**: This feature will output a new sorted list without modifying the original version of the list.

* **New feature**: Added the feature to edit the information of events. [#135](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/135)
  * **What it does**: Allows users to update event details, such as the name, datetime, venue, or priority of existing events.
  * **Justification**: Events may need adjustments after being scheduled, and this feature allows users to make changes without deleting and re-adding events, maintaining consistency in their schedule.
  * **Highlights**: Input validation is performed on the updated details to prevent errors, ensuring the event is found correctly before editing.

* **New feature**: Added the feature to edit the items of an event. [#143](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/143)
  * **What it does**: Enables users to modify items associated with a particular event, such as changing item names.
  * **Justification**: This feature allows users to keep track of necessary items for each event and make updates as needed, helping them stay organized.
  * **Highlights**: Includes checks for invalid or non-existent items, providing clear error messages to guide the user and improve overall usability.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=matchaRRR&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=MatchaRRR&tabRepo=AY2425S1-CS2113-W13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false&reverseAuthorshipOrder=true)

* **Project management**:
    * Managed release `v1.0` (1 release) on GitHub

* **Enhancements to existing features**:
  * Implement an EventList class to organize the events. [#35](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/35)
  * Developed the `main` function to initialize and run the application, setting up the primary command loop structure. [#45](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/45)

* **Documentation**
    * User Guide
        * Added documentation for list features. [#155](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/155)
        * Added documentation for edit features. [#155](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/155)
    * Developer Guide
        * Added implementation details for `list`, and `edit` features. [#154](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/154)
        * Created sequence diagrams for `list`, `edit` features. [#154](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/154)

* **Community**
    * PRs reviewed (with non-trivial review comments): [#136](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/136), [#79](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/79), [#65](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/65)
