public class Register implements SaleEventListener
{
    private final ReceiptReceiver receiptReceiver;

    public Register(ReceiptReceiver receiptReceiver)
    {
        this.receiptReceiver = receiptReceiver;
    }

    @Override
    public void newSaleInitiated()
    {

    }

    @Override
    public void saleCompleted()
    {
        receiptReceiver.receiveTotalDue(0.00);
    }

    @Override
    public void itemEntered(ItemId barcode, Quantity quantity)
    {

    }
}
