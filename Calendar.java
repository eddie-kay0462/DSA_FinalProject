import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;

public class Calendar {
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    public static HashMap<String, HashMap<Integer, ArrayList<Event>>> createCalendar(int year) {
        HashMap<String, Integer> months = new HashMap<>();
        months.put("January", 31);
        months.put("February", isLeapYear(year) ? 29 : 28);
        months.put("March", 31);
        months.put("April", 30);
        months.put("May", 31);
        months.put("June", 30);
        months.put("July", 31);
        months.put("August", 31);
        months.put("September", 30);
        months.put("October", 31);
        months.put("November", 30);
        months.put("December", 31);

        HashMap<String, HashMap<Integer, ArrayList<Event>>> calendar = new HashMap<>();

        for (String month : months.keySet()) {
            HashMap<Integer, ArrayList<Event>> daysInMonth = new HashMap<>();
            for (int day = 1; day <= months.get(month); day++) {
                daysInMonth.put(day, new ArrayList<>());
            }
            calendar.put(month, daysInMonth);
        }

        return calendar;
    }

    //sort the events  by title, date, or priority using the EventsSorter class
    public static void sortEvents(HashMap<String, HashMap<Integer, ArrayList<Event>>> calendar, String attribute, boolean reverse) {
        for (String month : calendar.keySet()) {
            for (int day : calendar.get(month).keySet()) {
                EventSorter.mergeSort(calendar.get(month).get(day), attribute, reverse);
            }
        }
    }

    // search for an event in the calendar using SearchingEvent class by title
    public static Event searchEvent(HashMap<String, HashMap<Integer, ArrayList<Event>>> calendar, String title) {
        for (String month : calendar.keySet()) {
            for (int day : calendar.get(month).keySet()) {
                SearchingEvent.searchEventByTitle(calendar.get(month).get(day), title);
            }
        }
        return null;
    }

    // search for an event in the calendar using SearchingEvent class by date
    public static Event searchEvent(HashMap<String, HashMap<Integer, ArrayList<Event>>> calendar, LocalDate date) {
        for (String month : calendar.keySet()) {
            for (int day : calendar.get(month).keySet()) {
                SearchingEvent.searchEventByDate(calendar.get(month).get(day), date);
            }
        }
        return null;
    }

    // search for an event in the calendar using SearchingEvent class by location
    public static Event searchEventLocation(HashMap<String, HashMap<Integer, ArrayList<Event>>> calendar, String location) {
        for (String month : calendar.keySet()) {
            for (int day : calendar.get(month).keySet()) {
                SearchingEvent.searchEventByLocation(calendar.get(month).get(day), location);
            }
        }
        return null;
    }
}