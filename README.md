# FlashBang
FlashBang is a CLI app designed to provide students with a smart way of studying for their modules. The app will manage a limited number of flashcards for a small number of modules, optimized for users who prefer a CLI.

## Setting up in Intellij

Prerequisites: JDK 17 (use the exact version), update Intellij to the most recent version.

1. **Ensure Intellij JDK 17 is defined as an SDK**, as described [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk) -- this step is not needed if you have used JDK 17 in a previous Intellij project.
1. **Import the project _as a Gradle project_**, as described [here](https://se-education.org/guides/tutorials/intellijImportGradleProject.html).
1. **Verify the setup**: After the importing is complete, locate the `src/main/java/seedu/duke/Flashbang.java` file, right-click it, and choose `Run Flashbang.main()`. If the setup is correct, you should see something like the below:
   ```
   > Task :compileJava
   > Task :processResources NO-SOURCE
   > Task :classes
   
   > Task :Duke.main()
   FlashBang
   Welcome to the FlashBang app - learning your modules through engaging flashcards
   __________________________________________________
   Type help to view all the available commands
   __________________________________________________
   >
   ```
   Type the available commands and press enter to let the execution proceed to the end.

## Build automation using Gradle

* This project uses Gradle for build automation and dependency management. It includes a basic build script as well (i.e. the `build.gradle` file).

## Documentation

`/docs` folder contains the project documentation.