
package classes;

import java.util.ArrayList;
public class SearchResult {
  private ArrayList<TravelInfo> _travelsinfo =  new ArrayList<TravelInfo>();

  public SearchResult() {
  }

  public SearchResult(ArrayList<TravelInfo> _travelsinfo) {
        this._travelsinfo = _travelsinfo;
  }

  public ArrayList<TravelInfo> getTravelsinfo() {
        return _travelsinfo;
  }

  public void setTravelsinfo(ArrayList<TravelInfo> _travelsinfo) {
        this._travelsinfo = _travelsinfo;
  }

}
