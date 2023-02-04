import ServerClient.ClientRequest;
import ServerUtils.ClientRequestProcess;
import ServerUtils.ReadCategories;
import UtilClasses.MaxCategory;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Класс содержащий unit тесты
 */
public class UnitTest {
    private static MaxCategory maxCategoryExpected;
    private static ClientRequest clientRequest1;
    private static ClientRequest clientRequest2;
    private static ClientRequest clientRequest3;
    public static Map<String, String> categoriesMap = new ReadCategories().readCategories();

    @Test
    public void other_greater_then_food() {
        System.out.println("TEST - other_greater_then_food");
        maxCategoryExpected = new MaxCategory("другое", 150);
        System.out.println("Expected: " + maxCategoryExpected);

        Map<String, Integer> mapSumResult = new LinkedHashMap<>();
        clientRequest1 = new ClientRequest("рыба", "2022.06.05", 100);
        new ClientRequestProcess().mapSumForm(clientRequest1, mapSumResult, categoriesMap);

        clientRequest2 = new ClientRequest("молоко", "2022.06.05", 50);
        new ClientRequestProcess().mapSumForm(clientRequest2, mapSumResult, categoriesMap);

        clientRequest3 = new ClientRequest("курица", "2022.06.05", 90);
        new ClientRequestProcess().mapSumForm(clientRequest3, mapSumResult, categoriesMap);
        System.out.println("Result: " + new MaxCategory().maxCategorySearch(mapSumResult));

        Assertions.assertEquals(maxCategoryExpected.toString(), new MaxCategory()
                .maxCategorySearch(mapSumResult).toString());
    }

    @Test
    public void other_equals_cloth() {
        System.out.println("TEST - other_equals_cloth");
        maxCategoryExpected = new MaxCategory("другое", 200);
        System.out.println("Expected: " + maxCategoryExpected);

        Map<String, Integer> mapSumResult = new LinkedHashMap<>();
        clientRequest1 = new ClientRequest("рыба", "2019.06.05", 200);
        new ClientRequestProcess().mapSumForm(clientRequest1, mapSumResult, categoriesMap);

        clientRequest2 = new ClientRequest("шапка", "2020.01.05", 100);
        new ClientRequestProcess().mapSumForm(clientRequest2, mapSumResult, categoriesMap);

        clientRequest3 = new ClientRequest("тапки", "2022.02.05", 100);
        new ClientRequestProcess().mapSumForm(clientRequest3, mapSumResult, categoriesMap);
        System.out.println("Result: " + new MaxCategory().maxCategorySearch(mapSumResult));

        Assertions.assertEquals(maxCategoryExpected.toString(), new MaxCategory()
                .maxCategorySearch(mapSumResult).toString());
    }

    @Test
    public void all_categories_cloth_greater() {
        System.out.println("TEST - all_categories_cloth_greater");
        maxCategoryExpected = new MaxCategory("одежда", 250);
        System.out.println("Expected: " + maxCategoryExpected);

        Map<String, Integer> mapSumResult = new LinkedHashMap<>();
        clientRequest1 = new ClientRequest("рыба", "2016.06.05", 90);
        new ClientRequestProcess().mapSumForm(clientRequest1, mapSumResult, categoriesMap);

        clientRequest2 = new ClientRequest("мыло", "2017.02.05", 85);
        new ClientRequestProcess().mapSumForm(clientRequest2, mapSumResult, categoriesMap);

        clientRequest3 = new ClientRequest("шапка", "2018.02.05", 250);
        new ClientRequestProcess().mapSumForm(clientRequest3, mapSumResult, categoriesMap);
        System.out.println("Result: " + new MaxCategory().maxCategorySearch(mapSumResult));

        ClientRequest clientRequest4 = new ClientRequest("курица", "2019.07.05", 160);
        new ClientRequestProcess().mapSumForm(clientRequest4, mapSumResult, categoriesMap);

        ClientRequest clientRequest5 = new ClientRequest("акции", "2020.11.05", 180);
        new ClientRequestProcess().mapSumForm(clientRequest5, mapSumResult, categoriesMap);

        Assertions.assertEquals(maxCategoryExpected.toString(), new MaxCategory()
                .maxCategorySearch(mapSumResult).toString());
    }

    @Test
    public void empty_Map_Exception() {
        System.out.println("TEST - empty_Map_Exception");
        Map<String, Integer> mapSum = new HashMap<>();
        System.out.println("Expected: NoSuchElementException");
        Assertions.assertThrows(java.util.NoSuchElementException.class, () -> new MaxCategory()
                .maxCategorySearch(mapSum));
    }
}
