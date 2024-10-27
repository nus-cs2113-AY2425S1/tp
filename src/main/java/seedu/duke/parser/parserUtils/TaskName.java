package seedu.duke.parser.parserUtils;

public class TaskName implements StringExtraction{
    @Override
    public String extract(String input) {
        String[] name;
        if(input.contains("/by ")){
            name = input.split("/by");
        } else if(input.contains("/every ")){
            name = input.split("/every");
        } else if(input.contains("/tag ")){
            name = input.split("/tag");
        } else {
            name = new String[]{input};
        }
        String[] result;
        if(input.contains("deadline ")){
            result = name[0].split("deadline ");
        } else if (input.contains("repeat ")){
            result = name[0].split("repeat ");
        } else if (input.contains("todo ")){
            result = name[0].split("todo ");
        } else {
            result = new String[]{input};
        }
        return result[1].trim();
    }
}
