
package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Travel implements Serializable {
  private Integer _ntravelers;
  private ArrayList<Journey> _journeys =  new ArrayList<Journey>();

  public Travel() {
  }

  /*
   * Note that this method is only useful when they're just one Journey in the
   * TravelInfo or all the Journey inside have the same class. If not, they've
   * to be added one by one.
   */
  public Travel(TravelInfo travelinfo, Integer ntravelers, String journeyclass) {
        Journey newj;
        JourneyInfo ji;
        for (int i=0; i<travelinfo.getJourneysinfo().size(); i++) {
            ji = travelinfo.getJourneysinfo().get(i);
            newj = new Journey(ji, journeyclass);
            this._journeys.add(newj);
        }
        this._ntravelers = ntravelers;
  }

  public Travel(Integer _ntravelers, ArrayList<Journey> _journeys) {
        this._ntravelers = _ntravelers;
        this._journeys = _journeys;
  }

  public ArrayList<Journey> getJourneys() {
        return _journeys;
  }

  public void setJourneys(ArrayList<Journey> _journeys) {
        this._journeys = _journeys;
  }

  public Integer getNtravelers() {
        return _ntravelers;
  }

  public void setNtravelers(Integer _ntravelers) {
        this._ntravelers = _ntravelers;
  }

  public boolean isCompound() {
        return (this._journeys.size() != 1);
  }

  public String getOriginJourneyId() {
        return this._journeys.get(0).getJourneyinfoid();
  }

  public String getDestinationJourneyId() {
        // TODO Remove this comment if size-1 is the correct index
        return this._journeys.get(this._journeys.size()-1).getJourneyinfoid();
  }

}
