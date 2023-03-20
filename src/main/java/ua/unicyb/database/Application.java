package ua.unicyb.database;

import org.postgresql.ds.PGPoolingDataSource;
import ua.unicyb.database.dao.Customer;
import ua.unicyb.database.dao.CustomersDao;
import ua.unicyb.database.dao.factory.CustomerDaoFactory;
import ua.unicyb.database.dao.impl.CustomersDaoImpl;
import ua.unicyb.database.transaction.TransactionUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;


public class Application {
    public static void main(String[] args) throws SQLException {

        CustomerDaoFactory factory = CustomerDaoFactory.getInstance();
        CustomersDao customersDao =  factory.getCustomersDao();
        TransactionUtils.beginTransaction();
        List<Customer> customerList = customersDao.getAllCustomers();

        TransactionUtils.endTransaction();

    }




}
