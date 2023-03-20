package ua.unicyb.database.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;



public interface CustomersDao {
    List<Customer> getAllCustomers() throws SQLException;
    Optional<Customer> insertCustomer(Customer customer) throws SQLException;
}
