### README Documentation

# Event Class

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
