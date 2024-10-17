package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunParameterizedTest {

    private final String expectedName;
    private final float expectedPrice;

    public BunParameterizedTest(String expectedName, float expectedPrice) {
        this.expectedName = expectedName;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"Wheat Bun", 30.0f},
                {"White Bun", 50.0f},
                {"Black Bun", 45.0f},
                {"Sesame Bun", 60.0f}
        };
    }

    @Test
    public void testBunConstructor() {
        Bun bun = new Bun(expectedName, expectedPrice);
        assertEquals(expectedName, bun.getName());
        assertEquals(expectedPrice, bun.getPrice(), 0.01);
    }
}
