import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Event Planner!");
        Year_Calendar yearCalendar = new Year_Calendar();

        // interactive menu
        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add an event");
            System.out.println("2. View events");
            System.out.println("3. Sort events");
            System.out.println("4. Search for an event");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the year: ");
                    int year = input.nextInt();
                    input.nextLine(); // Consume newline
                    System.out.print("Enter the month: ");
                    String month = input.nextLine();
                    System.out.print("Enter the day: ");
                    int day = input.nextInt();
                    input.nextLine(); // Consume newline

                    System.out.print("Enter the title: ");
                    String title = input.nextLine();
                    System.out.print("Enter the time: ");
                    String time = input.nextLine();
                    System.out.print("Enter the location: ");
                    String location = input.nextLine();
                    System.out.print("Enter the description: ");
                    String description = input.nextLine();

                    String monthNumber = getMonthNumber(month);
                    if (monthNumber == null) {
                        System.out.println("Invalid month. Please try again.");
                        continue;
                    }

                    // Check if the day map is properly initialized
                    if (!yearCalendar.getCalendar(year).containsKey(monthNumber)) {
                        yearCalendar.getCalendar(year).put(monthNumber, new HashMap<>());
                    }
                    if (!yearCalendar.getCalendar(year).get(monthNumber).containsKey(day)) {
                        yearCalendar.getCalendar(year).get(monthNumber).put(day, new ArrayList<>());
                    }

                    // Put the day, month, year in the format yyyy-MM-dd
                    String dateString = year + "-" + monthNumber + "-" + String.format("%02d", day);

                    Event event = new Event(title, dateString, time, location, description);
                    yearCalendar.getCalendar(year).get(monthNumber).get(day).add(event);
                    break;
                case 2:
                    System.out.print("Enter the year: ");
                    year = input.nextInt();
                    input.nextLine(); // Consume newline
                    System.out.print("Enter the month: ");
                    month = input.nextLine();
                    System.out.print("Enter the day: ");
                    day = input.nextInt();
                    input.nextLine(); // Consume newline

                    String viewMonthNumber = getMonthNumber(month);
                    if (viewMonthNumber == null) {
                        System.out.println("Invalid month. Please try again.");
                        continue;
                    }

                    HashMap<String, HashMap<Integer, ArrayList<Event>>> calendar = yearCalendar.getCalendar(year);
                    if (calendar.containsKey(viewMonthNumber) && calendar.get(viewMonthNumber).containsKey(day)) {
                        for (Event e : calendar.get(viewMonthNumber).get(day)) {
                            System.out.println(e.getTitle() + " " + e.getDate() + " " + e.getLocation() + " " + e.getDescription());
                        }
                    } else {
                        System.out.println("No events found for the specified date.");
                    }

                    System.out.println("Events displayed successfully!");
                    break;
                case 3:
                    System.out.print("Enter the year: ");
                    year = input.nextInt();
                    input.nextLine(); // Consume newline
                    System.out.print("Enter the attribute to sort by (date, title, location): ");
                    String attribute = input.nextLine();
                    System.out.print("Sort in reverse order (true/false): ");
                    boolean reverse = input.nextBoolean();
                    input.nextLine(); // Consume newline

                    calendar = yearCalendar.getCalendar(year);

                    Calendar.sortEvents(calendar, attribute, reverse);
                    System.out.println("Events sorted successfully!");
                    break;
                case 4:
                    System.out.print("Enter the year: ");
                    year = input.nextInt();
                    input.nextLine(); // Consume newline
                    System.out.println("Search by:");
                    System.out.println("1. Title");
                    System.out.println("2. Date");
                    System.out.println("3. Location");
                    System.out.print("Enter your choice: ");
                    int searchChoice = input.nextInt();
                    input.nextLine(); // Consume newline

                    Event foundEvent = null;
                    switch (searchChoice) {
                        case 1:
                            System.out.print("Enter the title: ");
                            String searchTitle = input.nextLine();
                            foundEvent = Calendar.searchEvent(yearCalendar.getCalendar(year), searchTitle);
                            break;
                        case 2:
                            System.out.print("Enter the date (yyyy-MM-dd): ");
                            String searchDate = input.nextLine();
                            foundEvent = Calendar.searchEvent(yearCalendar.getCalendar(year), LocalDate.parse(searchDate));
                            break;
                        case 3:
                            System.out.print("Enter the location: ");
                            String searchLocation = input.nextLine();
                            foundEvent = Calendar.searchEventLocation(yearCalendar.getCalendar(year), searchLocation);
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            continue;
                    }

                    if (foundEvent != null) {
                        System.out.println("Event found: " + foundEvent.title + " on " + foundEvent.date);
                    } else {
                        System.out.println("Event not found.");
                    }
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        input.close();
    }

    private static String getMonthNumber(String month) {
        switch (month) {
            case "January":
                return "01";
            case "February":
                return "02";
            case "March":
                return "03";
            case "April":
                return "04";
            case "May":
                return "05";
            case "June":
                return "06";
            case "July":
                return "07";
            case "August":
                return "08";
            case "September":
                return "09";
            case "October":
                return "10";
            case "November":
                return "11";
            case "December":
                return "12";
            default:
                return null;
        }
    }
}
