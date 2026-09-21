import java.util.Scanner;

interface Exportable2 {
    String exportData();
}

class ReportGenerator2 implements Exportable2 {

    private String reportName;
    private static int totalExports = 0;

    ReportGenerator2(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String exportData() {
        totalExports++;
        return "Exported report: " + reportName;
    }

    public static int getTotalExports() {
        return totalExports;
    }

    public static void increaseExportCount() {
        totalExports++;
    }
}

class UserProfile2 implements Exportable2 {

    private String username;

    UserProfile2(String username) {
        this.username = username;
    }

    @Override
    public String exportData() {
        ReportGenerator2.increaseExportCount();
        return "Exported profile: " + username;
    }
}

public class Problem2 {

    static void exportAll(Exportable2[] items) {

        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i].exportData());
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter report name: ");
        String reportName = in.nextLine();

        System.out.print("Enter username: ");
        String username = in.nextLine();

        ReportGenerator2 report = new ReportGenerator2(reportName);
        UserProfile2 user = new UserProfile2(username);

        // Upcasting
        Exportable2 ref = report;

        Exportable2[] items = {ref, user};

        System.out.println("\nExporting data:");

        exportAll(items);

        System.out.println("\nTotal exports: "
                + ReportGenerator2.getTotalExports());

        in.close();
    }
}

