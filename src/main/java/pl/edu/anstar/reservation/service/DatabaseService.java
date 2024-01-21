package pl.edu.anstar.reservation.service;

import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import pl.edu.anstar.reservation.model.Reservation;

@Configuration
@ConfigurationProperties
public class DatabaseService {

    static final String JDBC_DRIVER = "org.postgresql.Driver";

    private static Logger LOGGER = LoggerFactory.getLogger(DatabaseService.class);

    public DatabaseService() {
    }

    private static void close(Connection connection, Statement stmt, ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
                LOGGER.info("Object {} closed.", rs.getClass().getName());
            }
        } catch (Exception e) {
            LOGGER.error("An exception occurred while closing a result set.", e);
        }
        try {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
                LOGGER.info("Object {} closed.", stmt.getClass().getName());
            }
        } catch (NullPointerException e) {
            LOGGER.error("Null pointer exception occurred while closing Statement object.", e);
        } catch (Exception e) {
            LOGGER.error("An exception occurred while closing a {}.", stmt.getClass().getName() + e);
        }
        try {
            if (connection != null && !connection.isClosed()) {
                if (!connection.getAutoCommit()) {
                    try {
                        connection.setAutoCommit(true);
                        LOGGER.info("Connection AutoCommit mode set to {}.", connection.getAutoCommit());
                    } catch (SQLException e) {
                        LOGGER.error("An exception occurred while setting connection AutoCommit mode.", e);
                    }
                }
                connection.close();
                LOGGER.info("Object {} closed.", connection.getClass().getName());
            }
        } catch (Exception e) {
            LOGGER.error("An exception occurred while closing a connection.", e);
        }
    }

    public static int addReservation(Reservation reservation, String url, String user, String password) {
        int reservationID = 0;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(url, user, password);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            String sql = "INSERT INTO reservation.reservation VALUES (DEFAULT,?,?,?,?,?,DEFAULT) RETURNING reservation_id";

            pstmt = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pstmt.clearParameters();

            pstmt.setString(1, reservation.getUser_id().getFirst_name());
            pstmt.setString(2, reservation.getUser_id().getLast_name());
            pstmt.setString(3, reservation.getUser_id().getCompany_name());
            pstmt.setString(4, reservation.getRoom().getRoomName());
            pstmt.setInt(5, reservation.getRoom().getCapacity());

            rs = pstmt.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                reservationID = rs.getInt("reservation_id");
                LOGGER.info("Row inserted. Reservation ID: {}.", reservationID);
            } else {
                LOGGER.info("No rows were inserted.");
            }

        } catch (ClassNotFoundException e) {
            LOGGER.error("An exception occurred while loading JDBC class.", e);
        } catch (Exception e) {
            LOGGER.error("A generic exception occurred.", e);
        } finally {
            close(connection, pstmt, rs);
        }
        return reservationID;
    }
}
