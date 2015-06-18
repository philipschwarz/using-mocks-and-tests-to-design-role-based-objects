import java.util.HashMap;
import java.util.Map;

public class CommandParser
{
    private static final String END_SALE_COMMAND = "EndSale";
    public static final String INPUT_COMMAND = "Input";

    private final SaleEventListener saleEventListener;

    public CommandParser(SaleEventListener saleEventListener)
    {
        this.saleEventListener = saleEventListener;
    }

    public void parse(String messageFromDevice)
    {
        String[] command = messageFromDevice.split(":");
        String commandType = command[0].trim();
        String commandBody = command[1].trim();
        if(INPUT_COMMAND.equals(commandType))
        {
            processInputCommand(commandBody);
        }
        else
        {
            processCommand(commandBody);
        }
    }

    private void processCommand(String command)
    {
        if(END_SALE_COMMAND.equals(command))
        {
            saleEventListener.saleCompleted();
        }
        else
        {
            saleEventListener.newSaleInitiated();
        }
    }

    private void processInputCommand(String commandBody)
    {
        String[] commandArguments = commandBody.split(",");
        Map<String,String> argumentNameToValueMap = argumentNameToValueMapFor(commandArguments);
        String barcode = argumentNameToValueMap.get("Barcode");
        int quantity = Integer.parseInt(argumentNameToValueMap.get("Quantity"));
        saleEventListener.itemEntered(new ItemId(barcode), new Quantity(quantity));
    }

    private Map<String,String> argumentNameToValueMapFor(String[] commandArguments)
    {
        Map<String, String> map = new HashMap<>();
        for (String argument : commandArguments)
        {
            addArgument(argument, map);
        }
        return map;
    }

    private void addArgument(String argument, Map<String, String> map)
    {
        String[] argumentNameAndValue = argument.split("=");
        String argumentName = argumentNameAndValue[0].trim();
        String argumentValue = argumentNameAndValue[1].trim();
        map.put(argumentName, argumentValue);
    }
}
