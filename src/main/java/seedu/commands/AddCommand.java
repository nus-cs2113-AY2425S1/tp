package seedu.commands;

import seedu.duke.Internship;

import java.util.ArrayList;

public class AddCommand extends Command {
    @Override
    public void execute(ArrayList<String> args) {
        String role = "";
        String company = "";
        String startDate = "01/01";
        String endDate = "01/01";

        for (String arg : args) {
            String[] words = arg.split(" ", 2);
            String flag = words[0];
            switch (flag) {
            case "name":
                if (words.length > 1) {
                    role = words[INDEX_DATA];
                } else {
                    System.out.println("Role not specified.");
                }
                break;
            case "company":
                if (words.length > 1) {
                    company = words[INDEX_DATA];
                } else {
                    System.out.println("Company not specified.");
                }
                break;
            case "from":
                if (words.length > 1) {
                    startDate = words[INDEX_DATA];
                } else {
                    System.out.println("Start date not specified.");
                }
                break;
            case "to":
                if (words.length > 1) {
                    endDate = words[INDEX_DATA];
                } else {
                    System.out.println("End date not specified.");
                }
                break;
            default:
                System.out.println("Unknown flag: " + flag);
                break;
            }
        }

        Internship newInternship = new Internship(role, company, startDate, endDate);
        internships.addInternship(newInternship);
        System.out.println("Internship added: " + newInternship);
    }

    @Override
    public String getUsage() {
        return "Usage: add -name {Role name} -company {Company name} -from {start date} -to {end date}";
    }
}
