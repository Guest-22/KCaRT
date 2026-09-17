package kcart.dao;

import java.util.List;
import kcart.model.Customer;

public interface CustomerDAO {
    boolean addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(int customerId);
    boolean editCustomer(Customer customer);
}