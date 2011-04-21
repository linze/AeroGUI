package classes;

public class Price {
    private double _quantity = 0;
    private String _currency = "EUR";

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

}
