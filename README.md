# EasInternship ReadME

This ReadME contains information about EasInternship, your one stop shop for all your internship searching requests.


## Product Description
This product aims to streamline the tracking of internship applications for the user, where the user is able to store key details of the internship as well as save and edit the status of the application. 

**By using this tool, the user can:**

**Store Important Internship Information:** Users can input and store key details such as the role, company, duration, required skills, application deadlines, and any additional notes.

**Track Application Status:** Users can save the current status of their application (e.g., not applied, applied, interview scheduled, offer received) and update it as the application progresses.

**Edit and Manage Internships:** Users have the ability to edit saved internships as needed, modify statuses, update deadlines, or remove outdated internships from the list.

**Filter Internships by Key Criteria:** The user can filter the tracked internships by relevant skills, application status, or upcoming deadlines, allowing for better organization and prioritization.

**Monitor Deadlines Easily:** The tool helps ensure users stay on top of their internship deadlines by flagging or sorting applications with approaching deadlines.

## Setting up in Intellij

Prerequisites: JDK 17 (use the exact version), update Intellij to the most recent version.

1. **Ensure Intellij JDK 17 is defined as an SDK**, as described [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk) -- this step is not needed if you have used JDK 17 in a previous Intellij project.
1. **Import the project _as a Gradle project_**, as described [here](https://se-education.org/guides/tutorials/intellijImportGradleProject.html).
1. **Verify the setup**: After the importing is complete, locate the `src/main/java/seedu/duke/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the below:
   ```
**CS2113 T10 Team 1**

**Jaden Lim Jie Chen**

**Tan Ting Hui**

**Jai Vinod Kumar Rahul**

**Man Kit Yu****




## Build automation using Gradle

* This project uses Gradle for build automation and dependency management. It includes a basic build script as well (i.e. the `build.gradle` file).
* If you are new to Gradle, refer to the [Gradle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/gradle.html).

## Testing

### I/O redirection tests

* To run _I/O redirection_ tests (aka _Text UI tests_), navigate to the `text-ui-test` and run the `runtest(.bat/.sh)` script.

### JUnit tests

* A skeleton JUnit test (`src/test/java/seedu/duke/DukeTest.java`) is provided with this project template. 
* If you are new to JUnit, refer to the [JUnit Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/junit.html).

## Checkstyle

* A sample CheckStyle rule configuration is provided in this project.
* If you are new to Checkstyle, refer to the [Checkstyle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/checkstyle.html).

## CI using GitHub Actions

The project uses [GitHub actions](https://github.com/features/actions) for CI. When you push a commit to this repo or PR against it, GitHub actions will run automatically to build and verify the code as updated by the commit/PR.

## Documentation

`/docs` folder contains a skeleton version of the project documentation.

Steps for publishing documentation to the public: 
1. If you are using this project template for an individual project, go your fork on GitHub.<br>
   If you are using this project template for a team project, go to the team fork on GitHub.
1. Click on the `settings` tab.
1. Scroll down to the `GitHub Pages` section.
1. Set the `source` as `master branch /docs folder`.
1. Optionally, use the `choose a theme` button to choose a theme for your documentation.
