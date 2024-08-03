import java.util.HashMap;
import java.util.ArrayList;

class Year_Calendar {
    private HashMap<Integer, HashMap<String, HashMap<Integer, ArrayList<String>>>> yearCalendars;

    public Year_Calendar() {
        yearCalendars = new HashMap<>();
    }

    public void addYear(int year) {
        if (!yearCalendars.containsKey(year)) {
            yearCalendars.put(year, Calendar.createCalendar(year));
        }
    }

    public HashMap<String, HashMap<Integer, ArrayList<String>>> getCalendar(int year) {
        if (!yearCalendars.containsKey(year)) {
            addYear(year);
        }
        return yearCalendars.get(year);
    }

    public void printCalendar(int year) {
        HashMap<String, HashMap<Integer, ArrayList<String>>> calendar = getCalendar(year);
        System.out.println("Calendar for year " + year + ":");
        for (String month : calendar.keySet()) {
            System.out.println(month + ": " + calendar.get(month).keySet());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Year_Calendar yearCalendar = new Year_Calendar();

        // Add and print calendars for multiple years
        int[] years = {2024, 2025, 2026, 2027, 2028};
        for (int year : years) {
            yearCalendar.addYear(year);
            yearCalendar.printCalendar(year);
        }
    }
}
