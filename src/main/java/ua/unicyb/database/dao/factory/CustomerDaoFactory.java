package ua.unicyb.database.dao.factory;


import ua.unicyb.database.dao.CustomersDao;
import ua.unicyb.database.dao.impl.CustomersDaoImpl;
import ua.unicyb.database.transaction.TransactionUtils;


public class CustomerDaoFactory {


    private static CustomerDaoFactory factory = new CustomerDaoFactory();


    private CustomerDaoFactory() {

    }

    public static CustomerDaoFactory getInstance() {
        return factory;
    }

    public CustomersDao getCustomersDao() {
        return new CustomersDaoImpl(TransactionUtils.getThreadLocal());
    }
}
