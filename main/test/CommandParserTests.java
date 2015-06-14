import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class CommandParserTests
{
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private CommandParser commandParser;
    private SaleEventListener saleEventListener;

    @Before
    public void setUp()
    {
        saleEventListener = context.mock(SaleEventListener.class);
        commandParser = new CommandParser(saleEventListener);
    }

    @Test
    public void notifies_listener_of_new_sale_event()
    {
        String newSaleCommand = "Command:NewSale";
        context.checking(new Expectations() {{
            oneOf (saleEventListener).newSaleInitiated();
        }});

        commandParser.parse(newSaleCommand);
    }

    @Test
    public void notifies_listener_of_sale_completed_event() throws Exception
    {
        String endSaleCommand = "Command:EndSale";
        context.checking(new Expectations() {{
            oneOf (saleEventListener).saleCompleted();
        }});

        commandParser.parse(endSaleCommand);
    }

    @Test
    public void notifies_listener_of_item_and_quantity_entered() throws Exception
    {
        String message = "Input: Barcode=100008888559, Quantity =1";

        context.checking(new Expectations() {{
            oneOf (saleEventListener).itemEntered("100008888559",1);
        }});

        commandParser.parse(message);
    }
}
