package command;
import programme.ProgrammeList;
import history.History;

public abstract class Command {
    public Command(){}

    public abstract CommandResult execute(ProgrammeList pList, History history);

    @Override
    public boolean equals(Object other) {
        boolean isSameObject = (this == other);
        boolean isSameClass = (getClass() != other.getClass());
        return (isSameObject || isSameClass);
    }
}
