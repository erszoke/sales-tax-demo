package salesTax;

public enum TaxRatePct {

    NO_TAX(0), GENERAL(10), IMPORTED(5);

    private int taxRatePct;

    TaxRatePct(int taxRatePct) {
        this.taxRatePct = taxRatePct;
    }

    public int getTaxRatePct() {
        return taxRatePct;
    }
}
