# User Guide

## Product Description
This product aims to streamline the tracking of internship applications for the user, where the user is able to store key details of the internship as well as save and edit the status of the application.

**By using this tool, the user can:**

**Store Important seedu.duke.Internship Information:** Users can input and store key details such as the role, company, duration, required skills, application deadlines, and any additional notes.

**Track Application Status:** Users can save the current status of their application (e.g., not applied, applied, interview scheduled, offer received) and update it as the application progresses.

**Edit and Manage Internships:** Users have the ability to edit saved internships as needed, modify statuses, update deadlines, or remove outdated internships from the list.

**Filter Internships by Key Criteria:** The user can filter the tracked internships by relevant skills, application status, or upcoming deadlines, allowing for better organization and prioritization.

**Monitor Deadlines Easily:** The tool helps ensure users stay on top of their internship deadlines by flagging or sorting applications with approaching deadlines.


## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Down the latest version of `.jar` file from [here]().
3. Copy the file to the home folder of your Internship Tracker.
4. Open the terminal, `cd` into the folder with the `.jar` file and use `java -jar easInternship.jar` 
   command to run the application.
5. Type the command to the CLI and press `Enter` to execute it.
6. Refer to the [Features](#features) section below for details of each command.

# Features

## Add Command: `add`

Adds a new internship entry to the list of internships currently being tracked by the user. 
Each internship is assigned a unique ID, serving as the reference for functions that call specific Internship entries
(e.g. `update`, `delete`).



**Format:** `add -role {Role name} -company {Company name} -from {date} -to {date}`

- `role` and `company` are compulsory flags.
- `from` and `to` are optional and will be replaced with `01/01` when left empty.

**Example Input 1:**
`add -name Software Engineer Intern -company Google`

**Example Output 1:**
```
ID: 01
Role: Software Engineer Intern
Company: Google
Duration: 01/01 to 01/01
Skills: Not Stated
Deadlines:
    No deadlines set.  
```

**Example Input 2:**
`add -name Embedded Software Engineer Intern -company Continental -from 05/25 -to 08/25`

**Example Output 2:**
```
ID: 02
Role: Embedded Software Engineer Intern
Company: Continental
Duration: 05/25 to 08/25
Skills: Not Stated
Deadlines:
    No deadlines set.
```

## Update Command: `update`

Updates any field of an Internship entry.

Valid Fields:<br>
- `role`
- `company`
- `status`
- `from`
- `to`
- `skills`

Valid Statuses for `status` flag:<br>
- Application Pending (Default Status)
- Application Completed
- Accepted
- Rejected


**Format:**
`update {ID} -{field} {updated information}`

**Examples:**

`update 02 -status application completed`

```
ID: 02                                       Status
Role: Embedded Software Engineer Intern      application completed
Company: Continental
Duration: 05/25 to 08/25
Skills: Not Stated
Deadlines:
    No deadlines set.
```

`update 02 -company Venti`

```
ID: 02                                       Status
Role: Embedded Software Engineer Intern      application completed
Company: Venti
Duration: 05/25 to 08/25
Skills: Not Stated
Deadlines:
    No deadlines set.
```

`update 02 -from 04/25 -skills Python`

```
ID: 02                                       Status
Role: Embedded Software Engineer Intern      application completed
Company: Venti
Duration: 04/25 to 08/25
Skills: Python
Deadlines:
    No deadlines set.
```

## Sort Command
`sort`

This feature allows you to sort and list all the internships saved in your database. The default display format is in increasing order of their ID’s (based on time of creation)

Users can specify a flag to display them in alphabetical order (regardless of uppercase or lowercase) of their role, skills and status or by deadline.

**Format:**


`sort {field}`

**Example:**

`sort -duration`

```
ID: 02
Role: Software Engineer Intern
Company: Google
Duration: 05/23 to 08/25
Skills: Not Stated
Deadlines:
    online assessment: 11/11/24

ID: 01
Role: Embedded Software Engineer Intern
Company: Continental
Duration: 05/24 to 08/24
Skills: Not Stated
Deadlines:
    interview: 25/11/24
```
`sort -role`

```
ID: 01
Role: Embedded Software Engineer Intern
Company: Continental
Duration: 05/25 to 08/25
Skills: Not Stated
Deadlines:
    interview: 25/11/24


ID: 02
Role: Software Engineer Intern
Company: Google
Duration: Not Stated
Skills: Not Stated
Deadlines:
    online assessment: 11/11/24
```

`sort -deadline`

```
ID: 02
Role: Software Engineer Intern
Company: Google
Duration: 05/23 to 08/25
Skills: Not Stated
Deadlines:
    online assessment: 11/11/24

ID: 01
Role: Embedded Software Engineer Intern
Company: Continental
Duration: 05/24 to 08/24
Skills: Not Stated
Deadlines:
    interview: 25/11/24

```
`sort -skills`

```
__________________________________________________
__________________________________________________
ID: 1	Status: Application Completed
Role: Embedded Software Engineer Intern
Company: Google
Duration: 02/23 to 05/24
Skills: No Skills Entered 
Deadlines:
    interview: 25/11/24

__________________________________________________
ID: 2	Status: Accepted
Role: Marketing sales
Company: Castify
Duration: 05/23 to 05/24
Skills: No Skills Entered 
Deadlines:
    online assessment: 11/11/24

__________________________________________________
__________________________________________________
```
`sort -status`

```
__________________________________________________
__________________________________________________
ID: 2	Status: Accepted
Role: Marketing sales
Company: Castify
Duration: 05/23 to 05/24
Skills: No Skills Entered 
Deadlines:
    online assessment: 11/11/24

__________________________________________________
ID: 1	Status: Application Completed
Role: Embedded Software Engineer Intern
Company: Google
Duration: 02/23 to 05/24
Skills: No Skills Entered 
Deadlines:
    interview: 25/11/24

__________________________________________________
__________________________________________________
```

## Filter command: 
`filter`

This feature is an extension of the previous list feature. It allows the user to specify certain values for any of the role name, company name or duration parameters based on which only the relevant internships are shown. 

The role and company name parameters are case-insensitive, For the duration parameter, this feature displays all relevant internships whose start dates and end dates are within the duration timeline specified by the user.  For the MVP version of the product, user can only sort by 1 field at a time.

**Format:**

`filter -role {Role name} -`

`filter -company {Company name}`

`filter -duration /from {start date} /to {end date}`

`filter -skills {skill}`

`filter -deadline /by {date}`

**Example:**

`list`

```
ID: 01
Role: Software Engineer Intern
Company: Google
Duration: Not Stated
Skills: Not Stated

ID: 02
Role: Embedded Software Engineer Intern
Company: Continental
Duration: 05/25 to 08/25
Skills: Not Stated
```
`filter -company Google`

```
ID: 01
Role: Software Engineer Intern
Company: Google
Duration: Not Stated
Skills: Not Stated
```

`filter -duration /from 04/25 /to 09/25`

```
ID: 02
Role: Embedded Software Engineer Intern
Company: Continental
Duration: 05/25 to 08/25
Skills: Not Stated
```

## Delete Command: 
`del`

This feature removes an entire listing from the tracker.

**Format:** `del {ID}`

**Example:**

`list`

```
ID: 01
Role: Embedded Software Engineer Intern
Company: Continental
Duration: 05/25 to 08/25
Skills: Not Stated

ID: 02
Role: Software Engineer Intern
Company: Google
Duration: Not Stated
Skills: Not Stated
```

`del {01}`

```
You have deleted this listing:

ID: 01
Role: Embedded Software Engineer Intern
Company: Continental
Duration: 05/25 to 08/25
Skills: Not Stated
```

`list`

```
ID: 02
Role: Software Engineer Intern
Company: Google
Duration: Not Stated
Skills: Not Stated
```

## Help Command: 
`help`

Shows the commands available for the user.
Describes the available functions and inputs that are needed
Other basic commands.

**Format:** `/help`

**Example Output**

```
    add -name {Role name} -company {Company name}: Adds internship to the list of internships currently being tracked by the user.


    update {ID} {-field} {updated information}: Adds information to the field of the entry tagged to the ID.
    Eg. update 02 -status application completed
```


## Exit Command

Terminates the program.

**Format:** `/exit`



## Invalid Inputs
Shows whenever an incomplete or invalid search call is performed. Output also shows what corrective action can be taken.

Example Output:
Please include a duration if you are using the -duration flag!
Eg. Update 02 -duration /from 04/25

Please include specific skills if you are using the -skills flag!

I do not understand what flag you are using, type /help to get a list of valid commands.






## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
