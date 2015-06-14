import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CommandParserTests
{
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void notifies_listener_of_new_sale_event()
    {
        final SaleEventListener saleEventListener = context.mock(SaleEventListener.class);
        CommandParser commandParser = new CommandParser(saleEventListener);
        String newSaleCommand = "Command:NewSale";
        context.checking(new Expectations() {{
            oneOf (saleEventListener).newSaleInitiated();
        }});

        commandParser.parse(newSaleCommand);
    }

    @Test
    public void notifies_listener_of_sale_completed_event() throws Exception
    {
        final SaleEventListener saleEventListener = context.mock(SaleEventListener.class);
        CommandParser commandParser = new CommandParser(saleEventListener);
        String endSaleCommand = "Command:EndSale";
        context.checking(new Expectations() {{
            oneOf (saleEventListener).saleCompleted();
        }});

        commandParser.parse(endSaleCommand);
    }
}
