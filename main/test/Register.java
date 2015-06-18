
public class Register implements SaleEventListener
{
    private final ReceiptReceiver receiptReceiver;
    private final ProductCatalog productCatalog;
    private Sale sale;

    public Register(ReceiptReceiver receiptReceiver, ProductCatalog productCatalog)
    {
        this.receiptReceiver = receiptReceiver;
        this.productCatalog = productCatalog;
    }

    @Override
    public void newSaleInitiated()
    {
        sale = new Sale();
    }

    @Override
    public void saleCompleted()
    {
        if (sale != null)
        {
            sale.sendReceiptTo(receiptReceiver);
        }
    }

    @Override
    public void itemEntered(ItemId barcode, Quantity quantity)
    {
        sale.purchaseItemWith(productCatalog.productDescriptionFor(barcode));
    }
}
