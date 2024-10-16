package praktikum;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BunTest {
    private Bun bun;

    @Before
    public void createNewInstance() {
        // Создание нового экземпляра булки с тестовыми данными
        bun = new Bun("Флюоресцентная булка R2-D3", 988.0f);
    }

    @Test
    public void testGetName() {
        String expected = "Флюоресцентная булка R2-D3";
        String actual = bun.getName();

        assertEquals("Неверное значение имени булки", expected, actual);
    }

    @Test
    public void testGetPrice() {
        float expected = 988.0f;
        float actual = bun.getPrice();

        assertEquals("Неверное значение цены булки", expected, actual, 0.01);
    }

    @Test
    public void testAlternativeBun() {
        // Создание другого экземпляра булки для теста
        Bun alternativeBun = new Bun("Краторная булка N-200i", 1255.0f);

        assertEquals("Краторная булка N-200i", alternativeBun.getName());
        assertEquals(1255.0f, alternativeBun.getPrice(), 0.01);
    }
}
