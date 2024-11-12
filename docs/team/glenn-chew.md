# Glenn Chew - Project Portfolio Page

## Overview
EventManagerCLI is a desktop CLI application designed for managing small-scale events organised by a single user. It is written in Java, and has around 8kLoC.
The app is optimised for quick, command-based interactions, ideal for users who prefer or need a text-based interface for efficient management of events.

### Summary of Contributions
* **New Feature**: Implemented Menu (PR: [#37](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/37))
  * What it does: Displays the list of commands that users can enter, including the format and a brief description of each command.
  * Justification: This feature helps ensure that users are aware of the correct commands to input, which aids in the usage of the program.
  * Highlights: This feature was gradually updated based on new commands, new details or new usage of the program. 


* **New Feature**: Added feature to view the lists of participants for an event (PR: [#58](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/58))
    * What it does: Displays the participant list of a particular event.
    * Justification: This feature allows users to view all the participants of an event and allows them to see the participants they have added or removed from that event.   
    * Highlights: This feature was updated when details of participants were included, and was eventually updated to be able to view either the participant list or item list of an event.


* **New Feature**: Added feature to filter events by name or date/time (PR: [#144](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/144))
  * What it does: Displays the events that contains the keyword entered by the user, which is by name or by date/time.
  * Justification: This feature makes it easier for the user to see all the events with a common name, or common date/time.
  * Highlights: This feature has separate methods to filter by date, time or date-time, as the formatting for each input is different.


* **New Feature**: Added feature to sort events by date (PR: [#102](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/102))
  * What it does: Sorts the event to be displayed in chronological order (earliest to latest).
  * Justification: This makes it easier for users to view the order of their events, and see which ones are upcoming.
  * Highlights: This feature requires the addition of a new class and new methods, and uses the LocalDateTime class for comparison. 


* **Enhancements to Existing Features**
  * Changed all usages of `eventTime` from `String` to `LocalDateTime` for easier formatting and comparison, ensuring that each usage is formatted correctly: [#102](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/102)
  * Trimmed trailing spaces in input to prevent bugs in displaying details: [#58](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/58)
  * Update ViewCommand to include new participant parameters: [#133](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/133)
  * Refactored and extracted FilterCommand for better SLAP: [#164](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/164)


* **Code Contributed**: [RepoSense Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=glenn-chew&tabRepo=AY2425S1-CS2113-W13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Project Management**: 
  * Managed to release `v1.0` (1 release) on GitHub.


* Documentation
  * User Guide
    * Updated documentation for `filter` command: [#164](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/164) 
  * Developer Guide
    * Updated implementation details for `filter` command: [#164](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/164)
    * Updated sequence diagram for `filter` to include new self-invoked methods and additional alt cases: [#164](https://github.com/AY2425S1-CS2113-W13-3/tp/pull/164)
