# User Guide

## Outline
- [Product Description](#product-description)
- [Quick Start](#quick-start)
- [Features](#features)
  - [Add Command: `add`](#add-command-add)
  - [Update Command: `update`](#update-command-update)
  - [Remove Command: `remove`](#remove-command-remove)
  - [Sort Command: `sort`](#sort-command-sort)
  - [Filter Command: `filter`](#filter-command-filter)
  - [Delete Command: `delete`](#delete-command-delete)
  - [Help Command: `help`](#help-command-help)
  - [Exit Command: `exit`](#exit-command-exit)
  - [Invalid Inputs](#invalid-inputs)
- [FAQ](#faq)
- [Command Summary](#command-summary)

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
`add -role Software Engineer Intern -company Google`

**Example Output 1:**
```
__________________________________________________
__________________________________________________
Internship added:
ID: 1	Status: Application Pending
Role: Software Engineer Intern
Company: Google
Duration: 01/01 to 01/01
Skills: No Skills Entered 
Deadlines:
	No deadlines set.
__________________________________________________
__________________________________________________  
```

**Example Input 2:**
`add -role Embedded Software Engineer Intern -company Continental -from 05/25 -to 08/25`

**Example Output 2:**
```
__________________________________________________
__________________________________________________
Internship added:
ID: 2	Status: Application Pending
Role: Embedded Software Engineer Intern
Company: Continental
Duration: 05/25 to 08/25
Skills: No Skills Entered 
Deadlines:
	No deadlines set.
__________________________________________________
__________________________________________________
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
__________________________________________________
__________________________________________________
status updated: application completed
__________________________________________________
Internship updated:
ID: 2	Status: Application Completed
Role: Embedded Software Engineer Intern
Company: Continental
Duration: 05/25 to 08/25
Skills: No Skills Entered 
Deadlines:
	No deadlines set.
__________________________________________________
__________________________________________________
```

`update 02 -company Venti -deadline Interview Round 1 03/02/25`

```
__________________________________________________
__________________________________________________
company updated: Venti
deadline updated: Interview Round 1 03/02/25
__________________________________________________
Internship updated:
ID: 2	Status: Application Completed
Role: Embedded Software Engineer Intern
Company: Venti
Duration: 05/25 to 08/25
Skills: No Skills Entered 
Deadlines:
	Interview Round 1: 03/02/25
__________________________________________________
__________________________________________________
```

`update 02 -from 04/25 -skills Python`

```
__________________________________________________
__________________________________________________
from updated: 04/25
skills updated: Python
__________________________________________________
Internship updated:
ID: 2	Status: Application Completed
Role: Embedded Software Engineer Intern
Company: Venti
Duration: 04/25 to 08/25
Skills: Python 
Deadlines:
	Interview Round 1: 03/02/25
__________________________________________________
__________________________________________________
```

## Remove Command: `remove`

Removes specific values from fields of an Internship entry.

Valid Fields:
- `skills`
- `deadline`

**Format:**
`remove {ID} -{field} {value}`

**Examples:**

`remove 02 -skills Python`

```
__________________________________________________
__________________________________________________
skills removed: Python
__________________________________________________
Internship updated:
ID: 2	Status: Application Completed
Role: Embedded Software Engineer Intern
Company: Venti
Duration: 04/25 to 08/25
Skills: No Skills Entered 
Deadlines:
	Interview Round 1: 03/02/25
__________________________________________________
__________________________________________________
```

`remove 02 -deadline Interview Round 1 -deadline Interview Round 2`

```
__________________________________________________
__________________________________________________
deadline: Interview Round 2 is not found
__________________________________________________
deadline removed: Interview Round 1
__________________________________________________
Internship updated:
ID: 2	Status: Application Completed
Role: Embedded Software Engineer Intern
Company: Venti
Duration: 04/25 to 08/25
Skills: No Skills Entered 
Deadlines:
	No deadlines set.
__________________________________________________
__________________________________________________
```


## Sort Command: `sort`

Lists out all the Internships sorted by a specified field. The default list in increasing order of their IDs.

Valid Fields:
- `role`
- `duration`
- `skills`
- `status`
- `deadline`

The fields are not case-sensitive.

**Format:** `sort -{field}`

**Example:**

`sort -duration`

```
__________________________________________________
__________________________________________________
Sorted internships by start date (year first), then end date.
__________________________________________________
__________________________________________________


__________________________________________________
__________________________________________________
ID: 1	Status: Application Pending
Role: Software Engineer Intern
Company: Google
Duration: 05/24 to 08/24
Skills: No Skills Entered 
Deadlines:
	interview: 25/11/24
__________________________________________________
ID: 2	Status: Application Completed
Role: Embedded Software Engineer Intern
Company: Venti
Duration: 04/25 to 08/25
Skills: No Skills Entered 
Deadlines:
	online assessment: 11/11/24
__________________________________________________
__________________________________________________
```

`sort -deadline`

```
__________________________________________________
__________________________________________________
Sorted internships by deadline.
__________________________________________________
__________________________________________________


__________________________________________________
__________________________________________________
ID: 2	Status: Application Completed
Role: Embedded Software Engineer Intern
Company: Venti
Duration: 04/25 to 08/25
Skills: No Skills Entered 
Deadlines:
	online assessment: 11/11/24
__________________________________________________
ID: 1	Status: Application Pending
Role: Software Engineer Intern
Company: Google
Duration: 05/24 to 08/24
Skills: No Skills Entered 
Deadlines:
	interview: 25/11/24
__________________________________________________
__________________________________________________
```


## Filter command: `filter`

This feature is an extension of the previous list feature. It allows the user to specify certain values for any of the role name, company name or duration parameters based on which only the relevant internships are shown. 

The role and company name parameters are case-insensitive, For the duration parameter, this feature displays all relevant internships whose start dates and end dates are within the duration timeline specified by the user.  For the MVP version of the product, user can only sort by 1 field at a time.

**Format:** `filter -{field} {value}`

Valid fields:
- `role`: Provide the role name
- `company`: Provide the company name
- `from`: Provide the start date
- `to`: Provide the end date
- `skills`: Provide one skill listed in `skills`
- `deadline`: Provide the description of one of the deadlines

**Examples:**

`list`

```
__________________________________________________
__________________________________________________
ID: 1	Status: Application Pending
Role: engineer
Company: ABS
Duration: 01/01 to 01/01
Skills: No Skills Entered 
Deadlines:
	No deadlines set.
__________________________________________________
ID: 2	Status: Application Pending
Role: accountant
Company: XYZ
Duration: 01/01 to 01/01
Skills: No Skills Entered 
Deadlines:
	No deadlines set.
__________________________________________________
__________________________________________________
```

`filter -company ABS`

```
__________________________________________________
__________________________________________________
ID: 1	Status: Application Pending
Role: engineer
Company: ABS
Duration: 01/01 to 01/01
Skills: No Skills Entered 
Deadlines:
	No deadlines set.
__________________________________________________
__________________________________________________
```


## Delete Command: `delete`

This feature removes an entire listing from the tracker.

**Format:** `del {ID}`

**Example:**

`list`

```
__________________________________________________
__________________________________________________
ID: 1	Status: Application Pending
Role: engineer
Company: ABS
Duration: 01/01 to 01/01
Skills: No Skills Entered 
Deadlines:
	No deadlines set.
__________________________________________________
ID: 2	Status: Application Pending
Role: accountant
Company: XYZ
Duration: 01/01 to 01/01
Skills: No Skills Entered 
Deadlines:
	No deadlines set.
__________________________________________________
__________________________________________________
```

`delete 01`

```
__________________________________________________
__________________________________________________
Internship deleted: 1
__________________________________________________
__________________________________________________
```

## Help Command: `help`

Shows the commands available for the user and the inputs required.

**Format:** `help`


## Exit Command: `exit`

Terminates the program.

**Format:** `exit`



## Invalid Inputs
Shows whenever an incomplete or invalid search call is performed. Output also shows what corrective action can be taken.

Examples:

`add -company`

```
__________________________________________________
__________________________________________________
Company not specified.
Role not specified.
__________________________________________________
__________________________________________________
```



## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Locate the `/data/` directory, in the same directory as your `.jar` file. Copy this `/data/` directory to the second computer and store it in the same directory as the `.jar` file.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
