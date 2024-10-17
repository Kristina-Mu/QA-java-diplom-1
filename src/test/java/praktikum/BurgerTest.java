package praktikum;


import org.junit.Before;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class BurgerTest {
    private Burger burger;
    private Database database;

    @Before
    public void setUp() {
        database = new Database();
        burger = new Burger();
    }

    // Проверяет, что метод setBuns() корректно устанавливает булочку.
    @Test
    public void testSetBuns() {
        Bun bun = database.availableBuns().get(0); // Используем первую булочку из Database

        burger.setBuns(bun);

        assertEquals("Булочка должна быть установлена", bun, burger.bun);
    }

    // Проверяет корректное добавление ингредиента в бургер.
    @Test
    public void testAddIngredient() {
        Ingredient ingredient = database.availableIngredients().get(0); // Используем первый ингредиент из Database

        burger.setBuns(database.availableBuns().get(0)); // Устанавливаем булочку
        burger.addIngredient(ingredient);

        List<Ingredient> actualList = burger.ingredients;
        assertEquals("Список ингредиентов должен содержать один элемент", 1, actualList.size());
        assertEquals("Добавленный ингредиент должен соответствовать ожидаемому", ingredient, actualList.get(0));
    }

    // Проверяет, что ингредиенты удаляются корректно из списка.
    @Test
    public void testRemoveIngredient() {
        Ingredient ingredient = database.availableIngredients().get(0);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);

        List<Ingredient> actualList = burger.ingredients;
        assertEquals("Список ингредиентов должен быть пустым", 0, actualList.size());
    }

    // Проверяет корректное перемещение ингредиентов внутри списка.
    @Test
    public void testMoveIngredient() {
        Ingredient firstIngredient = database.availableIngredients().get(0);
        Ingredient secondIngredient = database.availableIngredients().get(1);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        assertEquals("Первый ингредиент должен быть перемещен", secondIngredient.getName(), burger.ingredients.get(0).getName());
        assertEquals("Второй ингредиент должен быть перемещен", firstIngredient.getName(), burger.ingredients.get(1).getName());
    }

    // Проверяет правильность расчета цены.
    @Test
    public void testGetPrice() {
        Bun bun = database.availableBuns().get(0);
        Ingredient ingredient = database.availableIngredients().get(0);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        //
        assertEquals("Цена должна быть корректной", 300F, burger.getPrice(), 0.01);
    }

    // Проверяет правильно сформированный чек.
    @Test
    public void testGetReceipt() {
        Bun bun = database.availableBuns().get(2); // Используем третью булочку (red bun)
        Ingredient ingredient = database.availableIngredients().get(3); // Используем первый ингредиент из начинки

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String expectedReceipt = String.format("(==== %s ====)%n", bun.getName()) +
                String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(), ingredient.getName()) +
                String.format("(==== %s ====)%n", bun.getName()) +
                String.format("%nPrice: %f%n", burger.getPrice());

        assertEquals("Чек должен соответствовать ожидаемому", expectedReceipt, burger.getReceipt());
    }

    // Добавляет тест для проверки доступных булочек
    @Test
    public void testAvailableBuns() {
        List<Bun> availableBuns = database.availableBuns();
        assertEquals("Должно быть 3 доступные булочки", 3, availableBuns.size());
        assertTrue("Должна содержать булочку black bun", availableBuns.stream().anyMatch(bun -> "black bun".equals(bun.getName())));
        assertTrue("Должна содержать булочку white bun", availableBuns.stream().anyMatch(bun -> "white bun".equals(bun.getName())));
        assertTrue("Должна содержать булочку red bun", availableBuns.stream().anyMatch(bun -> "red bun".equals(bun.getName())));
    }

    // Добавляет тест для проверки доступных ингредиентов
    @Test
    public void testAvailableIngredients() {
        List<Ingredient> availableIngredients = database.availableIngredients();
        assertEquals("Должно быть 6 доступных ингредиентов", 6, availableIngredients.size());
        assertTrue("Должен содержать ингредиент cutlet", availableIngredients.stream().anyMatch(ingredient -> "cutlet".equals(ingredient.getName())));
        assertTrue("Должен содержать ингредиент hot sauce", availableIngredients.stream().anyMatch(ingredient -> "hot sauce".equals(ingredient.getName())));
    }
}
