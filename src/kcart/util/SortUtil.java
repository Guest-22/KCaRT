package kcart.util;

import java.util.List;
import kcart.model.Car;

public class SortUtil {
    // Sorting Options for Car Module.
    // Sort by Daily Rate using Selection Sort.
    public static void sortCarByDailyRate(List<Car> cars) {
        for (int i = 0; i < cars.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < cars.size(); j++) {
                if (cars.get(j).getDailyRate() < cars.get(minIndex).getDailyRate()) {
                    minIndex = j;
                }
            }
            Car temp = cars.get(minIndex);
            cars.set(minIndex, cars.get(i));
            cars.set(i, temp);
        }
    }

    // Sort by Year using Selection Sort.
    public static void sortCarByYear(List<Car> cars) {
        for (int i = 0; i < cars.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < cars.size(); j++) {
                if (cars.get(j).getYear() < cars.get(minIndex).getYear()) {
                    minIndex = j;
                }
            }
            Car temp = cars.get(minIndex);
            cars.set(minIndex, cars.get(i));
            cars.set(i, temp);
        }
    }

    // Sort by Seat Capacity using Selection Sort.
    public static void sortCarBySeat(List<Car> cars) {
        for (int i = 0; i < cars.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < cars.size(); j++) {
                if (cars.get(j).getSeatCap() < cars.get(minIndex).getSeatCap()) {
                    minIndex = j;
                }
            }
            Car temp = cars.get(minIndex);
            cars.set(minIndex, cars.get(i));
            cars.set(i, temp);
        }
    }
    
}