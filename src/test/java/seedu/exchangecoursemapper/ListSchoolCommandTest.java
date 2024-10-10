package seedu.exchangecoursemapper;

import org.junit.jupiter.api.BeforeEach;
import seedu.exchangecoursemapper.command.ListSchoolCommand;

public class ListSchoolCommandTest {

    private ListSchoolCommand listSchoolCommand;
    private static final String INCORRECT_FILE_PATH = "C:\\Users\\Louis Joe\\IdeaProjects\\tp\\data\\wrongfile.json";

    @BeforeEach
    public void setUp() {
        listSchoolCommand = new ListSchoolCommand();
    }
}


