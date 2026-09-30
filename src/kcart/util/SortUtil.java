package kcart.util;

import java.util.List;
import kcart.model.Billing;
import kcart.model.Car;
import kcart.model.Customer;
import kcart.model.Rental;
import kcart.model.Return;
import kcart.model.User;

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

    // Sorting options for Rental Module.
    // Sort by Date Created (latest first)
    public static void sortRentalByCreatedAt(List<Rental> rentals) {
        for (int i = 0; i < rentals.size() - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < rentals.size(); j++) {
                if (rentals.get(j).getCreatedAt().after(rentals.get(maxIndex).getCreatedAt())) {
                    maxIndex = j;
                }
            }
            Rental temp = rentals.get(maxIndex);
            rentals.set(maxIndex, rentals.get(i));
            rentals.set(i, temp);
        }
    }

    // Sort by Start Date (earliest first).
    public static void sortRentalByStartDate(List<Rental> rentals) {
        for (int i = 0; i < rentals.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < rentals.size(); j++) {
                if (rentals.get(j).getStartDate().before(rentals.get(minIndex).getStartDate())) {
                    minIndex = j;
                }
            }
            Rental temp = rentals.get(minIndex);
            rentals.set(minIndex, rentals.get(i));
            rentals.set(i, temp);
        }
    }

    // Sort by Return Date (earliest first).
    public static void sortRentalByReturnDate(List<Rental> rentals) {
        for (int i = 0; i < rentals.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < rentals.size(); j++) {
                if (rentals.get(j).getExpectedReturnDate().before(rentals.get(minIndex).getExpectedReturnDate())) {
                    minIndex = j;
                }
            }
            Rental temp = rentals.get(minIndex);
            rentals.set(minIndex, rentals.get(i));
            rentals.set(i, temp);
        }
    }

    // Sort by Status (alphabetical order).
    public static void sortRentalByStatus(List<Rental> rentals) {
        for (int i = 0; i < rentals.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < rentals.size(); j++) {
                if (rentals.get(j).getRentalStatus()
                        .compareToIgnoreCase(rentals.get(minIndex).getRentalStatus()) < 0) {
                    minIndex = j;
                }
            }
            Rental temp = rentals.get(minIndex);
            rentals.set(minIndex, rentals.get(i));
            rentals.set(i, temp);
        }
    }

    // Sort returns by createdAt timestamp, newest first
    public static void sortReturnByDate(List<Return> returns) {
        for (int i = 0; i < returns.size() - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < returns.size(); j++) {
                // Compare by createdAt timestamp, newest first.
                if (returns.get(j).getCreatedAt().after(returns.get(maxIndex).getCreatedAt())) {
                    maxIndex = j;
                }
            }

            Return temp = returns.get(maxIndex);
            returns.set(maxIndex, returns.get(i));
            returns.set(i, temp);
        }
    }

    // Sort returns by condition, alphabetical order.
    public static void sortReturnByCondition(List<Return> returns) {
        for (int i = 0; i < returns.size() - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < returns.size(); j++) {
                // Compare by condition, alphabetical order.
                if (returns.get(j).getCondition().compareToIgnoreCase(
                        returns.get(maxIndex).getCondition()) > 0) {
                    maxIndex = j;
                }
            }

            Return temp = returns.get(maxIndex);
            returns.set(maxIndex, returns.get(i));
            returns.set(i, temp);
        }
    }

    // Sort users by createdAt timestamp, newest first.
    public static void sortUserByDate(List<User> users) {
        for (int i = 0; i < users.size() - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < users.size(); j++) {
                // Compare by createdAt timestamp, newest first,
                if (users.get(j).getCreatedAt().after(users.get(maxIndex).getCreatedAt())) {
                    maxIndex = j;
                }
            }

            User temp = users.get(maxIndex);
            users.set(maxIndex, users.get(i));
            users.set(i, temp);
        }
    }

    // Sort users by role, alphabetical order
    public static void sortUserByRole(List<User> users) {
        for (int i = 0; i < users.size() - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < users.size(); j++) {
                // Compare by role, alphabetical order
                if (users.get(j).getRole().compareToIgnoreCase(
                        users.get(maxIndex).getRole()) > 0) {
                    maxIndex = j;
                }
            }

            User temp = users.get(maxIndex);
            users.set(maxIndex, users.get(i));
            users.set(i, temp);
        }
    }

    // Sort users by status, alphabetical order
    public static void sortUserByStatus(List<User> users) {
        for (int i = 0; i < users.size() - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < users.size(); j++) {
                // Compare by user status, alphabetical order
                if (users.get(j).getUserStatus().compareToIgnoreCase(
                        users.get(maxIndex).getUserStatus()) > 0) {
                    maxIndex = j;
                }
            }

            User temp = users.get(maxIndex);
            users.set(maxIndex, users.get(i));
            users.set(i, temp);
        }
    }

    // Sort billings by payment date, newest first.
    public static void sortBillingByDate(List<Billing> billings) {
        for (int i = 0; i < billings.size() - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < billings.size(); j++) {
                // Compare by payment date, newest first.
                if (billings.get(j).getPaymentDate() != null
                        && (billings.get(maxIndex).getPaymentDate() == null
                        || billings.get(j).getPaymentDate().after(
                                billings.get(maxIndex).getPaymentDate()))) {
                    maxIndex = j;
                }
            }

            Billing temp = billings.get(maxIndex);
            billings.set(maxIndex, billings.get(i));
            billings.set(i, temp);
        }
    }

    // Sort billings by invoice amount, ascending order.
    public static void sortBillingByInvoiceAmount(List<Billing> billings) {
        for (int i = 0; i < billings.size() - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < billings.size(); j++) {
                // Compare by invoice amount, ascending order.
                if (billings.get(j).getInvoiceAmount()
                        > billings.get(maxIndex).getInvoiceAmount()) {
                    maxIndex = j;
                }
            }

            Billing temp = billings.get(maxIndex);
            billings.set(maxIndex, billings.get(i));
            billings.set(i, temp);
        }
    }

    // Sort billings by invoice status, alphabetical order.
    public static void sortBillingByStatus(List<Billing> billings) {
        for (int i = 0; i < billings.size() - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < billings.size(); j++) {
                // Compare by invoice status, alphabetical order.
                if (billings.get(j).getInvoiceStatus().compareToIgnoreCase(
                        billings.get(maxIndex).getInvoiceStatus()) > 0) {
                    maxIndex = j;
                }
            }

            Billing temp = billings.get(maxIndex);
            billings.set(maxIndex, billings.get(i));
            billings.set(i, temp);
        }

    }
}
