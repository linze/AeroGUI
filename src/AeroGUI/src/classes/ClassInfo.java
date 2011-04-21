package classes;

import java.io.Serializable;

public class ClassInfo implements Serializable {

  private Price _price =  new Price();
  private Integer _seatsLeft;

  public ClassInfo() {
  }

  public ClassInfo(Price price, Integer seatsLeft) {
        this._price = price;
        this._seatsLeft = seatsLeft;
  }

  public Price getPrice() {
        return _price;
  }

  public void setPrice(Price price) {
        this._price = price;
  }

  public Integer getSeatsLeft() {
        return _seatsLeft;
  }

  public void setSeatsLeft(Integer seatsLeft) {
        this._seatsLeft = seatsLeft;
  }

}
