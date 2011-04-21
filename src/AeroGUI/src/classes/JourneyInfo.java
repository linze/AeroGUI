package classes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
public class JourneyInfo implements Serializable {
    
  public static final SimpleDateFormat DATEFORMAT =  new SimpleDateFormat("d-MM-yyyy");

  public static final SimpleDateFormat TIMEFORMAT =  new SimpleDateFormat("H:mm");

  public static final SimpleDateFormat DATETIMEFORMAT =  new SimpleDateFormat("d-MM-yyyy H:mm");

  private String _id;

  private String _type;

  private String _status;

  private String _origin;

  private String _destination;

  private Calendar _departure =  new GregorianCalendar();

  private Calendar _arrival =  new GregorianCalendar();

  private ClassInfo _touristinfo =  new ClassInfo();

  private ClassInfo _businessinfo =  new ClassInfo();

  public JourneyInfo() {
  }

  public Calendar getArrival() {
        return _arrival;
  }

  public void setArrival(Calendar _arrival) {
        this._arrival = _arrival;
  }

  public ClassInfo getBusinessinfo() {
        return _businessinfo;
  }

  public void setBusinessinfo(ClassInfo _businessinfo) {
        this._businessinfo = _businessinfo;
  }

  public Calendar getDeparture() {
        return _departure;
  }

  public void setDeparture(Calendar _departure) {
        this._departure = _departure;
  }

  public String getDestination() {
        return _destination;
  }

  public void setDestination(String _destination) {
        this._destination = _destination;
  }

  public String getId() {
        return _id;
  }

  public void setId(String _id) {
        this._id = _id;
  }

  public String getOrigin() {
        return _origin;
  }

  public void setOrigin(String _origin) {
        this._origin = _origin;
  }

  public ClassInfo getTouristinfo() {
        return _touristinfo;
  }

  public void setTouristinfo(ClassInfo _touristinfo) {
        this._touristinfo = _touristinfo;
  }

  public String getType() {
        return _type;
  }

  public void setType(String _type) {
        this._type = _type;
  }

  public String getStatus() {
        return _status;
  }

  public void setStatus(String _status) {
        this._status = _status;
  }

}
