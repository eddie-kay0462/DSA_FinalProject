### README Documentation

# 1. Event Class

## Overview

The `Event` class is used to manage events with details like title, date, time, location, and description. It automatically assigns a unique ID to each event and supports different date formats.

## Features

- **Unique ID:** Each event gets a unique ID for easy identification.
- **Flexible Date Parsing:** Can handle various date formats, including:
  - `yyyy-MM-dd` (ISO format)
  - `yyyy/MM/dd` (Slash separator)
  - `MM/dd/yyyy` (US format)
  - `dd-MM-yyyy` (Day-Month-Year format)
  - `dd/MM/yyyy` (Alternative Day-Month-Year format)
- **Constructors:**
  - **Primary Constructor:** Accepts `priority`, `title`, `dateString`, `time`, `location`, and `description`. It parses the date string and handles errors for invalid formats.
  - **Secondary Constructor:** Accepts `priority`, `title`, `date` (as `LocalDate`), `time`, `location`, and `description`, making it easy to use a `LocalDate` directly.

## Usage

Create an event by using one of the constructors with the required details. The class will handle the unique ID assignment and date parsing for you.

### Example

```java
Event event1 = new Event(1, "Meeting", "2024-08-03", "10:00 AM", "Conference Room", "Monthly team meeting.");
Event event2 = new Event(2, "Workshop", LocalDate.of(2024, 8, 5), "2:00 PM", "Main Hall", "Workshop on new software.");
```

## Error Handling

If you provide an invalid date format, an `IllegalArgumentException` will be thrown. Make sure your date string matches one of the supported formats.

For more information or help, check the class documentation or contact support.


# 2. SortingCalendar Class

## Overview

The `SortingCalendar` class provides a command-line interface for managing events using a linked list. It supports creating, modifying, deleting, viewing, searching, sorting, and summarizing events. Each event is uniquely identified by an ID, and the class allows for flexible manipulation and management of these events.

## Features

- **Create Event:** Add a new event to the linked list with details such as title, date, time, location, and description.
- **Modify Event:** Update existing events based on their ID. You can change attributes like title, date, time, location, and description.
- **Delete Event:** Remove an event from the list by its ID.
- **View Events:** Display all events currently in the list. Filtering options can be implemented to view events based on specific criteria.
- **Search Event:** Find events based on attributes like title, date, or location.
- **Sort Events:** Organize events based on a specified attribute in either ascending or descending order.
- **Generate Summary:** Create a summary of events within a specified date range. (Summary generation logic needs to be implemented.)

## Usage

Run the `SortingCalendar` class and use the following commands to interact with the event list:

- **create_event** `title date time location description`  
  Example: `create_event Meeting 2024-08-03 10:00 AM Conference Room Monthly team meeting.`
  
- **modify_event** `id attribute newValue`  
  Example: `modify_event 1 title New Meeting Title`
  
- **delete_event** `id`  
  Example: `delete_event 1`
  
- **view_events** `filter`  
  Example: `view_events`

- **search_event** `attribute value`  
  Example: `search_event title Meeting`

- **sort_events** `attribute` or `-attribute`  
  Example: `sort_events date` for ascending order, `sort_events -date` for descending order

- **generate_summary** `dateRange`  
  Example: `generate_summary 2024-08-01 to 2024-08-31`

## Error Handling

- For invalid commands or malformed input, appropriate error messages will be displayed.
- If an attempt is made to modify or delete an event that does not exist, a message indicating the event was not found will be shown.

## Example

```java
Event event1 = new Event(1, "Meeting", LocalDate.of(2024, 8, 3), "10:00 AM", "Conference Room", "Monthly team meeting.");
Event event2 = new Event(2, "Workshop", LocalDate.of(2024, 8, 5), "2:00 PM", "Main Hall", "Workshop on new software.");
```

## Requirements

- Java Development Kit (JDK) 8 or higher
- LinkedList class implementation (should be included in your project)

For more details, refer to the class documentation or contact support.

# 3. Calendar Class

## Overview

The `Calendar` class provides tools for creating and managing a calendar structure for a specified year. It supports the creation of a calendar with months and days, handling leap years, and sorting events by various attributes. The calendar is implemented using nested `HashMap` structures to map months and days to lists of events.

## Features

- **Leap Year Calculation:** Determines if a given year is a leap year, adjusting the number of days in February accordingly.
- **Create Calendar:** Constructs a calendar for a specified year with months and days. Each day has an associated list to store `Event` objects.
- **Sort Events:** Allows sorting of events within the calendar by attributes such as title, date, or priority. Sorting is performed using the `EventSorter` class and supports both ascending and descending order.
- **Print Calendar Structure:** Provides a way to print the structure of the calendar, displaying months and their days.

## Methods

- **`isLeapYear(int year)`**  
  Checks if the given year is a leap year. Returns `true` if it is, otherwise `false`.

- **`createCalendar(int year)`**  
  Creates a `HashMap` representing the calendar for the specified year. Each month maps to another `HashMap` of days, with each day initially holding an empty list of `Event` objects.

- **`sortEvents(HashMap<String, HashMap<Integer, ArrayList<Event>>> calendar, String attribute, boolean reverse)`**  
  Sorts events in the calendar based on the specified attribute (title, date, or priority) and order (ascending or descending). Uses the `EventSorter` class for sorting.

- **`main(String[] args)`**  
  Demonstrates the creation of a calendar for the year 2024 and prints the calendar structure, showing months and their respective days.

## Usage

1. **Creating a Calendar:**
   ```java
   int year = 2024;
   HashMap<String, HashMap<Integer, ArrayList<Event>>> calendar = Calendar.createCalendar(year);
   ```

2. **Sorting Events:**
   ```java
   Calendar.sortEvents(calendar, "title", false); // Sort events by title in ascending order
   ```

3. **Printing Calendar Structure:**
   ```java
   for (String month : calendar.keySet()) {
       System.out.println(month + ": " + calendar.get(month).keySet());
   }
   ```

## Requirements

- Java Development Kit (JDK) 8 or higher
- `Event` class and `EventSorter` class implementations

For more information, consult the class documentation or reach out for support.
