public class ProductDescription
{
    private final String barcode;

    private final Money unitPrice;

    public ProductDescription(String barcode, Money money)
    {

        this.barcode = barcode;
        this.unitPrice = money;
    }

    public Money getUnitPrice()
    {
        return unitPrice;
    }
}
