import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MoneyTests
{
    @Test
    public void should_be_able_to_create_the_sum_of_two_amounts() throws Exception
    {
        Money twoDollars = new Money(2.0);
        Money threeDollars = new Money(3.0);
        Money fiveDollars = new Money(5.0);
        assertEquals(fiveDollars, twoDollars.plus(threeDollars));
    }
}
