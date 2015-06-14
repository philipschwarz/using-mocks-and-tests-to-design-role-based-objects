public class CommandParser
{
    private final SaleEventListener saleEventListener;

    public CommandParser(SaleEventListener saleEventListener)
    {

        this.saleEventListener = saleEventListener;
    }

    public void parse(String saleCommand)
    {
        saleEventListener.newSaleInitiated();
    }
}
