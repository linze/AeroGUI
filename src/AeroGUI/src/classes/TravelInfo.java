package classes;

import java.util.ArrayList;

public class TravelInfo {
    private ArrayList<JourneyInfo> _journeysinfo = new ArrayList<JourneyInfo>();

    public TravelInfo() {
    }

    public TravelInfo(ArrayList<JourneyInfo> _journeysinfo) {
        this._journeysinfo = _journeysinfo;
    }

    public ArrayList<JourneyInfo> getJourneysinfo() {
        return _journeysinfo;
    }

    public void setJourneysinfo(ArrayList<JourneyInfo> _journeysinfo) {
        this._journeysinfo = _journeysinfo;
    }


}
