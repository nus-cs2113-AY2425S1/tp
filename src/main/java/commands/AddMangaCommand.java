package commands;

import author.Author;
import author.AuthorList;
import exceptions.AuthorNameTooLongException;
import exceptions.MangaNameTooLongException;
import exceptions.NoAuthorProvidedException;
import exceptions.NoMangaProvidedException;
import exceptions.TantouException;
import manga.Manga;
import ui.Ui;

import static constants.Command.AUTHOR_INDEX;
import static constants.Command.CATALOG_COMMAND;
import static constants.Command.MANGA_INDEX;
import static constants.Options.MAX_AUTHOR_NAME_LENGTH;
import static constants.Options.MAX_MANGA_NAME_LENGTH;
import static storage.StorageHelper.saveFile;

//@@author averageandyyy
public class AddMangaCommand extends Command {
    private String authorName;
    private String mangaName;

    public AddMangaCommand(String[] argsAuthorManga) {
        super(CATALOG_COMMAND);
        this.authorName = argsAuthorManga[AUTHOR_INDEX];
        this.mangaName = argsAuthorManga[MANGA_INDEX];
    }

    @Override
    public void execute(Ui ui, AuthorList authorList) throws TantouException {
        // Empty user input should have been caught at the Parser level
        if (authorName == null || authorName.isEmpty()) {
            throw new NoAuthorProvidedException();
        }

        if (mangaName == null || mangaName.isEmpty()) {
            throw new NoMangaProvidedException();
        }

        //@@author xenthm
        if (authorName.length() > MAX_AUTHOR_NAME_LENGTH) {
            logger.warning("Author name " + authorName + " exceeds maximum length");
            throw new AuthorNameTooLongException();
        }

        if (mangaName.length() > MAX_MANGA_NAME_LENGTH) {
            logger.warning("Manga name " + mangaName + " exceeds maximum length");
            throw new MangaNameTooLongException();
        }

        //@@author
        Author incomingAuthor = new Author(authorName);
        Manga incomingManga = new Manga(mangaName, incomingAuthor);

        // If author exists add manga to the existing author
        if (authorList.hasAuthor(incomingAuthor)) {
            // Obtain the same Author object in authorList
            Author existingAuthor = authorList.getAuthor(incomingAuthor);
            if (!existingAuthor.hasManga(incomingManga)) {
                existingAuthor.addManga(incomingManga);
                System.out.printf("Manga %s added successfully to author %s\n",
                        incomingManga.getMangaName(), existingAuthor.getAuthorName());

                // Assert that the manga was successfully added
                assert authorList.getAuthor(incomingAuthor).hasManga(incomingManga) : "Failed to add manga";

                saveFile(authorList);
                return;
            }

            // Exception is thrown when adding manga that already exists with author
            assert authorList.getAuthor(incomingAuthor).hasManga(incomingManga) : "Manga does not actually exist!";
            logger.info("Manga already exists!");
            throw new TantouException("Manga already exists!");
        }

        // Otherwise create new Author and add Manga to it
        authorList.add(incomingAuthor);
        incomingAuthor.addManga(incomingManga);
        assert authorList.getAuthor(incomingAuthor).hasManga(incomingManga) : "Failed to add author and manga";
        System.out.printf("Manga %s added successfully to author %s\n", incomingManga.getMangaName(),
                incomingAuthor.getAuthorName());
        saveFile(authorList);
    }
}
