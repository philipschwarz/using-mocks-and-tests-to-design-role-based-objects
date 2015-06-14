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
        Register register = new Register();
        ReceiptPrinter receiptPrinter = context.mock(ReceiptPrinter.class);
        context.checking(new Expectations()
        {{
                oneOf(receiptPrinter).printTotalDue("0.00");
        }});

        register.newSaleInitiated();
        register.saleCompleted();
    }
}