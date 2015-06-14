import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class RegisterTests
{
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void receipt_total_for_a_sale_with_no_items_should_be_zero()
    {
        ReceiptReceiver receiptReceiver = context.mock(ReceiptReceiver.class);
        Register register = new Register(receiptReceiver);
        Money amountDue = new Money(0.00);
        context.checking(new Expectations()
        {{
            oneOf(receiptReceiver).receiveTotalDue(amountDue);
        }});

        register.newSaleInitiated();
        register.saleCompleted();
    }
}
