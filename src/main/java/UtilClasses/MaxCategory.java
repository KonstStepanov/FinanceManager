package UtilClasses;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/**
 * класс формирования максимальной суммы по категории товара
 */
public class MaxCategory {
    String category;
    Object sum;

    public MaxCategory(String category, Object sum) {
        this.category = category;
        this.sum = sum;
    }

    public MaxCategory() {
    }

    /**
     * метод для поиска максимальной суммы по товару в категории
     */
    public MaxCategory maxCategorySearch(Map<String, Integer> mapSum) {
        String title = Collections.max(mapSum.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        Integer sum = Collections.max(mapSum.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getValue();

        return new MaxCategory(title, sum);
    }

    @Override
    public String toString() {
        return category + " " + sum;
    }
}
