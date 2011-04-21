
package classes;

import java.util.ArrayList;
public class Travel {
  private Integer _ntravelers;

  private ArrayList<Journey> _journeys =  new ArrayList<Journey>();

  public Travel() {
  }

  public Travel(TravelInfo travelinfo) {
        // TODO: Implement conversion
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

}
