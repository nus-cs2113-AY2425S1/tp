package seedu.duke.parser.parserUtils;

public class Index implements StringExtraction{
    @Override
    public String extract(String input) {
        String[] find = input.split(" ");
        return find[1].trim();
    }
}
