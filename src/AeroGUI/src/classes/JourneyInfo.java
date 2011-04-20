package classes;

import java.util.Date;

public class JourneyInfo {
    private String _id;
    private String _type;
    private String _origin;
    private String _destination;
    private Date _departure;
    private Date _arrival;
    private ClassInfo _touristinfo;
    private ClassInfo _businessinfo;

    public JourneyInfo() {
    }

    public Date getArrival() {
        return _arrival;
    }

    public void setArrival(Date _arrival) {
        this._arrival = _arrival;
    }

    public ClassInfo getBusinessinfo() {
        return _businessinfo;
    }

    public void setBusinessinfo(ClassInfo _businessinfo) {
        this._businessinfo = _businessinfo;
    }

    public Date getDeparture() {
        return _departure;
    }

    public void setDeparture(Date _departure) {
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
}
