
package classes;

import java.io.Serializable;
public class Price implements Serializable {
  private double _quantity =  0;

  private String _currency =  "EUR";

  public Price() {
  }

  public Price(double quantity) {
        this._quantity = quantity;
  }

  public Price(double quantity, String currency) {
        this._quantity = quantity;
        this._currency = currency;
  }

  public double getQuantity() {
        return _quantity;
  }

  public void setQuantity(double quantity) {
        this._quantity = quantity;
  }

  public String getCurrency() {
        return _currency;
  }

  public void setCurrency(String currency) {
        this._currency = currency;
  }

  @Override
  public String toString() {
        return Double.toString(_quantity) + " " + _currency;
  }

}
