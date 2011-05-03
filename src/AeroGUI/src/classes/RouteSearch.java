package classes;

import java.util.ArrayList;
import java.util.Calendar;

public class RouteSearch {
    private JourneyHandler _handler;

    public RouteSearch(JourneyHandler handler) {
        _handler = handler;
    }


    /* Get the posible routes between two cities that meets some requirements
    *
    * @param origin
    *    Origin city.
    * @param destination
    *    Destination city.
    * @param handler
    *    JourneyHandler with all the JourneyInfo.
    * @return
    *    A SearchResult with all the valid simple and composite rutes between two cities.
    */
    public SearchResult routeSearch(String origin, String destination) {
      // Here will be saved the result that later we will return
      SearchResult result = new SearchResult();

      // Create auxiliary objects for recurrent function parameters
      // From there, only keep in mind that the first step is the origin step
      // and the transport list contains no transport yet.
      ArrayList<String> steps = new ArrayList<String>();
      steps.add(origin);
      ArrayList<JourneyInfo> transports = new ArrayList<JourneyInfo>();
      
      // Call the recurrent function that will get all the routes
      routeSearch(result, destination, steps, transports);

      // Return the result
      return result;
    }

    /* Recurrent function that checks for possible routes
    *
    * This should not be visible from outside. Is a auxiliary function for routeSearch.
    *
    * @param result
    *    SearchResult in which we want to add the found routes.
    *
    * @param destination
    *    City proposed to reach.
    *
    * @param steps
    *    Cities string reached
    *
    * @param transports
    *    Transports used to reach the current city.
    *
    * @return
    *    Nothing. It changes result parameter adding the routes.
    */
    private void routeSearch(SearchResult result, String destination, ArrayList<String> steps,
          ArrayList<JourneyInfo> transports) {
        // Check if this iteration step is the final one
        if (destination.equals(currentStep(steps))) {
            // Add the route to the routes list
            addResult(transports, result);
            // Nothing more to do here, we have reached the target
            return;
        }

        // If we have not reached the destination yet...

        // Obtain the places that could be visited from here
        ArrayList<JourneyInfo> nextJourneys = reachablePlaces(currentStep(steps));
        
        // Remove valid routes: repeated, timeout, etc...
        cleanInvalid(nextJourneys, steps, transports);
        
        // Call for next steps
        searchDeeper(result, destination, steps, transports, nextJourneys);

    }

    /* Find the current step in the step list
    *
    * This should not visible from outsite. Is a auxiliary function.
    *
    * @param steps
    *    ArrayList<String> with all the steps, being the first one the initial step and the last one
    * the current step.
    *
    * @return
    *    The current step. Null it they're no step.
    */
    private String currentStep(ArrayList<String> steps) {
        return steps.size() > 0 ? steps.get(steps.size() - 1) : null;
    }

    /* Find the last transport in the transport list
    *
    * This should not visible from outsite. Is a auxiliary function.
    *
    * @param steps
    *    ArrayList<JourneyInfo> with all the transports.
    *
    * @return
    *    The last transport. Null if they're no transport.
    */
    private JourneyInfo lastTransport(ArrayList<JourneyInfo> transports) {
        return transports.size() > 0 ? transports.get(transports.size() - 1) : null;
    }

    /* Find the first transport in the transport list
    *
    * This should not visible from outsite. Is a auxiliary function.
    *
    * @param steps
    *    ArrayList<JourneyInfo> with all the transports.
    *
    * @return
    *    The first transport. Null if they're no transport.
    */
    private JourneyInfo firstTransport(ArrayList<JourneyInfo> transports) {
        return transports.size() > 0 ? transports.get(0) : null;
    }

    /* Add the route to the result list.
    *
    * This shoud not visible from outside.
    *
    * @param transports
    *    List of transports used to reach the destination.
    *
    * @param result
    *    Routes list in which we want to add the result.
    */
    private void addResult(ArrayList<JourneyInfo> transports, SearchResult result) {
        // Make a TravelInfo to contain the JourneyInfo of each step
        TravelInfo thisroute = new TravelInfo();
        // Add the JourneyInfo to the travel
        thisroute.setJourneysinfo(transports);
        // Include the new route in the results
        result.addTravelInfo(thisroute);
    }

    /* Find next destinations from current step
     *
     * This should not visible from outside.
     *
     * @param city
     *  City name in which we are.
     *
     * @return
     *  A ArrayList of JourneyInfo with all the posible destinations
     */
     private ArrayList<JourneyInfo> reachablePlaces(String city) {
         ArrayList<JourneyInfo> result = new ArrayList<JourneyInfo>();

         for (JourneyInfo current : _handler.getJourneysInfo()) {
             if (current.getOrigin().equals(city))
                 result.add(current);
         }
         return result;
     }

    /* Invoke methods to search next level.
    *
    * This should not visible from outside.
    *
    * @param result
    *    SearchResult in which we want to add the found routes.
    *
    * @param destination
    *    City proposed to reach.
    *
    * @param steps
    *    Cities string reached
    *
    * @param transports
    *    Transports used to reach the current city.
    *
    * @param nexts
    *    Nexts transports that we will consider to find a route.
    */
    private void searchDeeper(SearchResult result, String destination, ArrayList<String> steps,
      ArrayList<JourneyInfo> transports, ArrayList<JourneyInfo> nexts) {
        // Parameters copy to be adapted to each path call
        ArrayList<String> updatedSteps;
        ArrayList<JourneyInfo> updatedTransports;

        for (JourneyInfo journey : nexts) {
            // Clone the parameters to avoid overriding
            updatedSteps = (ArrayList<String>) steps.clone();
            updatedTransports = (ArrayList<JourneyInfo>) transports.clone();
            // Add the new step and new transport
            updatedSteps.add(journey.getDestination());
            updatedTransports.add(journey);
            // Run a new iteration
            routeSearch(result, destination, updatedSteps, updatedTransports);
        }
    }

    /* Remove next places that does not apply the restrictions.
     *
     * This should not be visible from outside.
     *
     * @param nexts
     *  ArrayList of JourneyInfo with places that we are considering.
     *
     * @param visited
     *  ArrayList of String with places that we have already visited.
     *
     * @param transports
     *  ArrayList of JourneyInfo with transports that we have already used.
     */
    private void cleanInvalid(ArrayList<JourneyInfo> nexts, ArrayList<String> visited,
            ArrayList<JourneyInfo> transports) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (JourneyInfo current : nexts) {
            // Remove it if we have already been in this city
            if (isVisited(current.getDestination(), visited)) {
                indexes.add(nexts.indexOf(current));
                continue;
            }

            // Remove it if have passed
            if (!isFuture(current)) {
                indexes.add(nexts.indexOf(current));
                continue;
            }

            // Remove it if its not on time
            JourneyInfo lastjourney = lastTransport(transports);
            if ((lastjourney != null) && (!isOnTime(lastjourney, current))) {
                indexes.add(nexts.indexOf(current));
                continue;
            }

            // Remove if it exceed the established maximum travel time
            if ((lastjourney != null) && (exceedTime(current, transports))) {
                indexes.add(nexts.indexOf(current));
                continue;
            }

            // Remove if it status is suspended
            if (isSuspended(current)) {
                indexes.add(nexts.indexOf(current));
                continue;
            }
        }

        // Remove the indexes detected
        for (int i=indexes.size()-1; i >=0; i--)
            nexts.remove((int) indexes.get(i));
    }

    /* Check if a place have been visited or not
     *
     * @param city
     *  City that we want to check
     *
     * @param visited
     *  ArrayList of String with places that we have already visited.
     *
     * @returns
     *  True if visited, false if not.
     */
    private boolean isVisited(String city, ArrayList<String> visited) {
        return visited.contains(city);
    }

     /* Check if a journey have not departure yet
     *
     * @param transport
     *  JourneyInfo that we want to check for departure.
     *
     * @return
     *  True if we can get it, false if it's past.
     */
    private boolean isFuture(JourneyInfo transport) {
        Calendar now = Calendar.getInstance();
        return transport.getDeparture().compareTo(now) > 0;
    }
    private boolean isOnTime(JourneyInfo arrival, JourneyInfo departure) {
        // If arrival es before departure, we are on time
        return arrival.getArrival().compareTo(departure.getDeparture()) < 0 ? true : false;
    }

    /* Check if adding the new transport the travel will or not exceed the maximum time
     *
     * This should not be visible from outside.
     *
     * @param journey
     *  New journey that we are going to check.
     *
     * @param transports
     *  Journeys that we've done before.
     *
     * @return
     *  False if we can do it. True if maximum time conditions are not met.
     */
    private boolean exceedTime(JourneyInfo journey, ArrayList<JourneyInfo> transports) {
        // Maximum milliseconds for bus and plane: 32 hours.
        final long MAX_BUS_PLANE = 32*60*60*1000;
        // Maximum milliseconds  for train: 48 hours
        final long MAX_TRAIN = 48*60*60*1000;
        // Maximum milliseconds  for the ones that include ships: 5 days
        final long MAX_INC_SHIP = 5*24*60*60*1000;

        // Calc the duration of complete travel
        long departure = firstTransport(transports).getDeparture().getTimeInMillis();
        long arrival = journey.getArrival().getTimeInMillis();
        long duration = arrival - departure;

        // Clone the list and include the new one
        ArrayList<JourneyInfo> newTransports = (ArrayList<JourneyInfo>) transports.clone();
        newTransports.add(journey);

        // Check bus and plane restrictions
        if (useBus(newTransports) || usePlane(newTransports)) {
            if (duration > MAX_BUS_PLANE)
                return true;
        }

        if (useShip(newTransports))
            if (duration > MAX_INC_SHIP)
                    return true;

        if ((useTrain(newTransports)) && (!(useBus(newTransports)||usePlane(newTransports)||useShip(newTransports) )))
            if (duration > MAX_TRAIN)
                return true;

        return false;
    }

    /* Check if a journey have been suspended or not
     *
     * @param transport
     *  JourneyInfo that we want to check for suspension.
     *
     * @return
     *  True if suspended, false if not.
     */
    private boolean isSuspended(JourneyInfo transport) {
        return transport.getStatus().equals("SUSPENDIDO");
    }

    /* Find if a trayect uses or not a bus
     *
     * This should not be visible from outside.
     *
     * @param transports
     *  Transports involved in the travel.
     *
     * @return
     *  True if the travel uses buses, false if not.
     */
    private boolean useBus(ArrayList<JourneyInfo> transports) {
        for (JourneyInfo transport : transports) {
            if (transport.getType().equals("AU"))
                return true;
        }
        return false;
    }

    /* Find if a trayect uses or not a train
     *
     * This should not be visible from outside.
     *
     * @param transports
     *  Transports involved in the travel.
     *
     * @return
     *  True if the travel uses trains, false if not.
     */
    private boolean useTrain(ArrayList<JourneyInfo> transports) {
        for (JourneyInfo transport : transports) {
            if (transport.getType().equals("TR"))
                return true;
        }
        return false;
    }

    /* Find if a trayect uses or not a plain
     *
     * This should not be visible from outside.
     *
     * @param transports
     *  Transports involved in the travel.
     *
     * @return
     *  True if the travel uses plains, false if not.
     */
    private boolean usePlane(ArrayList<JourneyInfo> transports) {
        for (JourneyInfo transport : transports) {
            if (transport.getType().equals("AV"))
                return true;
        }
        return false;
    }

    /* Find if a trayect uses or not a ship
     *
     * This should not be visible from outside.
     *
     * @param transports
     *  Transports involved in the travel.
     *
     * @return
     *  True if the travel uses ships, false if not.
     */
    private boolean useShip(ArrayList<JourneyInfo> transports) {
        for (JourneyInfo transport : transports) {
            if (transport.getType().equals("BA"))
                return true;
        }
        return false;
    }
    
}
