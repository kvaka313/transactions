package ua.unicyb.database.dao.impl;

import ua.unicyb.database.dao.Customer;
import ua.unicyb.database.dao.CustomersDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomersDaoImpl implements CustomersDao {
    private ThreadLocal<Connection> threadLocal;

    public CustomersDaoImpl(ThreadLocal<Connection> threadLocal) {
        this.threadLocal = threadLocal;
    }
    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        Statement st =threadLocal.get().createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM customers");
        List<Customer> customers = new ArrayList<>();
        while(rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt(1));
            customer.setFirstName(rs.getString(2));
            customer.setLastName(rs.getString(3));
            customers.add(customer);
        }
        st.close();
        return customers;
    }

    @Override
    public void insertCustomer(Customer customer) throws SQLException {

        PreparedStatement ps = threadLocal.get().prepareStatement("INSERT INTO customers (first_name, last_name) VALUES (?, ?)");
        ps.setString(1, customer.getFirstName());
        ps.setString(2, customer.getLastName());
        ps.execute();


    }
}
