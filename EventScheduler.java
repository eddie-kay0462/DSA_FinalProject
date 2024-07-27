import java.time.LocalDate;

public class EventScheduler
{
    /**
     * Search an event by title
     * @param title
     * @return
     */
    public Event searchEventByTitle(String title)
    {
        sortEventsByTitle(); //make sure the list is sorted by title
        int index = binarySearchByTitle(title);
        if (index>=0)
        {
            return events.get(index);
        }
        return null;
    }

    /**
     * search for an event by date
     * @param data
     * @return
     */
    public Event searchEventByDate(LocalDate data)
    {
        sortEventsByDate(); //make sure the listt is sorted by date
        LocalDate searchDate = LocalDate.parse(data);
        int index = binarySearchByDate(searchDate);
        if (index>=0)
        {
            return events.get(index);
        }
        return null;
    }

    public Event searchEventByLocation(String location)
    {
        sortEventsByDate();
        int index = binarySearchByLocation(location);
        if (index >=0)
        {
            return events.get(index);
        }
        return null;
    }

    public int  binarySearchByDate(LocalDate date)
    {
        int low = 0;
        int high = events.size() -1;
        while (low <=high)
        {
            int mid = (low+high)/2;
            LocalDate midDate = events.get(mid).getDate();    
            if (midDate.equals(date))
            {
                return mid;
            }
            else if (midDate.isBefore(date))
            {
                low = mid+1;
            }
            else
            {
                high = mid-1;
            }
        }
        return -1;
    } 

    public int binarySearchByTitle(String title)
    {
        int low = 0;
        int high = events.size() +1;
        while (low<=high)
        {
            int mid = (low+high)/2;
            String midTitle = events.get(mid).getTitle().toLowerCase();
            int comparison = midTitle.compareTo(title.toLowerCase());    
            if (comparison == 0)
            {
                return mid;
            }

            else if (comparison <0)
            {
                low = mid+1;
            }
            else
            {
                high = mid-1;
            }
        }
        return -1;
    }

    public int binarySearchByLocation(String location)
    {
        int low = 0;
        int high = events.size() -1;
        while (low<=high)
        {
            int mid = (low+high)/2;
            String midLocation = events.get(mid).getLocation().toLowerCase();
            int comparison = midLocation.compareTo(location.toLowerCase());
            if (comparison==0)
            {
                return mid;
            }   
            else if (comparison<0)
            {
                low = mid+1;
            }
            else
            {
                high = mid+1;
            }
        } return -1;
    } 
}