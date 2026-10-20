
class BusTicket {
    String passengerName;
    String destination;
    boolean checkedIn = false;

    public BusTicket(String passengerName, String destination) {
        if (passengerName == null || destination == null ||
                passengerName.trim().isEmpty() || destination.trim().isEmpty() ||
                !passengerName.matches("[a-zA-Z ]+") ||
                !destination.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Invalid booking");
        }

        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
    }

    public void markCheckedIn() {
        if (!checkedIn) {
            checkedIn = true;
            System.out.println("Checked in successfully");
        } else {
            System.out.println("Already checked in");
        }
    }

    public static void processBatch(String[][] rawBookings) {
        BusTicket[] accepted = new BusTicket[rawBookings.length];

        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        for (int i = 0; i < rawBookings.length; i++) {
            try {
                BusTicket ticket = new BusTicket(
                        rawBookings[i][0],
                        rawBookings[i][1]
                );

                boolean duplicate = false;

                for (int j = 0; j < valid; j++) {
                    if (accepted[j].passengerName.equalsIgnoreCase(ticket.passengerName) &&
                            accepted[j].destination.equalsIgnoreCase(ticket.destination)) {
                        duplicate = true;
                        break;
                    }
                }

                if (duplicate) {
                    duplicates++;
                } else {
                    accepted[valid] = ticket;
                    valid++;
                }

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid +
                " | Rejected: " + rejected +
                " | Duplicates skipped: " + duplicates);
    }
}

public class W4Practice {
    public static void main(String[] args) {
        String[][] bookings = {
                {"Divya", "Chennai"},
                {"", "Bangalore"},
                {"Ravi123", "Pune"},
                {"Divya", "Chennai"},
                {" ", " "}
        };

        BusTicket.processBatch(bookings);
    }
}
