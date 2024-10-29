package command.programme.edit;

import command.CommandResult;
import programme.Day;
import programme.ProgrammeList;

import java.util.logging.Level;

/**
 * Command to create a new day in a programme.
 * This command adds a new Day object to an existing programme identified by the programme ID.
 */
public class CreateDayCommand extends EditCommand {
    public static final String SUCCESS_MESSAGE_FORMAT = "Created: %s%n";
    private final Day createdDay;

    /**
     * Constructs a new CreateDayCommand.
     *
     * @param programmeId The ID of the programme to add the day to
     * @param day The Day object to be added to the programme
     */
    public CreateDayCommand(int programmeId, Day day) {
        super(programmeId);
        assert day != null : "created day cannot be null";
        this.createdDay = day;
    }

    /**
     * Executes the command to insert the created day into the specified programme.
     * @param programmes the ProgrammeList that contains the programmes where the day will be added
     * @return a CommandResult containing a success message indicating the created day
     */
    @Override
    public CommandResult execute(ProgrammeList programmes) {
        assert programmes != null : "programmes cannot be null";
        programmes.insertDay(programmeIndex, createdDay);
        String resultMessage = String.format(SUCCESS_MESSAGE_FORMAT, createdDay);
        logger.log(Level.INFO, "CreateDayCommand executed successfully.");
        return new CommandResult(resultMessage);
    }
}
