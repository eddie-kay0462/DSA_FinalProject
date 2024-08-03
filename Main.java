// main class to create an event planner for the user using the Event, EventSorter, and Calendar classes.

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Event Planner!");

        System.out.print("Enter the year for the calendar: ");
        int year = input.nextInt();

        HashMap<String, HashMap<Integer, ArrayList<Event>>> calendar = Calendar.createCalendar(year);

        System.out.println("Calendar for " + year + ":");

        // Print the calendar structure
        for (String month : calendar.keySet()) {
            System.out.println(month + ": " + calendar.get(month).keySet());
        }

        // interactive menu
        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add an event");
            System.out.println("2. View events");
            System.out.println("3. Sort events");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
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

                    //map the month to a number
                    switch (month) {
                        case "January":
                            month = "01";
                            break;
                        case "February":
                            month = "02";
                            break;
                        case "March":
                            month = "03";
                            break;
                        case "April":
                            month = "04";
                            break;
                        case "May":
                            month = "05";
                            break;
                        case "June":
                            month = "06";
                            break;
                        case "July":
                            month = "07";
                            break;
                        case "August":
                            month = "08";
                            break;
                        case "September":
                            month = "09";
                            break;
                        case "October":
                            month = "10";
                            break;
                        case "November":
                            month = "11";
                            break;
                        case "December":
                            month = "12";
                            break;
                        default:
                            System.out.println("Invalid month. Please try again.");
                            continue;
                    }

                    // put the day, month, year in the format yyyy-MM-dd
                    String dateString = year + "-" + month + "-" + day;

                    Event event = new Event(title, dateString, time, location, description);
                    calendar.get(month).get(day).add(event);
                    System.out.println("Event added successfully!");
                    break;
                case 2:
                    System.out.print("Enter the month: ");
                    month = input.nextLine();
                    System.out.print("Enter the day: ");
                    day = input.nextInt();
                    input.nextLine(); // Consume newline

                    System.out.println("Events on " + month + " " + day + ":");
                    for (Event e : calendar.get(month).get(day)) {
                        System.out.println(e.title + " " + e.date + " " + e.priority);
                    }
                    break;
                case 3:
                    System.out.print("Enter the attribute to sort by (date, title, priority): ");
                    String attribute = input.nextLine();
                    System.out.print("Sort in reverse order (true/false): ");
                    boolean reverse = input.nextBoolean();
                    input.nextLine(); // Consume newline

                    Calendar.sortEvents(calendar, attribute, reverse);
                    System.out.println("Events sorted successfully!");
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
      System.out.println("Thank you for using the Event Planner!");
      input.close();
    }
}