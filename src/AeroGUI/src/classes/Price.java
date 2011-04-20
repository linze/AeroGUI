package classes;

public class Price {
    private Integer _quantity = 0;
    private String _currency = "EUR";

    public Price() {
    }

    public Price(Integer quantity) {
        this._quantity = quantity;
    }

    public Price(Integer quantity, String currency) {
        this._quantity = quantity;
        this._currency = currency;
    }

    public Integer getQuantity() {
        return _quantity;
    }

    public void setQuantity(Integer quantity) {
        this._quantity = quantity;
    }

    public String getCurrency() {
        return _currency;
    }

    public void setCurrency(String currency) {
        this._currency = currency;
    }

}
