import org.jmock.Expectations;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.jmock.Expectations.any;

public class RegisterTests
{
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private ReceiptReceiver receiptReceiver;
    private Register register;

    @Before
    public void setUp() throws Exception
    {
        receiptReceiver = context.mock(ReceiptReceiver.class);
        register = new Register(receiptReceiver);
    }

    @Test
    public void receipt_total_for_a_sale_with_no_items_should_be_zero()
    {
        final Money amountDue = new Money(0.0);
        context.checking(new Expectations()
        {{
            oneOf(receiptReceiver).receiveTotalDue(amountDue);
        }});

        register.newSaleInitiated();
        register.saleCompleted();
    }

    @Test
    public void should_not_calculate_receipt_when_there_is_no_sale() throws Exception
    {
        context.checking(new Expectations()
        {{
            never(receiptReceiver);
        }});

        register.saleCompleted();
    }

    @Test
    public void should_calculate_receipt_for_sale_with_multiple_items_of_single_quantity() throws Exception
    {
        final ItemId itemId_1 = new ItemId("000000001");
        final ItemId itemId_2 = new ItemId("000000002");
        ProductDescription descriptionForItemWithId1 = new ProductDescription("description 1", new Money(3.00));
        ProductDescription descriptionForItemWithId2 = new ProductDescription("description 2", new Money(7.00));
        Quantity single_item = new Quantity(1);
        final Money amountDue = new Money(10.0);
        final ProductCatalog productCatalog = context.mock(ProductCatalog.class);

        context.checking(new Expectations()
        {{
            allowing(productCatalog).productDescriptionFor(itemId_1);
            allowing(productCatalog).productDescriptionFor(itemId_2);
            oneOf(receiptReceiver).receiveTotalDue(amountDue);
        }});

        register.newSaleInitiated();
        register.itemEntered(itemId_1, single_item);
        register.itemEntered(itemId_2, single_item);
        register.saleCompleted();
    }
}
