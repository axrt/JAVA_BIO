package test;

import db.mysql.MySQL_Connector;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * A test to see if the MySQL_Connector is working properly
 */
public class MySQL_ConnectorTest {

    @Test
    public void testMySQL_Connector() {
        System.out.println("Starting test");
        MySQL_Connector mySQL_connector = MySQL_Connector.newDefaultInstance("jdbc:mysql://localhost/", "test", "test");
        try {
            assertTrue(mySQL_connector.connectToDatabase());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
