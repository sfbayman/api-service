package com.example.api.service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Service for handling database operations
 */
public class DatabaseService {
    private static final Logger logger = Logger.getLogger(DatabaseService.class.getName());
    
    private final DataSource dataSource;
    
    public DatabaseService(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    /**
     * Executes a SQL query
     * 
     * @param query The SQL query to execute
     */
    public void executeQuery(String query) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            // Execute query
            statement.execute();
        } catch (SQLException e) {
            logger.severe("SQL Exception occurred: " + e.getMessage());
        } finally {
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) { logger.severe("Failed to close connection: " + e.getMessage()); }
            }
        }
    }
    
    /**
     * Processes a database transaction
     */
    public void processTransaction() {
        // Database transaction logic
    }
}


/* Changes for TEST-1001:
@@ -6,7 +6,7 @@
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Service for handling database operations
 */
public class DatabaseService {
    private static final Logger logger = Logger.getLogger(DatabaseService.class.getName());
    
    private final DataSource dataSource;
    
    public DatabaseService(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    /**
     * Executes a SQL query
     * 
     * @param query The SQL query to execute
     */
    public void executeQuery(String query) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            // Execute query
            statement.execute();
        } catch (SQLException e) {
            logger.severe("SQL Exception occurred: " + e.getMessage());
        } finally {
            // Fixed memory leak by properly closing resources
            if (statement != null) {
                try { statement.close(); } catch (SQLException e) { logger.severe("Failed to close statement: " + e.getMessage()); }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) { logger.severe("Failed to close connection: " + e.getMessage()); }
            }
        }
    }
*/