package seedu.duke.flashutils.utils;

import seedu.duke.flashutils.types.FlashBook;

public class StubStorage extends Storage {

    private boolean isWriteFlashBookToFileCalled = false;

    public StubStorage() {
        super("./data");
    }

    @Override
    public void writeFlashBookToFile(FlashBook flashBook) {
        super.writeFlashBookToFile(flashBook);
        isWriteFlashBookToFileCalled = true;
    }

    public boolean isWriteFlashBookToFileCalled() {
        return isWriteFlashBookToFileCalled;
    }
}
