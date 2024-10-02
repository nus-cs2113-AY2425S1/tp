package seedu.duke;

public class AddCommand implements Command {
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
                        role = args[i++];
                    }
                    break;
                case "-company":
                    if (i + 1 < args.length) {
                        company = args[i++];
                    }
                    break;
                case "-from":
                    if (i + 1 < args.length) {
                        startDate = args[i++];
                    }
                    break;
                case "-to":
                    if (i + 1 < args.length) {
                        endDate = args[i++];
                    }
                    break;
                default:
                    System.out.println("Unknown flag: " + args[i]);

            }
        }

        Internship newInternship = new Internship(role, company, startDate, endDate);
    }
    @Override
    public String getUsage() {
        return "Usage: add -name {Role name} -company {Company name} -from {start date} -to {end date}";
    }
}
