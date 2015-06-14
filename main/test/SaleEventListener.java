public interface SaleEventListener
{
    void newSaleInitiated();

    void saleCompleted();

    void itemEntered(String barcode, int quantity);
}
