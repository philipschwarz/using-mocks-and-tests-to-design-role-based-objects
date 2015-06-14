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
}
