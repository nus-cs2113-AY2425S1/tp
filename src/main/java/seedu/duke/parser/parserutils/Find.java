package seedu.duke.parser.parserutils;

public class Find implements StringExtraction{
    @Override
    public String extract(String input) {
        String[] find = input.split("find");
        return find[1].trim();
    }
}
