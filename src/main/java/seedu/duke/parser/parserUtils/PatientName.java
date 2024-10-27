package seedu.duke.parser.parserUtils;

public class PatientName implements StringExtraction{
    @Override
    public String extract(String input) {
        String[] name;
        if(input.contains("/tag ")){
            name = input.split(" /tag ");
        } else {
            name = new String[]{input};
        }
        String[] result;
        result = name[0].split("add ");
        return result[1].trim();
    }
}
