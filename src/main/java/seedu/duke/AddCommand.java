package seedu.duke;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class AddCommand implements Command {
    @Override
    public void execute(String[] args) {
        String role = "";
        String company = "";
        YearMonth startDate = YearMonth.parse("01/00");
        YearMonth endDate = YearMonth.parse("01/00");

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
            case "-name":
                if (i + 1 < args.length) {
                    role = args[++i];
                } else {
                    System.out.println("Role not specified.");
                }
                break;
            case "-company":
                if (i + 1 < args.length) {
                    company = args[++i];
                } else {
                    System.out.println("Company not specified.");
                }
                break;
            case "-from":
                if (i + 1 < args.length) {
                    startDate = YearMonth.parse(args[++i]);
                } else {
                    System.out.println("Start date not specified.");
                }
                break;
            case "-to":
                if (i + 1 < args.length) {
                    endDate = YearMonth.parse(args[++i]);
                } else {
                    System.out.println("End date not specified.");
                }
                break;
            default:
                System.out.println("Unknown flag: " + args[i]);
                break;
            }
        }

        Internship newInternship = new Internship(role, company, startDate, endDate);
        System.out.println("Internship added: " + newInternship);
    }

    @Override
    public String getUsage() {
        return "Usage: add -name {Role name} -company {Company name} -from {start date} -to {end date}";
    }
}
