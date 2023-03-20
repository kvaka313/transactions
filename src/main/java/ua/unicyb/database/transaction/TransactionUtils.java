package ua.unicyb.database.transaction;

import org.postgresql.ds.PGPoolingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionUtils {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private static DataSource source = null;

    static {
        try {
            Class.forName("org.postgresql.Driver");

            PGPoolingDataSource source = new PGPoolingDataSource();
            source.setDataSourceName("A Data Source");
            source.setServerNames(new String[]{
                    "localhost"
            });
            source.setDatabaseName("firsttestdb");
            source.setUser("postgres");
            source.setPassword("Tsunami9");
            source.setMaxConnections(10);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private TransactionUtils() {
    }

    public static void beginTransaction() throws SQLException {
        Connection con = source.getConnection();
        con.setAutoCommit(false);
        threadLocal.set(con);
    }

    public static void endTransaction() throws SQLException {
        Connection con = null;
        try {
            con = threadLocal.get();
            con.commit();
        } catch (SQLException e) {
            con.rollback();
        }

        threadLocal.set(null);
        con.close();
    }

    public static ThreadLocal<Connection> getThreadLocal() {
        return threadLocal;
    }

}
