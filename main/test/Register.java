public class Register implements SaleEventListener
{
    private final ReceiptReceiver receiptReceiver;
    private boolean hasASaleInprogress;

    public Register(ReceiptReceiver receiptReceiver)
    {
        this.receiptReceiver = receiptReceiver;
    }

    @Override
    public void newSaleInitiated()
    {
        hasASaleInprogress = true;
    }

    @Override
    public void saleCompleted()
    {
        if (hasASaleInprogress)
        {
            receiptReceiver.receiveTotalDue(new Money(0.00));
        }
    }

    @Override
    public void itemEntered(ItemId barcode, Quantity quantity)
    {

    }
}
