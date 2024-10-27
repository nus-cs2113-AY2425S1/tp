package seedu.duke.parser.parserutils;

public class Tag implements StringExtraction{
    @Override
    public String extract(String input) {
        String[] tag;

        if(input.contains("/tag ")){
            tag = input.split("/tag ");
        } else {
            tag = new String[]{"No tag"};
            return tag[0].trim();
        }
        return tag[1].trim();
    }
}
