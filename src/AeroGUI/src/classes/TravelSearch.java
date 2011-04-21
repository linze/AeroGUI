
package classes;

public class TravelSearch {
  public static JourneyInfo doJourneyInfoSearch(String id, JourneyHandler handler) throws NotFoundException
  {
        JourneyInfo ji = null;
        boolean found = false, eol = (handler.getJourneysInfo().size() == 0);
        int i = 0;

        while ((!found) && (!eol)) {
            ji = handler.getJourneysInfo().get(i);
            if (ji.getId().equals(id)) {
                found = true;
            } else {
                i = i + 1;
                if (i == handler.getJourneysInfo().size())
                    eol = true;
            }
        }

        if (!found)
            throw new NotFoundException();
        
        return ji;
  }

  public static SearchResult doOriginSearch(String origin, JourneyHandler handler)
  {
        // TODO: Implement this
        return null;
  }

}
