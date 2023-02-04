package UtilClasses;

import ServerUtils.ClientRequestProcess;

import java.util.*;

/**
 * класс формирования максимальной суммы по категории товара в рамках года
 */
public class maxYearCategory {
    String category;
    Object sum;

    public maxYearCategory(String category, Object sum) {
        this.category = category;
        this.sum = sum;
    }

    /**
     * метод для поиска максимальной суммы по товару в категории в рамках года
     */
    public static maxYearCategory maxYearCategorySearch(RequestCollection requestCollection,
                                                        Map<String, String> categoriesMap) {

        RequestClass maxYearSearch =
                requestCollection.requestCollection
                        .stream()
                        .max(Comparator.comparing(RequestClass::getRequestYear))
                        .orElseThrow(NoSuchElementException::new);

        String maxYear = maxYearSearch.getRequestYear();

        RequestClass maxSumSearch =
                requestCollection.requestCollection
                        .stream()
                        .filter(y -> y.getRequestYear().equals(maxYear))
                        .max(Comparator.comparing(RequestClass::getRequestSum))
                        .orElseThrow(NoSuchElementException::new);
        String title = new ClientRequestProcess().titleSearchMaxCategories(maxSumSearch, categoriesMap);

        return new maxYearCategory(title, maxSumSearch.getRequestSum());
    }

    @Override
    public String toString() {
        return category + " " + sum;
    }
}
