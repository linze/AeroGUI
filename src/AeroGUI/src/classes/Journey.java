
package classes;

import java.io.Serializable;

public class Journey implements Serializable {
  private String _journeyinfoid;
  private String _journeyclass;

    public Journey() {
    }

    public Journey(String _journeyinfoid, String _journeyclass) {
        this._journeyinfoid = _journeyinfoid;
        this._journeyclass = _journeyclass;
    }

    public Journey(JourneyInfo ji, String journeyclass) {
        this._journeyinfoid = ji.getId();
        this._journeyclass = journeyclass;
    }

    public String getJourneyclass() {
        return _journeyclass;
    }

    public void setJourneyclass(String _journeyclass) {
        this._journeyclass = _journeyclass;
    }

    public String getJourneyinfoid() {
        return _journeyinfoid;
    }

    public void setJourneyinfoid(String _journeyinfoid) {
        this._journeyinfoid = _journeyinfoid;
    }
}
