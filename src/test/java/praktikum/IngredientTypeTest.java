package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IngredientTypeTest {

    @Test
    public void testIngredientTypeValues() {
        // Проверяем, что перечисление содержит нужные значения
        IngredientType[] expectedTypes = IngredientType.values();

        assertEquals("Должно быть 2 значения в IngredientType", 2, expectedTypes.length);
        assertTrue("Значение SAUCE должно быть в перечислении",
                contains(expectedTypes, IngredientType.SAUCE));
        assertTrue("Значение FILLING должно быть в перечислении",
                contains(expectedTypes, IngredientType.FILLING));
    }

    private boolean contains(IngredientType[] types, IngredientType type) {
        for (IngredientType t : types) {
            if (t == type) {
                return true;
            }
        }
        return false;
    }
}
