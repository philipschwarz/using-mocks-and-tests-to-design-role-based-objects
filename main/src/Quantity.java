public class Quantity
{
    private final int value;

    public Quantity(int value)
    {
        this.value = value;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quantity quantity = (Quantity) o;

        if (value != quantity.value) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return "Quantity:{value="+value+"}";
    }
}
