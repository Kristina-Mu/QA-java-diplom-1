package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientParametrizedTest {

    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientParametrizedTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                { IngredientType.FILLING, "Tomato", 15.0f },
                { IngredientType.SAUCE, "Ketchup", 5.0f },
                { IngredientType.FILLING, "Cheese", 25.0f },
                { null, "Onion", 10.0f },
                { IngredientType.FILLING, null, 10.0f },
                { IngredientType.FILLING, "InvalidIngredient", -5.0f },
                { IngredientType.FILLING, "InvalidIngredient", 0.0f },
                { IngredientType.FILLING, "ValidIngredient", 100.0f }
        };
    }

    @Test
    public void testIngredientConstructor() {
        // Создаем объект Ingredient, даже если параметры некорректные
        Ingredient ingredient = new Ingredient(type, name, price);

        // Ожидаем, что объект был создан, если name не null
        if (name != null) {
            assertEquals("Тип ингредиента неверен", type, ingredient.getType());
            assertEquals("Название ингредиента неверно", name, ingredient.getName());
            assertEquals("Цена ингредиента неверна", price, ingredient.getPrice(), 0.01);
        }
    }
}
/**
 * Модель ингредиента.
 * Ингредиент: начинка или соус.
 * У ингредиента есть тип (начинка или соус), название и цена.
 */