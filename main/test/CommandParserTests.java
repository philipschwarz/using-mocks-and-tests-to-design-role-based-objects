import org.junit.Test;

public class CommandParserTests
{
    @Test
    public void notifies_listener_of_new_sale_event()
    {
        CommandParser commandParser = new CommandParser();
        String newSaleCommand = "Command:NewSale";
        commandParser.parse(newSaleCommand);
    }
}
