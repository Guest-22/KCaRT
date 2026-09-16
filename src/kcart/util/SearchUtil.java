package kcart.util;

import java.util.ArrayList;
import java.util.List;
import kcart.model.Car;
import kcart.model.Customer;

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

    // Accepts a list of customer and the keyword; returns the matched first name/last name/contact/email.
    public static List<Customer> searchCustomersByKeyword(List<Customer> customers, String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return customers; // no filter
        }

        String lowerKeyword = keyword.toLowerCase();
        List<Customer> filtered = new ArrayList<>();

        for (Customer c : customers) {
            if ((c.getFirstName() != null && c.getFirstName().toLowerCase().contains(lowerKeyword))
                    || (c.getLastName() != null && c.getLastName().toLowerCase().contains(lowerKeyword))
                    || (c.getContactNo() != null && c.getContactNo().toLowerCase().contains(lowerKeyword))
                    || (c.getEmail() != null && c.getEmail().toLowerCase().contains(lowerKeyword))) {
                filtered.add(c);
            }
        }
        return filtered;
    }

}