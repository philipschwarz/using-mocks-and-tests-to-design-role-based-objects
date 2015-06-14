public class ItemId
{
    private final String barcode;

    public ItemId(String barcode)
    {
        this.barcode = barcode;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemId itemId = (ItemId) o;

        if (!barcode.equals(itemId.barcode)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return barcode.hashCode();
    }
}
