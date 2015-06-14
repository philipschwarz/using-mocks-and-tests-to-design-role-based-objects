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
        Map<String,String> argumentNameToValueMap = new HashMap<>();
        for (String argument : commandArguments)
        {
            String[] argumentNameAndValue = argument.split("=");
            String argumentName = argumentNameAndValue[0].trim();
            String argumentValue = argumentNameAndValue[1].trim();
            argumentNameToValueMap.put(argumentName, argumentValue);
        }
        String barcode = argumentNameToValueMap.get("Barcode");
        int quantity = Integer.parseInt(argumentNameToValueMap.get("Quantity"));
        saleEventListener.itemEntered(barcode, quantity);
    }
}
