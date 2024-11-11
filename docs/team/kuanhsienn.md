# Liang Kuan Hsien - Project Portfolio Page

## Overview
EventManagerCLI is a desktop CLI application meant for managing small-scale events organised by one person. It is written in Java, and has around 8kLoC.
The app is optimised for quick, command-based interactions, ideal for users who prefer or need a text-based interface for efficient event management.

### Summary of Contributions
* **New feature**: Added the feature to add events.
    * What it does: Allows the user to add events by specifying details such as event name, datetime, venue.
    * Justification: This feature is a core functionality of the application, enabling users to keep track of planned events and manage their schedules effectively.
    * Highlights: The add event functionality includes validation of input formats (such as date and time) and prevents duplicate event entries. 

* **New feature**: Added the feature to remove events.
    * What it does: Enables users to delete events they no longer need, helping them keep the event list relevant and up-to-date.
    * Justification: This feature is essential for maintaining an organised event list. Users can remove events that are canceled or no longer relevant, which helps in decluttering their schedule.
    * Highlights:  This feature also checks for invalid entries or non-existent events, providing error messages to guide users, thus enhancing usability and robustness.

* **New feature**: Added the feature to save/load EventManager details to/from hard disk
    * What it does: Enables users to save event details (such as event list, participants, and items) to a file on the hard disk and load it back when the application is restarted. This ensures data persistence between sessions.
    * Justification: Persistent storage is a crucial feature for any event management tool, as it allows users to retrieve previously entered data and continue their work seamlessly.
    <div style="page-break-after: always;"></div>

  * Highlights: The storage feature supports loading from a single CSV file, handling all event-related data in one file. 
  It includes validation checks to ensure data integrity, and if any data entry is corrupted or unreadable, a warning is logged without crashing the application.
  At the same time, it is able to save a wider range of characters due to adoption of UTF-8 encoding.

* **New feature**: Added feature to edit participant's contact info.
    * What it does: Allows the user to update the contact details (phone number and email) of participants associated with any event.
    * Justification:  Contact information can frequently change; this feature allows users to keep participant details current without needing to re-enter or delete participant records, maintaining data accuracy for effective communication.
    * Highlights: Provides validation for input formats, such as phone numbers and emails, and gives feedback when inputs are incorrect, ensuring data integrity

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=KuanHsienn&tabRepo=AY2425S1-CS2113-W13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
* **Project management**:
    * Managed release `v1.0` (1 release) on GitHub

* **Enhancements to existing features**:
    * Fixed input formats of email and phone number of participants (PR [#150](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/150))
    * Implemented feature to parse other characters outside of ASCII (PR [#235](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/235))
    * Fixed bugs from PE (PR [#150](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/150), [#249](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/249), [#252](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/252))
  
* **Documentation**
    * User Guide
        * Added documentation for storage features (PR [#156](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/156))
    * Developer Guide
        * Added design details for `add`, `remove` and `view` components (PR [#128](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/128))
        * Added implementation details for the `add`, `remove`, `view`, `copy`, `sort` and `find` feature (PR [#128](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/128), [#142](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/142)) 
        * Added documentation for storage features (PR [#156](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/156))

* **Community**
    * PRs reviewed (with non-trivial review comments): (PR [#119](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/119), [#130](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/130), [#149](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/149), [#234](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/234))
