import java.util.HashMap;
import java.util.ArrayList;

public class Calendar {
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    public static HashMap<String, HashMap<Integer, ArrayList<String>>> createCalendar(int year) {
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

        HashMap<String, HashMap<Integer, ArrayList<String>>> calendar = new HashMap<>();

        for (String month : months.keySet()) {
            HashMap<Integer, ArrayList<String>> daysInMonth = new HashMap<>();
            for (int day = 1; day <= months.get(month); day++) {
                daysInMonth.put(day, new ArrayList<>());
            }
            calendar.put(month, daysInMonth);
        }

        return calendar;
    }

    public static void main(String[] args) {
        int year = 2024;
        HashMap<String, HashMap<Integer, ArrayList<String>>> calendar = createCalendar(year);

        // Print the calendar structure
        for (String month : calendar.keySet()) {
            System.out.println(month + ": " + calendar.get(month).keySet());
        }
    }
}