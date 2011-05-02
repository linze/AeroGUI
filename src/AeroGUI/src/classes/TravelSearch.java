
package classes;

import java.util.ArrayList;

public class TravelSearch {
  public static JourneyInfo doJourneyInfoSearch(String id, JourneyHandler handler) throws NotFoundException {
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

  public static SearchResult doOriginSearch(String origin, JourneyHandler handler) {
        SearchResult sr = new SearchResult();
        // Get all the list of journeys
        ArrayList<JourneyInfo> alji = handler.getJourneysInfo();
        
        TravelInfo ti;
        JourneyInfo ji;
        for (int i=0; i<alji.size(); i++) {
            // Set the current JourneyInfo to work with
            ji = alji.get(i);
            if (ji.getOrigin().equals(origin)) {
                // Add to the results
                ti = new TravelInfo();
                ti.addJourneyInfo(ji);
                sr.addTravelInfo(ti);
            }
        }
        return sr;
  }

  public static SearchResult doDirectSearch(String origin, String destination, JourneyHandler handler) {
        SearchResult sr = new SearchResult();
        // Get all the list of journeys
        ArrayList<JourneyInfo> alji = handler.getJourneysInfo();

        TravelInfo ti;
        JourneyInfo ji;
        for (int i=0; i<alji.size(); i++) {
            // Set the current JourneyInfo to work with
            ji = alji.get(i);
            if ( (ji.getOrigin().equals(origin)) && (ji.getDestination().equals(destination))) {
                // Add to the results
                ti = new TravelInfo();
                ti.addJourneyInfo(ji);
                sr.addTravelInfo(ti);
            }
        }
        return sr;
  }

  public static SearchResult doRouteSearch(String origin, String destination, JourneyHandler handler) {
      RouteSearch rs = new RouteSearch(handler);
      return rs.routeSearch(origin, destination);
  }

}
