package classes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
public class JourneyHandler {
  private ArrayList<JourneyInfo> _journeysinfo =  new ArrayList<JourneyInfo>();

  private String _datafilename;

  public JourneyHandler() {
  }

  public JourneyHandler(ArrayList<JourneyInfo> _journeysinfo, String _datafilename) {
        this._journeysinfo = _journeysinfo;
        this._datafilename = _datafilename;
  }

  public String getDatafilename() {
        return _datafilename;
  }

  public void setDatafilename(String _datafilename) {
        this._datafilename = _datafilename;
  }

  public ArrayList<JourneyInfo> getJourneysInfo() {
        return _journeysinfo;
  }

  public void setJourneysInfo(ArrayList<JourneyInfo> _journeysinfo) {
        this._journeysinfo = _journeysinfo;
  }

  public void load() throws FileNotFoundException, ParseException {
        this._journeysinfo = JourneysFileParser.loadData(this._datafilename);
  }

  public void save() throws IOException {
        JourneysFileParser.saveData(this._journeysinfo, this._datafilename);
  }

}
