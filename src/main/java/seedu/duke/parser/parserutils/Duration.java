package seedu.duke.parser.parserutils;

public class Duration implements StringExtraction {
    @Override
    public String extract(String input) {
        String[] duration;

        if(input.contains("/by ")){
            duration = input.split("/by");
        } else if(input.contains("/every ")){
            duration = input.split("/every");
        } else {
            duration = new String[]{"No repeats"};
            return duration[0].trim();
        }
        return duration[1].trim();
    }
}
