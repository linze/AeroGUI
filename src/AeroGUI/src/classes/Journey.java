
package classes;

public class Journey {
  private Integer _travelid;

  private Price _price =  new Price();

  public Journey() {
  }

  public Journey(JourneyInfo journeyinfo) {
        // TODO: Implement conversion
  }

  public Journey(Integer _travelid, Price _price) {
        this._travelid = _travelid;
        this._price = _price;
  }

  public Price getPrice() {
        return _price;
  }

  public void setPrice(Price _price) {
        this._price = _price;
  }

  public Integer getTravelid() {
        return _travelid;
  }

  public void setTravelid(Integer _travelid) {
        this._travelid = _travelid;
  }

}
