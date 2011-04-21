package classes;

import java.util.ArrayList;

public class JourneyHandler {
    private ArrayList<JourneyHandler> _journeysinfo = new ArrayList<JourneyHandler>();
    private String _datafilename;

    public JourneyHandler() {
    }

    public JourneyHandler(ArrayList<JourneyHandler> _journeysinfo, String _datafilename) {
        this._journeysinfo = _journeysinfo;
        this._datafilename = _datafilename;
    }

    public String getDatafilename() {
        return _datafilename;
    }

    public void setDatafilename(String _datafilename) {
        this._datafilename = _datafilename;
    }

    public ArrayList<JourneyHandler> getJourneysInfo() {
        return _journeysinfo;
    }

    public void setJourneysInfo(ArrayList<JourneyHandler> _journeysinfo) {
        this._journeysinfo = _journeysinfo;
    }

    public void load() {
        // TODO: Implement this
    }

    public void save() {
        // TODO: Implement this
    }


}
