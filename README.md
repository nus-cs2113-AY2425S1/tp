# TutorLink

TutorLink is a command-line submission management assistant for students and teaching staff alike.

   ```
   -------------------------------------------------------------
   ___________      __               .____    .__        __
   \__    ___/_ ___/  |_  ___________|    |   |__| ____ |  | __
   |    | |  |  \   __\/  _ \_  __ \    |   |  |/    \|  |/ /
   |    | |  |  /|  | (  <_> )  | \/    |___|  |   |  \    <
   |____| |____/ |__|  \____/|__|  |_______ \__|___|  /__|_ \
   \/       \/     \/
   
   -------------------------------------------------------------
   Hello! I'm TutorLink
   What can I do for you?
   -------------------------------------------------------------
   ```
### Command Summary 
| **Command**        | **Description**                                              | **Example**                           |
|--------------------|--------------------------------------------------------------|---------------------------------------|
| `help`             | Displays list of commands                                    | `help`                                |
| `add_student`      | Adds a student to the class roster                           | `add_student i/A1234567X n/John Doe`  |
| `delete_student`   | Deletes a student from the class roster                      | `delete_student i/A1234567X`          |
| `list_student`     | Lists all students in the class                              | `list_student`                        |
| `find_student`     | Finds a student in the class roster by name or matric number | `find_student i/A1234567X n/John Doe` |
| `add_component`    | Adds a new grading component to the class                    | `add_component c/Quiz 1 w/30 m/50`    |
| `delete_component` | Deletes a grading component from the class                   | `delete_component c/Quiz 1`           |
| `update_component` | Updates a component with a new maxscore or weight            | `update_component c/Quiz 1 w/40 m/60` |
| `list_component`   | Lists all grading components                                 | `list_component`                      |
| `add_grade`        | Adds a grade for a student for a specific component          | `add_grade i/A1234567X c/Quiz 1 s/45` |
| `delete_grade`     | Deletes a student's grade for a specific component           | `delete_grade i/A1234567X c/Quiz 1`   |
| `list_grade`       | Lists all grades for a student                               | `list_grade i/A1234567X`              |
| `bye`              | Exits the program                                            | `bye`                                 |

### Useful links:
* [User Guide](/docs/UserGuide.md)
* [Developer Guide](/docs/DeveloperGuide.md)
* [About Us](/docs/AboutUs.md)


