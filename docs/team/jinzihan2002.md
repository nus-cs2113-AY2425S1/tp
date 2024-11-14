# Jin Zihan's Project Portfolio Page

## Project: TutorLink

TutorLink is a desktop CLI application designed to help
University professors better manage the grades of students
reading their course.

TutorLink keeps a running tally of grades (GPA, overall score) of each
`Student` by tagging `Components` to each student via a `Grade` object.

### Summary of Contributions

- **New Feature:** Added Storage classes to handle interactions with data files
  - What it does: The `StudentStorage`, `ComponentStorage` and `GradeStorage` classes handle the loading of 
  `.txt` data files when the application starts up, and the saving of the Student, Component and Grade lists into 
  the data files after every user command.
  - Justification: This feature improves the product by automatically saving list data after every command execution,
  and allowing data from the application to be retained between runs, thus removing the need for the user to re-enter 
  past data manually.
  - Highlights: Making the load file function robust enough to handle invalid data in the storage files was a challenge, 
  as there were many ways storage data could be modified or corrupted to cause errors. The function also had to be 
  modified to display the discarded data to the user, instead of silently dropping them when loading. 
  The implementation allows individual invalid file lines to be discarded, instead of resetting to an empty file.

- **New Feature:** Added `ComponentList` class to manage the list of components
  - What it does: This class contains the list of `Components`, as well as add, delete, find and toString methods.
  - Justification: The methods facilitate modifications to the list of components through the user commands, 
  as well as printing of the list to the command line.


- **Code contributed:** [RepoSense link](
  https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=jinzihan2002&breakdown=true)


- **Project Management:**
  - Assisted with release [`v1.0`](https://github.com/AY2425S1-CS2113-W13-4/tp/releases/tag/v1.0) and the
    migration of outstanding issues to `v2.0`, as well as the release of
    [`v2.0`](https://github.com/AY2425S1-CS2113-W13-4/tp/releases/tag/v2.0).

- **Enhancements to existing features:**
  - Updated `runtest` scripts to accept initial data files, to facilitate I/O redirection tests on handling of 
  data file entries: [#206](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/206)
  - Added tests for data file error handling: [#221](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/221)
  - Created string of list of commands for use in the help command: 
  [#209](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/209)

- **Documentation:**
  - Developer Guide:
    - Created sequence diagram and documentation for the Storage classes: 
    [#104](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/104)
    - Updated GradeStorage and Setup sequence diagrams to match code changes: 
    [#134](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/134), 
    [#220](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/220)
  - User Guide:
    - Added documentation for Storage 

- **Community:**
  - PRs reviewed: 
  [#25](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/25),
  [#99](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/99),
  [#216](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/216)
