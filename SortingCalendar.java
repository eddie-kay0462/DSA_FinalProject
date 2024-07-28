import java.time.LocalDate;

public class SortingCalendar {

    public static void main(String[] args) {
        LinkedList events = new LinkedList();
        events.push(new Event(1, "Meeting", LocalDate.of(2024, 7, 27), "10:00", "Room 101", "Project meeting"));
        events.push(new Event(2, "Workshop", LocalDate.of(2024, 7, 26), "14:00", "Lab", "AI workshop"));
        events.push(new Event(3, "Conference", LocalDate.of(2024, 7, 28), "09:00", "Main Hall", "Tech conference"));

        // Sort by priority
        System.out.println("Sorted by priority:");
        events.sort("priority", false);
        events.printList();
    }
}



class Event {
    int id;
    String title;
    LocalDate date;
    String time;
    String location;
    String description;

    public Event(int id, String title, LocalDate date, String time, String location, String description) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
        this.location = location;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

class Node {
    Event data;
    Node next;

    public Node(Event data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    public void push(Event data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    private Node sortedMerge(Node a, Node b, String attribute, boolean reverse) {
        if (a == null) return b;
        if (b == null) return a;

        Node result;
        int comparison;
        if ("priority".equals(attribute)) {
            comparison = compareByPriority(a.data, b.data);
        } else {
            comparison = compareByAttribute(a.data, b.data, attribute);
        }

        if (reverse) {
            comparison = -comparison;
        }

        if (comparison <= 0) {
            result = a;
            result.next = sortedMerge(a.next, b, attribute, reverse);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next, attribute, reverse);
        }
        return result;
    }

    private int compareByAttribute(Event a, Event b, String attribute) {
        switch (attribute) {
            case "date":
                return a.date.compareTo(b.date);
            case "title":
                return a.title.compareTo(b.title);
            // Add other cases as necessary
            default:
                return a.date.compareTo(b.date); // Default to date comparison
        }
    }

    private int compareByPriority(Event a, Event b) {
        LocalDate now = LocalDate.now();
        int dateComparison = a.date.compareTo(b.date);
        if (dateComparison != 0) {
            return dateComparison;
        } else {
            return a.title.compareTo(b.title);
        }
    }

    private Node getMiddle(Node head) {
        if (head == null) return head;

        Node slow = head;
        Node fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private Node mergeSort(Node h, String attribute, boolean reverse) {
        if (h == null || h.next == null) return h;

        Node middle = getMiddle(h);
        Node nextToMiddle = middle.next;
        middle.next = null;

        Node left = mergeSort(h, attribute, reverse);
        Node right = mergeSort(nextToMiddle, attribute, reverse);

        return sortedMerge(left, right, attribute, reverse);
    }

    public void sort(String attribute, boolean reverse) {
        head = mergeSort(head, attribute, reverse);
    }
}
