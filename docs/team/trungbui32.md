# Bui The Trung - Project Portfolio Page

## Project: TutorLink

TutorLink is a desktop CLI application designed to help
University professors better manage the grades of students
reading their course.

TutorLink keeps a running tally of grades (GPA, overall score) of each
`Student` by tagging `Components` to each student via a `Grade` object.

TutorLink provides useful summary statistics of a professor's course that
can be further filtered down for analysis.

### Summary of Contributions

- **Command**:
    - `ListGradeCommand` command:
        - Lists all student grades in the system, showing each student's name, matriculation number, component scores,
          and
          final percentage score in a formatted report
        - Can filter to show grades for a specific student when given their matriculation number (using i/ prefix),
          displaying their individual component scores with maximum possible points and final percentage score
    - `ListComponentCommand` command:
        - Simply lists all assessment components in the system by returning the string representation of the components
          stored in AppState
        - Takes no arguments/parameters (returns null for argument prefixes) and displays components directly from the
          application's state
    - `HelpCommand` command:
        - The HelpCommand class is used to display a list of available commands and
          their usage when the "help" command is triggered.
        - This command does not require any arguments and returns a CommandResult with the help message defined in
          Commons.HELP_MESSAGE

- **Component**:
    - `Component`:
        - Represents an assessment component in the system with three attributes: name (identifier), maxScore (maximum
          possible points), and weight (percentage contribution to final grade)
        - Provides getter/setter methods for its attributes and overrides equals() to compare components by name (
          case-insensitive) and toString() to display the component's details in format "name (maxScore: X, weight: Y%)"
    - `Grade`:
        - Represents a student's grade for a specific component, linking three pieces of information together: the
          student who received the grade, the component being graded, and the actual score (with automatic capping at
          component's max score)
        - Provides methods to access grade information and overrides equals() to compare grades based on both component
          and student (two grades are equal if they're for the same component and student) and toString() to display all
          grade details in a formatted string
- **Diagram**:
    - `DeleteComponentCommandUML`:
        - UML diagram for DeleteComponentCommand command
    - `AddComponentCommandUML`
        - UML diagram for AddComponentCommandUML
- **Test**:
    - `ComponentTest`:
        - Tests the constructor and getter methods of the Component class by creating test components ("Assignment 1"
          and "
          Final
          Exam") and verifying their name, maxScore, and weight values are correctly stored
        - Tests the equals() method by verifying that components with the same name (case-insensitive) are considered
          equal
          while components with different names, null values, or different object types are considered not equal

- **Code contribution
  **: [RepoSense](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=TrungBui32&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-09-20&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
- Project Management:
    - Assisted with release `V2.0`
    - PRs reviewed: [#51](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/51)
- Enhancement to existing features:
    - Wrote tests for Component [#77](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/77)
- **Documentation**:
    - Developer Guide:
        - Create UML for DeleteComponentCommand [#107](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/107) and
          AddComponentCommand [#105](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/105)
        - Non-Functional Requirements
- **Features implemented:**
    - Implemented ListGradeCommand [#91](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/91),
      ListComponentCommand [#83](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/83), and HelpCommand [#216](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/216)
    - Implemented Component [#49](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/49) and
      Grade [#55](https://github.com/AY2425S1-CS2113-W13-4/tp/pull/55)