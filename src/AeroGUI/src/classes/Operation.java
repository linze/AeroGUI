
package classes;

import java.io.Serializable;
import java.util.ArrayList;
public class Operation implements Serializable {
  private OperationStatus _status;
  private ArrayList<Travel> _travels =  new ArrayList<Travel>();

  public Operation() {
  }

  public Operation(OperationStatus _state, ArrayList<Travel> _travels) {
        this._status = _state;
        this._travels = _travels;
  }

  public OperationStatus getStatus() {
        return _status;
  }

  public void setStatus(OperationStatus _status) {
        this._status = _status;
  }

  public ArrayList<Travel> getTravels() {
        return _travels;
  }

  public void setTravels(ArrayList<Travel> _travels) {
        this._travels = _travels;
  }

  /*
   * Note that this method is only useful when they're just one Journey in the
   * TravelInfo or all the Journey inside have the same class. If not, they've
   * to be added one by one.
   */
  public void addTravel(TravelInfo travelinfo, Integer ntravelers, String journeyclass) {
        Travel newTravel = new Travel(travelinfo, ntravelers, journeyclass);
        this._travels.add(newTravel);
  }

}
