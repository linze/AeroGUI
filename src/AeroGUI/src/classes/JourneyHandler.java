package classes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
public class JourneyHandler {
  private ArrayList<JourneyInfo> _journeysinfo =  new ArrayList<JourneyInfo>();

  private String _datafilename;


  /*
   * Constructors
   */
  public JourneyHandler() {
  }

  public JourneyHandler(ArrayList<JourneyInfo> _journeysinfo, String _datafilename) {
        this._journeysinfo = _journeysinfo;
        this._datafilename = _datafilename;
  }

  /*
   * Attributes operations
   */
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

  /*
   * Handler data saving operations
   */
  public void load() throws FileNotFoundException, ParseException {
        this._journeysinfo = JourneysFileParser.loadData(this._datafilename);
  }

  public void save() throws IOException {
        JourneysFileParser.saveData(this._journeysinfo, this._datafilename);
  }

  /*
   * Extensions
   */
  public ArrayList<String> getOrigins() {
      ArrayList<String> result = new ArrayList<String>();

      String current;
      for (int i=0; i<this._journeysinfo.size(); i++) {
          current = this._journeysinfo.get(i).getOrigin();
          if (!result.contains(current)) {
              result.add(current);
          }
      }
      return result;
  }

  public ArrayList<String> getDestinations() {
      ArrayList<String> result = new ArrayList<String>();

      String current;
      for (int i=0; i<this._journeysinfo.size(); i++) {
          current = this._journeysinfo.get(i).getDestination();
          if (!result.contains(current)) {
              result.add(current);
          }
      }
      return result;
  }

  public ArrayList<String> getDestinations(String origin) {
      ArrayList<String> result = new ArrayList<String>();

      JourneyInfo current;
      for (int i=0; i<this._journeysinfo.size(); i++) {
          current = this._journeysinfo.get(i);
          if (current.getOrigin().equals(origin)) {
              if (!result.contains(current.getDestination())) {
                  result.add(current.getDestination());
              }
          }
      }
      return result;
  }

  // TODO: Remove
  public ArrayList<JourneyInfo> getDestinationsJourneys(String origin) {
      ArrayList<JourneyInfo> result = new ArrayList<JourneyInfo>();

      JourneyInfo current;
      for (int i=0; i<this._journeysinfo.size(); i++) {
          current = this._journeysinfo.get(i);
          if (current.getOrigin().equals(origin)) {
            result.add(current);
          }
      }
      return result;
  }
}
