package seedu.commands;

import seedu.duke.Internship;

public class AddCommand implements Command {
    private final InternshipList internships;

    public AddCommand(InternshipList internshipList) {
        this.internships = internshipList;
    }
    @Override
    public void execute(String[] args) {
        String role = "";
        String company = "";
        String startDate = "";
        String endDate = "";

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
                    startDate = args[++i];
                } else {
                    System.out.println("Start date not specified.");
                }
                break;
            case "-to":
                if (i + 1 < args.length) {
                    endDate = args[++i];
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
        internships.addInternship(newInternship);
        System.out.println("Internship added: " + newInternship);
    }

    @Override
    public String getUsage() {
        return "Usage: add -name {Role name} -company {Company name} -from {start date} -to {end date}";
    }
}
