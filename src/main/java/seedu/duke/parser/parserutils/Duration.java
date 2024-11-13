package seedu.duke.parser.parserutils;

public class Duration implements StringExtraction {
    @Override
    public String extract(String input) {
        String result = "";  //default if dont have /by or /every

        if (input.contains("/by ")) {
            //extract the part after /by, and split by /tag if present
            String[] parts = input.split("/by", 2);
            String[] dateAndTag = parts[1].split("/tag", 2);
            result = dateAndTag[0].trim();  //take only the date portion
        } else if (input.contains("/every ")) {
            //extract the part after /every, and split by /tag if present
            String[] parts = input.split("/every", 2);
            String[] intervalAndTag = parts[1].split("/tag", 2);
            result = intervalAndTag[0].trim();  //take only the interval portion
        }

        return result;
    }
}
