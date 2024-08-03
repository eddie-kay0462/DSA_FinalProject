import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AutomateMain {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt for the input file
        System.out.print("Enter the input file name (e.g., events.txt): ");
        String fileName = input.nextLine();

        // Load events from file
        HashMap<String, HashMap<Integer, ArrayList<Event>>> calendar = loadEventsFromFile(fileName);

        if (calendar == null) {
            System.out.println("Failed to load events from file.");
            return;
        }

        // Interactive menu
        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Search for an event");
            System.out.println("2. Sort events");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    long startTimeSearch = System.currentTimeMillis();

                    // Search options
                    System.out.println("Search by:");
                    System.out.println("1. Title");
                    System.out.println("2. Date");
                    System.out.println("3. Location");
                    System.out.print("Enter your choice: ");
                    int searchChoice = input.nextInt();
                    input.nextLine(); // Consume newline

                    Event searchedEvent = null;
                    switch (searchChoice) {
                        case 1:
                            System.out.print("Enter the title to search for: ");
                            String title = input.nextLine();
                            searchedEvent = Calendar.searchEvent(calendar, title);
                            break;
                        case 2:
                            System.out.print("Enter the date to search for (yyyy-MM-dd): ");
                            String dateString = input.nextLine();
                            LocalDate date = LocalDate.parse(dateString);
                            searchedEvent = Calendar.searchEvent(calendar, date);
                            break;
                        case 3:
                            System.out.print("Enter the location to search for: ");
                            String location = input.nextLine();
                            searchedEvent = Calendar.searchEventLocation(calendar, location);
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            continue;
                    }

                    long endTimeSearch = System.currentTimeMillis();
                    System.out.println("Time taken to search: " + (endTimeSearch - startTimeSearch) + " ms");

                    if (searchedEvent != null) {
                        System.out.println("Event found: " + searchedEvent.title + " " + searchedEvent.date + " " + searchedEvent.priority);
                    } else {
                        System.out.println("Event not found.");
                    }
                    break;

                case 2:
                    long startTimeSort = System.currentTimeMillis();

                    // Sorting options
                    System.out.print("Enter the attribute to sort by (date, title, priority): ");
                    String attribute = input.nextLine();
                    System.out.print("Sort in reverse order (true/false): ");
                    boolean reverse = input.nextBoolean();
                    input.nextLine(); // Consume newline

                    Calendar.sortEvents(calendar, attribute, reverse);

                    long endTimeSort = System.currentTimeMillis();
                    System.out.println("Time taken to sort: " + (endTimeSort - startTimeSort) + " ms");
                    System.out.println("Events sorted successfully!");
                    break;

                case 3:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Thank you for using the Event Planner!");
        input.close();
    }

    private static HashMap<String, HashMap<Integer, ArrayList<Event>>> loadEventsFromFile(String fileName) {
        HashMap<String, HashMap<Integer, ArrayList<Event>>> calendar = new HashMap<>();

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                //print the parts 
                for (String part : parts) {
                    System.out.println(part);
                }

                if (parts.length != 5) {
                    System.out.println("Invalid event format in file.");
                    continue;
                }

                String title = parts[0];
                String date = parts[1];
                String time = parts[2];
                String location = parts[3];
                String description = parts[4];

                LocalDate eventDate = LocalDate.parse(date);
                String month = String.format("%02d", eventDate.getMonthValue());
                int day = eventDate.getDayOfMonth();

                Event event = new Event(title, date, time, location, description);

                calendar.putIfAbsent(month, new HashMap<>());
                calendar.get(month).putIfAbsent(day, new ArrayList<>());
                calendar.get(month).get(day).add(event);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return null;
        }

        return calendar;
    }
}