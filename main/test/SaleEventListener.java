public interface SaleEventListener
{
    void newSaleInitiated();

    void saleCompleted();

    void itemEntered(ItemId barcode, Quantity quantity);
}
