package kcart.util;

import java.util.List;
import kcart.model.Car;
import kcart.model.Customer;

public class SortUtil {
    // Sorting Options for Car Module.
    // Sort by Date Added using Selection Sort.
    public static void sortCarByDate(List<Car> cars) {
        for (int i = 0; i < cars.size() - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < cars.size(); j++) {
                // Compare by createdAt timestamp → newest first
                if (cars.get(j).getCreatedAt().after(cars.get(maxIndex).getCreatedAt())) {
                    maxIndex = j;
                }
            }
            Car temp = cars.get(maxIndex);
            cars.set(maxIndex, cars.get(i));
            cars.set(i, temp);
        }
    }

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
    
    // Sorting options for Customer Module.
    // Sort by Date Added using Selection Sort (newest first).
    public static void sortCustomerByDate(List<Customer> customers) {
        for (int i = 0; i < customers.size() - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < customers.size(); j++) {
                // Compare by createdAt timestamp → newest first
                if (customers.get(j).getCreatedAt().after(customers.get(maxIndex).getCreatedAt())) {
                    maxIndex = j;
                }
            }
            Customer temp = customers.get(maxIndex);
            customers.set(maxIndex, customers.get(i));
            customers.set(i, temp);
        }
    }

    // Sort by Last Name using Selection Sort.
    public static void sortCustomerByLastName(List<Customer> customers) {
        for (int i = 0; i < customers.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < customers.size(); j++) {
                if (customers.get(j).getLastName().compareToIgnoreCase(customers.get(minIndex).getLastName()) < 0) {
                    minIndex = j;
                }
            }
            Customer temp = customers.get(minIndex);
            customers.set(minIndex, customers.get(i));
            customers.set(i, temp);
        }
    }

    // Sort by First Name using Selection Sort.
    public static void sortCustomerByFirstName(List<Customer> customers) {
        for (int i = 0; i < customers.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < customers.size(); j++) {
                if (customers.get(j).getFirstName().compareToIgnoreCase(customers.get(minIndex).getFirstName()) < 0) {
                    minIndex = j;
                }
            }
            Customer temp = customers.get(minIndex);
            customers.set(minIndex, customers.get(i));
            customers.set(i, temp);
        }
    }
}