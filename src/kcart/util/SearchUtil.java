package kcart.util;

import java.util.ArrayList;
import java.util.List;
import kcart.model.Billing;
import kcart.model.Car;
import kcart.model.Customer;
import kcart.model.Rental;
import kcart.model.Return;
import kcart.model.User;

public class SearchUtil {

    // Linear Search: accepts a list of cars and the keyword; returns matched cars.
    public static List<Car> searchCarsByKeyword(List<Car> carList, String keyword) {
        keyword = keyword.toLowerCase();
        List<Car> results = new ArrayList<>();

        for (Car c : carList) {
            String brand = c.getBrand().toLowerCase();
            String model = c.getModel().toLowerCase();
            String carType = c.getCarType().toLowerCase();
            String transmissionType = c.getTransmissionType().toLowerCase();
            String fuelType = c.getFuelType().toLowerCase();
            String carStatus = c.getCarStatus();

            if (brand.contains(keyword) || model.contains(keyword) || carType.contains(keyword)
                    || transmissionType.contains(keyword) || fuelType.contains(keyword) || carStatus.contains(keyword)) {
                results.add(c);
            }
        }
        return results;
    }

    // Accepts a list of customer and the keyword; returns the matched first name/last name/contact/email.
    public static List<Customer> searchCustomersByKeyword(List<Customer> customers, String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return customers;
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

    // Receives list of Rentals and a keyword; returns matched details below
    public static List<Rental> searchRentalsByKeyword(List<Rental> rentals, String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return rentals;
        }

        keyword = keyword.toLowerCase();
        List<Rental> filtered = new ArrayList<>();

        for (Rental r : rentals) {
            if ((r.getCustomerName() != null && r.getCustomerName().toLowerCase().contains(keyword))
                    || (r.getCarInfo() != null && r.getCarInfo().toLowerCase().contains(keyword))
                    || (r.getProcessedByName() != null && r.getProcessedByName().toLowerCase().contains(keyword))
                    || (r.getRentalStatus() != null && r.getRentalStatus().toLowerCase().contains(keyword))
                    || (r.getStartDate() != null && r.getStartDate().toString().contains(keyword))
                    || (r.getExpectedReturnDate() != null && r.getExpectedReturnDate().toString().contains(keyword))) {
                filtered.add(r);
            }
        }
        return filtered;
    }

    // Receives list of Returns and a keyword; returns matched details below.
    public static List<Return> searchReturnsByKeyword(List<Return> returns, String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return returns;
        }

        keyword = keyword.toLowerCase();
        List<Return> filtered = new ArrayList<>();

        for (Return r : returns) {
            if (String.valueOf(r.getReturnId()).contains(keyword)
                    || String.valueOf(r.getRentalId()).contains(keyword)
                    || (r.getProcessedByName() != null
                    && r.getProcessedByName().toLowerCase().contains(keyword))
                    || (r.getCondition() != null
                    && r.getCondition().toLowerCase().contains(keyword))) {
                filtered.add(r);
            }
        }

        return filtered;
    }

    // Receives list of Users and a keyword; returns matched details below.
    public static List<User> searchUsersByKeyword(List<User> users, String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return users;
        }

        keyword = keyword.toLowerCase();
        List<User> filtered = new ArrayList<>();

        for (User u : users) {
            if (String.valueOf(u.getUserId()).contains(keyword)
                    || (u.getLastName() != null
                    && u.getLastName().toLowerCase().contains(keyword))
                    || (u.getFirstName() != null
                    && u.getFirstName().toLowerCase().contains(keyword))
                    || (u.getContactNo() != null
                    && u.getContactNo().toLowerCase().contains(keyword))
                    || (u.getRole() != null
                    && u.getRole().toLowerCase().contains(keyword))
                    || (u.getUsername() != null
                    && u.getUsername().toLowerCase().contains(keyword))
                    || (u.getUserStatus() != null
                    && u.getUserStatus().toLowerCase().contains(keyword))) {
                filtered.add(u);
            }
        }

        return filtered;
    }

    // Filter by keyword.
    public static List<Billing> searchBillingsByKeyword(
            List<Billing> billings, String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return billings;
        }

        keyword = keyword.toLowerCase();
        List<Billing> filtered = new ArrayList<>();

        for (Billing b : billings) {
            if (String.valueOf(b.getInvoiceId()).contains(keyword)
                    || String.valueOf(b.getRentalId()).contains(keyword)
                    || (b.getDescription() != null
                    && b.getDescription().toLowerCase().contains(keyword))
                    || (b.getInvoiceStatus() != null
                    && b.getInvoiceStatus().toLowerCase().contains(keyword))) {

                filtered.add(b);
            }
        }

        return filtered;
    }

}
