package seedu.duke.parser.parserUtils;

public class Find implements StringExtraction{
    @Override
    public String extract(String input) {
        String[] find = input.split("find");
        return find[1].trim();
    }
}
