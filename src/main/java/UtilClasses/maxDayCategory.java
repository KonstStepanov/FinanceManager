package UtilClasses;

import ServerUtils.ClientRequestProcess;

import java.util.*;

/**
 * класс формирования максимальной суммы по категории товара в рамках дня
 */
public class maxDayCategory {
    String category;
    Object sum;

    public maxDayCategory(String category, Object sum) {
        this.category = category;
        this.sum = sum;
    }

    /**
     * метод для поиска максимальной суммы по товару в категории в рамках дня
     */
    public static maxDayCategory maxDayCategorySearch(RequestCollection requestCollection,
                                                      Map<String, String> categoriesMap) {

        RequestClass maxDaySearch =
                requestCollection.requestCollection
                        .stream()
                        .max(Comparator.comparing(RequestClass::getRequestDay))
                        .orElseThrow(NoSuchElementException::new);

        String maxDay = maxDaySearch.getRequestDay();

        RequestClass maxSumSearch =
                requestCollection.requestCollection
                        .stream()
                        .filter(y -> y.getRequestDay().equals(maxDay))
                        .max(Comparator.comparing(RequestClass::getRequestSum))
                        .orElseThrow(NoSuchElementException::new);
        String title = new ClientRequestProcess().titleSearchMaxCategories(maxSumSearch, categoriesMap);

        return new maxDayCategory(title, maxSumSearch.getRequestSum());
    }

    @Override
    public String toString() {
        return category + " " + sum;
    }
}
