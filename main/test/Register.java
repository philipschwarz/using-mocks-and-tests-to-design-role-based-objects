import java.util.ArrayList;
import java.util.List;

public class Register implements SaleEventListener
{
    private final ReceiptReceiver receiptReceiver;
    private final ProductCatalog productCatalog;
    private List<ProductDescription> purchasedProducts = new ArrayList<ProductDescription>();
    private boolean hasASaleInprogress;

    public Register(ReceiptReceiver receiptReceiver, ProductCatalog productCatalog)
    {
        this.receiptReceiver = receiptReceiver;
        this.productCatalog = productCatalog;
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
            Money total = new Money(0.0);
            for ( ProductDescription productDescription : purchasedProducts )
            {
                total = total.plus(productDescription.getUnitPrice());
            }
            receiptReceiver.receiveTotalDue(total);
        }
    }

    @Override
    public void itemEntered(ItemId barcode, Quantity quantity)
    {
        purchasedProducts.add(productCatalog.productDescriptionFor(barcode));
    }
}
