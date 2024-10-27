package seedu.duke.parser.parserutils;

public class Index implements StringExtraction{
    @Override
    public String extract(String input) {
        String[] find = input.split(" ");
        return find[1].trim();
    }
}
