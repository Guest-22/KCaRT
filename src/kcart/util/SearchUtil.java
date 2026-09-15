package kcart.util;

import java.util.ArrayList;
import java.util.List;
import kcart.model.Car;

public class SearchUtil {
    // Linear Search: accepts a list of cars and the keyword; returns matched brand/model.
    public static List<Car> searchCarsByKeyword(List<Car> carList, String keyword) {
        keyword = keyword.toLowerCase();
        List<Car> results = new ArrayList<>();

        for (Car c : carList) {
            String brand = c.getBrand().toLowerCase();
            String model = c.getModel().toLowerCase();

            if (brand.contains(keyword) || model.contains(keyword)) {
                results.add(c);
            }
        }
        return results;
    }

}