public class CommandParser
{
    private static final String END_SALE_COMMAND = "EndSale";

    private final SaleEventListener saleEventListener;

    public CommandParser(SaleEventListener saleEventListener)
    {
        this.saleEventListener = saleEventListener;
    }

    public void parse(String messageFromDevice)
    {
        String commandName = messageFromDevice.split(":")[1].trim();
        if(END_SALE_COMMAND.equals(commandName))
        {
            saleEventListener.saleCompleted();
        }
        else
        {
            saleEventListener.newSaleInitiated();
        }
    }
}
