# EasInternship ReadME

This ReadME contains information about EasInternship, your one stop shop for all your internship searching requests.

## Setting up in Intellij

Prerequisites: JDK 17 (use the exact version), update Intellij to the most recent version.

1. **Ensure Intellij JDK 17 is defined as an SDK**, as described [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk) -- this step is not needed if you have used JDK 17 in a previous Intellij project.
1. **Import the project _as a Gradle project_**, as described [here](https://se-education.org/guides/tutorials/intellijImportGradleProject.html).
1. **Verify the setup**: After the importing is complete, locate the `src/main/java/seedu/duke/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the below:
   ```
 CS2113 T10 Team 2
Jaden Lim Jie Chen
Tan Ting Hui
Jai Vinod Kumar Rahul
Man Kit Yu
Features
Search for Internship by default settings
Search Command: search
Searches the relevant websites (LinkedIn, Indeed, NUSTalentConnect) for roles related to Computer Engineering. This search does not take into account the user's skills and will return all results that have Computer Science / Engineering attached to them.
User can click on embedded link within the role title to be directed to application website

Format: search

Example Output
Software Engineer Intern, Google
Duration: 05/25 - 08/25
Skills: Go, Python, Java, Communication Skills
Deadline: 10/24

Autonomous Vehicle Embedded Software Engineer Intern
	Duration: Not stated
	Skills: Bash, Python, C++, ROS
	Deadline: Not stated


Search for Internship by skills
This feature builds upon the previous feature. It provides the additional functionality of being able to filter the search results by a certain subset of skills. It showcases the most relevant internships which contain all the skills mentioned by the user at the top. The remaining internships which contain a few of the skills mentioned by the user are displayed at the bottom.

Format: search -skills {skill 1} {skill 2} {skill 3}

Example inputs and outputs :-

Input : search -skills {Python} 
Output :

Autonomous Vehicle Embedded Software Engineer Intern
	Duration: 05/25 - 07/25
	Skills: Bash, Python, C++, ROS
	Deadline: 10/24

Software Engineer Intern, Shopee
Duration: 05/25 - 07/25
Skills: Java, Python, SQL, Communication Skills
	Deadline: 11/24

Data Science Intern, Grab
Duration: 05/25 - 06/25
Skills: Python, Machine Learning, SQL, Data Analysis
Deadline: 01/25

Input : search -skills {Python} {SQL}
Output :

Software Engineer Intern, Shopee
Duration: 05/25 - 07/25
Skills: Java, Python, SQL, Communication Skills
	Deadline: 11/24

Data Science Intern, Grab
Duration: 05/25 - 06/25
Skills: Python, Machine Learning, SQL, Data Analysis
Deadline: 01/25

Autonomous Vehicle Embedded Software Engineer Intern
	Duration: 05/25 - 07/25
	Skills: Bash, Python, C++, ROS
	Deadline: 10/24

SQL Developer Internship, APSV Technologies
Duration: 06/25 - 08/25
Skills: SQL, Database management, Data Analysis
Deadline: 02/25
Search for Internship by duration
This feature builds upon the first feature. It provides the additional functionality of being able to filter the search results by a certain date. It filters internships by date. All results within the boundaries provided will be listed.

Format: search -from {MM/YY} -to {MM/YY}

Input : search -from {05/25} -to {07/25} 
Example Output :

Autonomous Vehicle Embedded Software Engineer Intern
Duration: 05/25 - 07/25
Skills: Bash, Python, C++, ROS
Deadline: 10/24

Software Engineer Intern, Shopee
Duration: 05/25 - 07/25
Skills: Java, Python, SQL, Communication Skills
Deadline: 11/24

Data Science Intern, Grab
Duration: 05/25 - 06/25
Skills: Python, Machine Learning, SQL, Data Analysis
Deadline: 01/25



Help Command: help
Shows the commands available for the user
Describes the search function and that inputs are needed
Other basic commands

Format: /help

Example Output
-location {location}: Modifies the search function to print listings for that specified location

-skills {skill 1} {skill 2}: Modifies the search function to only print listings which have specified those skills

	/exit: terminates the programme

Exit Command
Terminates the program.

Format: /exit
Invalid Inputs
Shows whenever an incomplete or invalid search call is performed. Output also shows what corrective action can be taken.

Example Output:
Please include a location if you are using the -location flag!

Please include specific skills if you are using the -skills flag!

I do not understand what flag you are using, consider typing /help to get a list of valid commands


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
