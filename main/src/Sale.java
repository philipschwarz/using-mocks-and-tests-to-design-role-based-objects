import java.util.ArrayList;
import java.util.List;

public class Sale
{
    private List<ProductDescription> purchasedProducts = new ArrayList<ProductDescription>();

    void sendReceiptTo(ReceiptReceiver receiptReceiver)
    {
        Money total = new Money(0.0);
        for ( ProductDescription productDescription : purchasedProducts )
        {
            total = total.plus(productDescription.getUnitPrice());
        }
        receiptReceiver.receiveTotalDue(total);
    }

    public void purchaseItemWith(ProductDescription productDescription)
    {
        purchasedProducts.add(productDescription);
    }
}
